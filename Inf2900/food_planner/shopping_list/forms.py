from django.forms import ModelForm
from .models import ShoppingList


class ShoppingListForm(ModelForm):
    class Meta:
        model = ShoppingList
        fields = ("ingredient", "amount", "unit")
