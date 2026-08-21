from resources import *
from config import *
import pygame as pg
import random as rand
from objects import FlyerList
import pygame_gui as gui
import gui

class game():
    def __init__(self):
        pg.init()
        self.screen = pg.display.set_mode(SCREEN_RES)
        self.manager = gui.init_gui()
        self.clock = pg.time.Clock()
        self.flyer_list = FlyerList()
        self.is_mouse_button_pressed = False
        self.running = True
        self.GameLoop()

    def GameLoop(self):
        while self.running:
            self.handle_events()
            self.update()
            self.draw()

    def handle_events(self):
        for event in pg.event.get():
            if event.type == pg.QUIT or (event.type == pg.KEYDOWN and event.key == pg.K_ESCAPE):
                self.running = False

            #   Gives the coordinate of the mouse to make an object, based on what that is pushed
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

            self.manager.process_events(event)

    def update(self):
        self.manager.update(self.clock.tick(60) / 1000.0)

        self.flyer_list.move_all()
        self.flyer_list.draw_all(self.screen)

        pg.display.update()

    def draw(self):
        self.screen.fill(BLACK)
        self.flyer_list.draw_all(self.screen)
        self.manager.draw_ui(self.screen)
        pg.display.flip()
        # #   Defines the screen and time
        # screen = pg.display.set_mode(SCREEN_RES)
        # screen_name = pg.display.set_caption("Boids simulator!")
        # clock = pg.time.Clock()
        
        # #   Plays the game
        # while 1:
        #     #   Makes the screen and clock
        #     pg.draw.rect(screen, BLACK, (0, 0, screen.get_width(), screen.get_height()))
        #     time_passed = clock.tick(100) # limit to 100FPS

        #     for event in pg.event.get():
        #         if event.type == pg.QUIT or (event.type == pg.KEYDOWN and event.key == pg.K_ESCAPE):
        #             exit()
            
        #         #   Gives the coordinate of the mouse to make an object, based on what that is pushed
        #         x, y = pg.mouse.get_pos()
        #         if(event.type == pg.MOUSEBUTTONDOWN and event.button == 4):
        #             self.flyer_list.new_boid(x, y)
        #         elif(event.type == pg.MOUSEBUTTONDOWN and event.button == 5):
        #             self.flyer_list.new_hoik(x, y)
        #         elif(event.type == pg.MOUSEBUTTONDOWN and event.button == 1):
        #             self.flyer_list.new_bait(x, y, active = 1)
        #             self.flyer_list.update_bait(x, y)
        #             self.is_mouse_button_pressed = True
        #         elif(event.type == pg.MOUSEBUTTONUP and event.button == 1):
        #             self.flyer_list.new_bait(x, y, active = 0)
        #             self.is_mouse_button_pressed = False
        #         if rand.randint(0,5) == 0 and pg.mouse.get_pressed()[2]:
        #             self.flyer_list.new_obstacle(x, y)

        #         if self.is_mouse_button_pressed and event.type == pg.MOUSEMOTION:
        #             self.flyer_list.update_bait(x, y)

        #         self.manager.process_events(event)

        #     self.manager.update(time_passed)
        #     self.manager.draw_ui(screen)

        #     self.flyer_list.move_all()
        #     self.flyer_list.draw_all(screen)

        #     pg.display.update()
if __name__ == '__main__':
    game()