import pytest
from pytest_django.asserts import (
    assertTemplateUsed as assert_template_used,
    assertRedirects as assert_redirects,
)
from django.urls import reverse


@pytest.mark.django_db
class TestProfileView:
    pass
