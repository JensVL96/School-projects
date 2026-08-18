import pygame_gui as gui
import pygame as pg
from pygame_gui.elements.ui_horizontal_slider import UIHorizontalSlider
from config import *
import random as rand

def init_gui():
    global SCREEN_RES
    screen_width, screen_height = SCREEN_RES

    # Define the dimensions and position of the panel
    panel_height = screen_height
    panel_x = screen_width - PANEL_WIDTH

    # Update the global screen size to accommodate the panel
    SCREEN_RES = (SCREEN_WIDTH + PANEL_WIDTH, SCREEN_HEIGHT)

    # Create a UIManager to manage the GUI elements
    manager = gui.UIManager(SCREEN_RES)

    order = 0

    # Create a UIPanel to represent the panel
    panel = gui.elements.UIPanel(
        relative_rect=pg.Rect((panel_x, 0), (PANEL_WIDTH, panel_height)),
        manager=manager,
    )

    # Add a title to the panel
    title_label = gui.elements.UILabel(
        relative_rect=pg.Rect((0, 0), (PANEL_WIDTH, 30)),
        manager=manager,
        text="Control Panel",
        container=panel,
    )

    order += 40     # Increment order for height spacing

    # Add labels for Boids
    boids_label = gui.elements.UILabel(
        relative_rect=pg.Rect((0, order), (150, 30)),
        manager=manager,
        text="Boids",
        container=panel,
    )

    order += 25     # Increment order for height spacing

    # Add a boids toggleable bar
    boids_toggle = gui.elements.UIHorizontalSlider(
        relative_rect=pg.Rect((2.5, order), (150, 30)),
        start_value=0,
        value_range=(0, BOID_LIMIT),
        manager=manager,
        container=panel,
        # text="Toggle Boids",
    )

    order += 40     # Increment order for height spacing

    # Add labels for Hoiks
    hoiks_label = gui.elements.UILabel(
        relative_rect=pg.Rect((0, order), (150, 30)),
        manager=manager,
        text="Hoiks",
        container=panel,
    )

    order += 25     # Increment order for height spacing

    # Add a hoiks toggleable bar
    hoiks_toggle = gui.elements.UIHorizontalSlider(
        relative_rect=pg.Rect((2.5, order), (150, 30)),
        start_value=0,
        value_range=(0, HOIK_LIMIT),
        manager=manager,
        container=panel,
    )

    order += 40     # Increment order for height spacing

    # Add labels for boid ranges
    range_label = gui.elements.UILabel(
        relative_rect=pg.Rect((2.5, order), (150, 30)),
        manager=manager,
        text="Boid ranges:",
        container=panel,
    )

    order += 25     # Increment order for height spacing

    # Boid range buttons list
    boid_ranges = ["boids", "hoiks", "bait", "obj"]

    # Add range buttons for boids
    boids_range_buttons = []
    for i, text in enumerate(boid_ranges):
        button = gui.elements.UIButton(
            relative_rect=pg.Rect((2.5 + (i % 2) * 77.5, order + (i // 2) * 30), (75, 25)),
            manager=manager,
            text=text,
            container=panel,
        )
        boids_range_buttons.append(button)

    order += 70     # Increment order for height spacing

    # Add labels for hoik ranges
    range_label = gui.elements.UILabel(
        relative_rect=pg.Rect((2.5, order), (150, 30)),
        manager=manager,
        text="Hoik ranges:",
        container=panel,
    )

    order += 25     # Increment order for height spacing

    # Hoik range buttons list
    hoik_ranges = ["hoiks", "hunt", "obj"]

    # Add range buttons for hoiks
    hoiks_range_buttons = []
    for i, text in enumerate(hoik_ranges):
        button = gui.elements.UIButton(
            relative_rect=pg.Rect((2.5 + (i % 2) * 77.5, order + (i // 2) * 30), (75, 25)),
            manager=manager,
            text=text,
            container=panel,
        )
        hoiks_range_buttons.append(button)

    order += 100     # Increment order for height spacing

    # Add a remove ranges button
    rm_ranges_button = gui.elements.UIButton(
        relative_rect=pg.Rect((2.5, order), (150, 30)),
        manager=manager,
        text="Remove ranges",
        container=panel,
    )

    order += 40     # Increment order for height spacing

    # Add a remove obstacles button
    rm_obj_button = gui.elements.UIButton(
        relative_rect=pg.Rect((2.5, order), (150, 30)),
        manager=manager,
        text="Remove obstacles",
        container=panel,
    )

    order += 100     # Increment order for height spacing

    # Add a mute button
    mute_button = gui.elements.UIButton(
        relative_rect=pg.Rect((2.5, order), (150, 30)),
        manager=manager,
        text="Mute",
        container=panel,
    )

    # Return the manager and UI elements as a dictionary
    return {
        'manager': manager,
        'boids_toggle': boids_toggle,
        'hoiks_toggle': hoiks_toggle,
        'boids_range': boids_range_buttons,
        'hoiks_range': hoiks_range_buttons,
        'rm_ranges_button': rm_ranges_button,
        'rm_obj_button': rm_obj_button,
        'mute_button': mute_button
    }

# Handle changes in slider values for adding or removing boids or hoiks.
def handle_slider_change(flyerlist, flyers, event, toggle_button, limit, object_type):
    if event.type == pg.USEREVENT and event.user_type == gui.UI_HORIZONTAL_SLIDER_MOVED:
        if event.ui_element == toggle_button:
            # Get the value from the slider
            count = int(event.value)
            # Calculate the difference between the slider value and the current count
            new_count = count - len(flyers)
            if new_count > 0:
                # Add new objects if the slider value increases
                for _ in range(new_count):
                    if len(flyers) < limit:
                        if object_type == 'boid':
                            flyerlist.new_boid(rand.randint(0, SCREEN_WIDTH), rand.randint(0, SCREEN_HEIGHT))
                        elif object_type == 'hoik':
                            flyerlist.new_hoik(rand.randint(0, SCREEN_WIDTH), rand.randint(0, SCREEN_HEIGHT))
                    else:
                        break  # Exit loop if the limit is reached
            elif new_count < 0:
                # Remove excess objects if the slider value decreases
                for _ in range(-new_count):
                    if len(flyers) > 0:
                        flyers.pop()

# Update the number of boids based on the slider value.
def update_boids(flyer_list, slider):
    count = len(flyer_list.boids)
    limit = int(slider.get_current_value())
    if count < limit:
        for _ in range(limit - count):
            flyer_list.new_boid(rand.randint(0, SCREEN_WIDTH), rand.randint(0, SCREEN_HEIGHT))