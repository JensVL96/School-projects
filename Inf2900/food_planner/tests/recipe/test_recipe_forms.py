import pytest
from pytest_django.asserts import (
    assertTemplateUsed as assert_template_used,
    assertRedirects as assert_redirects,
)
from django.shortcuts import reverse
from recipes.forms import RecipeCreationForm, RecipeIngredientForm


@pytest.mark.django_db
class TestRecipeCreationForm:
    def test_name_field_label(self):
        form = RecipeCreationForm()
        assert form.fields["name"].label == "Name"

    def test_instructions_field_label(self):
        form = RecipeCreationForm()
        assert form.fields["instructions"].label == "Instructions"

    def test_image_field_label(self):
        form = RecipeCreationForm()
        assert form.fields["image"].label == "Image"

    def test_valid_form_save(self, uploaded_image, admin_user):
        image = uploaded_image("test.png")
        data = {
            "name": "Hamburger",
            "instructions": "Slap together",
        }
        form = RecipeCreationForm(data, {"image": image})
        assert form.is_valid()
        recipe = form.save(commit=False)
        recipe.author = admin_user
        recipe.save()

    def test_instructions_field_is_required(self, uploaded_image):
        image = uploaded_image("test.png")
        data = {
            "name": "Hamburger",
            "instructions": "",
        }
        form = RecipeCreationForm(data, {"image": image})
        assert not form.is_valid()
        assert len(form.errors) == 1

    def test_name_field_is_required(self, uploaded_image):
        image = uploaded_image("test.png")
        data = {
            "name": "",
            "instructions": "Slap together.",
        }
        form = RecipeCreationForm(data, {"image": image})
        assert not form.is_valid()
        assert len(form.errors) == 1

    def test_image_field_is_required(self):
        data = {
            "name": "Hamburger",
            "instructions": "Slap together.",
        }
        form = RecipeCreationForm(data, {"image": ""})
        assert not form.is_valid()
        assert len(form.errors) == 1


@pytest.mark.django_db
class TestRecipeIngredientForm:
    def test_ingredient_field_label(self):
        form = RecipeIngredientForm()
        assert form.fields["ingredient"].label == "Ingredient"

    def test_amount_field_label(self):
        form = RecipeIngredientForm()
        assert form.fields["amount"].label == "Amount"

    def test_valid_form_save(self, default_recipe):
        data = {
            "ingredient": default_recipe.ingredients.first(),
            "amount": 42,
        }
        form = RecipeIngredientForm(data)
        assert form.is_valid()
        ingredient = form.cleaned_data["ingredient"]
        amount = form.cleaned_data["amount"]
        detail = default_recipe.ingredientdetails_set.get(ingredient=ingredient)
        detail.amount = amount
        detail.save()

    def test_ingredient_field_is_required(self, default_recipe):
        data = {
            "ingredient": "",
            "amount": 42,
        }
        form = RecipeIngredientForm(data)
        assert not form.is_valid()
        assert len(form.errors) == 1
        assert form.errors["ingredient"]

    def test_amount_field_is_required(self, default_recipe):
        data = {
            "ingredient": default_recipe.ingredients.first(),
            "amount": "",
        }
        form = RecipeIngredientForm(data)
        assert not form.is_valid()
        assert len(form.errors) == 1
        assert form.errors["amount"]

    def test_negative_amount_is_invalid(self, default_recipe):
        data = {
            "ingredient": default_recipe.ingredients.first(),
            "amount": -4,
        }
        form = RecipeIngredientForm(data)
        assert not form.is_valid()
        assert len(form.errors) == 1
        assert form.errors["amount"]
