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
    def __init__(self, x, y, speVec):
        self.pos = Vector2D(x, y)
        self.speVec = speVec

    #@abstractmethod
    def draw(self, color, screen):
        pg.draw.polygon(screen, color, ([self.pos.x, self.pos.y],
                                                    [self.pos.x + self.speVec.x * 10, self.pos.y + self.speVec.y * 10],
                                                    [self.pos.x + self.speVec.rotate(90).x * 3, self.pos.y + self.speVec.rotate(90).y * 3],
                                                    [self.pos.x - self.speVec.rotate(90).x * 3, self.pos.y - self.speVec.rotate(90).y * 3],
                                                    [self.pos.x + self.speVec.x * 10, self.pos.y + self.speVec.y * 10]), 2)

        pg.draw.circle(screen, color, [int(self.pos.x + self.speVec.x * 5), int(self.pos.y + self.speVec.y * 5)], 20, 2)
        pg.draw.circle(screen, color, [int(self.pos.x + self.speVec.x * 5), int(self.pos.y + self.speVec.y * 5)], 200, 2)
        pg.draw.circle(screen, color, [int(self.pos.x + self.speVec.x * 5), int(self.pos.y + self.speVec.y * 5)], 50, 2)


    #@abstractmethod
    def move_boid(self, boids, hoiks, obstacles):
        v1 = self.centralize(boids) * 5 - self.centralize(hoiks)
        v2 = self.bumping(boids) + self.bumping(obstacles)
        v3 = self.match_speed(boids)

        self.speVec += v1 + v2 + v3
        game.speed_limit(self)
        self.pos += self.speVec / 5

    def move_hoik(self, boids, hoiks, obstacles):
        v1 = self.centralize(boids)
        v2 = self.bumping(boids) + self.bumping(obstacles) * 10

        self.speVec += v1 + v2
        game.speed_limit(self)
        self.pos += self.speVec / 5

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
    def __init__(self, x, y):
        self.pos = Vector2D(x, y)
        self.speVec = Vector2D(0, 0)

    def centralize(self, boids):
        vector = Vector2D(0,0)

        for boid in boids:
            if boid is not self:
                vector += boid.pos

        if(len(boids) == 0):
            pass
        elif(len(boids) == 1):
            vector /= len(boids)
        else:
            vector /= len(boids) - 1

        return (vector - self.pos)

    def bumping(self, boids):
        vector = Vector2D(0,0)

        for boid in boids:
            if boid is not self:
                if (self.pos - boid.pos).mag() < 20:
                    vector -= (boid.pos - self.pos)
        return vector

    def match_speed(self, boids):
        vector = Vector2D(0,0)

        for boid in boids:
            if boid is not self:
                vector += boid.speVec
        
        if(len(boids) == 0):
            pass
        elif(len(boids) == 1):
            vector /= len(boids)
        else:
            vector /= len(boids) - 1

        return (vector - self.speVec)

class Hoiks(Flyer):
    def __init__(self, x, y):
        self.pos = Vector2D(x, y)
        self.speVec = Vector2D(0, 0)

    def centralize(self, boids):
        vector = Vector2D(0,0)

        for boid in boids:
            #if boid is not self:
            vector += boid.pos

        if(len(boids) == 0):
            pass
        elif(len(boids) == 1):
            vector /= len(boids)
        else:
            vector /= len(boids) - 1

        return (vector - self.pos) / 2

    def bumping(self, hoiks):
        vector = Vector2D(0,0)

        for hoik in hoiks:
            if hoik is not self:
                if (self.pos - hoik.pos).mag() < 25:
                    vector -= (boid.pos - self.pos)
        return vector

class Obstacles():
    def __init__(self, x, y, radius):
        self.pos = Vector2D(x, y)
        self.radius = radius

    def draw(self, color, screen):
        pg.draw.circle(screen, color, [self.pos.x, self.pos.y], self.radius, 2)

class FlyerList(Flyer):
    def __init__(self):
        self.boids = []
        self.hoiks = []
        self.obstacles = []
        self.pos_x_list = []
        self.pos_y_list = []

    def new_boid(self, x, y):
        boid = Boids(x, y)
        self.boids.append(boid)

    def new_hoik(self, x, y):
        hoik = Hoiks(x, y)
        self.hoiks.append(hoik)

    def new_obstacle(self, x, y, radius):
        obstacle = Obstacles(x, y, radius)
        self.obstacles.append(obstacle)

    def move_all(self):
        for boid in self.boids:
            boid.move_boid(self.boids, self.hoiks, self.obstacles)
            boid.mirror_border()
        for hoik in self.hoiks:
            hoik.move_hoik(self.boids, self.hoiks, self.obstacles)
            hoik.mirror_border()
            

    def draw_all(self, screen):
        for boid in self.boids:
            boid.draw(white, screen)
        for hoik in self.hoiks:
            hoik.draw(red, screen)
        for obstacle in self.obstacles:
            obstacle.draw(green, screen)

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
                    self.flyer_list.new_boid(x, y)
                elif(event.type == pg.MOUSEBUTTONDOWN and event.button == 5):
                    self.flyer_list.new_hoik(x, y)
                elif(event.type == pg.MOUSEBUTTONUP and event.button == 3):
                    radius += number
                    self.flyer_list.new_obstacle(x, y, radius)
                elif(pg.mouse.get_pressed()[0] == True):
                    number += 10

            self.flyer_list.move_all()
            self.flyer_list.draw_all(screen)

            pg.display.update()

    def speed_limit(boid):
        # Limit boid speed.
        if boid.speVec.mag() > 5:
            boid.speVec /= boid.speVec.mag() / 3

if __name__ == '__main__':
    game()