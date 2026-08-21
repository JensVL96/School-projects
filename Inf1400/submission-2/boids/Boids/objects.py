import pygame.math as vec
import random as rand
from config import *
from behaviour import Flyer
import pygame as pg

class Boid(Flyer):
    def __init__(self, x, y):
        self.pos = vec.Vector2(x, y)
        self.speVec = vec.Vector2(rand.randint(-1,1), rand.randint(-1,1))
        self.id = "boid"


class Hoik(Flyer):
    def __init__(self, x, y):
        self.pos = vec.Vector2(x, y)
        self.speVec = vec.Vector2(0, 0)
        self.num_boids_eaten = 0
        self.id = "hoik"
        self.size = HOIK_SIZE
        self.speed = HOIK_SPEED_RANGE[0]


class Obstacle():
    def __init__(self, x, y):
        self.pos = vec.Vector2(x, y)
        self.id = "obstacle"
        self.sprite_paths = [
            "Sprites/tree1.png",
            "Sprites/tree2.png",
            "Sprites/tree3.png",
            "Sprites/tree4.png",
            "Sprites/tree5.png",
            "Sprites/tree6.png",
        ]
        # Load sprite images and choose a random image index
        self.images = [pg.image.load(path) for path in self.sprite_paths]
        self.images = [pg.transform.scale(image, (OBSTACLE_RADIUS * 2, OBSTACLE_RADIUS * 2)) for image in self.images]
        self.image_index = rand.randint(0, len(self.images) - 1)

    def draw(self, screen):
        # pg.draw.circle(screen, GREEN, [self.pos.x, self.pos.y], OBSTACLE_RADIUS, LINE_THICKNESS)
        # Draw the obstacle on the screen using its current image
        if self.images is not None:
            screen.blit(self.images[self.image_index], self.pos)


class Bait():
    def __init__(self, x, y):
        self.pos = vec.Vector2(x, y)


class FlyerList():
    def __init__(self):
        self.boids = []
        self.hoiks = []
        self.obstacles = []
        self.baits = []

        # Initialize states for toggling circles around boids and hoiks
        self.boid_states = [False, False, False, False]
        self.hoik_states = [False, False, False]

    # Create a new boid at the given position if the limit is not reached
    def new_boid(self, x, y):
        if len(self.boids) < BOID_LIMIT:
            self.boids.append(Boid(x, y))

    # Create a new hoik at the given position if the limit is not reached
    def new_hoik(self, x, y):
        if len(self.hoiks) < HOIK_LIMIT:
            self.hoiks.append(Hoik(x, y))

    # Create a new obstacle at the given position
    def new_obstacle(self, x, y):
        self.obstacles.append(Obstacle(x, y))

    # Create a new bait at the given position if active is true
    def new_bait(self, x, y, active):
        self.bait = Bait(x, y)
        if active == 1:
            self.baits = [self.bait]
        else:
            self.baits = []

    # Move all boids and hoiks according to their behaviors
    def move_all(self):
        for boid in self.boids:
            boid.move_boid(self.boids, self.hoiks, self.obstacles, self.baits)
            boid.mirror_border()
        for hoik in self.hoiks:
            hoik.move_hoik(self.boids, self.hoiks, self.obstacles)
            hoik.mirror_border()

    # Draw all boids, hoiks, and obstacles on the screen
    def draw_all(self, screen):
        for boid in self.boids:
            boid.draw(WHITE, screen)
        for hoik in self.hoiks:
            hoik.draw(RED, screen)
        for obstacle in self.obstacles:
            obstacle.draw(screen)

    # Toggle circles around boids or hoiks based on the object type 
    def toggle_circles(self, screen, object_type):
        if object_type == 'boid':
            for boid in self.boids:
                boid.draw_circles(screen, self.boid_states, BOID_COLORS, BOID_RANGES)
        elif object_type == 'hoik':
            for hoik in self.hoiks:
                hoik.draw_circles(screen, self.hoik_states, HOIK_COLORS, HOIK_RANGES)

    # Update the position of the bait (to drag around)
    def update_bait(self, x, y):
        if self.bait:
            self.bait.pos = vec.Vector2(x,y)

    # Remove obstacles from the screen 
    def rm_obstacles(self):
        for obstacle in self.obstacles:
            obstacle.images = None

