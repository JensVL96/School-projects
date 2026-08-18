import pytest
from django.db import IntegrityError


@pytest.mark.django_db
class TestCustomUserModel:
    def test_new_user_is_not_superuser(self, django_user_model):
        user = django_user_model.objects.create(username="maple")
        assert not user.is_superuser

    def test_duplicate_username_is_invalid(self, django_user_model):
        name = "maple"
        django_user_model.objects.create(username=name)
        with pytest.raises(IntegrityError):
            django_user_model.objects.create(username=name)

    def test_new_user_is_not_staff(self, django_user_model):
        user = django_user_model.objects.create(username="maple")
        assert not user.is_staff

    def test_new_user_is_active(self, django_user_model):
        user = django_user_model.objects.create(username="maple")
        assert user.is_active

    def test_first_name_label(self, django_user_model):
        first_name = django_user_model._meta.get_field("first_name").verbose_name
        assert first_name == "first name"

    def test_first_name_max_length(self, django_user_model):
        max_length = django_user_model._meta.get_field("first_name").max_length
        assert max_length == 30

    def test_last_name_label(self, django_user_model):
        last_name = django_user_model._meta.get_field("last_name").verbose_name
        assert last_name == "last name"

    def test_last_name_max_length(self, django_user_model):
        max_length = django_user_model._meta.get_field("last_name").max_length
        assert max_length == 150

    def test_object_representation_is_username(self, django_user_model):
        name = "maple"
        user = django_user_model.objects.create(username=name)
        assert str(user) == name

    def test_blank_email_is_false(self, django_user_model):
        blank = django_user_model._meta.get_field("email").blank
        assert blank == False

    def test_email_max_length(self, django_user_model):
        max_length = django_user_model._meta.get_field("email").max_length
        assert max_length == 254
