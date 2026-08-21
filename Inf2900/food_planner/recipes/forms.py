from django import forms
from django.forms import ModelForm
from recipes.models import Recipe
from ingredients.models import Ingredient


class RecipeIngredientForm(forms.Form):
    """ Form for adding recipe ingredient with its amount. """

    ingredient = forms.ModelChoiceField(
        queryset=Ingredient.objects.all(), label="Ingredient"
    )
    amount = forms.IntegerField(min_value=0, label="Amount")


class RecipeCreationForm(ModelForm):
    """ Form for recipe creation. """

    class Meta:
        model = Recipe
        fields = ("name", "instructions", "image")


class RecipeSearchForm(forms.Form):
    recipe_name = forms.CharField(required=False)
