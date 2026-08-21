from config import *
import pygame.math as vec
import random as rand

class Rulebook():
    def __init__(self, pos, speVec):
        self.pos = pos
        self.speVec = speVec

    #   Makes the boids move towards the groups central position
    def centralize(self, flyers):
        vector = vec.Vector2(0,0)

        for flyer in flyers:
            if flyer is not self:
                if (flyer.pos - self.pos).length() < FAMILY_RADIUS:
                    vector += flyer.pos - self.pos

        return vector / (len(flyers) - 1) if len(flyers) > 1 else vec.Vector2(0, 0)

    # Makes the boids in a group aim for the same coordinate together
    def match_vector(self, flyers):  
        vector = vec.Vector2(0, 0)

        for flyer in flyers:
            if flyer is not self:
                distance = (flyer.pos - self.pos).length()

                # Adjust the contribution based on distance
                if 0 < distance <= FAMILY_RADIUS:
                    vector += flyer.speVec / distance
                elif distance > FAMILY_RADIUS:
                    vector += flyer.speVec * FAMILY_RADIUS / distance

        # Add a random component to the vector
        random_vector = vec.Vector2(rand.uniform(-1, 1), rand.uniform(-1, 1))
        vector += random_vector

        vector = vector / len(flyers) if len(flyers) == 1 else vector / (len(flyers) - 1)

        return vector

    #   Makes the flyers avoid obstacles and eachother
    def collision(self, flyers, obstacles):
        vector = vec.Vector2(0,0)

        # Adjust vector to avoid similar flyers
        for flyer in flyers:
            if flyer is not self:
                diff = (flyer.pos - self.pos)
                size = AVOIDANCE_RADIUS - diff.length()
                vector -= (diff * size - self.speVec) * FAMILY_AVOIDANCE_WEIGHT if size > 0 else vec.Vector2(0, 0)

        # Adjust vector to avoid obstacles
        for obstacle in obstacles:
            diff = obstacle.pos - self.pos
            size = AVOIDANCE_RADIUS - diff.length() + OBSTACLE_AVOIDANCE
            avoidance_vector = diff * size - self.speVec
            vector -= avoidance_vector * OBSTACLE_AVOIDANCE_WEIGHT if size > 0 else vec.Vector2(0, 0)

        return vector

    # Makes the boid flee from hoiks
    def evade(self, hoiks):
        vector = vec.Vector2(0, 0)
        for hoik in hoiks:
            if (hoik.pos - self.pos).length() < HOIK_DETECTION_RANGE:
                vector -= hoik.pos - self.pos

        return vector

    # Makes the flyers prioritize geting to the position given
    def eating(self, targets):
        vector = vec.Vector2(0,0)

        if len(targets) > 0:
            min_target = targets[0]
            min_distance = (min_target.pos - self.pos).length()

            # Find the closest target within the hunting range
            for target in targets:
                distance_to_target = (target.pos - self.pos).length()
                if distance_to_target < min_distance and distance_to_target < FOOD_DETECTION_RANGE:
                    min_target = target
                    min_distance = distance_to_target

            # If a target within range is found, prioritize hunting it
            if min_distance < FOOD_DETECTION_RANGE:
                vector += min_target.pos - self.pos

        return vector

    # Simulate the behavior of a flyer exploring its surroundings
    def explore(self):
        random_direction = vec.Vector2(rand.uniform(-5, 5), rand.uniform(-5, 5))
        vector = self.speVec + random_direction

        noise = vec.Vector2(rand.uniform(-1, 1), rand.uniform(-1, 1))
        vector += noise

        return vector
