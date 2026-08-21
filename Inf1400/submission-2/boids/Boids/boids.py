import pygame as pg
import gui
import os
from resources import *
from config import *
from objects import FlyerList

class Game():
    # Initialize the game
    def __init__(self):
        pg.init()
        self.screen = pg.display.set_mode(SCREEN_RES)
        self.manager = gui.init_gui()
        self.clock = pg.time.Clock()
        self.init_game()

    # Initialize the game state
    def init_game(self):
        music_file = os.path.join("Sprites", "stompss.ogg")
        pg.mixer.music.load(music_file)
        self.is_mouse_button_pressed = False
        self.music_paused = True
        self.running = True
        self.counter = 0
        self.flyer_list = FlyerList()

    # Handle mouse click and movement events
    def handle_mouse_events(self, event):
        x, y = pg.mouse.get_pos()
        if event.type == pg.MOUSEBUTTONDOWN:
            if event.button == 4:
                self.flyer_list.new_boid(x, y)
            elif event.button == 5:
                self.flyer_list.new_hoik(x, y)
            elif event.button == 1:
                self.flyer_list.new_bait(x, y, active=1)
                self.is_mouse_button_pressed = True
        elif(event.type == pg.MOUSEBUTTONUP and event.button == 1):
            self.flyer_list.new_bait(x, y, active = 0)
            self.is_mouse_button_pressed = False
        elif pg.mouse.get_pressed()[2]:
            if self.counter >= 5:
                self.flyer_list.new_obstacle(x, y)
                self.counter = 0  # Reset the counter after creating an obstacle
            else:
                self.counter += 1
        elif self.is_mouse_button_pressed and event.type == pg.MOUSEMOTION:
            self.flyer_list.update_bait(x, y)

    # Handle game events and graphics
    def handle_events(self):
        for event in pg.event.get():
            if event.type == pg.QUIT or (event.type == pg.KEYDOWN and event.key == pg.K_ESCAPE):
                self.running = False

            # UIpanel object control
            gui.handle_slider_change(
                self.flyer_list,
                self.flyer_list.boids,
                event,
                self.manager['boids_toggle'],
                limit=BOID_LIMIT,
                object_type='boid',
            )
            gui.handle_slider_change(
                self.flyer_list,
                self.flyer_list.hoiks,
                event,
                self.manager['hoiks_toggle'],
                limit=HOIK_LIMIT,
                object_type='hoik',
            )

            self.handle_mouse_events(event)
            self.manager['manager'].process_events(event)

    # Update the game state
    def update(self):
        self.manager['manager'].update(self.clock.tick(60) / 1000.0)
        gui.update_boids(self.flyer_list, self.manager['boids_toggle'])
        self.toggle_music_button()
        self.flyer_list.move_all()
        self.toggle_range_buttons()
        pg.display.update()

    # Toggle the music
    def toggle_music_button(self):
        if self.manager['mute_button'].check_pressed():
            if self.music_paused:
                pg.mixer.music.unpause()
                self.music_paused = False
                self.manager['mute_button'].set_text("Mute")
            else:
                pg.mixer.music.pause()
                self.music_paused = True
                self.manager['mute_button'].set_text("Play")

    # events from the panel buttons
    def toggle_range_buttons(self):
        for i, button in enumerate(self.manager['boids_range']):
            if button.check_pressed():
                self.flyer_list.boid_states[i] = not self.flyer_list.boid_states[i]

        for i, button in enumerate(self.manager['hoiks_range']):
            if button.check_pressed():
                self.flyer_list.hoik_states[i] = not self.flyer_list.hoik_states[i]

        if self.manager['rm_ranges_button'].check_pressed():
            self.flyer_list.boid_states = [False] * len(self.flyer_list.boid_states)
            self.flyer_list.hoik_states = [False] * len(self.flyer_list.hoik_states)

        if self.manager['rm_obj_button'].check_pressed():
            self.flyer_list.rm_obstacles()

    # Draw the game
    def draw(self):
        self.screen.fill(BLACK)
        self.flyer_list.draw_all(self.screen)
        self.flyer_list.toggle_circles(self.screen, object_type='boid')
        self.flyer_list.toggle_circles(self.screen, object_type='hoik')
        self.manager['manager'].draw_ui(self.screen)
        pg.display.flip()

    # Run the game loop
    def run(self):
        while self.running:
            self.handle_events()
            self.update()
            self.draw()
        pg.mixer.music.stop()
if __name__ == '__main__':
    game = Game()
    game.run()