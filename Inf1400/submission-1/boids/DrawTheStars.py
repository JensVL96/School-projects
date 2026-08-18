from abc import ABC, abstractmethod
from Resources import *
import pygame as pg
import random as rand
import time

class Flyer():
    def __init__(self, x, y, speed, speVec, screen):
        self.pos = Vector2D(x, y)
        self.speVec = speVec
        self.screen = screen
        self.speed = speed

    #@abstractmethod
    def draw(self):
        pg.draw.polygon(self.screen, (255,255,255), ([self.pos.x, self.pos.y],
                                                    [self.pos.x + self.speVec.x * 10, self.pos.y + self.speVec.y * 10],
                                                    [self.pos.x + self.speVec.rotate(90).x * 3, self.pos.y + self.speVec.rotate(90).y * 3],
                                                    [self.pos.x - self.speVec.rotate(90).x * 3, self.pos.y - self.speVec.rotate(90).y * 3],
                                                    [self.pos.x + self.speVec.x * 10, self.pos.y + self.speVec.y * 10]), 2)

    #@abstractmethod
    def move(self):
        if(rand.randint(0, 3) == 0):
            if(self.speed.x < 3) and (self.speed.y < 3) and (self.speed.x > -3) and (self.speed.y > -3):
                self.speed = Vector2D(rand.randint(-1,1) / 2,rand.randint(-1,1) / 2)
                #print("ideal speed")
            elif(self.speed.x > 3) and (self.speed.y > 3):
                self.speed.x -= self.speed.x / 2
                self.speed.y -= self.speed.y / 2
                #print("high speed")
            else:
                self.speed.x += self.speed.x / 2
                self.speed.y += self.speed.y / 2
                #print("low speed")

                
        if(rand.randint(0, 10) == 0):
            if(self.speVec.x < 3) and (self.speVec.y < 3) and (self.speVec.x > -3) and (self.speVec.y > -3):
                self.speVec.x += self.speed.x / 5
                self.speVec.y += self.speed.y / 5
            elif(self.speVec.x > 3) and (self.speVec.y > 3):
                self.speVec.x -= self.speVec.x / 5
                self.speVec.y -= self.speVec.y / 5
            else:
                self.speVec.x += self.speed.x / 5
                self.speVec.y += self.speed.y / 5

        #print(self.pos.x)
        #print(self.pos.y)
        self.pos.x += self.speVec.x
        self.pos.y += self.speVec.y
        #print(self.pos.x)
        #print(self.pos.y)
        #print(self.speVec.x)
        #print(self.speVec.y)
        #print(self.speed.x)
        #print(self.speed.y)

        return self.pos

class Boids(Flyer):
    def __init__(self, x, y, screen):
        self.pos = Vector2D(x, y)
        self.speVec = Vector2D(0, 0)
        self.screen = screen
        self.speed = Vector2D(1, 1)

    def mirror_border(self):
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

class FLyerList(Flyer):
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

        flyer_list = []

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
            boids.mirror_border()
            
            boids.move()
            #print("loop", boids.pos.x)
            #print("loop", boids.pos.y)
            if(event.type == pg.MOUSEBUTTONDOWN):
                x, y = pg.mouse.get_pos()
                boid = Boids(x, y, screen)
                flyer_list.append(boid)

            for boid in flyer_list:
                boid.draw()
                

            pg.display.update()

if __name__ == '__main__':
    game()