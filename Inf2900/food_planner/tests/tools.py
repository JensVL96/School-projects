from django.shortcuts import reverse
from selenium.webdriver.support.ui import WebDriverWait
from selenium.webdriver.support import expected_conditions as EC


def id_find_and_fill_element(driver, name, string):
    """ Find element by id name and sends string keys to it.
    Returns element.
    """
    element = driver.find_element_by_id(f"id_{name}")
    element.clear()
    element.send_keys(string)
    return element


def admin_login(django_server, webdriver):
    """ Logs admin user in. """
    path = reverse("login")
    webdriver.get(django_server.url + path)
    id_find_and_fill_element(webdriver, "username", "admin")
    element = id_find_and_fill_element(webdriver, "password", "password")
    element.submit()
    WebDriverWait(webdriver, 10).until(EC.title_is("Home"))
    return webdriver
