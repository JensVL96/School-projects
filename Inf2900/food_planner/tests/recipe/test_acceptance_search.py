import time
from django.shortcuts import reverse
from tools import id_find_and_fill_element


class TestAcceptanceRecipeSearch:
    def test_find_search_bar_by_id(self, django_server, webdriver):
        webdriver.get(django_server.url)
        webdriver.find_element_by_id("id_search")

    def test_find_search_bar_by_name(self, django_server, webdriver):
        webdriver.get(django_server.url)
        webdriver.find_element_by_name("recipe_name")

    def test_find_recipe_with_query(self, django_server, webdriver, recipe_list):
        webdriver.get(django_server.url)
        name = str(recipe_list[0])
        id_find_and_fill_element(webdriver, "search", name).submit()
        webdriver.find_element_by_link_text(name).click()

    def test_find_recipe_at_page_two(self, django_server, webdriver, recipe_list):
        webdriver.get(django_server.url)
        name = str(recipe_list[-1])
        id_find_and_fill_element(webdriver, "search", "mu").submit()
        webdriver.find_element_by_partial_link_text("next").click()
        webdriver.find_element_by_link_text(name).click()
