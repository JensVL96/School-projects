from django.urls import path
from . import views

urlpatterns = [
    path("", views.index, name="inventory"),
    path("add/", views.add, name="add"),
    path("remove/", views.remove, name="remove"),
]
