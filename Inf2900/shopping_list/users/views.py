from django.urls import reverse_lazy
from django.views.generic.edit import CreateView, UpdateView
from django.shortcuts import render
from .models import CustomUser
from .forms import CustomUserCreationForm, CustomUserChangeForm


class SignUpView(CreateView):
    """ Default view for creating new user. """
    form_class = CustomUserCreationForm
    success_url = reverse_lazy("login")
    template_name = "signup.html"


class ProfileEditView(UpdateView):
    """ Custom view for editing user profile details. """
    form_class = CustomUserChangeForm
    success_url = reverse_lazy("home")
    template_name = "users/profile.html"

    def get_object(self, queryset=None):
        return self.request.user
