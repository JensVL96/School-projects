import random
import time
import pytest
from selenium.webdriver.support.ui import Select
from tools import id_find_and_fill_element, admin_login


@pytest.fixture
def ingredient_list(ingredient_create):
    ingredient_names = ["egg", "mayo", "ham", "bacon", "cucumber"]
    [ingredient_create(name=n) for n in ingredient_names]
    return ingredient_names


class TestAcceptanceRecipeCreate:
    def test_recipe_create(
        self, django_server, webdriver, ingredient_list, image_create
    ):
        recipe_name = "burger"
        instructions = "Throw in a kettle, stir vigorously!"
        image = str(image_create(f"{recipe_name}.png"))
        amounts = [random.randint(0, 100) for _ in ingredient_list]
        admin_login(django_server, webdriver)
        webdriver.find_element_by_link_text("recipes").click()
        webdriver.find_element_by_link_text("New recipe").click()
        id_find_and_fill_element(webdriver, "name", recipe_name)
        id_find_and_fill_element(webdriver, "instructions", instructions)
        id_find_and_fill_element(webdriver, "image", image)
        element = None
        for i, name in enumerate(ingredient_list):
            id_ing = f"id_form-{i}-ingredient"
            id_n = f"form-{i}-amount"
            select = Select(webdriver.find_element_by_id(id_ing))
            select.select_by_visible_text(name)
            element = id_find_and_fill_element(webdriver, id_n, amounts[i])
        element.submit()
        webdriver.find_element_by_link_text(recipe_name).click()
        assert webdriver.title == recipe_name
