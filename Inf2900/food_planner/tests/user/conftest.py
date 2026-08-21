import pytest


@pytest.fixture
def default_user(django_user_model):
    """ Creates a test user. Returns (username, password)."""
    username = "maple"
    password = "supersecret"
    django_user_model.objects.create_user(username=username, password=password)
    return username, password
