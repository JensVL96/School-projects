import pytest
from django.test import TestCase
from django.urls import reverse
from users.forms import CustomUserCreationForm

assert_template_used = TestCase().assertTemplateUsed
assert_redirects = TestCase().assertRedirects


@pytest.mark.django_db
class TestUserLogin:
    def test_user_not_present(self, client):
        response = client.login(username="maple", password="secret")
        assert response == False

    def test_incorrect_login(self, client):
        response = client.post(reverse("login"),
                               {"username": "maple",
                                "password": "secret"})
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

    def test_redirect_when_login(self, django_user_model, client):
        username = "maple"
        password = "supersecret"
        django_user_model.objects.create_user(username=username, password=password)
        response = client.post(reverse("login"),
                               {"username": username,
                                "password": password})
        assert_redirects(response, reverse("home"))


class TestUserLoginForm:
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
