import pytest
from pytest_django.asserts import (
    assertTemplateUsed as assert_template_used,
    assertRedirects as assert_redirects,
)
from django.shortcuts import reverse
from recipes.models import Recipe


@pytest.fixture
def recipe_list(admin_user):
    create = Recipe.objects.create
    count = 25
    return [create(name=f"mu:{i}", author=admin_user) for i in range(count)]


@pytest.mark.django_db
class TestRecipeListView:
    def test_url_exsists_at_desired_location(self, client):
        response = client.get("/recipes/")
        assert response.status_code == 200

    def test_uses_correct_template(self, client):
        response = client.get(reverse("recipes"))
        assert_template_used(response, "recipes/recipe_list.html")

    def test_link_to_recipe_creation_is_present(self, client):
        url = f"""<a href="{reverse('recipe_create')}">"""
        response = client.get(reverse("recipes"))
        assert url in response.content.decode()

    def test_message_when_no_recipes(self, client):
        message = "No recipes yet."
        response = client.get(reverse("recipes"))
        assert message in response.content.decode()

    def test_message_not_present_when_recipes_exist(self, client, recipe_list):
        message = "No recipes yet."
        response = client.get(reverse("recipes"))
        assert message not in response.content.decode()

    def test_pagination_is_twenty(self, client, recipe_list):
        response = client.get(reverse("recipes"))
        assert response.context["is_paginated"]
        assert len(response.context["recipe_list"]) == 20

    def test_lists_remaining_recipes(self, client, recipe_list):
        response = client.get(reverse("recipes") + "?page=2")
        assert response.context["is_paginated"]
        assert len(response.context["recipe_list"]) == 5

    def test_list_entries_displays_recipe_name(self, client, recipe_list):
        response = client.get(reverse("recipes"))
        content = response.content.decode()
        for recipe in response.context["recipe_list"]:
            assert recipe.name in content

    def test_list_entries_links_to_recipe_page(self, client, recipe_list):
        response = client.get(reverse("recipes"))
        content = response.content.decode()
        for recipe in response.context["recipe_list"]:
            url = reverse("recipe_detail", kwargs={"pk": recipe.pk})
            link = f"""<a href="{url}">{recipe}</a>"""
            assert link in content
