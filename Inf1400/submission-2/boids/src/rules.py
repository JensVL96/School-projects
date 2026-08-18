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

        if(len(flyers) == 1):
            vector /= len(flyers)
        else:
            vector /= len(flyers) - 1

        # Apply deviation
        deviation = self.deviate()
        vector += deviation

        return (vector) / 200

    #   Makes the boids match the direction of the group 
    def match_speed(self, flyers):  
        vector = vec.Vector2(0,0)

        for flyer in flyers:
            if flyer is not self:
                vector += flyer.speVec

        if(len(flyers) == 1):
            vector /= len(flyers)
        else:
            vector /= len(flyers) - 1

        return (vector - self.speVec) / 5

    #   Makes the flyers avoid obstacles and eachother
    def collision(self, flyers):
        vector = vec.Vector2(0,0)

        for flyer in flyers:
            if flyer is not self:
                diff = (flyer.pos - self.pos)
                size = AVOIDANCE_RADIUS - diff.length()
                if size > 0:
                    vector -= diff * size - self.speVec
        return vector / 2
    
    # Makes the boid flee from hoiks
    def evade(self, hoiks):
        vector = vec.Vector2(0, 0)
        for hoik in hoiks:
            if (hoik.pos - self.pos).length() < HOIK_DETECTION_RANGE:
                vector -= hoik.pos - self.pos
        return vector / 100

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

        return vector / 50

    def deviate(self, weight=0.1):
        random_direction = vec.Vector2(rand.uniform(-1, 1), rand.uniform(-1, 1))
        noise = vec.Vector2(rand.uniform(-1, 1), rand.uniform(-1, 1))
        deviation = (random_direction + noise) * weight
        return deviation


    def explore(self):
        random_direction = vec.Vector2(rand.uniform(-5, 5), rand.uniform(-5, 5))
        vector = self.speVec + random_direction

        noise = vec.Vector2(rand.uniform(-1, 1), rand.uniform(-1, 1))
        vector += noise

        return vector / 100
