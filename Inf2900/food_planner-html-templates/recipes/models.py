from django.db import models
from users.models import CustomUser
from ingredients.models import Ingredient


class Recipe(models.Model):
    name = models.CharField(max_length=100, unique=True)
    author = models.ForeignKey(CustomUser, on_delete=models.CASCADE)
    ingredients = models.ManyToManyField(Ingredient, through="IngredientDetails")

    def __str__(self):
        return self.name


class IngredientDetails(models.Model):
    """ Contains the amount of ingredients. """

    ingredient = models.ForeignKey(Ingredient, on_delete=models.CASCADE)
    recipe = models.ForeignKey(Recipe, on_delete=models.CASCADE)
    amount = models.BigIntegerField(default=1)

    def __str__(self):
        return f"Name: {self.ingredient}, amount: {self.amount}"
