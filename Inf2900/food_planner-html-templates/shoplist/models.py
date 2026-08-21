from django.db import models
from ingredients.models import Ingredient


class ShopList_content(models.Model):
    ingredient = models.ForeignKey(Ingredient, on_delete=models.CASCADE)
    amount = models.IntegerField(default=1)
    unit = models.CharField(max_length=100)

    def __str__(self):
        return self.ingredient.name
