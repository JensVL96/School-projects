from django.contrib.auth.mixins import LoginRequiredMixin
from django.urls import reverse_lazy
from django.views.generic.edit import CreateView
from django.shortcuts import redirect, render
from django.views.generic.detail import DetailView
from django.views.generic import ListView
from django.forms import formset_factory
from .forms import RecipeCreationForm, RecipeIngredientForm, RecipeSearchForm
from .models import Recipe, IngredientDetails


class RecipeCreationView(LoginRequiredMixin, CreateView):
    """ View for creating a new recipe. """

    form_class = RecipeCreationForm
    detail_formset = formset_factory(RecipeIngredientForm, extra=5, min_num=1)
    template_name = "recipes/recipe_create.html"
    success_url = reverse_lazy("recipes")
    object = None  # Required by get_context_data

    def get_context_data(self, **kwargs):
        """ Insert the formset into the context dict. """
        context = super().get_context_data(**kwargs)
        context["ingredients"] = self.detail_formset()
        return context

    def post(self, request, *args, **kwargs):
        """ Handle POST requests: instantiate form and formset with
        the passed POST variables and then check if it is valid.
        """
        form = self.form_class(request.POST, request.FILES)
        formset = self.detail_formset(request.POST)
        if form.is_valid() and formset.is_valid():
            return self.form_valid(form, formset)
        return self.form_invalid(form, formset)

    def form_valid(self, form, formset):
        """ If form and formset is valid, redirect to the supplied URL."""
        recipe = form.save(commit=False)
        recipe.author = self.request.user
        recipe.save()
        for form in formset.cleaned_data:
            if form == dict():
                continue
            ingredient = form["ingredient"]
            amount = form["amount"]
            IngredientDetails.objects.create(
                recipe=recipe, ingredient=ingredient, amount=amount
            )
        return redirect(self.success_url)

    def form_invalid(self, form, formset):
        """ If form or formset is invald, render the invalid form."""
        context = self.get_context_data(form=form)
        context["ingredients"] = formset
        return self.render_to_response(context)


class RecipeView(DetailView):
    """ A view for recipe details. """

    model = Recipe
    template_name = "recipes/recipe.html"

    def get_context_data(self, **kwargs):
        """ Insert recipe ingredient details into the context dict. """
        recipe = self.object
        ingredients = recipe.ingredientdetails_set.all()
        context = super().get_context_data(**kwargs)
        context["ingredients"] = ingredients
        return context


class RecipeListView(ListView):
    """ A list view for recipes. """

    model = Recipe
    paginate_by = 20
    ordering = ["id"]

    def get_queryset(self):
        queryset = super().get_queryset()
        name = self.kwargs.get("name")
        if name:
            queryset = queryset.filter(name__icontains=name)
        return queryset


def search(request):
    """ Searches recipes by recipe name."""
    form = RecipeSearchForm()
    context = {"form": form}
    recipe_name = request.GET.get("recipe_name")
    if request.method == "GET" and recipe_name:
        return redirect("recipe_search", name=recipe_name)
    return render(request, "recipes/recipe_search.html", context)
