from math import hypot, cos, sin, radians, pi
from Resources import *
from Config import *
import random as rand
import pygame as pg
import time
import copy


class Spaceship():
    def __init__(self, model, pos, image, size, up, left, right, shoot):
        self.flag = 0
        self.pos = pos
        self.model = model
        self.collision = False
        self.pos_copy = self.pos

        #   Controls
        self.up = up
        self.left = left
        self.right = right
        self.shoot = shoot

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
        self.screen = screen
        if self.ex.alive == True:
            self.screen.blit(self.ship, (self.pos.x - (self.rect.width / 2), self.pos.y - (self.rect.height / 2)))

            startpoint = (self.pos.x, self.pos.y)
            endpoint = (self.pos.x + self.end_vector.x * BULLET_LENGTH_FROM_CENTER, self.pos.y + self.end_vector.y * BULLET_LENGTH_FROM_CENTER)
            pg.draw.line(screen, WHITE, startpoint, endpoint, 2)
        else:
            #   Reset to start values after death
            self.pos = self.pos_copy
            self.speVec = Vector2D(0,0)
            self.fuel_amount = MAX_FUEL
            self.angle = - STARTING_ANGLE
            self.health_amount = MAX_HEALTH

    def move(self):
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
        self.move()

        if pg.key.get_pressed()[self.up] and self.fuel_amount > 0:       #   Thrust
            self.speVec += (self.end_vector * SHIP_SPEED)
            self.fuel_amount -= FUEL_DECLINE

        if pg.key.get_pressed()[self.shoot]:  #   Shoot
            if pg.time.get_ticks() > self.flag + BULLET_DELAY:
                bullet_spawn_point_x = self.pos.x - CENTER_POS_DIFFER + self.end_vector.x * BULLET_LENGTH_FROM_CENTER
                bullet_spawn_point_y = self.pos.y - CENTER_POS_DIFFER + self.end_vector.y * BULLET_LENGTH_FROM_CENTER
                Bullets.bullet_list.append(Bullets(Vector2D(bullet_spawn_point_x, bullet_spawn_point_y), self.angle, self.end_vector * BULLET_SPEED, self.model))
                self.flag = pg.time.get_ticks()

    def gravity(self):
        self.speVec.y += GRAVITY
        self.pos += self.speVec

    def land(self, pos):
        #   Takeoff
        if pg.key.get_pressed()[self.up]:
            return

        #   Refueling and stopping the ships movement
        if intersect_circles(self.pos, SHIP_RADIUS, Vector2D(pos.x, pos.y + STAND_POS_DIFFER), STAND_RADIUS):
            self.pos.y = self.pos_copy.y + STAND_POS_DIFFER
            self.speVec = Vector2D(0,0)
            self.fuel_amount = MAX_FUEL

    def hit(self, sheet, el_pos, box_pos, obs):
        #   When the bullet collides with the ship
        for i in Bullets.bullet_list:
            if (intersect_circles(self.pos, SHIP_RADIUS, i.pos, BULLET_RADIUS) and i.model != self.model) or intersect_circles(self.pos - (self.speVec.normalized() * 50), SHIP_RADIUS, i.pos, BULLET_RADIUS):
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
        sheet_center = Vector2D(sheet.sheet_pos.x + sheet.rect.width / 2, sheet.sheet_pos.y + sheet.rect.height / 2)
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

    def fuel(self, screen):     #   The fuel bars at the top of the screen  
        if self.model == ("el_guitar"):
            pg.draw.rect(screen, WHITE, (self.bar.pos_1.x, self.bar.pos_1.y, self.fuel_amount, self.bar.height))
        else:
            pg.draw.rect(screen, WHITE, (self.bar.pos_2.x, self.bar.pos_2.y, self.fuel_amount, self.bar.height))

        self.bar.__init__(self.fuel_amount)

    def health(self):   #   Updates and checks the health
        self.health_bar.__init__(self.health_amount, self.model)
        if self.health_bar.empty == True:
            self.ex.activate()

    def score(self, group):
        if self.points < 0:
            self.points = 0

        if self.health_bar.empty and pg.time.get_ticks() > self.flag + RESPAWN_DELAY:
            for x in group:
                if x != self:
                    x.points += 500
                    self.flag = pg.time.get_ticks()

        self.score_board.__init__(self.points, self.model)

class Bullets():
    bullet_list = []
    def __init__(self, pos, angle, vec, model):
        self.pos = pos
        self.vec = vec
        self.model = model
        self.radius = BULLET_RADIUS
        self.bullet_image = pg.image.load("Sprites/Bullet.png")
        self.bullet = pg.transform.rotozoom(self.bullet_image, STARTING_ANGLE + angle, BULLET_SIZE)
        Bullets.fill(self.bullet, pg.Color(rand.randint(0,255), rand.randint(0,255), rand.randint(0,255)))

    def update():       #   Make the bullet have a constant velocity
        for i in Bullets.bullet_list:
            i.pos.x += i.vec.x
            i.pos.y += i.vec.y
            if i.pos.x > SCREEN_WIDTH or i.pos.x < 0 or i.pos.y > SCREEN_HEIGHT or i.pos.y < 0:
                Bullets.bullet_list.remove(i)

    def fill(image, colour):    #   Change the colour of the bullets
        r, g, b, _ = colour
        w, h = image.get_size()
        for x in range(w):
            for y in range(h):
                a = image.get_at((x, y))[3]
                image.set_at((x, y), pg.Color(r, g, b, a))

    def draw(screen):
        for j in Bullets.bullet_list:
            screen.blit(j.bullet, (j.pos.x, j.pos.y))

class Fuel_bar():
    def __init__(self, amount):
        self.empty = False
        self.pos_1 = Vector2D(100,50)
        self.pos_2 = Vector2D(SCREEN_WIDTH - 250, 50)
        self.start_width = MAX_FUEL
        self.height = FUEL_TANK_HEIGHT

        if amount > 0:
            pass
        else:
            self.empty = True

    def draw(self, screen):
        group = [self.pos_1, self.pos_2]

        for i in group:
            pg.draw.rect(screen, GREY, (i.x - BAR_POS_DIFFER, i.y - BAR_POS_DIFFER, self.start_width + BAR_SIZE_DIFFER, self.height + BAR_SIZE_DIFFER))
            pg.draw.rect(screen, BLACK, (i.x, i.y, self.start_width, self.height))

class Health_bars():
    def __init__(self, amount, model):
        self.empty = False
        self.model = model
        self.amount = amount
        self.width = HEALTH_BAR_WIDTH
        self.height = HEALTH_BAR_HEIGHT
        self.pos_1 = Vector2D(0 + 100,100)
        self.pos_2 = Vector2D(SCREEN_WIDTH - 250, 100)

        if self.amount > 0:
            pass
        else:
            self.empty = True

    def draw(self, screen):
        if self.model == ("el_guitar"):
            for i in range(self.amount):
                pg.draw.rect(screen, GREEN, (self.pos_1.x + i * HEALTH_SPACE_IN_BETWEEN, self.pos_1.y, self.width, self.height))
        else:
            for i in range(self.amount):
                pg.draw.rect(screen, GREEN, (self.pos_2.x + i * HEALTH_SPACE_IN_BETWEEN, self.pos_2.y, self.width, self.height))

class Score():
    def __init__(self, points, model):
        self.score = points
        self.model = model

    def draw(self, screen):
        self.font = pg.font.SysFont('Comic Sans MS', 22)
        self.text = self.font.render(("Score: %d" %self.score), True, WHITE)

        if self.model == ("el_guitar"):
            screen.blit(self.text, (100,140))
        else:
            screen.blit(self.text, (SCREEN_WIDTH - 250, 140))

class Explosion():
    def __init__(self):
        self.iter = 0
        self.flag_ex = 0
        self.alive = True
        self.active = False
        self.explosions = []

        #   Explosion animation
        self.explosions.append(pg.image.load("Sprites/Ex1.png"))
        self.explosions.append(pg.image.load("Sprites/Ex2.png"))
        self.explosions.append(pg.image.load("Sprites/Ex3.png"))
        self.explosions.append(pg.image.load("Sprites/Ex4.png"))
        self.explosions.append(pg.image.load("Sprites/Ex5.png"))
        self.explosions.append(pg.image.load("Sprites/Ex6.png"))
        self.explosions.append(pg.image.load("Sprites/Ex7.png"))
        self.explosions.append(pg.image.load("Sprites/Ex8.png"))
        self.explosions.append(pg.image.load("Sprites/Ex9.png"))
        self.explosions.append(pg.image.load("Sprites/Ex10.png"))

    def activate(self):
        self.active = True

    def draw(self, pos, screen):
        if self.iter == 0:
            self.pos = pos
        if self.active == True:
            screen.blit(self.explosions[self.iter], (self.pos.x - 150, self.pos.y - 150))
            if pg.time.get_ticks() > self.flag_ex + EXPLOSION_DELAY:
                self.flag_ex = pg.time.get_ticks()
                self.iter += 1
                if self.iter == 1:
                    self.alive = False
                if self.iter == 10:
                    self.iter = 0
                    self.alive = True
                    self.active = False

class Obstacles():
    def __init__(self, pos):
        self.obs_image = pg.image.load("Sprites/Thumb.png")
        self.obs = pg.transform.rotozoom(self.obs_image, 0, THUMB_SIZE)
        self.rect = self.obs.get_rect()
        self.speVec = Vector2D(0,0)
        self.obstacle_list = []
        self.obs_flag = 0
        self.pos = pos

    def draw(self, screen):
        if pg.time.get_ticks() > self.obs_flag + OBSTACLE_DELAY:
            self.pos.x = rand.randint(0 + SIDE_DISTANCE, SCREEN_WIDTH - SIDE_DISTANCE - self.rect.width)
            self.obs_flag = pg.time.get_ticks()
            self.pos.y = 0 - self.rect.height
            self.speVec = Vector2D(0,0)
        for i in self.obstacle_list:
            screen.blit(i.obs, (i.pos.x, i.pos.y))

    def move(self):
        self.speVec.y = GRAVITY * OBSTACLE_SPEED
        self.pos += self.speVec

        if self.pos.y > SCREEN_HEIGHT:
            self.obstacle_list.clear()

    def update(self):   #   Removes and adds the image to a new position
        self.obstacle_list.clear()
        self.obstacle_list.append(Obstacles(self.pos))

class Fuel_sheet():
    def __init__(self):
        self.sheet = pg.image.load("Sprites/Music_fuel.png")
        self.music_sheet = pg.transform.rotozoom(self.sheet, 0, SHEET_SIZE)
        self.sheet_pos = Vector2D(SCREEN_WIDTH / 2, SCREEN_HEIGHT / 2)
        self.rect = self.music_sheet.get_rect()

    def update_pos(self):   #   Gives a random spawn location
        self.sheet_pos = Vector2D(rand.randint( 0 + SIDE_DISTANCE + self.rect.width, 
                                                SCREEN_WIDTH - SIDE_DISTANCE - self.rect.width), 
                                                rand.randint(0 + self.rect.height,
                                                SCREEN_HEIGHT - self.rect.height))

    def draw(self, screen):
        screen.blit(self.music_sheet, (self.sheet_pos.x, self.sheet_pos.y))

class Landing_pads():
    def __init__(self, pos_1, pos_2):
        self.pit_stop = pg.image.load("Sprites/Stand.png")
        self.stop = pg.transform.rotozoom(self.pit_stop, 0, STAND_SIZE)
        self.pos_1 = pos_1
        self.pos_2 = pos_2

    def draw(self, screen):
        screen.blit(self.stop, (self.pos_1.x - STAND_POS_DIFFER, self.pos_1.y))
        screen.blit(self.stop, (self.pos_2.x - STAND_POS_DIFFER, self.pos_2.y))

class Game():
    def __init__(self):
        self.clock = pg.time.Clock()
        self.obs = Obstacles(Vector2D(rand.randint(0 + SIDE_DISTANCE, SCREEN_WIDTH - SIDE_DISTANCE - OBS_LENGTH), 0 - OBS_LENGTH))
        self.p1_ship = Spaceship("el_guitar", START_POS_1, pg.image.load("Sprites/Ship.png"), EL_SIZE, pg.K_w, pg.K_a, pg.K_d, pg.K_LCTRL)
        self.p2_ship = Spaceship("box_guitar", START_POS_2, pg.image.load("Sprites/Guitar.png"), BOX_SIZE, pg.K_UP, pg.K_LEFT, pg.K_RIGHT, pg.K_RCTRL)
        #self.sprites = pg.sprite.Group()
        self.land = Landing_pads(START_POS_1, START_POS_2)
        self.sheet = Fuel_sheet()
        self.gameLoop()

    def gameLoop(self):
        pg.init()
        self.screen = pg.display.set_mode(SCREEN_RES)
        screen_name = pg.display.set_caption("Guitar showdown!")

        self.flag = 0

        group = [self.p1_ship, self.p2_ship]

        self.background = pg.image.load("Sprites/Back.png").convert()

        while(1):
            start = time.clock()
            pg.draw.rect(self.screen, BLACK, (0, 0, self.screen.get_width(), self.screen.get_height()))

            self.screen.blit(self.background, (0,0))

            for event in pg.event.get():
                if event.type == pg.QUIT or (event.type == pg.KEYDOWN and event.key == pg.K_ESCAPE):
                    exit()
            
            self.land.draw(self.screen)

            self.obs.draw(self.screen)
            self.obs.move()

            self.p1_ship.bar.draw(self.screen)

            for player in group:
                player.health()
                player.gravity()
                player.controls()
                player.score(group)
                player.fuel(self.screen)
                player.draw(self.screen)
                player.land(player.pos_copy)
                player.health_bar.draw(self.screen)
                player.score_board.draw(self.screen)
                player.ex.draw(player.pos, self.screen)
                player.hit(self.sheet, self.p1_ship.pos, self.p2_ship.pos, self.obs)

            self.sheet.draw(self.screen)

            self.obs.update()

            Bullets.update()
            Bullets.draw(self.screen)

            end = time.clock()
            #print(1/ (end - start))

            self.clock.tick(30) # limit to 30FPS
            pg.display.update()

if __name__ == '__main__':
    Game()