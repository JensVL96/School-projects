from django import forms
from django.forms import ModelForm
from inventory.models import Inventory
from ingredients.models import Ingredient


class InventoryIngredientForm(ModelForm):
    """ Form for adding inventory ingredient with its amount. """

    class Meta:
        model = Inventory
        fields = ["ingredient", "amount"]

    def __init__(self, user=None, **kwargs):
        super(InventoryIngredientForm, self).__init__(**kwargs)
        if user:
            qs = Inventory.objects.filter(user=user).values("ingredient_id")
            self.fields["ingredient"].queryset = Ingredient.objects.filter(id__in=qs)
