from resources import *
from config import *
import pygame as pg
import random as rand
import pygame.math as vec

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
        pg.draw.circle(screen, BLUE, [int(self.pos.x + self.speVec.x * 5), int(self.pos.y + self.speVec.y * 5)], OBS_DISTANCE, 2)
        pg.draw.circle(screen, BLUE, [int(self.pos.x + self.speVec.x * 5), int(self.pos.y + self.speVec.y * 5)], HOIK_DISTANCE, 2)
        pg.draw.circle(screen, BLUE, [int(self.pos.x + self.speVec.x * 5), int(self.pos.y + self.speVec.y * 5)], FAMILY_RADIUS, 2)

    #   Applies the vector changes with the rules to the boids
    def move_boid(self, boids, hoiks, obstacles, baits):
        rules = Rulebook(self.pos, self.speVec)
        family = self.family(boids)

        # Check if there are nearby hoiks within the escape range
        if any((hoik.pos - self.pos).length() < HUNTERS_DETECTION_RANGE for hoik in hoiks):
            v1 = -rules.collision(hoiks)
            v3 = v4 = v5 = vec.Vector2(0, 0)
        else:
            v1 = rules.centralize(family)
            v3 = rules.match_speed(family)
            v4 = rules.eating(baits) * 0.5
            v5 = rules.deviate() * 0.01

        v2 = rules.collision(family) * 0.01 + rules.collision(obstacles)

        self.speVec += v1 + v2 + v3 + v4 + v5
        self.speVec = self.speVec.normalize() if self.speVec.length() != 0 else self.speVec
        self.pos += self.speVec * 3


    #   Applies the vector changes with the rules to the hoiks
    def move_hoik(self, boids, hoiks, obstacles, time_passed):
        rules = Rulebook(self.pos, self.speVec)
        remove = self.remove(boids)
        v2 = rules.collision(hoiks) * 0.05 + rules.collision(obstacles)

        # Check if there are nearby boids within the prey range
        if any((self.pos - flyer.pos).length() < self.prey_range for flyer in boids):
            v3 = rules.eating(remove)
        # Check if there are nearby hoiks hunting prey within the hunt range
        elif any((hoik.pos - self.pos).length() < self.hunt_range for hoik in hoiks if hoik != self):
            v3 = rules.eating(remove)
        else:
            v3 = rules.explore()

        # Adjust speed multiplier based on the number of boids eaten
        multiplier = HOIK_SPEED_MULTIPLIER / (1 + self.num_boids_eaten)  # Decrease speed as boids eaten increases

        # Revert eating effects gradually over time
        if self.size > HOIK_SIZE and multiplier < HOIK_SPEED_MULTIPLIER:
            self.size -= 0.01
            self.size = max(HOIK_SIZE, self.size)
            multiplier += 10
            multiplier = min(HOIK_SPEED_MULTIPLIER, multiplier)

        self.speVec += v2 + v3
        self.speVec = self.speVec.normalize() if self.speVec.length() != 0 else self.speVec
        self.pos += self.speVec * multiplier
        self.speVec *= self.size

    #   Combines boids into groups when within a certain distance
    def family(self, boids):
        group = []
        group.append(self)
        for i in boids:
            hit = intersect_circles(self.pos, self.fam_rad, i.pos, i.fam_rad)
            if hit and i.speVec.length() != 0:
                i.speVec.normalize()
                group.append(i)
        return group

    #   Removes the boids once hit by the hoiks
    def remove(self, boids):
        for i in boids:
            hit = intersect_circles(self.pos, self.eat_rad, i.pos, i.hoik_rad)
            if hit:
                boids.remove(i)
                self.num_boids_eaten += 1
                self.size += 0.1
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
        self.pos = vec.Vector2(x, y)
        self.speVec = vec.Vector2(rand.randint(-1,1), rand.randint(-1,1))
        self.hoik_rad = REMOVE_DISTANCE
        self.fam_rad = FAMILY_RADIUS
        self.size = BOID_SIZE  # Initial size of the boid
        self.max_speed = BOID_SPEED_RANGE[0]  # Maximum speed of the boid
        self.min_speed = BOID_SPEED_RANGE[1]  # Minimum speed of the boid

class Hoiks(Flyer):
    def __init__(self, x, y):
        self.pos = vec.Vector2(x, y)
        self.speVec = vec.Vector2(0, 0)
        self.num_boids_eaten = 0
        self.size = HOIK_SIZE
        self.eat_rad = EAT_DISTANCE
        self.prey_range = PREY_DETECTION_RANGE
        self.hunt_range = HUNTERS_DETECTION_RANGE

class Obstacles():
    def __init__(self, x, y):
        self.pos = vec.Vector2(x, y)
        self.radius = OBSTACLE_RADIUS

    def draw(self, color, screen):
        pg.draw.circle(screen, color, [self.pos.x, self.pos.y], self.radius, LINE_THICKNESS)

    # If obstacle collides with another obstacles
    def collides_with(self, other_obstacle):
        distance_squared = (self.pos - other_obstacle.pos).length_squared()
        combined_radius = self.radius + other_obstacle.radius
        return distance_squared <= combined_radius ** 2

class Bait():
    def __init__(self, x, y):
        self.pos = vec.Vector2(x, y)



class Rulebook():
    def __init__(self, pos, speVec):
        self.pos = pos
        self.speVec = speVec

    #   Makes the flyers move towards the groups central position
    def centralize(self, flyers):
        vector = vec.Vector2(0,0)

        for flyer in flyers:
            if flyer is not self:
                if (flyer.pos - self.pos).length() < HOIK_DISTANCE * 2:
                    vector += flyer.pos - self.pos

        if(len(flyers) == 1):
            vector /= len(flyers)
        else:
            vector /= len(flyers) - 1
        return (vector) / 200

    #   Makes the flyers avoid obstacles and eachother
    def collision(self, flyers):
        vector = vec.Vector2(0,0)

        for flyer in flyers:
            if flyer is not self:
                diff = (flyer.pos - self.pos)
                size = OBS_DISTANCE - diff.length()
                if size > 0:
                    vector -= diff * size - self.speVec
        return vector / 2

    #   Makes the flyers match the direction of their group 
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

    # Makes the flyers prioritize geting to the position given
    def eating(self, targets):
        vector = vec.Vector2(0,0)

        if(len(targets) > 0):
            min_1 = targets[0]
            for target in targets:
                if (target.pos - self.pos).length() < (min_1.pos - self.pos).length():
                    min_1 = target
            vector += min_1.pos - self.pos
        return vector / 100
    
    # def eat_boids(self, boids, hoik_radius, eat_boids_weight=1):
    #     """Makes the hoiks prioritize hunting boids within their detection radius."""
    #     eat_boids_vector = vec.Vector2(0, 0)
    #     for boid in boids:
    #         distance_to_boid = (boid.pos - self.pos).length()
    #         if distance_to_boid < hoik_radius:
    #             eat_boids_vector += boid.pos - self.pos
    #     return eat_boids_vector * eat_boids_weight
    
    # def eat_baits(self, baits, eat_baits_weight=1):
    #     """Makes the hoiks prioritize eating baits."""
    #     eat_baits_vector = vec.Vector2(0, 0)
    #     for bait in baits:
    #         eat_baits_vector += bait.pos - self.pos
    #     return eat_baits_vector * eat_baits_weight
    
    # Makes the boids fluctuate their flight paths
    def deviate(self, weight=0.1):
        # Calculate a random direction for deviation
        random_direction = vec.Vector2(rand.uniform(-1, 1), rand.uniform(-1, 1))
        
        # Add noise to introduce variability
        noise = vec.Vector2(rand.uniform(-1, 1), rand.uniform(-1, 1))
        
        # Combine random direction and noise with the specified weight
        deviation = (random_direction + noise) * weight
        
        # Apply the deviation to the boid's velocity vector
        self.speVec += deviation
        
        # Normalize the velocity vector to maintain the boid's speed
        self.speVec = self.speVec.normalize() if self.speVec.length() != 0 else self.speVec
        
        return self.speVec


    def explore(self):
        random_direction = vec.Vector2(rand.uniform(-5, 5), rand.uniform(-5, 5))
        vector = self.speVec + random_direction

        noise = vec.Vector2(rand.uniform(-1, 1), rand.uniform(-1, 1))
        vector += noise

        return vector / 100





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
        for existing_obstacle in self.obstacles:
            if obstacle.collides_with(existing_obstacle):
                # don't place until far enough away
                existing_obstacle.radius = max(existing_obstacle.radius, obstacle.radius)
                existing_obstacle.pos = (existing_obstacle.pos + obstacle.pos) / 2
                return
        self.obstacles.append(obstacle)


    def new_bait(self, x, y, active):
        # print("new bait")
        if active == 1:
            self.bait = Bait(x, y)
            self.baits.append(self.bait)
        else:
            self.baits.clear()

    def move_all(self, time_passed):
        # print(f"moving {len(self.boids) + len(self.hoiks)} amount of objects")
        for boid in self.boids:
            boid.move_boid(self.boids, self.hoiks, self.obstacles, self.baits)
            boid.mirror_border()
        for hoik in self.hoiks:
            hoik.move_hoik(self.boids, self.hoiks, self.obstacles, time_passed)
            hoik.mirror_border()

    def draw_all(self, screen):
        # print(f"drawing {len(self.boids) + len(self.hoiks) + len(self.obstacles)} amount of objects")
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

            self.flyer_list.move_all(time_passed)
            self.flyer_list.draw_all(screen)

            pg.display.update()
if __name__ == '__main__':
    game()