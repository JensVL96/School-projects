from Resources import *
from Config import *
import pygame as pg

class Bullets():
    bullet_list = []
    """ The bullets that is the notes .

    :param pos: A positional vector.
    :param angle: the angle of the ship.
    :param vec: A directional vector.
    :param model: The name of the ship image to differentiate.
    """
    def __init__(self, pos, angle, vec, model):
        self.pos = pos
        self.vec = vec
        self.model = model
        self.radius = BULLET_RADIUS
        self.bullet_image = pg.image.load("Sprites/Bullet.png")
        self.bullet = pg.transform.rotozoom(self.bullet_image, STARTING_ANGLE + angle, BULLET_SIZE)
        self.rect = self.bullet.get_rect()
        Bullets.fill(self.bullet, pg.Color(rand.randint(0,255), rand.randint(0,255), rand.randint(0,255)))

    def update():
        """ Makes the bullet have a constant velocity and removes them once outside screen.
        """
        for i in Bullets.bullet_list:
            i.pos.x += i.vec.x
            i.pos.y += i.vec.y
            if i.pos.x > SCREEN_WIDTH or i.pos.x < 0 or i.pos.y > SCREEN_HEIGHT or i.pos.y < 0:
                Bullets.bullet_list.remove(i)

    def fill(image, colour):
        """ Changes the colour of the bullets.
        """
        r, g, b, _ = colour
        w, h = image.get_size()
        for x in range(w):
            for y in range(h):
                a = image.get_at((x, y))[3]
                image.set_at((x, y), pg.Color(r, g, b, a))

    def draw(screen):
        """ Draws the bullets.
        """
        for j in Bullets.bullet_list:
            screen.blit(j.bullet, (j.pos.x, j.pos.y))

class Fuel_bar():
    """ The fuel bar.

    :param amount: An integer of the fuel amount.
    """
    def __init__(self, amount):
        self.empty = False
        self.pos_1 = Vector2D(100,50)
        self.pos_2 = Vector2D(SCREEN_WIDTH - 250, 50)
        self.start_width = MAX_FUEL
        self.height = FUEL_TANK_HEIGHT

        #   Changes the state to empty if out of fuel
        if amount > 0:
            pass
        else:
            self.empty = True

    def draw(self, screen):
        """ Draws the frames of the fuel bar background.
        """
        group = [self.pos_1, self.pos_2]

        for i in group:
            frame_pos_x = i.x - BAR_POS_DIFFER
            frame_pos_y = i.y - BAR_POS_DIFFER
            frame_width = self.start_width + BAR_SIZE_DIFFER
            frame_height = self.height + BAR_SIZE_DIFFER
            pg.draw.rect(screen, GREY, (frame_pos_x, frame_pos_y, frame_width, frame_height))
            pg.draw.rect(screen, BLACK, (i.x, i.y, self.start_width, self.height))

class Health_bars():
    """ The health bar.

    :param amount: An integer of the fuel amount.
    :param model: The ship image model
    """
    def __init__(self, amount, model):
        self.dead = False
        self.model = model
        self.amount = amount
        self.width = HEALTH_BAR_WIDTH
        self.height = HEALTH_BAR_HEIGHT
        self.pos_1 = Vector2D(0 + 100,100)
        self.pos_2 = Vector2D(SCREEN_WIDTH - 250, 100)

        #   Changes the state to dead if out of life
        if self.amount > 0:
            pass
        else:
            self.dead = True

    def draw(self, screen):
        """ Draws the health bars.
        """
        if self.model == ("el_guitar"):
            for i in range(self.amount):
                pos_x = self.pos_1.x + i * HEALTH_SPACE_IN_BETWEEN
                pg.draw.rect(screen, GREEN, (pos_x, self.pos_1.y, self.width, self.height))
        else:
            for i in range(self.amount):
                pos_x = self.pos_2.x + i * HEALTH_SPACE_IN_BETWEEN
                pg.draw.rect(screen, GREEN, (pos_x, self.pos_2.y, self.width, self.height))

class Score():
    """ The score.

    :param points: An integer of the score points.
    :param model: The ship image model.
    """
    def __init__(self, points, model):
        self.score = points
        self.model = model

    def draw(self, screen):
        """ Draws the text that is the score.
        """
        self.font = pg.font.SysFont('Comic Sans MS', 22)
        self.text = self.font.render(("Score: %d" %self.score), True, WHITE)

        if self.model == ("el_guitar"):
            screen.blit(self.text, (100,140))
        else:
            screen.blit(self.text, (SCREEN_WIDTH - 250, 140))

class Explosion():
    """ The explosion which is an animation.
    """
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
        """ Starts the explosion sequence.
        """
        self.active = True

    def draw(self, pos, screen):
        """ Draws the iterated images one at the time along with a list.
        """
        if self.iter == 0:
            self.pos = pos
        if self.active == True:
            exps = self.explosions[self.iter]
            size_ex = pg.transform.rotozoom(exps, 0, EXPLOSION_SIZE)
            screen.blit(size_ex, (self.pos.x - size_ex.get_width()/2, self.pos.y - size_ex.get_height()/2))
            if pg.time.get_ticks() > self.flag_ex + EXPLOSION_DELAY:
                self.flag_ex = pg.time.get_ticks()
                self.iter += 1
                if self.iter == 1:
                    self.alive = False
                if self.iter == 10:
                    self.iter = 0
                    self.alive = True
                    self.active = False