import pytest
from PIL import Image, ImageDraw
from django.core.files.uploadedfile import SimpleUploadedFile
from recipes.models import Recipe
from ingredients.models import Ingredient


@pytest.fixture
def default_recipe(recipe_create):
    ingredients = ["lettuce", "tomato"]
    amounts = [4, 8]
    return recipe_create("burger", ingredients, amounts)


@pytest.fixture
def recipe_list(recipe_create):
    """ Minimal recipes. """
    ingredients = ["egg", "mayo"]
    amounts = [42, 5]
    count = 25
    return [recipe_create(f"mu:{i}", ingredients, amounts) for i in range(count)]


@pytest.fixture
def ingredient_create():
    """ Create an ingredient entry, if it already exists return it. """

    def _ingredient_create(name):
        try:
            ing = Ingredient.objects.get(name=name)
        except Ingredient.DoesNotExist:
            ing = Ingredient.objects.create(name=name)
        return ing

    return _ingredient_create


@pytest.fixture
def recipe_create(admin_user, uploaded_image, ingredient_create):
    """ Create a recipe entry. Returns the entry."""
    text = "Throw into kettle."

    def _recipe_create(name, ing_names, amounts):
        image = uploaded_image(f"{name}.png")
        recipe = Recipe.objects.create(
            name=name, author=admin_user, instructions=text, image=image
        )
        ingredients = [ingredient_create(name=n) for n in ing_names]
        for ing, amount in zip(ingredients, amounts):
            recipe.ingredients.add(ing, through_defaults={"amount": amount})
        return recipe

    return _recipe_create


@pytest.fixture
def image_create(settings, tmp_path_factory):
    """ Create a image for uploading. """
    upload_dir = tmp_path_factory.mktemp("media")
    dir = tmp_path_factory.mktemp("images")
    settings.MEDIA_ROOT = upload_dir

    def _image_create(name, size=(100, 100)):
        p = dir / name
        img = Image.new("RGB", size, color="purple")
        d = ImageDraw.Draw(img)
        d.text((10, 10), "Hello world", fill=(255, 255, 0))
        img.save(p)
        return p

    return _image_create


@pytest.fixture
def uploaded_image(image_create):
    def _uploaded_image(name, size=(100, 100)):
        path = image_create(name, size)
        with open(path, "rb") as fp:
            image = SimpleUploadedFile(name=name, content=fp.read())
        return image

    return _uploaded_image
