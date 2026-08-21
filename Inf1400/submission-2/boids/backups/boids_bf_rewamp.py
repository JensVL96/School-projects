import pygame as pg
import random as rand
import pygame_gui as gui
import os
from resources import *
from config import *
from gui import *
from objects import FlyerList

class game():
    def __init__(self):
        pg.init()
        self.screen = pg.display.set_mode(SCREEN_RES)
        
        self.gui_elements = gui.init_gui()
        self.manager = self.gui_elements['manager']
        self.clock = pg.time.Clock()

        music_file = os.path.join("Sprites", "stompss.ogg")
        pg.mixer.music.load(music_file)
        self.music_paused = False
        pg.mixer.music.play(-1)

        self.flyer_list = FlyerList()
        self.is_mouse_button_pressed = False
        self.running = True
        self.GameLoop()

    def GameLoop(self):
        while self.running:
            self.handle_events()
            self.update()
            self.draw()
        pg.mixer.music.stop()

    def handle_events(self):
        for event in pg.event.get():
            if event.type == pg.QUIT or (event.type == pg.KEYDOWN and event.key == pg.K_ESCAPE):
                self.running = False

            #   Mouse based object control
            x, y = pg.mouse.get_pos()
            if(event.type == pg.MOUSEBUTTONDOWN and event.button == 4):
                self.flyer_list.new_boid(x, y)
            elif(event.type == pg.MOUSEBUTTONDOWN and event.button == 5):
                self.flyer_list.new_hoik(x, y)
            elif(event.type == pg.MOUSEBUTTONDOWN and event.button == 1):
                self.flyer_list.new_bait(x, y, active = 1)
                self.flyer_list.update_bait(x, y)
                self.is_mouse_button_pressed = True
            elif(event.type == pg.MOUSEBUTTONUP and event.button == 1):
                self.flyer_list.new_bait(x, y, active = 0)
                self.is_mouse_button_pressed = False
            if rand.randint(0,5) == 0 and pg.mouse.get_pressed()[2]:
                self.flyer_list.new_obstacle(x, y)

            if self.is_mouse_button_pressed and event.type == pg.MOUSEMOTION:
                self.flyer_list.update_bait(x, y)

            # UIpanel object control
            gui.handle_slider_change(
                self.flyer_list,
                self.flyer_list.boids,
                event,
                self.gui_elements['boids_toggle'],
                limit=BOID_LIMIT,
                object_type='boid',
            )
            gui.handle_slider_change(
                self.flyer_list,
                self.flyer_list.hoiks,
                event,
                self.gui_elements['hoiks_toggle'],
                limit=HOIK_LIMIT,
                object_type='hoik',
            )
            gui.update_boids(self.flyer_list, self.gui_elements['boids_toggle'])

            self.manager.process_events(event)


    def update(self):
        self.manager.update(self.clock.tick(60) / 1000.0)

        # Handle range buttons toggling
        for i, button in enumerate(self.gui_elements['boids_range']):
            if button.check_pressed():
                self.flyer_list.boid_states[i] = not self.flyer_list.boid_states[i]

        for i, button in enumerate(self.gui_elements['hoiks_range']):
            if button.check_pressed():
                self.flyer_list.hoik_states[i] = not self.flyer_list.hoik_states[i]

        if self.gui_elements['rm_ranges_button'].check_pressed():
            self.flyer_list.boid_states = [False] * len(self.flyer_list.boid_states)
            self.flyer_list.hoik_states = [False] * len(self.flyer_list.hoik_states)

        if self.gui_elements['rm_obj_button'].check_pressed():
            self.flyer_list.rm_obstacles()

        if self.gui_elements['mute_button'].check_pressed():
            self.toggle_music()

        self.flyer_list.move_all()

        pg.display.update()

    def toggle_music(self):
        if self.music_paused:
            pg.mixer.music.unpause()
            self.music_paused = False
            self.gui_elements['mute_button'].set_text("Mute")
        else:
            pg.mixer.music.pause()
            self.music_paused = True
            self.gui_elements['mute_button'].set_text("Play")

    def draw(self):
        self.screen.fill(BLACK)
        self.flyer_list.draw_all(self.screen)
        self.flyer_list.toggle_circles(self.screen, object_type='boid')
        self.flyer_list.toggle_circles(self.screen, object_type='hoik')

        self.manager.draw_ui(self.screen)
        pg.display.flip()
if __name__ == '__main__':
    game()