import pytest
from pytest_django.asserts import (
    assertTemplateUsed as assert_template_used,
    assertRedirects as assert_redirects,
)
from django.urls import reverse
from django.core.exceptions import ObjectDoesNotExist


@pytest.mark.django_db
class TestAccountDeletionView:
    def test_correct_template_is_used(self, client, default_user):
        username, password = default_user
        client.login(username=username, password=password)
        response = client.get(reverse("delete"))
        assert_template_used(response, "users/delete.html")

    def test_default_delete_done_context(self, client, default_user):
        username, password = default_user
        client.login(username=username, password=password)
        response = client.get(reverse("delete"))
        assert response.context["delete_done"] == False

    def test_redirect_when_logged_out(self, client):
        response = client.get(reverse("delete"))
        url = f"{reverse('login')}?next={reverse('delete')}"
        assert_redirects(response, url)

    def test_user_delete(self, client, default_user, django_user_model):
        username, password = default_user
        client.login(username=username, password=password)
        client.post(reverse("delete"), {"delete_confirm": "delete_confirm"})
        with pytest.raises(ObjectDoesNotExist):
            django_user_model.objects.get(username=username)

    def test_user_delete_shows_success_message(self, client, default_user):
        username, password = default_user
        client.login(username=username, password=password)
        response = client.post(reverse("delete"), {"delete_confirm": "delete_confirm"})
        assert "Account deleted successfully" in response.content.decode()
