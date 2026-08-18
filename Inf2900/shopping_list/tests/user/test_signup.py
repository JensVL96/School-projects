import pytest
from django.test import TestCase
from django.urls import reverse
from users.models import CustomUser

assert_template_used = TestCase().assertTemplateUsed


def user_create(client, username, password, email):
    """ Creates a user using client.post.
    Helper function for TestSignupView. """
    response = client.post(reverse("signup"),
                           {"username": username,
                            "email": email,
                            "password1": password,
                            "password2": password})
    return response


@pytest.mark.django_db
class TestSignUpView:
    def test_url_resolve_to_signup_page(self, client):
        response = client.get(reverse("signup"))
        assert_template_used(response, "signup.html")

    def test_signup_fields_in_page(self, client):
        fields = ["username", "email", "password"]
        response = client.get(reverse("signup"))
        content = response.content.decode()
        for field in fields:
            assert field in content

    def test_signup_admin_fields_not_in_page(self, client):
        fields = ["staff", "active", "superuser", "last login",
                  "date joined", "permissions", "groups"]
        response = client.get(reverse("signup"))
        content = response.content.decode()
        for field in fields:
            assert field not in content

    def test_create_user_blank_email_is_invalid(self, client):
        username = "maple"
        password = "supersecret"
        response = user_create(client, username, password, "")
        assert client.login(username=username, password=password) == False

    def test_create_valid_user(self, client):
        username = "maple"
        email = "maple@mu.com"
        password = "supersecret"
        response = user_create(client, username, password, email)
        assert client.login(username=username, password=password)
