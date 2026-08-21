import pytest
from django.db import IntegrityError, models
from django.db.models import ManyToManyField, ForeignKey, TextField
from recipes.models import Recipe, IngredientDetails
from ingredients.models import Ingredient


@pytest.mark.django_db
class TestRecipeModel:
    def test_name_label(self):
        label = Recipe._meta.get_field("name").verbose_name
        assert label == "name"

    def test_name_max_length(self):
        max_length = Recipe._meta.get_field("name").max_length
        assert max_length == 100

    def test_duplicate_name_is_invalid(self, admin_user):
        name = "apple"
        Recipe.objects.create(name=name, author=admin_user)
        with pytest.raises(IntegrityError):
            Recipe.objects.create(name=name, author=admin_user)

    def test_object_name_is_name(self):
        name = "apple"
        recipe = Recipe(name=name)
        assert str(recipe) == name

    def test_ingredients_label(self):
        label = Recipe._meta.get_field("ingredients").verbose_name
        assert label == "ingredients"

    def test_ingredients_is_many_to_many(self):
        field_type = Recipe._meta.get_field("ingredients")
        assert isinstance(field_type, ManyToManyField)

    def test_ingredients_related_model(self):
        model = Recipe.ingredients.field.related_model()
        assert isinstance(model, Ingredient)

    def test_ingredients_related_intermediary_model(self):
        model = Recipe.ingredients.rel.through()
        assert isinstance(model, IngredientDetails)

    def test_author_label(self):
        label = Recipe._meta.get_field("author").verbose_name
        assert label == "author"

    def test_author_is_foreign_key(self):
        field_type = Recipe._meta.get_field("author")
        assert isinstance(field_type, ForeignKey)

    def test_instructions_label(self):
        label = Recipe._meta.get_field("instructions").verbose_name
        assert label == "instructions"

    def test_instructions_is_text_field(self):
        field_type = Recipe._meta.get_field("instructions")
        assert isinstance(field_type, TextField)

    def test_image_label(self):
        label = Recipe._meta.get_field("image").verbose_name
        assert label == "image"

    def test_image_is_image_field(self):
        field = Recipe._meta.get_field("image")
        assert isinstance(field, models.ImageField)

    def test_recipe_a_does_not_contain_recipe_b_ingredient(self, admin_user):
        egg = Ingredient.objects.create(name="egg")
        bacon = Ingredient.objects.create(name="bacon")
        recipe_a = Recipe.objects.create(name="eggbat", author=admin_user)
        recipe_b = Recipe.objects.create(name="hamwitch", author=admin_user)
        recipe_a.ingredients.add(egg)
        recipe_b.ingredients.add(bacon)
        assert egg not in list(recipe_b.ingredientdetails_set.all())
        assert bacon not in list(recipe_a.ingredientdetails_set.all())


@pytest.mark.django_db
class TestIngredientDetails:
    def test_amount_label(self):
        label = IngredientDetails._meta.get_field("amount").verbose_name
        assert label == "amount"

    def test_amount_default_value(self):
        value = IngredientDetails._meta.get_field("amount").default
        assert value == 1

    def test_ingredient_label(self):
        label = IngredientDetails._meta.get_field("ingredient").verbose_name
        assert label == "ingredient"

    def test_ingredient_related_model(self):
        model = IngredientDetails.ingredient.field.related_model()
        assert isinstance(model, Ingredient)

    def test_recipe_label(self):
        label = IngredientDetails._meta.get_field("recipe").verbose_name
        assert label == "recipe"

    def test_recipe_related_model(self):
        model = IngredientDetails.recipe.field.related_model()
        assert isinstance(model, Recipe)

    def test_object_name_is_ingredient_amount_and_name(self):
        name = "egg"
        detail = IngredientDetails(ingredient=Ingredient(name=name))
        assert str(detail) == f"{detail.amount} {name}"
