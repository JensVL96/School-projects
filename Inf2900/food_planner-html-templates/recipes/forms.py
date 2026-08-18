from django import forms
from django.forms import ModelForm
from .models import Recipe
from ingredients.models import Ingredient


class RecipeIngredientForm(forms.Form):
    """ Form for adding recipe ingredient with its amount. """

    ingredient = forms.ModelChoiceField(queryset=Ingredient.objects.all())
    amount = forms.IntegerField(min_value=0)


class RecipeCreationForm(ModelForm):
    class Meta:
        model = Recipe
        fields = ("name",)
