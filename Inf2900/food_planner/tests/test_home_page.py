import pytest
from pytest_django.asserts import (
    assertTemplateUsed as assert_template_used,
    assertRedirects as assert_redirects,
)
from django.urls import reverse


@pytest.mark.django_db
class TestHomePage:
    def test_home_page_status_code(self, client):
        response = client.get("/")
        assert response.status_code == 200

    def test_view_url_by_name(self, client):
        response = client.get(reverse("home"))
        assert response.status_code == 200

    def test_view_uses_correct_template(self, client):
        response = client.get(reverse("home"))
        assert_template_used(response, "home.html")

    def test_contains_link_to_recipe_list(self, client):
        response = client.get("/")
        url = f"""<a href="{reverse('recipes')}"> recipes </a>"""
        assert url in response.content.decode()

    def test_contains_link_to_login(self, client):
        response = client.get("/")
        url = f"""<a href="{reverse('login')}"> login </a>"""
        assert url in response.content.decode()

    def test_contains_link_to_signup(self, client):
        response = client.get("/")
        url = f"""<a href="{reverse('signup')}"> signup </a>"""
        assert url in response.content.decode()

    def test_contains_link_to_inventory(self, client):
        response = client.get("/")
        url = f"""<a href="{reverse('inventory')}"> inventory </a>"""
        assert url in response.content.decode()

    def test_contains_link_to_shopping_list(self, client):
        response = client.get("/")
        url = f"""<a href="{reverse('shopping_list')}"> shopping list </a>"""
        assert url in response.content.decode()

    def test_logout_is_not_visible_to_anonymous_user(self, client):
        response = client.get("/")
        assert "logout" not in response.content.decode()

    def test_logout_is_visble_to_authenticated_user(self, client, admin_user):
        client.force_login(admin_user)
        response = client.get("/")
        url = f"""<a href="{reverse('logout')}"> logout </a>"""
        assert url in response.content.decode()
