from django.views.generic.base import TemplateView
from django.urls import path
from .views import ShopListView
# from . import views


urlpatterns = [
    path("", TemplateView.as_view(template_name="shoplist.html"), name="shoplist"),
    path("<int:pk>/", ShopListView.as_view(), name="shoplist_detail"),
    # path("add/", views.add, name="add"),
    # path("add/<int:ingredient_id>/", views.add_ingredient, name="add_ingredient"),
]

# urlpatterns = [
#     path("", RecipeListView.as_view(), name="recipes"),
#     path("<int:pk>/", RecipeView.as_view(), name="recipe_detail"),
#     path("create/", RecipeCreationView.as_view(), name="recipe_create"),
# ]