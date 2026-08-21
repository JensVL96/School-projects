from django.urls import path
from .views import RecipeListView, RecipeView, RecipeCreationView, search


urlpatterns = [
    path("", RecipeListView.as_view(), name="recipes"),
    path("<int:pk>/", RecipeView.as_view(), name="recipe_detail"),
    path("create/", RecipeCreationView.as_view(), name="recipe_create"),
    path("search/<name>/", RecipeListView.as_view(), name="recipe_search"),
    path("search/", search, name="recipe_search"),
]
