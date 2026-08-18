from django.contrib.auth.models import AbstractUser
from django.db import models


class CustomUser(AbstractUser):
    """ Custom user model. Overrides email field setting;
    disables blank email. """

    email = models.EmailField("Email address", blank=False)
    # add additional fields here

    def __str__(self):
        return self.username
