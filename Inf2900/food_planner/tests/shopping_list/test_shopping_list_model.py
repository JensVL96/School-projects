import pytest
from django.db import models
from shopping_list.models import ShoppingList
from users.models import CustomUser
from ingredients.models import Ingredient


@pytest.mark.django_db
class TestShoppingListModel:
    def test_user_label(self):
        label = ShoppingList._meta.get_field("user").verbose_name
        assert label == "user"

    def test_user_is_foreign_key(self):
        field = ShoppingList._meta.get_field("user")
        assert isinstance(field, models.ForeignKey)

    def test_user_is_related_to_correct_model(self):
        related_model = ShoppingList.user.field.related_model()
        assert isinstance(related_model, CustomUser)

    def test_ingredient_label(self):
        label = ShoppingList._meta.get_field("ingredient").verbose_name
        assert label == "ingredient"

    def test_ingredient_is_foreign_key(self):
        field = ShoppingList._meta.get_field("ingredient")
        assert isinstance(field, models.ForeignKey)

    def test_ingredient_is_related_to_correct_model(self):
        related_model = ShoppingList.ingredient.field.related_model()
        assert isinstance(related_model, Ingredient)

    def test_amount_label(self):
        label = ShoppingList._meta.get_field("amount").verbose_name
        assert label == "amount"

    def test_amount_is_integer_field(self):
        field = ShoppingList._meta.get_field("amount")
        assert isinstance(field, models.IntegerField)

    def test_unit_label(self):
        label = ShoppingList._meta.get_field("unit").verbose_name
        assert label == "unit"

    def test_duplicate_ingredient_is_valid(self):
        egg = Ingredient.objects.create(name="egg")
        maple = CustomUser.objects.create_user(username="maple")
        trunks = CustomUser.objects.create_user(username="trunks")
        a = ShoppingList.objects.create(ingredient=egg, user=maple)
        b = ShoppingList.objects.create(ingredient=egg, user=trunks)
        assert len(ShoppingList.objects.all()) == 2
