import pytest
from pytest_django.asserts import (
    assertTemplateUsed as assert_template_used,
    assertRedirects as assert_redirects,
)
from django.shortcuts import reverse
from recipes.models import Recipe
from recipes.views import search
from pprint import pprint


@pytest.mark.django_db
class TestRecipeSearchResultView:
    def test_url_exists_at_desired_location(self, client):
        response = client.get("/recipes/search/")
        assert response.status_code == 200

    def test_url_accessible_by_name(self, client):
        response = client.get(reverse("recipe_search"))
        assert response.status_code == 200

    def test_uses_correct_template(self, client):
        path = reverse("recipe_search", kwargs={"name": "maple"})
        response = client.get(path)
        assert_template_used(response, "recipes/recipe_list.html")

    def test_no_results_found(self, client, recipe_list):
        query = "duck duck"
        queryset = Recipe.objects.filter(name__icontains=query)
        path = reverse("recipe_search", kwargs={"name": query})
        response = client.get(path)
        assert list(queryset) == list(response.context["recipe_list"])

    def test_unique_recipe_found(self, client, recipe_list):
        query = recipe_list[0].name
        queryset = Recipe.objects.filter(name__icontains=query)
        path = reverse("recipe_search", kwargs={"name": query})
        response = client.get(path)
        assert list(queryset) == list(response.context["recipe_list"])

    def test_all_recipes_found(self, client, recipe_list):
        query = "mu"
        queryset = list(Recipe.objects.all()[:20])
        path = reverse("recipe_search", kwargs={"name": query})
        response = client.get(path)
        assert queryset == list(response.context["recipe_list"])

    def test_search_is_not_case_sensitive(self, client, recipe_list):
        query = "MU"
        queryset = list(Recipe.objects.all()[:20])
        path = reverse("recipe_search", kwargs={"name": query})
        response = client.get(path)
        assert queryset == list(response.context["recipe_list"])

    def test_pagination_is_twenty(self, client, recipe_list):
        query = "mu"
        path = reverse("recipe_search", kwargs={"name": query})
        response = client.get(path)
        assert response.context["is_paginated"]
        assert len(response.context["recipe_list"]) == 20

    def test_lists_remaining_recipes(self, client, recipe_list):
        query = "mu"
        path = reverse("recipe_search", kwargs={"name": query})
        response = client.get(path + "?page=2")
        assert response.context["is_paginated"]
        assert len(response.context["recipe_list"]) == 5


@pytest.mark.django_db
class TestSearchView:
    def test_uses_correct_template(self, client):
        response = client.get(reverse("recipe_search"))
        assert_template_used(response, "recipes/recipe_search.html")

    def test_query_redirects(self, rf):
        query = "maple"
        search_path = reverse("recipe_search")
        path = f"{search_path}?recipe_name={query}"
        request = rf.get(path)
        response = search(request)
        url = f"{search_path}{query}/"
        assert response.url == url

    def test_handles_query_no_hits(self, client):
        query = "maple"
        path = f"{reverse('recipe_search')}?recipe_name={query}"
        response = client.get(path, follow=True)
        assert list(response.context_data["recipe_list"]) == list()

    def test_handles_query_with_hits(self, client):
        query = "mu"
        path = f"{reverse('recipe_search')}?recipe_name={query}"
        response = client.get(path, follow=True)
        queryset = list(Recipe.objects.filter(name__icontains=query)[:20])
        assert list(response.context_data["recipe_list"]) == queryset

    def test_uses_recipe_list_template_for_query_results(self, client):
        query = "maple"
        path = f"{reverse('recipe_search')}?recipe_name={query}"
        response = client.get(path, follow=True)
        assert "recipes/recipe_list.html" in response.template_name
