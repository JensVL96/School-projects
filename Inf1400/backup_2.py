from abc import ABC, abstractmethod
from Resources import *
from statistics import mean
import pygame as pg
import random as rand
import time

white = [255,255,255]
red = [255,0,0]
green = [0,255,0]
blue = [0,0,255]

class Flyer():
    def __init__(self, x, y, speed, speVec):
        self.pos = Vector2D(x, y)
        self.speVec = speVec
        self.speed = speed

    #@abstractmethod
    def draw(self, color):
        pg.draw.polygon(self.screen, color, ([self.pos.x, self.pos.y],
                                                    [self.pos.x + self.speVec.x * 10, self.pos.y + self.speVec.y * 10],
                                                    [self.pos.x + self.speVec.rotate(90).x * 3, self.pos.y + self.speVec.rotate(90).y * 3],
                                                    [self.pos.x - self.speVec.rotate(90).x * 3, self.pos.y - self.speVec.rotate(90).y * 3],
                                                    [self.pos.x + self.speVec.x * 10, self.pos.y + self.speVec.y * 10]), 2)

    #@abstractmethod
    def move(self, c_x, c_y):
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

        #new_vector = Rule_book(self.pos.x, self.pos.y, c_x, c_y, self.speVec)

        self.pos.x += self.speVec.x
        self.pos.y += self.speVec.y

    def mirror_border(self):
        if (self.pos.x >= 1620):
            self.pos.x = -20
        elif(self.pos.x <= -20):
            self.pos.x = 1620
        elif (self.pos.y >= 900):
            self.pos.y = -20
        elif(self.pos.y <= -20):
            self.pos.y = 900

class Boids(Flyer):
    def __init__(self, x, y, screen):
        self.pos = Vector2D(x, y)
        self.speVec = Vector2D(0, 0)
        self.screen = screen
        self.speed = Vector2D(2, 2)

class Hoiks(Flyer):
    def __init__(self, x, y, screen):
        self.pos = Vector2D(x, y)
        self.speVec = Vector2D(0, 0)
        self.screen = screen
        self.speed = Vector2D(2, 2)

class Obstacles():
    def __init__(self, x, y, radius, screen):
        self.pos = Vector2D(x, y)
        self.radius = radius
        self.screen = screen

    def draw(self, color):
        pg.draw.circle(self.screen, color, [self.pos.x, self.pos.y], self.radius, 2)

class Rule_book():
    def __init__(self, x, y, c_x, c_y, speVec):
        self.pos = Vector2D(x, y)
        self.flyer = Vector2D(c_x, c_y)
        self.speVec = speVec

        v1 = self.centralize()
        v2 = self.bumping()
        v3 = self.match_speed()

        self.speVec += v1 + v2 + v3
        self.pos += self.speVec / 100

    def centralize(self):
        vector = Vector2D(0,0)

        if self.flyer is not self:
            vector += self.flyer

        return (vector - self.pos) / 7.5

    def bumping(self):
        vector = Vector2D(0,0)

        if self.flyer is not self:
            if (self.pos.x - self.flyer.x) < 25:
                vector.x -= (self.flyer.x - self.pos.x)
            elif (self.pos.y - self.flyer.y) < 25:
                vector.y -= (self.flyer.y - self.pos.y)

        return vector

    def match_speed(self):
        vector = Vector2D(0,0)

        if self.flyer is not self:
            vector += self.speVec

        return (vector - self.speVec) / 2

class FlyerList(Flyer):
    def __init__(self):
        self.boids = []
        self.hoiks = []
        self.obstacles = []
        self.pos_x_list = []
        self.pos_y_list = []

    def new_boid(self, x, y, screen):
        boid = Boids(x, y, screen)
        self.boids.append(boid)

        self.pos_x_list.append(x)
        self.pos_y_list.append(y)

        if(len(self.pos_x_list) == 1):
            self.center_x = mean(self.pos_x_list)
            self.center_y = mean(self.pos_y_list)
        else:
            self.center_x = mean(self.pos_x_list) - 1
            self.center_y = mean(self.pos_y_list) - 1

    def new_hoik(self, x, y, screen):
        hoik = Hoiks(x, y, screen)
        self.hoiks.append(hoik)

    def new_obstacle(self, x, y, radius, screen):
        obstacle = Obstacles(x, y, radius, screen)
        self.obstacles.append(obstacle)

    def move_all(self):
        for boids in self.boids:
            boids.move(self.center_x, self.center_y)
            boids.mirror_border()
        for hoiks in self.hoiks:
            hoiks.move(self.center_x, self.center_y)
            hoiks.mirror_border()
            

    def draw_all(self):
        for boid in self.boids:
            boid.draw(white)
        for hoik in self.hoiks:
            hoik.draw(red)
        for obstacle in self.obstacles:
            obstacle.draw(green)

class Pictures():
    def __init__(self):
        self.img_path = {'area' : "img_res/arwin.png"} # Stores image paths

    def imageLoader(self, screen, name, alignment, size, offset=(0,0)):
        if alignment == "screen ":
            self.pos = Align.center(size, offset[0], offset[1])

        self.img_load = pg.image.load(self.img_path[name]) # Fetch image
        self.img_trans = pg.transform.scale(self.img_load, size) # Transform image
        screen.blit(self.img_trans, self.pos) # Displays image

class game():
    def __init__(self):
        pygame.init()
        self.flyer_list = FlyerList()
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
        
        number = 1
        radius = 10

        while 1:
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
            
                x, y = pg.mouse.get_pos()
                if(event.type == pg.MOUSEBUTTONDOWN and event.button == 4):
                    self.flyer_list.new_boid(x, y, screen)
                elif(event.type == pg.MOUSEBUTTONDOWN and event.button == 5):
                    self.flyer_list.new_hoik(x, y, screen)
                elif(event.type == pg.MOUSEBUTTONUP and event.button == 3):
                    radius += number
                    self.flyer_list.new_obstacle(x, y, radius, screen)
                elif(pg.mouse.get_pressed()[0] == True):
                    number += 10

            self.flyer_list.move_all()
            self.flyer_list.draw_all()

            pg.display.update()

if __name__ == '__main__':
    game()