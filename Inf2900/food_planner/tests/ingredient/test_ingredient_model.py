import pytest
from django.db import IntegrityError
from ingredients.models import Ingredient


@pytest.mark.django_db
class TestIngredientModel:
    def test_name_label(self):
        field_label = Ingredient._meta.get_field("name").verbose_name
        assert field_label == "name"

    def test_name_max_length(self):
        max_length = Ingredient._meta.get_field("name").max_length
        assert max_length == 200

    def test_name_allow_blank_is_false(self):
        blank = Ingredient._meta.get_field("name").blank
        assert blank == False

    def test_duplicate_name_is_invalid(self):
        name = "apple"
        Ingredient.objects.create(name=name)
        with pytest.raises(IntegrityError):
            Ingredient.objects.create(name=name)

    def test_str_ingredient_is_name(self):
        name = "apple"
        ingredient = Ingredient(name=name)
        assert str(ingredient) == name
