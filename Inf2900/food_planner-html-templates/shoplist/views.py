from django.shortcuts import render, redirect, get_object_or_404
from django.views.generic.detail import DetailView
from .models import ShopList_content
from ingredients.models import Ingredient


def index(request):
    inventory = ShopList_content.objects.all()
    context = {"inventory": inventory}
    return render(request, "inventory.html", context)


def add(request):
    ingredient_list = Ingredient.objects.all()
    context = {"ingredient_list": ingredient_list}
    return render(request, "shoplist_add.html", context)


def add_ingredient(request, ingredient_id):
    new = get_object_or_404(Ingredient, pk=ingredient_id)
    shoplist_entry = ShopList_content.objects.create(ingredient=new)
    return redirect("index")

class ShopListView(DetailView):
    model = ShopList_content
    template_name = "shoplist/shoplist.html"