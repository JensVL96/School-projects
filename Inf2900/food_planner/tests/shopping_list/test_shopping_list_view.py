import pytest
from pytest_django.asserts import (
    assertTemplateUsed as assert_template_used,
    assertRedirects as assert_redirects,
)
from django.shortcuts import reverse
from ingredients.models import Ingredient
from shopping_list.models import ShoppingList
from pprint import pprint


def context_init(ingredients, amounts, units):
    """ Creates a context dictionary for shopping list POST.
    Uses ingredient.pk to guarantee valid primary keys.
    """
    context = {}
    entries = enumerate(zip(ingredients, amounts, units))
    i = 0
    for i, (ingredient, amount, unit) in entries:
        context[f"form-{i}-ingredient"] = ingredient.pk
        context[f"form-{i}-amount"] = amount
        context[f"form-{i}-unit"] = unit
    context.update(
        {
            "form-TOTAL_FORMS": i + 1,
            "form-INITIAL_FORMS": 0,
            "form-MAX_NUM_FORMS": 1000,
        }
    )
    return context


class TestShoppingListView:
    def test_exists_at_desired_url(self, client, admin_user):
        client.force_login(admin_user)
        response = client.get("/shopping_list/")
        assert response.status_code == 200

    def test_url_is_accesible_by_name(self, client, admin_user):
        client.force_login(admin_user)
        response = client.get(reverse("shopping_list"))
        assert response.status_code == 200

    def test_correct_template_is_used(self, client, admin_user):
        client.force_login(admin_user)
        response = client.get(reverse("shopping_list"))
        assert_template_used(response, "shopping_list/shopping_list.html")

    def test_redirect_if_not_logged_in(self, client):
        path = reverse("shopping_list")
        response = client.get(path)
        url = f"{reverse('login')}?next={path}"
        assert_redirects(response, url)

    def test_page_contains_list_entries(self, client, admin_user, entries):
        client.force_login(admin_user)
        response = client.get(reverse("shopping_list"))
        content = response.content.decode()
        for entry in response.context["shopping_list"]:
            assert str(entry) in content

    def test_page_contains_entry_details(self, client, admin_user, entries):
        client.force_login(admin_user)
        response = client.get(reverse("shopping_list"))
        content = response.content.decode()
        for entry in entries:
            assert str(entry.amount) in content
            assert str(entry.unit) in content

    def test_only_owner_entries_in_page(
        self, client, admin_user, entries, django_user_model
    ):
        client.force_login(admin_user)
        ingredient_names = ["pickle", "sugar", "pepper"]
        ingredients = [Ingredient.objects.create(name=n) for n in ingredient_names]
        maple = django_user_model.objects.create_user(username="maple", password="me")
        create = ShoppingList.objects.create
        maple_entries = [create(ingredient=i, user=maple) for i in ingredients]
        response = client.get(reverse("shopping_list"))
        response_entries = response.context["shopping_list"]
        assert set(maple_entries).intersection(response_entries) == set()

    def test_correct_post_creates_entry(self, client, admin_user):
        client.force_login(admin_user)
        ingredients = [Ingredient.objects.create(name="milk")]
        amount = 2
        unit = "liter"
        context = context_init(ingredients, [amount], [unit])
        response = client.post(reverse("shopping_list"), context)
        entry = ShoppingList.objects.get(user=admin_user)
        assert entry.amount == amount
        assert entry.unit == unit

    def test_displays_error_messages(self, client, admin_user, ingredients):
        client.force_login(admin_user)
        error_msg = "This field is required."
        amounts = ["", 42, "", 6, ""]
        units = ["", "", "liter", "cup", "bag"]
        context = context_init(ingredients, amounts, units)
        response = client.post(reverse("shopping_list"), context)
        content = response.content.decode()
        formset = response.context["shopping_list"]
        assert formset.total_error_count() == content.count(error_msg)
