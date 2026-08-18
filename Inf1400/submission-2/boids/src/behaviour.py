from config import *
from rules import Rulebook
import pygame as pg
import pygame.math as vec
from resources import intersect_circles

class Flyer():
    def draw(self, color, screen):
        #   Draws the objects
        pg.draw.polygon(screen, color, ([self.pos.x, self.pos.y],
                                        [self.pos.x + self.speVec.x * EXTEND, self.pos.y + self.speVec.y * EXTEND],
                                        [self.pos.x + self.speVec.rotate(RIGHT_ANGLE).x * ANGLE_MULTIPLIER, self.pos.y + self.speVec.rotate(RIGHT_ANGLE).y * ANGLE_MULTIPLIER],
                                        [self.pos.x - self.speVec.rotate(LEFT_ANGLE).x * ANGLE_MULTIPLIER, self.pos.y - self.speVec.rotate(LEFT_ANGLE).y * ANGLE_MULTIPLIER],
                                        [self.pos.x + self.speVec.x * EXTEND, self.pos.y + self.speVec.y * EXTEND]), LINE_THICKNESS)
        
        # boid ranges
        if self.id == "boid":
            #   The distances appointed by the rules
            pg.draw.circle(screen, BLUE, [int(self.pos.x + self.speVec.x * 5), int(self.pos.y + self.speVec.y * 5)], FAMILY_RADIUS, 2)
            pg.draw.circle(screen, RED, [int(self.pos.x + self.speVec.x * 5), int(self.pos.y + self.speVec.y * 5)], HOIK_DETECTION_RANGE, 2)
            pg.draw.circle(screen, YELLOW, [int(self.pos.x + self.speVec.x * 5), int(self.pos.y + self.speVec.y * 5)], FOOD_DETECTION_RANGE, 2)
            pg.draw.circle(screen, GREEN, [int(self.pos.x + self.speVec.x * 5), int(self.pos.y + self.speVec.y * 5)], AVOIDANCE_RADIUS, 2)
        # hoik ranges
        if self.id == "hoik":
            #   The distances appointed by the rules
            pg.draw.circle(screen, BLUE, [int(self.pos.x + self.speVec.x * 5), int(self.pos.y + self.speVec.y * 5)], COMMUNICATION_RANGE, 2)
            pg.draw.circle(screen, RED, [int(self.pos.x + self.speVec.x * 5), int(self.pos.y + self.speVec.y * 5)], FOOD_DETECTION_RANGE, 2)
            pg.draw.circle(screen, GREEN, [int(self.pos.x + self.speVec.x * 5), int(self.pos.y + self.speVec.y * 5)], AVOIDANCE_RADIUS, 2)

    def move_boid(self, boids, hoiks, obstacles, baits):
        rules = Rulebook(self.pos, self.speVec)
        family = self.family(boids)

        # Check if there are nearby hoiks within the escape range
        if self.check_nearby(hoiks, HOIK_DETECTION_RANGE):
            self.flee(family)
            v1 = rules.evade(hoiks)
            v2 = v3 = vec.Vector2(0, 0)
        else:
            v1 = rules.centralize(family)
            v2 = rules.match_speed(family)
            v3 = rules.eating(baits)
        v4 = rules.collision(family) + rules.collision(obstacles)

        # Update velocity vector
        self.speVec += v1 + v2 + v3 + v4
        self.speVec = self.speVec.normalize() if self.speVec.length() != 0 else self.speVec

        # Move the boid
        self.pos += self.speVec * BOID_SPEED_RANGE[1]

    #   Applies the vector changes with the rules to the hoiks
    def move_hoik(self, boids, hoiks, obstacles):
        rules = Rulebook(self.pos, self.speVec)
        remove = self.remove(boids)
        v2 = rules.collision(hoiks) + rules.collision(obstacles)

        # Check if there are nearby boids within the prey range
        if self.check_nearby(boids, FOOD_DETECTION_RANGE) or self.check_nearby(hoiks, COMMUNICATION_RANGE):
            v3 = rules.eating(remove)
        else:
            v3 = rules.explore()
        # Check if there are nearby hoiks hunting prey within the hunt range
        # elif any((hoik.pos - self.pos).length() < COMMUNICATION_RANGE for hoik in hoiks if hoik != self):

        # Adjust speed and size based on the number of boids eaten
        self.saturation()

        self.speVec += v2 + v3
        self.speVec = self.speVec.normalize() if self.speVec.length() != 0 else self.speVec
        self.pos += self.speVec * self.speed

        if self.num_boids_eaten > 0:
            self.speVec *= self.size

    #   Combines boids into groups when within a certain distance
    def family(self, boids):
        group = []
        group.append(self)
        for i in boids:
            hit = intersect_circles(self.pos, FAMILY_RADIUS, i.pos, FAMILY_RADIUS)
            if hit and i.speVec.length() != 0:
                i.speVec.normalize()
                group.append(i)
        return group

    def flee(self, family):
        if self in family:
            family.remove(self)

    def saturation(self):
        speed_multiplier = HOIK_SPEED_RANGE[1] / (1 + self.num_boids_eaten)
        self.speed = max(HOIK_SPEED_RANGE[0], speed_multiplier)

        self.num_boids_eaten -= 0.001
        self.num_boids_eaten = max(0, self.num_boids_eaten)

        growth_rate = self.num_boids_eaten * GROWTH_RATE
        new_size = HOIK_SIZE + growth_rate
        self.size = min(MAX_HOIK_SIZE, max(HOIK_SIZE, new_size))

    def check_nearby(self, target, range):
        if any((self.pos - flyer.pos).length() < range for flyer in target if flyer != self):
            return True
        return False

    #   Removes the boids once hit by the hoiks
    def remove(self, boids):
        for i in boids:
            hit = intersect_circles(self.pos, EAT_RANGE, i.pos, EATEN_RANGE)
            if hit:
                boids.remove(i)
                self.num_boids_eaten += 1
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