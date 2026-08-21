from django.db import models
from ingredients.models import Ingredient
from users.models import CustomUser


class Inventory(models.Model):
    user = models.ForeignKey(CustomUser, on_delete=models.CASCADE)
    ingredient = models.ForeignKey(Ingredient, on_delete=models.CASCADE)
    amount = models.IntegerField(default=1)

    def __str__(self):
        return self.ingredient.name
