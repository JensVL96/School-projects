from config import *
from rules import Rulebook
import pygame as pg
import pygame.math as vec
from resources import intersect_circles

class Drawing:
    # Draw the object as a polygon with specified color
    def draw(self, color, screen):
        pg.draw.polygon(screen, color, ([self.pos.x, self.pos.y],
                                        [self.pos.x + self.speVec.x * EXTEND, self.pos.y + self.speVec.y * EXTEND],
                                        [self.pos.x + self.speVec.rotate(RIGHT_ANGLE).x * ANGLE_MULTIPLIER, self.pos.y + self.speVec.rotate(RIGHT_ANGLE).y * ANGLE_MULTIPLIER],
                                        [self.pos.x - self.speVec.rotate(LEFT_ANGLE).x * ANGLE_MULTIPLIER, self.pos.y - self.speVec.rotate(LEFT_ANGLE).y * ANGLE_MULTIPLIER],
                                        [self.pos.x + self.speVec.x * EXTEND, self.pos.y + self.speVec.y * EXTEND]), LINE_THICKNESS)

    # Draw circles based on button presses, using provided color and radius maps
    def draw_circles(self, screen, is_pressed, color_map, radius_map):
        for index, pressed in enumerate(is_pressed, start=1):
            if pressed:
                color = color_map[index - 1]
                radius = radius_map[index - 1]
                pg.draw.circle(screen, color, [int(self.pos.x + self.speVec.x * 5), int(self.pos.y + self.speVec.y * 5)], radius, 2)


class Movement:
    # Apply movement rules for boids
    def move_boid(self, boids, hoiks, obstacles, baits):
        rules = Rulebook(self.pos, self.speVec)
        family = self.family(boids)
        initial_vec = self.speVec

        # Check if there are nearby hoiks within the escape range
        if self.check_nearby(hoiks, HOIK_DETECTION_RANGE):
            self.flee(family)
            v1 = rules.evade(hoiks)             * EVADE_WEIGHT
            v2 = v3 = vec.Vector2(0, 0)
        else:
            v1 = rules.centralize(family)       * CENTRALIZE_WEIGHT
            v2 = rules.match_vector(family)     * MATCH_SPEED_WEIGHT
            v3 = rules.eating(baits)            * EATING_WEIGHT
        v4 = rules.collision(family, obstacles) * COLLISION_WEIGHT

        # Apply the affecting vectors and handle the changes
        self.speVec += v1 + v2 + v3 + v4
        self.normalize_speed()
        self.smooth_movement(initial_vec)
        self.limit_speed(BOID_SPEED_RANGE[1])

        # Move the boid
        self.pos += self.speVec

    # Apply movement rules for hoiks
    def move_hoik(self, boids, hoiks, obstacles):
        rules = Rulebook(self.pos, self.speVec)
        remove = self.remove(boids)

        # Check if there are nearby boids within the prey range
        if self.check_nearby(boids, FOOD_DETECTION_RANGE) or self.check_nearby(hoiks, COMMUNICATION_RANGE):
            v1 = rules.eating(remove)           * EATING_WEIGHT
        else:
            v1 = rules.explore()                * EXPLORE_WEIGHT
        v2 = rules.collision(hoiks, obstacles)  * COLLISION_WEIGHT

        # Adjust speed and size based on the number of boids eaten
        self.saturation()

        # Apply the affecting vectors and handle the changes
        self.speVec += v1 + v2
        self.normalize_speed()

        # Move the boid
        self.pos += self.speVec * self.speed

        # Increase in size when eating boids
        if self.num_boids_eaten > 0:
            self.speVec *= self.size


class Behaviour:
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

    # Flee from the hoiks
    def flee(self, family):
        if self in family:
            family.remove(self)

    # Adjust speed and size based on the number of boids eaten
    def saturation(self):
        speed_multiplier = HOIK_SPEED_RANGE[1] / (1 + self.num_boids_eaten)
        self.speed = max(HOIK_SPEED_RANGE[0], speed_multiplier)

        self.num_boids_eaten -= SATURATION_FACTOR
        self.num_boids_eaten = max(0, self.num_boids_eaten)

        growth_rate = self.num_boids_eaten * GROWTH_RATE
        new_size = HOIK_SIZE + growth_rate
        self.size = min(MAX_HOIK_SIZE, max(HOIK_SIZE, new_size))

    # Check if there are nearby objects within a specified range
    def check_nearby(self, target, range):
        return any((self.pos - flyer.pos).length() < range for flyer in target if flyer != self)

    # Normalize speed vector if it's non-zero
    def normalize_speed(self):
        if self.speVec.length() != 0:
            self.speVec = self.speVec.normalize()

    # Limit speed vector magnitude
    def limit_speed(self, limit):
        if self.speVec.length() > limit:
            self.speVec.scale_to_length(limit)

    # Compares two vectors and applies magnitude
    def smooth_movement(self, vector):
        self.speVec = vector *0.7 + self.speVec *0.3

    # Remove the boids once hit by the hoiks
    def remove(self, boids):
        for i in boids:
            hit = intersect_circles(self.pos, EAT_RANGE, i.pos, EATEN_RANGE)
            if hit:
                boids.remove(i)
                self.num_boids_eaten += 1
        return boids

    # Create a positional loop so the flyers stay in the screen
    def mirror_border(self):
        if (self.pos.x >= SCREEN_WIDTH - PANEL_WIDTH + EXTEND):
            self.pos.x = -EXTEND
        elif(self.pos.x <= -EXTEND):
            self.pos.x = SCREEN_WIDTH - PANEL_WIDTH + EXTEND
        elif (self.pos.y >= SCREEN_HEIGHT + EXTEND):
            self.pos.y = -EXTEND
        elif(self.pos.y <= -EXTEND):
            self.pos.y = SCREEN_HEIGHT + EXTEND


class Flyer(Drawing, Movement, Behaviour):
    def __init__(self):
        pass