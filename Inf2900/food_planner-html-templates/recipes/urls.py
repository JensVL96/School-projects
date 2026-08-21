from django.urls import path
from .views import RecipeListView, RecipeView, RecipeCreationView


urlpatterns = [
    path("", RecipeListView.as_view(), name="recipes"),
    path("<int:pk>/", RecipeView.as_view(), name="recipe_detail"),
    path("create/", RecipeCreationView.as_view(), name="recipe_create"),
]
