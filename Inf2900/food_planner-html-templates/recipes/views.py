from django.urls import reverse_lazy
from django.views.generic.edit import CreateView
from django.shortcuts import render, redirect, reverse
from django.views.generic.detail import DetailView
from django.views.generic import ListView
from django.forms import formset_factory
from .forms import RecipeCreationForm, RecipeIngredientForm
from .models import Recipe, IngredientDetails


class RecipeCreationView(CreateView):
    """ View for creating a new recipe. """

    form_class = RecipeCreationForm
    detail_formset = formset_factory(RecipeIngredientForm, extra=5)
    template_name = "recipes/recipe_create.html"
    success_url = reverse_lazy("recipes")

    def get_context_data(self, **kwargs):
        """ Insert the formset into the context dict. """
        context = super().get_context_data(**kwargs)
        context["ingredients"] = self.detail_formset()
        return context

    def post(self, request, *args, **kwargs):
        """ Handle POST requests: instantiate form and formset with
        the passed POST variables and then check if it is valid.
        """
        self.object = None
        form = self.form_class(request.POST)
        formset = self.detail_formset(request.POST)
        if form.is_valid() and formset.is_valid():
            return self.form_valid(form, formset)
        else:
            return self.form_invalid(form, formset)

    def form_valid(self, form, formset):
        """ If form and formset is valid, redirect to the supplied URL."""
        recipe = Recipe(name=form.cleaned_data["name"])
        for form in formset.cleaned_data:
            if form == dict():
                continue
            ingredient = form["ingredient"]
            amount = form["amount"]
            mu = IngredientDetails(ingredient=ingredient, amount=amount)
        return redirect(self.success_url)

    def form_invalid(self, form, formset):
        """ If form or formset is invald, render the invalid form."""
        context = self.get_context_data(form=form)
        context["ingredients"] = formset
        return self.render_to_response(context)


def recipe_create(request):
    template_name = "recipes/recipe_create.html"
    ing_formset = formset_factory(RecipeIngredientForm, extra=2)
    formset = ing_formset()
    form = RecipeCreationForm()
    context = {"form": form, "ingredients": formset}
    if request.method == "POST":
        form = RecipeCreationForm(request.POST)
        formset = ing_formset(request.POST)
        if formset.is_valid() and form.is_valid():
            name = form.cleaned_data["name"]
            recipe = Recipe(name=name)
            recipe.save()
            for data in formset.cleaned_data:
                ingredient = data["ingredient"]
                amount = data["amount"]
                detail = IngredientDetails(
                    recipe=recipe, ingredient=ingredient, amount=amount
                )
                detail.save()
            ingredients = formset.cleaned_data
            print("formset is valid yay")
            print(ingredients)
            print(recipe)
        else:
            print("well shit formset is invalid.")
    return render(request, template_name, context)


class RecipeView(DetailView):
    model = Recipe
    template_name = "recipes/recipe.html"

    def get_context_data(self, **kwargs):
        """ """
        recipe = self.object
        ingredients = recipe.ingredientdetails_set.all()
        context = super().get_context_data(**kwargs)
        context["ingredients"] = ingredients
        return context


class RecipeListView(ListView):
    """ A list view for recpies. """

    model = Recipe
    paginate_by = 20
    ordering = ["id"]


def index(request):
    """ Renders all recipes. """
    context = {"recipes": Recipe.objects.all()}
    return render(request, "recipes/index.html", context)
