import pytest
from pytest_django.asserts import (
    assertTemplateUsed as assert_template_used,
    assertRedirects as assert_redirects,
)
from django.urls import reverse


@pytest.mark.django_db
class TestUserLogin:
    def test_user_not_present(self, client):
        response = client.login(username="maple", password="secret")
        assert response == False

    def test_incorrect_login(self, client):
        response = client.post(
            reverse("login"), {"username": "maple", "password": "secret"}
        )
        assert "login" in response.content.lower().decode()

    def test_view_uses_correct_template(self, client):
        response = client.get(reverse("login"))
        assert_template_used(response, "registration/login.html")

    def test_page_contains_data(self, client):
        data = ["login", "username", "password"]
        response = client.get(reverse("login"))
        content = response.content.lower().decode()
        for field in data:
            assert field in content

    def test_redirect_when_login(self, client, default_user):
        username, password = default_user
        response = client.post(
            reverse("login"), {"username": username, "password": password}
        )
        assert_redirects(response, reverse("home"))
