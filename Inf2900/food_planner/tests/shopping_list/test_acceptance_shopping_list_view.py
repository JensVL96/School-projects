import pytest
from django.shortcuts import reverse
from selenium.webdriver.support.ui import WebDriverWait, Select
from selenium.webdriver.support import expected_conditions as EC
from shopping_list.models import ShoppingList
from tools import admin_login, id_find_and_fill_element
import time


class TestAcceptanceShoppingListView:
    def test_update_existing_entries(self, django_server, webdriver, entries):
        admin_login(django_server, webdriver)
        webdriver.get(django_server.url + reverse("shopping_list"))
        amounts = [entry.amount + 2 for entry in entries]
        for i, amount in enumerate(amounts):
            id_find_and_fill_element(webdriver, f"form-{i}-amount", amount)
        webdriver.find_element_by_id("id_save").click()
        entries = ShoppingList.objects.all()
        for entry, amount in zip(entries, amounts):
            assert entry.amount == amount
        assert len(entries) == len(amounts)

    def test_delete_entry(self, django_server, webdriver, entries):
        admin_login(django_server, webdriver)
        webdriver.get(django_server.url + reverse("shopping_list"))
        entry = entries[0]
        element = webdriver.find_element_by_id("id_form-0-DELETE")
        element.click()
        element.submit()
        assert WebDriverWait(webdriver, 10).until(EC.staleness_of(element))
        select = Select(webdriver.find_element_by_id("id_form-0-ingredient"))
        option = select.first_selected_option
        assert entry not in ShoppingList.objects.all()
        assert option.text != str(entry)
        assert option.get_attribute("value") != str(entry.ingredient.pk)

    def test_deleted_entry_is_not_present_in_page(
        self, django_server, webdriver, entries
    ):
        admin_login(django_server, webdriver)
        webdriver.get(django_server.url + reverse("shopping_list"))
        element = webdriver.find_element_by_id("id_form-0-DELETE")
        element.click()
        element.submit()
        assert WebDriverWait(webdriver, 10).until(EC.staleness_of(element))
        element = webdriver.find_element_by_id("id_form-TOTAL_FORMS")
        form_count = int(element.get_attribute("value"))
        entry_name = str(entries[0])
        entry_id = str(entries[0].ingredient.pk)
        for i in range(form_count):
            select = Select(webdriver.find_element_by_id(f"id_form-{i}-ingredient"))
            option = select.first_selected_option
            assert option.text != entry_name
            assert option.get_attribute("value") != entry_id

    def test_add_entries(self, django_server, webdriver, ingredients):
        amounts = [i for i, _ in enumerate(ingredients, 2)]
        units = [f"unit:{i}" for i, _ in enumerate(ingredients, 1)]
        admin_login(django_server, webdriver)
        webdriver.get(django_server.url + reverse("shopping_list"))
        element = None
        for i, ingredient in enumerate(ingredients):
            id_ing = f"id_form-{i}-ingredient"
            id_n = f"form-{i}-amount"
            id_u = f"form-{i}-unit"
            select = Select(webdriver.find_element_by_id(id_ing))
            select.select_by_visible_text(str(ingredient))
            id_find_and_fill_element(webdriver, id_n, amounts[i])
            element = id_find_and_fill_element(webdriver, id_u, units[i])
        element.submit()
        assert WebDriverWait(webdriver, 10).until(EC.staleness_of(element))
        assert len(ShoppingList.objects.all()) == len(ingredients)
        for i, entry in enumerate(ShoppingList.objects.all()):
            assert entry.ingredient == ingredients[i]
            assert entry.amount == amounts[i]
            assert entry.unit == units[i]

    def test_delete_new_entry(self, django_server, webdriver, ingredients):
        amount = 42
        unit = "liter"
        admin_login(django_server, webdriver)
        webdriver.get(django_server.url + reverse("shopping_list"))
        id_ing = f"id_form-0-ingredient"
        id_n = f"form-0-amount"
        id_u = f"form-0-unit"
        select = Select(webdriver.find_element_by_id(id_ing))
        select.select_by_visible_text(str(ingredients[0]))
        id_find_and_fill_element(webdriver, id_n, amount)
        id_find_and_fill_element(webdriver, id_u, unit)
        element = webdriver.find_element_by_id("id_form-0-DELETE")
        element.click()
        element.submit()
        assert WebDriverWait(webdriver, 10).until(EC.staleness_of(element))
        assert list(ShoppingList.objects.all()) == list()
