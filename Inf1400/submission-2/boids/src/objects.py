import pygame.math as vec
import random as rand
from config import *
from behaviour import Flyer
import pygame as pg

class Boids(Flyer):
    def __init__(self, x, y):
        self.pos = vec.Vector2(x, y)
        self.speVec = vec.Vector2(rand.randint(-1,1), rand.randint(-1,1))
        self.id = "boid"

        # static
        # self.size = BOID_SIZE  # Initial size of the boid
        # self.hoik_rad = EATEN_RANGE
        # self.fam_rad = FAMILY_RADIUS
        # self.max_speed = BOID_SPEED_RANGE[0]  # Maximum speed of the boid
        # self.min_speed = BOID_SPEED_RANGE[1]  # Minimum speed of the boid

class Hoiks(Flyer):
    def __init__(self, x, y):
        self.pos = vec.Vector2(x, y)
        self.speVec = vec.Vector2(0, 0)
        self.num_boids_eaten = 0
        self.id = "hoik"
        self.size = HOIK_SIZE
        self.speed = 1

        # static
        # self.eat_rad = EAT_RANGE
        # self.prey_range = PREY_DETECTION_RANGE
        # self.hunt_range = COMMUNICATION_RANGE

class Obstacles():
    def __init__(self, x, y):
        self.pos = vec.Vector2(x, y)
        self.id = "obstacle"

        # static
        # self.radius = OBSTACLE_RADIUS

    def draw(self, color, screen):
        pg.draw.circle(screen, color, [self.pos.x, self.pos.y], OBSTACLE_RADIUS, LINE_THICKNESS)

    # # If obstacle collides with another obstacles
    # def collides_with(self, other_obstacle):
    #     distance_squared = (self.pos - other_obstacle.pos).length_squared()
    #     combined_radius = self.radius + other_obstacle.radius
    #     return distance_squared <= combined_radius ** 2

class Bait():
    def __init__(self, x, y):
        self.pos = vec.Vector2(x, y)

class FlyerList():
    def __init__(self):
        self.boids = []
        self.hoiks = []
        self.obstacles = []
        self.baits = []

    def new_boid(self, x, y):
        # print("new boid")
        boid = Boids(x, y)
        try:
            if len(self.boids) < BOID_LIMIT:
                self.boids.append(boid)
            else:
                raise Exception("Too many boids")
        except Exception:
            pass

    def new_hoik(self, x, y):
        # print("new hoik")
        hoik = Hoiks(x, y)
        try:
            if len(self.hoiks) < HOIK_LIMIT:
                self.hoiks.append(hoik)
            else:
                raise Exception("Too many hoiks")
        except Exception:
            pass

    def new_obstacle(self, x, y):
        # print("new obstacle")
        obstacle = Obstacles(x, y)
        # for existing_obstacle in self.obstacles:
        #     if obstacle.collides_with(existing_obstacle):
        #         # don't place until far enough away
        #         existing_obstacle.radius = max(existing_obstacle.radius, obstacle.radius)
        #         existing_obstacle.pos = (existing_obstacle.pos + obstacle.pos) / 2
        #         return
        self.obstacles.append(obstacle)

    def new_bait(self, x, y, active):
        # print("new bait")
        if active == 1:
            self.bait = Bait(x, y)
            self.baits.append(self.bait)
        else:
            self.baits.clear()

    def move_all(self):
        # print(f"moving {len(self.boids) + len(self.hoiks)} amount of objects")
        for boid in self.boids:
            boid.move_boid(self.boids, self.hoiks, self.obstacles, self.baits)
            boid.mirror_border()
        for hoik in self.hoiks:
            hoik.move_hoik(self.boids, self.hoiks, self.obstacles)
            hoik.mirror_border()

    def draw_all(self, screen):
        # print(f"drawing {len(self.boids) + len(self.hoiks) + len(self.obstacles)} amount of objects")
        for boid in self.boids:
            boid.draw(WHITE, screen)
        for hoik in self.hoiks:
            hoik.draw(RED, screen)
        for obstacle in self.obstacles:
            obstacle.draw(GREEN, screen)

