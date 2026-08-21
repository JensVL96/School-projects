from django.forms import ModelForm
from django.contrib.auth.forms import UserCreationForm
from .models import CustomUser


class CustomUserCreationForm(UserCreationForm):
    """ Form for creating a custom user."""

    class Meta:
        model = CustomUser
        fields = ("username", "email")


class CustomUserChangeForm(ModelForm):
    """ Form for editing custom user details."""

    class Meta:
        model = CustomUser
        fields = ("username", "email", "first_name", "last_name")
