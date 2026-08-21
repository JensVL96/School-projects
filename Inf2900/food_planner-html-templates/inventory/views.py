from django.shortcuts import render, redirect, get_object_or_404
from .models import Inventory
from ingredients.models import Ingredient


def index(request):
    inventory = Inventory.objects.all()
    context = {"inventory": inventory}
    return render(request, "inventory/inventory.html", context)


def add(request):
    ingredient_list = Ingredient.objects.all()
    context = {"ingredient_list": ingredient_list}
    return render(request, "inventory/inventory_add.html", context)


def remove(request):
    fridge = Inventory.objects.all()
    context = {"fridge": fridge}
    return render(request, "inventory/inventory_remove.html", context)


def add_ingredient(request, ingredient_id):
    new = get_object_or_404(Ingredient, pk=ingredient_id)
    Inventory.objects.create(ingredient=new)
    return redirect("inventory")


def remove_ingredient(request, ingredient_id):
    item = get_object_or_404(Inventory, pk=ingredient_id)
    item.delete()
    return redirect("inventory")
