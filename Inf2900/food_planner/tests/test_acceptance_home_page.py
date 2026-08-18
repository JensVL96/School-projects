from django.shortcuts import reverse
from tools import admin_login


class TestAcceptanceHomePage:
    def test_recipes_link_in_page(self, django_server, webdriver):
        webdriver.get(django_server.url)
        webdriver.find_element_by_link_text("recipes")

    def test_recipes_link_forwards_correctly(self, django_server, webdriver):
        path = reverse("recipes")
        webdriver.get(django_server.url)
        webdriver.find_element_by_link_text("recipes").click()
        assert f"{django_server.url}{path}" == webdriver.current_url
        assert webdriver.title == "Recipes"

    def test_login_link_in_page(self, django_server, webdriver):
        webdriver.get(django_server.url)
        webdriver.find_element_by_link_text("login")

    def test_login_link_forwards_correctly(self, django_server, webdriver):
        path = reverse("login")
        webdriver.get(django_server.url)
        webdriver.find_element_by_link_text("login").click()
        assert f"{django_server.url}{path}" == webdriver.current_url
        assert webdriver.title == "Login"

    def test_follow_inventory_link(self, django_server, webdriver):
        path = reverse("inventory")
        admin_login(django_server, webdriver)
        webdriver.find_element_by_link_text("inventory").click()
        assert f"{django_server.url}{path}" == webdriver.current_url
        assert webdriver.title == "Inventory"

    def test_follow_signup_link(self, django_server, webdriver):
        path = reverse("signup")
        webdriver.get(django_server.url)
        webdriver.find_element_by_link_text("signup").click()
        assert f"{django_server.url}{path}" == webdriver.current_url
        assert webdriver.title == "Signup"

    def test_follow_logout_link(self, django_server, webdriver):
        url = f"{django_server.url}/"
        admin_login(django_server, webdriver)
        webdriver.find_element_by_link_text("logout").click()
        assert url == webdriver.current_url
        assert webdriver.title == "Home"

    def test_follow_profile_link(self, django_server, webdriver):
        path = reverse("profile")
        url = f"{django_server.url}{path}"
        admin_login(django_server, webdriver)
        webdriver.find_element_by_link_text("profile").click()
        assert url == webdriver.current_url
        assert webdriver.title == "Profile"

    def test_follow_shopping_list_link(self, django_server, webdriver):
        path = reverse("shopping_list")
        url = f"{django_server.url}{path}"
        admin_login(django_server, webdriver)
        webdriver.find_element_by_link_text("shopping list").click()
        assert url == webdriver.current_url
        assert webdriver.title == "Shopping list"
