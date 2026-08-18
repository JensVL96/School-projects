from Resources import *
from config import *
import pygame as pg
import random as rand
import time

class Flyer():
    def __init__(self, x, y, speVec):
        self.pos = Vector2D(x, y)
        self.speVec = speVec

    def draw(self, color, screen):
        pg.draw.polygon(screen, color, ([self.pos.x, self.pos.y],
                                        [self.pos.x + self.speVec.x * EXTEND, self.pos.y + self.speVec.y * EXTEND],
                                        [self.pos.x + self.speVec.rotate(RIGHT_ANGLE).x * ANGLE_MULTIPLIER, self.pos.y + self.speVec.rotate(RIGHT_ANGLE).y * ANGLE_MULTIPLIER],
                                        [self.pos.x - self.speVec.rotate(LEFT_ANGLE).x * ANGLE_MULTIPLIER, self.pos.y - self.speVec.rotate(LEFT_ANGLE).y * ANGLE_MULTIPLIER],
                                        [self.pos.x + self.speVec.x * EXTEND, self.pos.y + self.speVec.y * EXTEND]), LINE_THICKNESS)

        pg.draw.circle(screen, BLUE, [int(self.pos.x + self.speVec.x * 5), int(self.pos.y + self.speVec.y * 5)], OBS_DISTANCE, 2)
        pg.draw.circle(screen, BLUE, [int(self.pos.x + self.speVec.x * 5), int(self.pos.y + self.speVec.y * 5)], HOIK_DISTANCE, 2)
        pg.draw.circle(screen, BLUE, [int(self.pos.x + self.speVec.x * 5), int(self.pos.y + self.speVec.y * 5)], FAMILY_RADIUS, 2)

    def move_boid(self, boids, hoiks, obstacles, baits):
        rules = Rulebook(self.pos, self.speVec)
        family = self.family(boids)
        v1 = rules.centralize(family) - rules.centralize(hoiks)
        v2 = rules.collision(family) * 0.01 + rules.collision(obstacles)
        v3 = rules.match_speed(family)
        v4 = rules.eating(baits) * 50

        self.oldVec = self.speVec
        self.speVec += v1 + v2 + v3 + v4
        self.speVec = self.speVec.normalized()
        self.pos += self.speVec * 3

    def move_hoik(self, boids, hoiks, obstacles):
        rules = Rulebook(self.pos, self.speVec)
        remove = self.remove(boids)
        v2 = rules.collision(hoiks) + rules.collision(obstacles)
        v3 = rules.eating(remove)

        self.speVec += v2 + v3
        self.speVec = self.speVec.normalized()
        self.pos += self.speVec * 3.5

    def family(self, boids):
        group = []
        group.append(self)
        for i in boids:
            hit = intersect_circles(self.pos, self.fam_rad, i.pos, i.fam_rad)
            if hit:
                i.speVec.normalized()
                group.append(i)
        return group

    def remove(self, boids):
        for i in boids:
            hit = intersect_circles(self.pos, self.eat_rad, i.pos, i.hoik_rad)
            if hit:
                boids.remove(i)
        return boids

    def mirror_border(self):
        if (self.pos.x >= SCREEN_WIDTH + 20):
            self.pos.x = -20
        elif(self.pos.x <= -20):
            self.pos.x = SCREEN_WIDTH + 20
        elif (self.pos.y >= SCREEN_HEIGHT + 20):
            self.pos.y = -20
        elif(self.pos.y <= -20):
            self.pos.y = SCREEN_HEIGHT + 20

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
    def __init__(self, x, y, radius):
        self.pos = Vector2D(x, y)
        self.radius = radius

    def draw(self, color, screen):
        pg.draw.circle(screen, color, [self.pos.x, self.pos.y], self.radius, LINE_THICKNESS)

class Bait():
    def __init__(self, x, y):
        self.pos = Vector2D(x, y)

class Rulebook(Flyer):
    def __init__(self, pos, speVec):
        self.pos = pos
        self.speVec = speVec

    def centralize(self, flyers):
        vector = Vector2D(0,0)

        for flyer in flyers:
            if flyer is not self:
                if abs(flyer.pos - self.pos) < HOIK_DISTANCE:
                    vector += flyer.pos - self.pos

        if(len(flyers) == 1):
            vector /= len(flyers)
        else:
            vector /= len(flyers) - 1
        return (vector) / 100

    def collision(self, flyers):
        vector = Vector2D(0,0)

        for flyer in flyers:
            if flyer is not self:
                diff = (flyer.pos - self.pos)
                size = OBS_DISTANCE - abs(diff)
                if size > 0:
                    vector -= diff * size - self.speVec
        return vector / 10

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

    def eating(self, targets):
        vector = Vector2D(0,0)

        if(len(targets) > 0):
            min_1 = targets[0]
            for target in targets:
                if abs(target.pos - self.pos) < abs(min_1.pos - self.pos):
                    min_1 = target
            vector += min_1.pos - self.pos
        return vector / 100

class FlyerList(Flyer):
    def __init__(self):
        self.boids = []
        self.hoiks = []
        self.obstacles = []
        self.baits = []

    def new_boid(self, x, y):
        boid = Boids(x, y)
        self.boids.append(boid)

    def new_hoik(self, x, y):
        hoik = Hoiks(x, y)
        self.hoiks.append(hoik)

    def new_obstacle(self, x, y, radius):
        obstacle = Obstacles(x, y, radius)
        self.obstacles.append(obstacle)

    def new_bait(self, x, y):
        if len(self.baits) == 0:
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
            boid.draw(WHITE , screen)
        for hoik in self.hoiks:
            hoik.draw(RED, screen)
        for obstacle in self.obstacles:
            obstacle.draw(GREEN, screen)

class game():
    def __init__(self):
        pygame.init()
        self.flyer_list = FlyerList()
        self.GameLoop()

    def GameLoop(self):
        pg.init()

        screen = pg.display.set_mode(SCREEN_RES)
        screen_name = pg.display.set_caption("Boids simulator!")
        clock = pg.time.Clock()
        
        #Plays the game
        while 1:
            #Make screen
            pg.draw.rect(screen, BLACK, (0, 0, screen.get_width(), screen.get_height()))
            time_passed = clock.tick(100) # limit to 100FPS
            time_passed_seconds = time_passed / 1000.0   # convert to seconds

            for event in pg.event.get():
                if event.type == pg.QUIT or (event.type == pg.KEYDOWN and event.key == pg.K_ESCAPE):
                    exit()
            
                x, y = pg.mouse.get_pos()
                if(event.type == pg.MOUSEBUTTONDOWN and event.button == 4):
                    self.flyer_list.new_boid(x, y)
                elif(event.type == pg.MOUSEBUTTONDOWN and event.button == 5):
                    self.flyer_list.new_hoik(x, y)
                elif(pg.mouse.get_pressed()[2]):
                    self.flyer_list.new_obstacle(x, y, OBSTACLE_RADIUS)
                elif(pg.mouse.get_pressed()[0]):
                    self.flyer_list.new_bait(x, y)

            self.flyer_list.move_all()
            self.flyer_list.draw_all(screen)

            pg.display.update()
if __name__ == '__main__':
    game()