from django.contrib.auth.decorators import login_required
from django.shortcuts import render, redirect, get_object_or_404
from django.forms import modelformset_factory
from .models import ShoppingList
from .forms import ShoppingListForm
from ingredients.models import Ingredient


@login_required
def index(request):
    """ View for shopping list page. """
    initial_formset = modelformset_factory(
        ShoppingList, form=ShoppingListForm, extra=5, can_delete=True
    )
    template_name = "shopping_list/shopping_list.html"
    qs = ShoppingList.objects.filter(user=request.user)
    qs.prefetch_related("ingredient")
    formset = initial_formset(queryset=qs)
    if request.method == "POST":
        formset = initial_formset(request.POST)
        if formset.is_valid():
            formset_is_valid(formset, request.user)
            formset = initial_formset(queryset=qs.all())
    context = {"shopping_list": formset}
    return render(request, template_name, context)


def formset_is_valid(formset, user):
    """ Handles creation/deletion of ShoppingList entries.
    Deletes objects marked for deletion in formset.
    Adds user to entries before saving it.
    """
    instances = formset.save(commit=False)
    [obj.delete() for obj in formset.deleted_objects]
    for obj in instances:
        obj.user = user
        obj.save()
