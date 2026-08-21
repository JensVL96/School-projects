import pytest
from pytest_django.asserts import (
    assertTemplateUsed as assert_template_used,
    assertRedirects as assert_redirects,
)
from django.urls import reverse
from users.models import CustomUser
from users.forms import CustomUserCreationForm


def user_create(client, username, password, email):
    """ Creates a user using client.post.
    Helper function for TestSignupView. """
    response = client.post(
        reverse("signup"),
        {
            "username": username,
            "email": email,
            "password1": password,
            "password2": password,
        },
    )
    return response


class TestUserCreationForm:
    def test_login_form_username_label(self):
        form = CustomUserCreationForm()
        assert form.fields["username"].label == "Username"

    def test_login_form_password_label(self):
        form = CustomUserCreationForm()
        assert form.fields["password1"].label == "Password"

    def test_login_form_password_confirmation_label(self):
        form = CustomUserCreationForm()
        assert form.fields["password2"].label == "Password confirmation"

    def test_login_form_email_label(self):
        form = CustomUserCreationForm()
        assert form.fields["email"].label == "Email address"

    def test_login_form_email_required(self):
        form = CustomUserCreationForm()
        assert form.fields["email"].required


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
        fields = [
            "staff",
            "active",
            "superuser",
            "last login",
            "date joined",
            "permissions",
            "groups",
        ]
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
