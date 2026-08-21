from Resources import *
from Config import *
import pygame as pg

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

    def update(self):
        pass


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

    def update(self):
        pass

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
        super().__init__()
        self.iter = 0
        self.flag_ex = 0
        self.active = False
        self.explosions = []
        # self.pos = Vector2D(0, 0)  # Initialize explosion position

        # Load explosion images
        for i in range(1, 11):
            self.explosions.append(pg.image.load(f"Sprites/Ex{i}.png"))

    def activate(self, pos):
        """ Starts the explosion sequence at the given position. """
        self.active = True
        self.pos = pos
        # self.iter = 0

    def draw(self, screen):
        """ Draws the iterated images one at the time along with a list.
        """
        # if self.iter == 0:
        #     self.pos = pos
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