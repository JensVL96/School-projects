from math import hypot, cos, sin, radians, pi
from Resources import *
from Config import *
import random as rand
import pygame as pg
import time
import copy

class Spaceship():
    def __init__(self, model, pos):
        self.flag = 0
        self.pos = pos
        self.model = model
        self.light_list = []
        self.collision = False
        self.pos_copy = self.pos

        #   Starting values
        self.points = 0
        self.speVec = Vector2D(0,0)
        self.fuel_amount = MAX_FUEL
        self.angle = - STARTING_ANGLE
        self.health_amount = MAX_HEALTH

        #   Class instances called
        self.score_board = Score(self.points)
        self.ex = Explosion()
        self.bar = Fuel_bar(self.fuel_amount)
        self.health_bar = Health_bars(self.health_amount)

        #   Differentiation between the two ships
        if self.model == ("el_guitar"):
            self.el_guitar()
        else:
            self.box_guitar()

    def el_guitar(self):
        self.ship = pg.image.load("Sprites/Ship.png")
        self.ship = pg.transform.rotozoom(self.ship, STARTING_ANGLE, EL_SIZE)
        self.rect = self.ship.get_rect()
        self.ship_copy = self.ship

    def box_guitar(self):
        self.ship = pg.image.load("Sprites/Guitar.png")
        self.ship = pg.transform.rotozoom(self.ship, STARTING_ANGLE, BOX_SIZE)
        self.rect = self.ship.get_rect()
        self.ship_copy = self.ship

    def draw(self, screen):
        self.screen = screen
        if self.ex.alive == True:
            self.screen.blit(self.ship, (self.pos.x - (self.ship.get_width() / 2), self.pos.y - (self.ship.get_height() / 2)))
            #new_pos_1 = Vector2D(self.pos.x + (self.end_vector.normalized().x * 70), self.pos.y + (self.end_vector.normalized().y * 70))
            #new_pos_2 = Vector2D(self.pos.x - (self.end_vector.normalized().x * 50), self.pos.y - (self.end_vector.normalized().y * 50))
            #pg.draw.circle(screen, GREEN, (int(new_pos_1.x), int(new_pos_1.y)), SHIP_RADIUS, 2)
            #pg.draw.circle(screen, GREEN, (int(self.pos.x), int(self.pos.y)), SHIP_RADIUS, 2)
            #pg.draw.circle(screen, GREEN, (int(new_pos_2.x), int(new_pos_2.y)), SHIP_RADIUS, 2)
        else:
            #   Reset to start values after death
            self.pos = self.pos_copy
            self.speVec = Vector2D(0,0)
            self.fuel_amount = MAX_FUEL
            self.angle = - STARTING_ANGLE
            self.health_amount = MAX_HEALTH

    def move(self, keys, screen):
        #   Rotate the ship
        if keys == ("arrows"):
            if pg.key.get_pressed()[pg.K_LEFT]:
                self.angle += TURNING_ANGLE
            elif pg.key.get_pressed()[pg.K_RIGHT]:
                self.angle -= TURNING_ANGLE
        elif keys == ("wasd"):
            if pg.key.get_pressed()[pg.K_a]:
                self.angle += TURNING_ANGLE
            elif pg.key.get_pressed()[pg.K_d]:
                self.angle -= TURNING_ANGLE

        self.ship = pg.transform.rotozoom(self.ship_copy, self.angle, SHIP_SIZE)

        #   A rotation vector for the ship image to follow
        cos_theta, sin_theta = cos(self.angle / SHIP_ROTATE_DIVIDER), sin(self.angle / SHIP_ROTATE_DIVIDER)

        xEnd = self.pos.x * TURNING_RADIUS * cos_theta
        yEnd = - self.pos.y * TURNING_RADIUS * sin_theta
        self.end_vector = Vector2D(xEnd, yEnd)
        self.end_vector = self.end_vector.normalized()

        #pg.draw.line(screen, WHITE, (self.pos.x, self.pos.y), (self.pos.x + self.end_vector.x * LENGTH_FROM_CENTER, self.pos.y + self.end_vector.y * LENGTH_FROM_CENTER), 2)

        #   Screen border blockade
        if self.pos.x > SCREEN_WIDTH or self.pos.x < 0 or self.pos.y > SCREEN_HEIGHT or self.pos.y < 0:
            if pg.time.get_ticks() > self.flag + RESPAWN_DELAY:
                self.ex.activate()
                self.points += OUTSIDE_BOUNDARY_POINTS
                self.flag = pg.time.get_ticks()
        self.score()

    def controls(self, screen, keys):
        self.move(keys, screen)

        if keys == ("arrows") and self.bar.empty == False:
            if pg.key.get_pressed()[pg.K_UP]:       #   Thrust
                self.speVec += (self.end_vector * SHIP_SPEED)

            elif pg.key.get_pressed()[pg.K_RCTRL]:  #   Shoot
                if pg.time.get_ticks() > self.flag + BULLET_DELAY:
                    bullet_spawn_point_x = self.pos.x - CENTER_POS_DIFFER + self.end_vector.x * LENGTH_FROM_CENTER
                    bullet_spawn_point_y = self.pos.y - CENTER_POS_DIFFER + self.end_vector.y * LENGTH_FROM_CENTER
                    Bullets.bullet_list.append(Bullets(Vector2D(bullet_spawn_point_x, bullet_spawn_point_y), self.angle, self.end_vector * BULLET_SPEED, self.model))
                    self.flag = pg.time.get_ticks()

            elif pg.key.get_pressed()[pg.K_RSHIFT]: #   Respawn
                self.speVec = self.speVec.normalized()
                self.pos = self.pos_copy


        elif keys == ("wasd") and self.bar.empty == False:
            if pg.key.get_pressed()[pg.K_w]:        #   Thrust
                self.speVec += (self.end_vector * SHIP_SPEED)

            elif pg.key.get_pressed()[pg.K_LCTRL]:  #   Shoot
                if pg.time.get_ticks() > self.flag + BULLET_DELAY:
                    bullet_spawn_point_x = self.pos.x - CENTER_POS_DIFFER + self.end_vector.x * LENGTH_FROM_CENTER
                    bullet_spawn_point_y = self.pos.y - CENTER_POS_DIFFER + self.end_vector.y * LENGTH_FROM_CENTER
                    Bullets.bullet_list.append(Bullets(Vector2D(bullet_spawn_point_x,bullet_spawn_point_y), self.angle, self.end_vector * BULLET_SPEED, self.model))
                    self.flag = pg.time.get_ticks()

            elif pg.key.get_pressed()[pg.K_LSHIFT]: #   Respawn
                self.speVec = self.speVec.normalized()
                self.pos = self.pos_copy

    def gravity(self):
        self.speVec.y += GRAVITY
        self.pos += self.speVec

    def land(self, pos):
        #   Takeoff
        if pg.key.get_pressed()[pg.K_w] and self.model == ("el_guitar"):
            return
        if pg.key.get_pressed()[pg.K_UP] and self.model == ("box_guitar"):
            return

        #   Refueling and stopping the ships movement
        if intersect_circles(self.pos, SHIP_RADIUS, Vector2D(pos.x, pos.y + STAND_POS_DIFFER), STAND_RADIUS):
            self.pos.y = self.pos_copy.y + STAND_POS_DIFFER
            self.speVec = Vector2D(0,0)
            self.fuel_amount = MAX_FUEL

    def fuel(self, screen):     #   The fuel bars at the top of the screen
        if self.model == ("el_guitar"):
            pg.draw.rect(screen, GREY, (self.bar.pos_1.x - BAR_POS_DIFFER, self.bar.pos_1.y - BAR_POS_DIFFER, self.bar.start_width + BAR_SIZE_DIFFER, self.bar.height + BAR_SIZE_DIFFER))
            pg.draw.rect(screen, BLACK, (self.bar.pos_1.x, self.bar.pos_1.y, self.bar.start_width, self.bar.height))
            pg.draw.rect(screen, WHITE, (self.bar.pos_1.x, self.bar.pos_1.y, self.fuel_amount, self.bar.height))
            if pg.key.get_pressed()[pg.K_w] and self.fuel_amount > 0:
                self.fuel_amount -= FUEL_DECLINE
        else:
            pg.draw.rect(screen, GREY, (self.bar.pos_2.x - BAR_POS_DIFFER, self.bar.pos_2.y - BAR_POS_DIFFER, self.bar.start_width + BAR_SIZE_DIFFER, self.bar.height + BAR_SIZE_DIFFER))
            pg.draw.rect(screen, BLACK, (self.bar.pos_2.x, self.bar.pos_2.y, self.bar.start_width, self.bar.height))
            pg.draw.rect(screen, WHITE, (self.bar.pos_2.x, self.bar.pos_2.y, self.fuel_amount, self.bar.height))
            if pg.key.get_pressed()[pg.K_UP] and self.fuel_amount > 0:
                self.fuel_amount -= FUEL_DECLINE

        self.bar.__init__(self.fuel_amount)

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

    def health(self, screen):   #   The health bars at the top of the screen
        if self.model == ("el_guitar"):
            for i in range(self.health_amount):
                pg.draw.rect(screen, GREEN, (self.health_bar.pos_1.x + i * HEALTH_SPACE_IN_BETWEEN, self.health_bar.pos_1.y, self.health_bar.width, self.health_bar.height))
        else:
            for i in range(self.health_amount):
                pg.draw.rect(screen, GREEN, (self.health_bar.pos_2.x + i * HEALTH_SPACE_IN_BETWEEN, self.health_bar.pos_2.y, self.health_bar.width, self.health_bar.height))

        self.health_bar.__init__(self.health_amount)
        if self.health_bar.empty == True:
            self.ex.activate()

    def score(self):
        if self.points < 0:
            self.points = 0

        self.score_board.__init__(self.points)

class Bullets():
    bullet_list = []
    def __init__(self, pos, angle, vec, model):
        self.pos = pos
        self.vec = vec
        self.model = model
        self.radius = BULLET_RADIUS
        self.bullet = pg.image.load("Sprites/Bullet.png")
        self.bullet = pg.transform.rotate(self.bullet, STARTING_ANGLE)
        self.bullet_copy = self.bullet
        self.bullet = pg.transform.rotozoom(self.bullet_copy, angle, BULLET_SIZE)
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
            #pg.draw.circle(screen, GREEN, [int(j.pos.x) + j.rect.center[0], int(j.pos.y) + j.rect.center[1]], BULLET_RADIUS, 2)

class Landing_pads():
    def __init__(self):
        self.pit_stop = pg.image.load("Sprites/Stand.png")
        self.stop = pg.transform.rotozoom(self.pit_stop, 0, STAND_SIZE)

    def draw(self, pos_1, pos_2, screen):
        screen.blit(self.stop, (pos_1.x - STAND_POS_DIFFER, pos_1.y))
        screen.blit(self.stop, (pos_2.x - STAND_POS_DIFFER, pos_2.y))

class Obstacles():
    def __init__(self, pos):
        self.obs = pg.image.load("Sprites/Thumb.png")
        self.rect = self.obs.get_rect()
        self.speVec = Vector2D(0,0)
        self.obs_flag = 0
        self.pos = pos
        self.obstacle_list = []

    def draw(self, screen):
        if pg.time.get_ticks() > self.obs_flag + OBSTACLE_DELAY:
            self.pos.x = rand.randint(0 + SIDE_DISTANCE, SCREEN_WIDTH - SIDE_DISTANCE - self.rect.width)
            self.obs_flag = pg.time.get_ticks()
            self.pos.y = 0 - self.rect.height
            self.speVec = Vector2D(0,0)
        for i in self.obstacle_list:
            screen.blit(i.obs, (i.pos.x, i.pos.y))
            self.obs_pos = i.pos
            #pg.draw.circle(screen, GREEN, [int(i.pos.x + self.rect.width / 2), int(i.pos.y + self.rect.height / 2)], OBS_RADIUS, 2)

    def move(self):
        self.speVec.y = GRAVITY * OBSTACLE_SPEED
        self.pos += self.speVec

        if self.pos.y > SCREEN_HEIGHT:
            self.obstacle_list.clear()

    def update(self):   #   Removes and adds the image to a new position
        self.obstacle_list.clear()
        self.obstacle_list.append(Obstacles(self.pos))

class Health_bars():
    def __init__(self, amount):
        self.empty = False
        self.width = HEALTH_BAR_WIDTH
        self.height = HEALTH_BAR_HEIGHT
        self.pos_1 = Vector2D(100,100)
        self.pos_2 = Vector2D(2660, 100)

        if amount > 0:
            pass
        else:
            self.empty = True

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
        #pg.draw.circle(screen, GREEN, [int(self.sheet_pos.x + self.rect.width / 2), int(self.sheet_pos.y + self.rect.height / 2)], SHEET_RADIUS, 2)

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

class Score():
    def __init__(self, points):
        self.score = points

    def draw(self, model, screen):
        self.font = pg.font.SysFont('Comic Sans MS', 22)
        self.text = self.font.render(("Score: %d" %self.score), True, WHITE)

        if model == ("el_guitar"):
            screen.blit(self.text, (100,140))
        else:
            screen.blit(self.text, (SCREEN_WIDTH - 250, 140))

class Explosion(Spaceship):
    def __init__(self):
        self.iter = 0
        self.flag_ex = 0
        self.alive = True
        self.active = False
        self.explosions = []
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

    def draw(self, model, pos, screen):
        if self.iter == 0:
            self.pos = pos
        if self.active == True:
            screen.blit(self.explosions[self.iter], (self.pos.x - 150, self.pos.y - 150))
            if pg.time.get_ticks() > self.flag_ex + EXPLOSION_DELAY:
                self.flag_ex = pg.time.get_ticks()
                self.iter += 1
                if self.iter == 3:
                    self.alive = False
                if self.iter == 10:
                    self.iter = 0
                    self.alive = True
                    self.active = False
        else:
            pass

class Game():
    def __init__(self):
        self.clock = pg.time.Clock()
        self.start_pos_1 = Vector2D(0 + SHIP_POS_X,SCREEN_HEIGHT - SHIP_POS_Y)
        self.start_pos_2 = Vector2D(SCREEN_WIDTH - SHIP_POS_X,SCREEN_HEIGHT - SHIP_POS_Y)
        self.obs = Obstacles(Vector2D(rand.randint(0 + SIDE_DISTANCE, SCREEN_WIDTH - SIDE_DISTANCE - OBS_LENGTH), 0 - OBS_LENGTH))
        self.p1_ship = Spaceship("el_guitar", self.start_pos_1)
        self.p2_ship = Spaceship("box_guitar", self.start_pos_2)
        self.land = Landing_pads()
        self.sheet = Fuel_sheet()
        self.gameLoop()

    def gameLoop(self):
        pg.init()
        screen = pg.display.set_mode(SCREEN_RES)
        screen_name = pg.display.set_caption("Guitar showdown!")

        self.p1_controls = ("wasd")
        self.p2_controls = ("arrows")

        self.flag = 0

        self.background = pg.image.load("Sprites/Back.png").convert()

        while(1):
            start = time.clock()
            pg.draw.rect(screen, BLACK, (0, 0, screen.get_width(), screen.get_height()))

            screen.blit(self.background, (0,0))

            for event in pg.event.get():
                if event.type == pg.QUIT or (event.type == pg.KEYDOWN and event.key == pg.K_ESCAPE):
                    exit()
            
            self.land.draw(self.start_pos_1, self.start_pos_2, screen)

            self.p1_ship.controls(screen, self.p1_controls)
            self.p2_ship.controls(screen, self.p2_controls)

            self.obs.draw(screen)
            self.obs.move()

            self.p1_ship.draw(screen)
            self.p1_ship.gravity()
            self.p1_ship.land(self.start_pos_1)
            self.p1_ship.ex.draw(self.p1_ship, self.p1_ship.pos, screen)
            self.p1_ship.fuel(screen)
            self.p1_ship.health(screen)
            self.p1_ship.hit(self.sheet, self.p1_ship.pos, self.p2_ship.pos, self.obs)
            self.p1_ship.score_board.draw("el_guitar", screen)

            self.p2_ship.draw(screen)
            self.p2_ship.gravity()
            self.p2_ship.land(self.start_pos_2)
            self.p2_ship.ex.draw(self.p2_ship, self.p2_ship.pos, screen)
            self.p2_ship.fuel(screen)
            self.p2_ship.health(screen)
            self.p2_ship.hit(self.sheet, self.p1_ship.pos, self.p2_ship.pos, self.obs)
            self.p2_ship.score_board.draw("box_guitar", screen)

            self.sheet.draw(screen)

            self.obs.update()

            Bullets.update()
            Bullets.draw(screen)

            if self.p1_ship.health_bar.empty:
                if pg.time.get_ticks() > self.flag + RESPAWN_DELAY:
                    self.p2_ship.points += 500
                    self.flag = pg.time.get_ticks()
            if self.p2_ship.health_bar.empty:
                if pg.time.get_ticks() > self.flag + RESPAWN_DELAY:
                    self.p1_ship.points += 500
                    self.flag = pg.time.get_ticks()

            end = time.clock()
            #print(1/ (end - start))

            self.clock.tick(30) # limit to 30FPS
            pg.display.update()

if __name__ == '__main__':
    Game()