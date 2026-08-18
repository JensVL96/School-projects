from abc import ABC, abstractmethod
from Resources import *
import pygame as pg
import random as rand
import time

class Flyer(ABC):
    @abstractmethod
    def draw(self):
        pass

    @abstractmethod
    def move(self, speed, speVec):
        pass

class Boids(ABC):
    def __init__(self, x, y, screen):
        self.pos = Vector2D(x, y)
        self.speVec = Vector2D(0, 0)
        self.screen = screen
        self.speed = Vector2D(1, 1)

    def move(self):
        if(rand.randint(0, 3) == 0):
            if(self.speed.x < 3) and (self.speed.y < 3) and (self.speed.x > -3) and (self.speed.y > -3):
                self.speed = Vector2D(rand.randint(-1,1) / 2,rand.randint(-1,1) / 2)
            elif(self.speed.x > 3) and (self.speed.y > 3):
                self.speed.x -= self.speed.x / 2
                self.speed.y -= self.speed.y / 2
            else:
                self.speed.x += 1
                self.speed.y += 1
                
        if(rand.randint(0, 10) == 0):
            if(self.speVec.x < 3) and (self.speVec.y < 3) and (self.speVec.x > -3) and (self.speVec.y > -3):
                self.speVec.x += self.speed.x / 5
                self.speVec.y += self.speed.y / 5
            elif(self.speVec.x > 3) and (self.speVec.y > 3):
                self.speVec.x -= self.speVec.x / 5
                self.speVec.y -= self.speVec.y / 5
            else:
                self.speVec.x += 1
                self.speVec.y += 1

        self.pos.x += self.speVec.x
        self.pos.y += self.speVec.y

    def draw(self):
        pg.draw.polygon(self.screen, (255,255,255), ([self.pos.x, self.pos.y],
                                                    [self.pos.x + self.speVec.x * 10, self.pos.y + self.speVec.y * 10],
                                                    [self.pos.x + self.speVec.rotate(90).x * 3, self.pos.y + self.speVec.rotate(90).y * 3],
                                                    [self.pos.x - self.speVec.rotate(90).x * 3, self.pos.y - self.speVec.rotate(90).y * 3],
                                                    [self.pos.x + self.speVec.x * 10, self.pos.y + self.speVec.y * 10]), 2)

    def border(self):
        if (self.pos.x >= 1620):
            self.pos.x = -20
        elif(self.pos.x <= -20):
            self.pos.x = 1620
        if (self.pos.y >= 900):
            self.pos.y = -20
        elif(self.pos.y <= -20):
            self.pos.y = 900

class Hoiks(Flyer):
    def __init__(self):
        pass

    def move(self):
        pass

    def draw(self, color):
        pass

class FLyerList():
    def __init__(self):
        self.flyers = []

    def move_all(self):
        for flyer in self.flyers:
            flyer.move()

    def draw_all(self):
        for flyer in self.flyers:
            flyer.draw()

class game():
    def __init__(self):
        pygame.init()
        self.flyer_list = FLyerList()
        ''' self.check_input = check_input
        self.check_collision = check_collision '''
        self.GameLoop()

    def GameLoop(self):
        #Screen data
        screen_res = (1600, 900)
        pg.init()

        screen = pg.display.set_mode(screen_res)
        screen_name = pg.display.set_caption("Boids simulator!")
        clock = pg.time.Clock()

        boids = Boids((screen.get_width() / 2), (screen.get_height() / 2), screen)

        active = 1

        while 1:
            self.flyer_list.move_all()
            self.flyer_list.draw_all()
            ''' self.check_input()
            self.check_collision() '''

            #Make screen
            pg.draw.rect(screen, (0,0,0), (0, 0, screen.get_width(), screen.get_height()))
            time_passed = clock.tick(100) # limit to 100FPS
            time_passed_seconds = time_passed / 1000.0   # convert to seconds

            #Plays the game
            for event in pg.event.get():
                if event.type == pg.QUIT or (event.type == pg.KEYDOWN and event.key == pg.K_ESCAPE):
                    exit()

            boids.draw()
            boids.border()

            boids.move()

            pg.display.update()

if __name__ == '__main__':
    game()