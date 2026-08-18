import pytest
from pytest_django.asserts import (
    assertTemplateUsed as assert_template_used,
    assertRedirects as assert_redirects,
)
from django.shortcuts import reverse
from recipes.models import Recipe
from ingredients.models import Ingredient


@pytest.fixture
def default_recipe(admin_user):
    instructions = "Slap ingredients together. Tada done!"
    lettuce = Ingredient.objects.create(name="lettuce")
    tomato = Ingredient.objects.create(name="tomato")
    recipe = Recipe.objects.create(
        name="burger", author=admin_user
    )  # Todo: Add usage of instructions when field is present in Recipe
    recipe.ingredients.add(lettuce, tomato)
    return recipe


@pytest.mark.django_db
class TestRecipeView:
    def test_url_exists_at_desired_location(self, client, default_recipe):
        url = f"/recipes/{default_recipe.pk}/"
        response = client.get(url)
        assert response.status_code == 200

    def test_url_accessible_by_name_and_pk(self, client, default_recipe):
        kwargs = {"pk": default_recipe.pk}
        response = client.get(reverse("recipe_detail", kwargs=kwargs))
        assert response.status_code == 200

    def test_uses_correct_template(self, client, default_recipe):
        kwargs = {"pk": default_recipe.pk}
        response = client.get(reverse("recipe_detail", kwargs=kwargs))
        assert_template_used(response, "recipes/recipe.html")

    def test_page_contains_recipe_ingredients(self, client, default_recipe):
        kwargs = {"pk": default_recipe.pk}
        response = client.get(reverse("recipe_detail", kwargs=kwargs))
        content = response.content.decode()
        assert response.context["ingredients"].exists()
        for ingredient in default_recipe.ingredientdetails_set.all():
            assert str(ingredient) in content

    def test_page_contains_recipe_name(self, client, default_recipe):
        kwargs = {"pk": default_recipe.pk}
        response = client.get(reverse("recipe_detail", kwargs=kwargs))
        assert str(default_recipe) in response.content.decode()

    def test_page_contains_recipe_instructions_context(self, client, default_recipe):
        kwargs = {"pk": default_recipe.pk}
        response = client.get(reverse("recipe_detail", kwargs=kwargs))
        assert response.context["instructions"] == default_recipe.instructions
