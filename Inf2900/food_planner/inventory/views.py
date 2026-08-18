from django.shortcuts import render, redirect, get_object_or_404
from django.contrib.auth.decorators import login_required
from .models import Inventory
from ingredients.models import Ingredient
from inventory.forms import InventoryIngredientForm
from users.models import CustomUser


@login_required
def index(request):
    """View for inventory"""
    inventory = Inventory.objects.filter(user=request.user)
    context = {"inventory": inventory}
    return render(request, "inventory/inventory.html", context)


@login_required
def add(request):
    """Handles adding to inventory.
    Adds user to the entry before saving it"""
    if request.method == "POST":
        form = InventoryIngredientForm(data=request.POST)
        if form.is_valid():
            for entry in Inventory.objects.filter(user=request.user):
                if entry.ingredient == form.cleaned_data["ingredient"]:
                    entry.amount += form.cleaned_data["amount"]
                    entry.save()
                    return redirect("inventory")
            new_entry = form.save(commit=False)
            new_entry.user = request.user
            new_entry.save()
        return redirect("inventory")
    else:
        form = InventoryIngredientForm()
        context = {"form": form}
        return render(request, "inventory/inventory_add.html", context)


@login_required
def remove(request):
    if request.method == "POST":
        form = InventoryIngredientForm(data=request.POST)
        if form.is_valid():
            for entry in Inventory.objects.filter(user=request.user):
                if entry.ingredient == form.cleaned_data["ingredient"]:
                    if form.cleaned_data["amount"] < entry.amount:
                        entry.amount -= form.cleaned_data["amount"]
                        entry.save()
                    else:
                        entry.delete()

                    return redirect("inventory")

        return redirect("inventory")
    else:
        form = InventoryIngredientForm(user=request.user)
        context = {"form": form}

        return render(request, "inventory/inventory_remove.html", context)
