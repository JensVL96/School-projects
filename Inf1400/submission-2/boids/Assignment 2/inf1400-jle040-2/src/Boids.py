from Resources import *
from config import *
import pygame as pg
import random as rand
import time

class Flyer():
    def draw(self, color, screen):
        #   Draws an arrow
        pg.draw.polygon(screen, color, ([self.pos.x, self.pos.y],
                                        [self.pos.x + self.speVec.x * EXTEND, self.pos.y + self.speVec.y * EXTEND],
                                        [self.pos.x + self.speVec.rotate(RIGHT_ANGLE).x * ANGLE_MULTIPLIER, self.pos.y + self.speVec.rotate(RIGHT_ANGLE).y * ANGLE_MULTIPLIER],
                                        [self.pos.x - self.speVec.rotate(LEFT_ANGLE).x * ANGLE_MULTIPLIER, self.pos.y - self.speVec.rotate(LEFT_ANGLE).y * ANGLE_MULTIPLIER],
                                        [self.pos.x + self.speVec.x * EXTEND, self.pos.y + self.speVec.y * EXTEND]), LINE_THICKNESS)

        #   The distances appointed by the rules
        #pg.draw.circle(screen, BLUE, [int(self.pos.x + self.speVec.x * 5), int(self.pos.y + self.speVec.y * 5)], OBS_DISTANCE, 2)
        #pg.draw.circle(screen, BLUE, [int(self.pos.x + self.speVec.x * 5), int(self.pos.y + self.speVec.y * 5)], HOIK_DISTANCE, 2)
        #pg.draw.circle(screen, BLUE, [int(self.pos.x + self.speVec.x * 5), int(self.pos.y + self.speVec.y * 5)], FAMILY_RADIUS, 2)

    #   Applies the vector changes with the rules to the boids
    def move_boid(self, boids, hoiks, obstacles, baits):
        rules = Rulebook(self.pos, self.speVec)
        family = self.family(boids)
        v1 = rules.centralize(family) - rules.centralize(hoiks)
        v2 = rules.collision(family) * 0.01 + rules.collision(obstacles)
        v3 = rules.match_speed(family)
        v4 = rules.eating(baits) * 0.5

        self.speVec += v1 + v2 + v3 + v4
        self.speVec = self.speVec.normalized()
        self.pos += self.speVec * 3

    #   Applies the vector changes with the rules to the hoiks
    def move_hoik(self, boids, hoiks, obstacles):
        rules = Rulebook(self.pos, self.speVec)
        remove = self.remove(boids)
        v2 = rules.collision(hoiks) * 0.05 + rules.collision(obstacles)
        v3 = rules.eating(remove)

        self.speVec += v2 + v3
        self.speVec = self.speVec.normalized()
        self.pos += self.speVec * 3.5

    #   Combines boids into groups when within a certain distance
    def family(self, boids):
        group = []
        group.append(self)
        for i in boids:
            hit = intersect_circles(self.pos, self.fam_rad, i.pos, i.fam_rad)
            if hit:
                i.speVec.normalized()
                group.append(i)
        return group

    #   Removes the boids once hit by the hoiks
    def remove(self, boids):
        for i in boids:
            hit = intersect_circles(self.pos, self.eat_rad, i.pos, i.hoik_rad)
            if hit:
                boids.remove(i)
        return boids

    #   Creates an positional loop so the flyers stay in the screen
    def mirror_border(self):
        if (self.pos.x >= SCREEN_WIDTH + EXTEND):
            self.pos.x = -EXTEND
        elif(self.pos.x <= -EXTEND):
            self.pos.x = SCREEN_WIDTH + EXTEND
        elif (self.pos.y >= SCREEN_HEIGHT + EXTEND):
            self.pos.y = -EXTEND
        elif(self.pos.y <= -EXTEND):
            self.pos.y = SCREEN_HEIGHT + EXTEND



class Boids(Flyer):
    def __init__(self, x, y):
        self.pos = Vector2D(x, y)
        self.speVec = Vector2D(rand.randint(-1,1), rand.randint(-1,1))
        self.hoik_rad = REMOVE_DISTANCE
        self.fam_rad = FAMILY_RADIUS

class Hoiks(Flyer):
    def __init__(self, x, y):
        self.pos = Vector2D(x, y)
        self.speVec = Vector2D(0, 0)
        self.eat_rad = EAT_DISTANCE

class Obstacles():
    def __init__(self, x, y):
        self.pos = Vector2D(x, y)
        self.radius = OBSTACLE_RADIUS

    def draw(self, color, screen):
        pg.draw.circle(screen, color, [self.pos.x, self.pos.y], self.radius, LINE_THICKNESS)

class Bait():
    def __init__(self, x, y):
        self.pos = Vector2D(x, y)



class Rulebook():
    def __init__(self, pos, speVec):
        self.pos = pos
        self.speVec = speVec

    #   Makes the flyers move towards the groups central position
    def centralize(self, flyers):
        vector = Vector2D(0,0)

        for flyer in flyers:
            if flyer is not self:
                if abs(flyer.pos - self.pos) < HOIK_DISTANCE * 2:
                    vector += flyer.pos - self.pos

        if(len(flyers) == 1):
            vector /= len(flyers)
        else:
            vector /= len(flyers) - 1
        return (vector) / 200

    #   Makes the flyers avoid obstacles and eachother
    def collision(self, flyers):
        vector = Vector2D(0,0)

        for flyer in flyers:
            if flyer is not self:
                diff = (flyer.pos - self.pos)
                size = OBS_DISTANCE - abs(diff)
                if size > 0:
                    vector -= diff * size - self.speVec
        return vector / 2

    #   Makes the flyers match the direction of their group 
    def match_speed(self, flyers):  
        vector = Vector2D(0,0)

        for flyer in flyers:
            if flyer is not self:
                vector += flyer.speVec

        if(len(flyers) == 1):
            vector /= len(flyers)
        else:
            vector /= len(flyers) - 1
        return (vector - self.speVec) / 5

    # Makes the flyers prioritize geting to the position given
    def eating(self, targets):
        vector = Vector2D(0,0)

        if(len(targets) > 0):
            min_1 = targets[0]
            for target in targets:
                if abs(target.pos - self.pos) < abs(min_1.pos - self.pos):
                    min_1 = target
            vector += min_1.pos - self.pos
        return vector / 100



class FlyerList():
    def __init__(self):
        self.boids = []
        self.hoiks = []
        self.obstacles = []
        self.baits = []

    def new_boid(self, x, y):
        boid = Boids(x, y)
        try:
            if len(self.boids) < 20:
                self.boids.append(boid)
            else:
                raise Exception("Too many boids")
        except Exception:
            pass

    def new_hoik(self, x, y):
        hoik = Hoiks(x, y)
        try:
            if len(self.hoiks) < 10:
                self.hoiks.append(hoik)
            else:
                raise Exception("Too many hoiks")
        except Exception:
            pass



    def new_obstacle(self, x, y):
        obstacle = Obstacles(x, y)
        self.obstacles.append(obstacle)

    def new_bait(self, x, y, active):
        if active == 1:
            self.bait = Bait(x, y)
            self.baits.append(self.bait)
        else:
            self.baits.clear()

    def move_all(self):
        for boid in self.boids:
            boid.move_boid(self.boids, self.hoiks, self.obstacles, self.baits)
            boid.mirror_border()
        for hoik in self.hoiks:
            hoik.move_hoik(self.boids, self.hoiks, self.obstacles)
            hoik.mirror_border()

    def draw_all(self, screen):
        for boid in self.boids:
            boid.draw(WHITE, screen)
        for hoik in self.hoiks:
            hoik.draw(RED, screen)
        for obstacle in self.obstacles:
            obstacle.draw(GREEN, screen)



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