from math import cos, sin, radians
from Resources import *
from objects import *
from classes import *
from Config import *
import pygame as pg


class Spaceship(pg.sprite.Sprite):
    """ The spaceship that is the player 

    :param model: The name of the image to differentiate the models.
    :param pos: A positional vector.
    :param image: the image png loaded with pygame.
    :param size: A constant that determines the image size.
    :param controls: A list of keys.
    """
    def __init__(self, model, pos, image, size, controls):
        super().__init__()
        self.flag = 0
        self.pos = pos
        self.model = model
        self.collision = False
        self.initial_pos = self.pos
        self.size = size

        #   Controls
        self.up, self.left, self.right, self.shoot, self.shoot2 = controls

        #   Starting values
        self.points = 0
        self.speVec = Vector2D(cos(radians(90)), sin(radians(90)))
        self.fuel_amount = MAX_FUEL
        self.angle = 90
        self.health_amount = MAX_HEALTH

        #   Class instances called
        self.ex = Explosion()
        self.score_board = Score(self.points, model)
        self.bar = Fuel_bar(self.fuel_amount)
        self.health_bar = Health_bars(self.health_amount, model)

        #   Ship image
        self.original_image = pg.transform.rotate(image, 270)
        self.rect = self.original_image.get_rect(center=pos.as_point)

    def draw(self, screen):
        """ Draws the image and resets values after death.
        """
        screen.blit(self.image, self.rect)


    def update(self):
        # Rotate and scale the original image based on the current angle and size
        self.image = pg.transform.rotozoom(self.original_image, self.angle, self.size)
        self.rect = self.image.get_rect(center=self.pos.as_point)
        # self.rect.x = self.pos.x - (self.rect.width / 2)
        # self.rect.y = self.pos.y - (self.rect.height / 2)

        # Reset to start values after death if explosion is active
        if self.ex.active:
            self.pos = self.initial_pos
            self.speVec = Vector2D(0,0)
            self.fuel_amount = MAX_FUEL
            self.angle = 90
            self.health_amount = MAX_HEALTH

    def move(self):
        # Rotate the ship
        if pg.key.get_pressed()[self.left]:
            self.angle = (self.angle + TURNING_ANGLE) % 360
        elif pg.key.get_pressed()[self.right]:
            self.angle = (self.angle - TURNING_ANGLE) % 360

        cos_theta, sin_theta = cos(radians(self.angle)), sin(radians(self.angle))
        self.end_vector = Vector2D(cos_theta, -sin_theta)

        # Move the ship
        if pg.key.get_pressed()[self.up] and self.fuel_amount > 0:
            self.speVec += Vector2D(cos_theta, -sin_theta) * SHIP_SPEED
            self.fuel_amount -= FUEL_DECLINE

        # Apply gravity
        self.speVec.y += GRAVITY

        # Update position
        self.pos += self.speVec

        # Screen border blockade
        if self.pos.x > SCREEN_WIDTH or self.pos.x < 0 or self.pos.y > SCREEN_HEIGHT or self.pos.y < 0:
            if pg.time.get_ticks() > self.flag + RESPAWN_DELAY:
                self.ex.activate(self.pos)
                self.points += OUTSIDE_BOUNDARY_POINTS
                self.flag = pg.time.get_ticks()

    def controls(self, bullets_group, obs):
        # Move the ship
        self.move()

        # Shoot bullets and add them to the Bullets group
        if pg.key.get_pressed()[self.shoot] or pg.key.get_pressed()[self.shoot2]:
            if pg.time.get_ticks() > self.flag + BULLET_DELAY:
                bullet_spawn_point_x = self.pos.x - CENTER_POS_DIFFER + self.end_vector.x * LENGTH_FROM_SHIP
                bullet_spawn_point_y = self.pos.y - CENTER_POS_DIFFER + self.end_vector.y * LENGTH_FROM_SHIP
                bullet_spawn = Vector2D(bullet_spawn_point_x, bullet_spawn_point_y)
                
                # Make bullets
                bullets_group.add(Bullets(bullet_spawn, self.angle, self.end_vector * BULLET_SPEED, self.model, bullets_group, obs))
                self.flag = pg.time.get_ticks()

    def land(self, pos):
        """ Lands the ship and refuels it.
        """
        #   Takeoff
        if pg.key.get_pressed()[self.up]:
            return

        #   Refueling and stopping the ships movement
        if intersect_circles(self.pos, SHIP_RADIUS, Vector2D(pos.x, pos.y + STAND_POS_DIFFER), STAND_RADIUS):
            self.pos.y = self.initial_pos.y + STAND_POS_DIFFER
            self.speVec = Vector2D(0,0)
            self.fuel_amount = MAX_FUEL

    def hit(self, sheet, obs, bullets_group):
        """ Checks the collision of the player ship with other objects.
        """
        # Check for collision with bullets
        for bullet in pg.sprite.spritecollide(self, bullets_group, False):
            bullet_center = Vector2D(bullet.rect.x + bullet.rect.width / 2, bullet.rect.y + bullet.rect.height / 2)
            distance = self.pos.distance_to(bullet_center)
            if distance < SHIP_RADIUS + BULLET_RADIUS:
                bullet.kill()  # Destroy bullet upon collision
                self.health_amount -= 1  # Reduce health upon collision


        # Check collision with obstacles
        center_obs_1 = Vector2D(obs.rect.x + obs.rect.width / 2, obs.rect.y + obs.rect.width / 2)
        if intersect_circles(self.pos, SHIP_RADIUS, center_obs_1, OBS_RADIUS):
            self.ex.activate(self.pos)
            if pg.time.get_ticks() > self.flag + RESPAWN_DELAY:
                self.points += DEATH_POINTS
                self.flag = pg.time.get_ticks()

        # Check collision with the music sheet
        if pg.sprite.collide_circle(self, sheet):
            sheet_center = Vector2D(sheet.rect.x + sheet.rect.width / 2, sheet.rect.y + sheet.rect.height / 2)
            if intersect_circles(self.pos, SHIP_RADIUS, sheet_center, SHEET_RADIUS):
                self.fuel_amount += 50
                if self.fuel_amount > MAX_FUEL:
                    self.fuel_amount = MAX_FUEL
                sheet.on_hit()

        # When the two ships collide
        for ship in self.groups()[0]:
            if isinstance(ship, Spaceship) and ship != self:
                if intersect_circles(self.pos, SHIP_RADIUS, ship.pos, SHIP_RADIUS * 2):
                    print("Collision detected!")
                    if pg.time.get_ticks() > self.flag + RESPAWN_DELAY:
                        self.ex.activate(self.pos)
                        ship.ex.activate(ship.pos)  # Activate explosion for the other ship
                        self.points += COLLISION_POINTS
                        ship.points += COLLISION_POINTS  # Update points for both ships
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
            self.ex.activate(self.pos)

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
