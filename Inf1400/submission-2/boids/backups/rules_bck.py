from config import *
import pygame.math as vec
import random as rand
from math import acos, radians, degrees

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

        return vector

    #   Makes the boids match the direction of the group 
    def match_vector(self, flyers):  
        vector = vec.Vector2(0,0)

        for flyer in flyers:
            if flyer is not self:
                distance = (flyer.pos - self.pos).length()

                # Adjust the contribution based on distance
                if 0 < distance <= FAMILY_RADIUS:
                    vector += flyer.speVec / distance  # Weighted contribution based on distance
                elif distance > FAMILY_RADIUS:
                    vector += flyer.speVec * FAMILY_RADIUS / distance

        if(len(flyers) == 1):
            vector /= len(flyers)
        else:
            vector /= len(flyers) - 1

        vector = vector - self.speVec

        return (vector - self.speVec)

    #   Makes the flyers avoid obstacles and eachother
    def collision(self, flyers, obstacles):
        vector = vec.Vector2(0,0)

        for flyer in flyers:
            if flyer is not self:
                diff = (flyer.pos - self.pos)
                size = AVOIDANCE_RADIUS - diff.length()
                if size > 0:
                    vector -= (diff * size - self.speVec) * FAMILY_AVOIDANCE_WEIGHT

        # Adjust vector to avoid obstacles
        for obstacle in obstacles:
            diff = obstacle.pos - self.pos
            size = AVOIDANCE_RADIUS - diff.length() + OBSTACLE_AVOIDANCE
            if size > 0:
                # If there's a potential collision with an obstacle, adjust the vector
                avoidance_vector = diff * size - self.speVec
                vector -= avoidance_vector * OBSTACLE_AVOIDANCE_WEIGHT

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

    # def deviation(self, new_vec):
    #     if self.speVec.length() == 0 or new_vec.length() == 0:
    #         return self.speVec
            
    #     # temp_vec = new_vec.copy()
    #     # Calculate the angle between the initial speed vector and the new speed vector
    #     angle = acos(min(1, max(-1, self.speVec.normalize().dot(new_vec.normalize()))))

    #     # Limit the angle to 30% (in radians) if it exceeds
    #     max_angle = radians(BOID_TURN_ANGLE)  # Convert 30% to radians

    #     if angle > max_angle:
    #         deviation = self.speVec.rotate(degrees(angle - max_angle))
    #         return deviation
    #     else:
    #         return new_vec


    def explore(self):
        random_direction = vec.Vector2(rand.uniform(-5, 5), rand.uniform(-5, 5))
        vector = self.speVec + random_direction

        noise = vec.Vector2(rand.uniform(-1, 1), rand.uniform(-1, 1))
        vector += noise

        return vector
