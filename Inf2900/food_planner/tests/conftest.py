import pytest
import os
from selenium.webdriver import Firefox, Chrome
from selenium import webdriver as WD


@pytest.fixture
def django_server(settings, live_server, django_user_model):
    """ An instance of django live_server with MD5 hashing for passwords.
    Creates superuser "admin" with password: "password".
    Returns an instance of live_server.
    """
    settings.PASSWORD_HASHERS = [
        "django.contrib.auth.hashers.MD5PasswordHasher",
    ]
    django_user_model.objects.create_superuser(username="admin", password="password")
    return live_server


@pytest.fixture(scope="session", params=[Firefox,])
def webdriver(request):
    """ A web driver for acceptance tests.
    Sets implicit waiting for two seconds on responses.
    Yields an instance of selenium webdriver.
    """
    headless = bool(os.getenv("WEBDRIVER_HEADLESS", False))
    driver_path = "./tests/drivers/"
    options = None
    if request.param == Chrome:
        driver_path += "chromedriver"
        options = WD.chrome.options.Options()
    else:
        driver_path += "geckodriver"
        options = WD.firefox.options.Options()
    options.headless = headless
    driver = request.param(executable_path=driver_path, options=options)
    driver.implicitly_wait(2)
    yield driver
    driver.close()
