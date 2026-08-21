import pytest
from shopping_list.forms import ShoppingListForm
from shopping_list.models import ShoppingList
from django.forms import modelformset_factory


@pytest.mark.django_db
class TestShoppingListForm:
    def test_ingredient_field_label(self):
        form = ShoppingListForm()
        assert form.fields["ingredient"].label == "Ingredient"

    def test_amount_field_label(self):
        form = ShoppingListForm()
        assert form.fields["amount"].label == "Amount"

    def test_unit_field_label(self):
        form = ShoppingListForm()
        assert form.fields["unit"].label == "Unit"

    def test_valid_input(self, ingredients):
        egg = ingredients[0]
        data = {
            "ingredient": egg.pk,
            "amount": 42,
            "unit": "liters",
        }
        form = ShoppingListForm(data=data)
        assert form.is_valid()

    def test_save_form(self, admin_user, ingredients):
        egg = ingredients[0]
        data = {
            "ingredient": egg.pk,
            "amount": 42,
            "unit": "liters",
        }
        form = ShoppingListForm(data=data)
        assert form.is_valid()
        entry = form.save(commit=False)
        entry.user = admin_user
        entry.save()
        tmp = ShoppingList.objects.get(user=admin_user)
        assert entry == tmp

    def test_ingredient_missing_is_invalid(self):
        data = {"amount": 42, "unit": "liters"}
        form = ShoppingListForm(data=data)
        assert form.is_valid() == False

    def test_amount_missing_is_invalid(self, ingredients):
        egg = ingredients[0]
        data = {"ingredient": egg.pk, "unit": "liters"}
        form = ShoppingListForm(data=data)
        assert form.is_valid() == False

    def test_unit_missing_is_invalid(self, ingredients):
        egg = ingredients[0]
        data = {
            "ingredient": egg.pk,
            "amount": 42,
        }
        form = ShoppingListForm(data=data)
        assert form.is_valid() == False
