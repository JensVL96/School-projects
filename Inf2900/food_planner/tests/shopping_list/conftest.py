import pytest
from ingredients.models import Ingredient
from shopping_list.models import ShoppingList


@pytest.fixture
def ingredients():
    names = ["egg", "bacon", "cucumber", "mayo", "salt"]
    create = Ingredient.objects.create
    return [create(name=name) for name in names]


@pytest.fixture
def entries(admin_user, ingredients):
    amounts = [6, 4, 2, 1, 50]
    units = ["liter", "cup", "spoon", "n", "gram"]
    entries = []
    for ingredient, amount, unit in zip(ingredients, amounts, units):
        entry = ShoppingList.objects.create(
            ingredient=ingredient, amount=amount, unit=unit, user=admin_user
        )
        entries.append(entry)
    return entries
