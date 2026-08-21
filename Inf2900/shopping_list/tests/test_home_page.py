from django.test import SimpleTestCase
from django.urls import reverse


class TestHomePage(SimpleTestCase):
    def test_home_page_status_code(self):
        response = self.client.get("/")
        assert response.status_code == 200

    def test_view_url_by_name(self):
        response = self.client.get(reverse("home"))
        assert response.status_code == 200

    def test_view_uses_correct_template(self):
        response = self.client.get(reverse("home"))
        self.assertTemplateUsed(response, "home.html")

    def test_contains_correct_html(self):
        response = self.client.get(reverse("home"))
        self.assertContains(response, "Homepage")

    def test_does_not_contain_incorrect_html(self):
        response = self.client.get(reverse("home"))
        self.assertNotContains(response, "Blarg more")
