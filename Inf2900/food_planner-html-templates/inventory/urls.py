from django.urls import path
from . import views

urlpatterns = [
    path("", views.index, name="inventory"),
    path("add/", views.add, name="add"),
    path("add/<int:ingredient_id>/", views.add_ingredient, name="add_ingredient"),
    path("remove/", views.remove, name="remove"),
    path(
        "remove/<int:ingredient_id>/", views.remove_ingredient, name="remove_ingredient"
    ),
]
