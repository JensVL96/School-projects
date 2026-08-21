from math import cos, sin, radians
from Resources import *
from classes import *
from Config import *
import pygame as pg


class Spaceship():
    """ The spaceship that is the player 

    :param model: The name of the image to differentiate the models.
    :param pos: A positional vector.
    :param image: the image png loaded with pygame.
    :param size: A constant that determines the image size.
    :param controls: A list of keys.
    """
    def __init__(self, model, pos, image, size, controls):
        self.flag = 0
        self.pos = pos
        self.model = model
        self.collision = False
        self.pos_copy = self.pos

        #   Controls
        self.up = controls[0]
        self.left = controls[1]
        self.right = controls[2]
        self.shoot = controls[3]

        #   Starting values
        self.points = 0
        self.speVec = Vector2D(0,0)
        self.fuel_amount = MAX_FUEL
        self.angle = - STARTING_ANGLE
        self.health_amount = MAX_HEALTH

        #   Class instances called
        self.ex = Explosion()
        self.score_board = Score(self.points, model)
        self.bar = Fuel_bar(self.fuel_amount)
        self.health_bar = Health_bars(self.health_amount, model)

        #   Ship image
        self.ship_image = image
        self.ship_angle = pg.transform.rotozoom(self.ship_image, STARTING_ANGLE, size)

    def draw(self, screen):
        """ Draws the image and resets values after death.
        """
        self.screen = screen
        if self.ex.alive == True:
            self.screen.blit(self.ship, (self.pos.x - (self.rect.width / 2), self.pos.y - (self.rect.height / 2)))
        else:
            #   Reset to start values after death
            self.pos = self.pos_copy
            self.speVec = Vector2D(0,0)
            self.fuel_amount = MAX_FUEL
            self.angle = - STARTING_ANGLE
            self.health_amount = MAX_HEALTH

    def move(self):
        """ Makes the player image rotate, creates a new vector and creates a position border.
        """
        #   Rotate the ship
        if pg.key.get_pressed()[self.left]:
            self.angle = (self.angle + TURNING_ANGLE) % 360
        elif pg.key.get_pressed()[self.right]:
            self.angle = (self.angle - TURNING_ANGLE) % 360

        self.ship = pg.transform.rotozoom(self.ship_angle, self.angle, SHIP_SIZE)
        self.rect = self.ship.get_rect()

        #   A rotation vector for the ship image to follow
        cos_theta, sin_theta = cos(radians(self.angle)), sin(radians(self.angle))

        xEnd = cos_theta
        yEnd = -sin_theta
        self.end_vector = Vector2D(xEnd, yEnd)

        #   Screen border blockade
        if self.pos.x > SCREEN_WIDTH or self.pos.x < 0 or self.pos.y > SCREEN_HEIGHT or self.pos.y < 0:
            if pg.time.get_ticks() > self.flag + RESPAWN_DELAY:
                self.ex.activate()
                self.points += OUTSIDE_BOUNDARY_POINTS
                self.flag = pg.time.get_ticks()

    def controls(self):
        """ Makes the ship move and shoot according to the controls.
        """
        self.move()

        if pg.key.get_pressed()[self.up] and self.fuel_amount > 0:       #   Thrust
            self.speVec += (self.end_vector * SHIP_SPEED)
            self.fuel_amount -= FUEL_DECLINE

        if pg.key.get_pressed()[self.shoot]:  #   Shoot
            if pg.time.get_ticks() > self.flag + BULLET_DELAY:

                bullet_spawn_point_x = self.pos.x - CENTER_POS_DIFFER + self.end_vector.x * LENGTH_FROM_SHIP
                bullet_spawn_point_y = self.pos.y - CENTER_POS_DIFFER + self.end_vector.y * LENGTH_FROM_SHIP
                bullet_spawn = Vector2D(bullet_spawn_point_x, bullet_spawn_point_y)

                Bullets.bullet_list.append(Bullets(bullet_spawn, self.angle, self.end_vector * BULLET_SPEED, self.model))
                self.flag = pg.time.get_ticks()

    def gravity(self):
        """ Adds a vector downwards to simulate gravity.
        """
        self.speVec.y += GRAVITY
        self.pos += self.speVec

    def land(self, pos):
        """ Lands the ship and refuels it.
        """
        #   Takeoff
        if pg.key.get_pressed()[self.up]:
            return

        #   Refueling and stopping the ships movement
        if intersect_circles(self.pos, SHIP_RADIUS, Vector2D(pos.x, pos.y + STAND_POS_DIFFER), STAND_RADIUS):
            self.pos.y = self.pos_copy.y + STAND_POS_DIFFER
            self.speVec = Vector2D(0,0)
            self.fuel_amount = MAX_FUEL

    def hit(self, sheet, el_pos, box_pos, obs):
        """ Checks the collision of the player ship with other objects.
        """
        #   When the bullet collides with the ship
        for i in Bullets.bullet_list:
            if ((intersect_circles(self.pos, SHIP_RADIUS, i.pos, BULLET_RADIUS)
                or intersect_circles(self.pos + (self.end_vector.normalized() * 70), SHIP_RADIUS, i.pos, BULLET_RADIUS)
                or intersect_circles(self.pos - (self.end_vector.normalized() * 50), SHIP_RADIUS, i.pos, BULLET_RADIUS))
                and i.model != self.model):

                Bullets.bullet_list.remove(i)
                self.health_amount -= 1

        #   When the ship collides with the obstacle
        for j in obs.obstacle_list:
            center_obs_1 = Vector2D(j.pos.x + j.rect.width / 2, j.pos.y + j.rect.width / 2)
            if intersect_circles(self.pos, SHIP_RADIUS, center_obs_1, OBS_RADIUS):
                self.ex.activate()
                if pg.time.get_ticks() > self.flag + RESPAWN_DELAY:
                    self.points += DEATH_POINTS
                    self.flag = pg.time.get_ticks()

            #   When the bullet collides with the obstacle
            for k in Bullets.bullet_list:
                center_obs_2 = Vector2D(j.pos.x + j.rect.width / 2, j.pos.y + j.rect.width / 2)
                if intersect_circles(k.pos, BULLET_RADIUS, center_obs_2, OBS_RADIUS):
                    Bullets.bullet_list.remove(k)

        #   When the ship collides with the music sheet
        sheet_x = sheet.sheet_pos.x + sheet.rect.width / 2
        sheet_y = sheet.sheet_pos.y + sheet.rect.height / 2
        sheet_center = Vector2D(sheet_x, sheet_y)
        if intersect_circles(self.pos, SHIP_RADIUS, sheet_center, SHEET_RADIUS):
            self.fuel_amount += 50
            if self.fuel_amount > MAX_FUEL:
                self.fuel_amount = MAX_FUEL
            sheet.update_pos()

        #   When the two ships collide
        if intersect_circles(el_pos, SHIP_RADIUS, box_pos, SHIP_RADIUS):
            if pg.time.get_ticks() > self.flag + RESPAWN_DELAY:
                self.ex.activate()
                self.points += COLLISION_POINTS
                self.flag = pg.time.get_ticks()

    def fuel(self, screen):
        """ Draws the fuel amount at the fuel bar. 
        """  
        if self.model == ("el_guitar"):
            pg.draw.rect(screen, WHITE, (self.bar.pos_1.x, self.bar.pos_1.y, self.fuel_amount, self.bar.height))
        else:
            pg.draw.rect(screen, WHITE, (self.bar.pos_2.x, self.bar.pos_2.y, self.fuel_amount, self.bar.height))

        self.bar.__init__(self.fuel_amount)

    def health(self):
        """ Updates and checks the health
        """
        self.health_bar.__init__(self.health_amount, self.model)
        if self.health_bar.dead == True:
            self.ex.activate()

    def score(self, group):
        """ Updates and checks the score
        """
        if self.points < 0:
            self.points = 0

        if self.health_bar.dead and pg.time.get_ticks() > self.flag + RESPAWN_DELAY:
            for x in group:
                if x != self:
                    x.points += 500
                    self.flag = pg.time.get_ticks()

        self.score_board.__init__(self.points, self.model)
