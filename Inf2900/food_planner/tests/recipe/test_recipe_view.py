import pytest
from pytest_django.asserts import (
    assertTemplateUsed as assert_template_used,
    assertRedirects as assert_redirects,
)
from django.shortcuts import reverse
from recipes.models import Recipe
from ingredients.models import Ingredient
from pprint import pprint


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

    def test_page_contains_recipe_author(self, client, default_recipe):
        kwargs = {"pk": default_recipe.pk}
        response = client.get(reverse("recipe_detail", kwargs=kwargs))
        assert str(default_recipe.author) in response.content.decode()

    def test_page_contains_recipe_instructions(self, client, default_recipe):
        kwargs = {"pk": default_recipe.pk}
        response = client.get(reverse("recipe_detail", kwargs=kwargs))
        assert default_recipe.instructions in response.content.decode()

    def test_page_contains_recipe_image(self, client, default_recipe):
        recipe = default_recipe
        path = reverse("recipe_detail", kwargs={"pk": recipe.pk})
        response = client.get(path)
        image = f"""<img src="{recipe.image.url}" alt="{recipe}">"""
        assert recipe.image.url in response.content.decode()
        assert image in response.content.decode()

    def test_page_does_not_contain_image_path(self, client, default_recipe):
        path = reverse("recipe_detail", kwargs={"pk": default_recipe.pk})
        response = client.get(path)
        assert default_recipe.image.path not in response.content.decode()

    def test_page_does_not_show_other_recipe(
        self, client, default_recipe, recipe_create
    ):
        recipe_name = "sandwich"
        ingredients = ["bacon", "avocado"]
        amounts = [7, 1]
        recipe = recipe_create(recipe_name, ingredients, amounts)
        recipe.instructions = "Slap together"
        recipe.save()
        path = reverse("recipe_detail", kwargs={"pk": recipe.pk})
        response = client.get(path)
        content = response.content.decode()
        assert str(default_recipe) not in content
        assert default_recipe.instructions not in content
        for details in default_recipe.ingredientdetails_set.all():
            assert str(details) not in content
