from django.contrib.auth.decorators import login_required
from django.urls import reverse_lazy
from django.views.generic.edit import CreateView, UpdateView
from django.shortcuts import render
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


@login_required
def user_delete(request):
    """ Return a rendered view of user deletion page. """
    context = {"delete_done": False}
    if request.method == "POST" and request.POST.get("delete_confirm"):
        request.user.delete()
        context["delete_done"] = True
    return render(request, "users/delete.html", context)
