import time
from django.shortcuts import reverse


class TestAcceptanceRecipeList:
    def test_navigate_to_next_page(self, django_server, webdriver, recipe_list):
        path = reverse("recipes")
        url = f"{django_server.url}{path}?page=2"
        webdriver.get(django_server.url + path)
        webdriver.find_element_by_link_text("next").click()
        assert webdriver.current_url == url

    def test_navigate_to_last_page(self, django_server, webdriver, recipe_list):
        path = reverse("recipes")
        url = f"{django_server.url}{path}?page=2"
        webdriver.get(django_server.url + path)
        webdriver.find_element_by_partial_link_text("last").click()
        assert webdriver.current_url == url

    def test_navigate_to_previous_page(self, django_server, webdriver, recipe_list):
        path = reverse("recipes")
        url = f"{django_server.url}{path}?page=1"
        webdriver.get(f"{django_server.url}{path}?page=2")
        webdriver.find_element_by_link_text("previous").click()
        assert webdriver.current_url == url

    def test_navigate_to_first_page(self, django_server, webdriver, recipe_list):
        path = reverse("recipes")
        url = f"{django_server.url}{path}?page=1"
        webdriver.get(f"{django_server.url}{path}?page=2")
        webdriver.find_element_by_partial_link_text("first").click()
        assert webdriver.current_url == url

    def test_click_image_redirects_to_recipe_page(
        self, django_server, webdriver, recipe_list
    ):
        webdriver.get(django_server.url + reverse("recipes"))
        webdriver.find_element_by_tag_name("img").click()
        assert webdriver.title == str(recipe_list[0])

    def test_click_recipe_link(self, django_server, webdriver, recipe_list):
        recipe = str(recipe_list[0])
        webdriver.get(django_server.url + reverse("recipes"))
        webdriver.find_element_by_partial_link_text(recipe).click()
        assert webdriver.title == str(recipe)
