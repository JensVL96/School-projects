from resources import *
from config import *
import pygame as pg
import random as rand
from objects import FlyerList

class game():
    def __init__(self):
        pg.init()
        self.flyer_list = FlyerList()
        self.GameLoop()

    def GameLoop(self):
        
        #   Defines the screen and time
        screen = pg.display.set_mode(SCREEN_RES)
        screen_name = pg.display.set_caption("Boids simulator!")
        clock = pg.time.Clock()
        
        #   Plays the game
        while 1:
            #   Makes the screen and clock
            pg.draw.rect(screen, BLACK, (0, 0, screen.get_width(), screen.get_height()))
            time_passed = clock.tick(100) # limit to 100FPS

            for event in pg.event.get():
                if event.type == pg.QUIT or (event.type == pg.KEYDOWN and event.key == pg.K_ESCAPE):
                    exit()
            
                #   Gives the coordinate of the mouse to make an object, based on what that is pushed
                x, y = pg.mouse.get_pos()
                if(event.type == pg.MOUSEBUTTONDOWN and event.button == 4):
                    self.flyer_list.new_boid(x, y)
                elif(event.type == pg.MOUSEBUTTONDOWN and event.button == 5):
                    self.flyer_list.new_hoik(x, y)
                elif(event.type == pg.MOUSEBUTTONDOWN and event.button == 1):
                    self.flyer_list.new_bait(x, y, active = 1)
                elif(event.type == pg.MOUSEBUTTONUP and event.button == 1):
                    self.flyer_list.new_bait(x, y, active = 0)
                if rand.randint(0,5) == 0 and pg.mouse.get_pressed()[2]:
                    self.flyer_list.new_obstacle(x, y)

            self.flyer_list.move_all()
            self.flyer_list.draw_all(screen)

            pg.display.update()
if __name__ == '__main__':
    game()