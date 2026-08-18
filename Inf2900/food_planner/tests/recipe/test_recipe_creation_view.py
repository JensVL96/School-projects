import pytest
from pytest_django.asserts import (
    assertTemplateUsed as assert_template_used,
    assertRedirects as assert_redirects,
)
from django.shortcuts import reverse
from django.forms import formset_factory
from recipes.models import Recipe
from recipes.forms import RecipeIngredientForm, RecipeCreationForm
from recipes.views import RecipeCreationView
from pprint import pprint


@pytest.fixture
def recipe_context(uploaded_image, ingredient_create):
    """ Default context for recipe creation post.
    Creates a test image, and ingredient entries.
    context; ingredients and amounts are not used in page.
    These keys are for usage in tests.
    """
    ingredient_names = ["mayo", "bacon", "cucumber", "lettuce"]
    amounts = [42, 4, 6, 1]
    context = {
        "name": "burger",
        "image": uploaded_image("burger.png"),
        "instructions": "Throw ingredients into kettle.",
        "form-INITIAL_FORMS": 0,
        "form-MAX_NUM_FORMS": 1000,
        "form-TOTAL_FORMS": len(amounts),
        "ingredients": list(),
        "amounts": amounts,
    }
    for i, (name, amount) in enumerate(zip(ingredient_names, amounts)):
        ingredient = ingredient_create(name)
        context["ingredients"].append(ingredient)
        context[f"form-{i}-ingredient"] = ingredient.pk
        context[f"form-{i}-amount"] = amount
    return context


@pytest.fixture
def auth_client(client, admin_user):
    """ Convenience fixture with admin force authenticated.
    Returns a django client instance.
    """
    client.force_login(admin_user)
    return client


@pytest.mark.django_db
class TestRecipeCreationView:
    def test_url_exists_at_desired_location(self, client, admin_user):
        client.force_login(admin_user)
        response = client.get("/recipes/create/")
        assert response.status_code == 200

    def test_url_accessible_by_name(self, client, admin_user):
        client.force_login(admin_user)
        response = client.get(reverse("recipe_create"))
        assert response.status_code == 200

    def test_uses_correct_template(self, client, admin_user):
        client.force_login(admin_user)
        response = client.get(reverse("recipe_create"))
        assert_template_used(response, "recipes/recipe_create.html")

    def test_redirect_when_logged_out(self, client):
        create_url = reverse("recipe_create")
        response = client.get(create_url)
        url = f"{reverse('login')}?next={create_url}"
        assert_redirects(response, url)

    def test_current_user_context(self, client, admin_user):
        client.force_login(admin_user)
        response = client.get(reverse("recipe_create"))
        assert response.context["user"] == admin_user

    def test_recipe_creation_form_in_page(self, client, admin_user):
        client.force_login(admin_user)
        response = client.get(reverse("recipe_create"))
        form = RecipeCreationForm()
        assert str(form.as_p()) in response.content.decode()

    def test_recipe_ingredient_forms_in_page(self, client, admin_user):
        client.force_login(admin_user)
        response = client.get(reverse("recipe_create"))
        formset = formset_factory(RecipeIngredientForm, extra=5, min_num=1)
        formset = formset()
        assert str(formset) in response.content.decode()

    def test_valid_post_creates_recipe(self, client, admin_user, recipe_context):
        client.force_login(admin_user)
        response = client.post(reverse("recipe_create"), recipe_context)
        Recipe.objects.get(name=recipe_context["name"])

    def test_verify_recipe_entry(self, client, admin_user, recipe_context):
        client.force_login(admin_user)
        response = client.post(reverse("recipe_create"), recipe_context)
        recipe = Recipe.objects.get(name=recipe_context["name"])
        ingredients = recipe_context["ingredients"]
        amounts = recipe_context["amounts"]
        assert recipe.author == admin_user
        assert recipe.image == recipe_context["image"]
        for i, detail in enumerate(recipe.ingredientdetails_set.all()):
            assert detail.ingredient == ingredients[i]
            assert detail.amount == amounts[i]

    def test_valid_post_redirects_to_recipe_list(
        self, client, admin_user, recipe_context
    ):
        client.force_login(admin_user)
        response = client.post(reverse("recipe_create"), recipe_context)
        assert_redirects(response, reverse("recipes"))

    def test_invalid_ingredient(self, client, admin_user, recipe_context):
        client.force_login(admin_user)
        recipe_context["form-0-ingredient"] = ""
        response = client.post(reverse("recipe_create"), recipe_context)
        with pytest.raises(Recipe.DoesNotExist):
            Recipe.objects.get(name=recipe_context["name"])

    def test_missing_name_is_invalid(self, client, admin_user, recipe_context):
        client.force_login(admin_user)
        recipe_context["name"] = ""
        response = client.post(reverse("recipe_create"), recipe_context)
        with pytest.raises(Recipe.DoesNotExist):
            Recipe.objects.get(name=recipe_context["name"])

    def test_missing_ingredient_amount_is_invalid(
        self, client, admin_user, recipe_context
    ):
        client.force_login(admin_user)
        recipe_context["form-0-amount"] = ""
        response = client.post(reverse("recipe_create"), recipe_context)
        with pytest.raises(Recipe.DoesNotExist):
            Recipe.objects.get(name=recipe_context["name"])

    def test_empty_recipe_instructions_is_invalid(
        self, client, admin_user, recipe_context
    ):
        client.force_login(admin_user)
        recipe_context["instructions"] = ""
        response = client.post(reverse("recipe_create"), recipe_context)
        with pytest.raises(Recipe.DoesNotExist):
            Recipe.objects.get(name=recipe_context["name"])
