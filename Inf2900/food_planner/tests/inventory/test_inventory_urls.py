import pytest
from django.test import TestCase
from django.urls import reverse, resolve


class TestUrls(TestCase):
    def test_inventory_url(self):
        path = reverse("inventory")
        assert resolve(path).view_name == "inventory"

    def test_add_url(self):
        path = reverse("add")
        assert resolve(path).view_name == "add"

    def test_remove_url(self):
        path = reverse("remove")
        assert resolve(path).view_name == "remove"
