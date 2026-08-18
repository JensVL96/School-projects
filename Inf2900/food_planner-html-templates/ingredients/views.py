from django.shortcuts import render
from .models import Ingredient


def index(request):
    ingredient_list = Ingredient.objects.all()
    context = {"ingredient_list": ingredient_list}
    return render(request, "ingredients/ingredient_list.html", context)
