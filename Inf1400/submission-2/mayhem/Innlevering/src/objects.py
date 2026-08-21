from Resources import *
from Config import *
import pygame as pg

class Obstacles():
    """ The obstacle that is the thumb 

    :param pos: A positional vector.
    """
    def __init__(self, pos):
        self.obs_image = pg.image.load("Sprites/Thumb.png")
        self.obs = pg.transform.rotozoom(self.obs_image, 0, THUMB_SIZE)
        self.rect = self.obs.get_rect()
        self.speVec = Vector2D(0,0)
        self.obstacle_list = []
        self.obs_flag = 0
        self.pos = pos

    def draw(self, screen):
        """ Randomizes the position and draws the image.
        """
        if pg.time.get_ticks() > self.obs_flag + OBSTACLE_DELAY:
            self.pos.x = rand.randint(0 + SIDE_DISTANCE, SCREEN_WIDTH - SIDE_DISTANCE - self.rect.width)

            self.obs_flag = pg.time.get_ticks()   #   resets the delay
            self.pos.y = 0 - self.rect.height
            self.speVec = Vector2D(0,0)
            
        for i in self.obstacle_list:
            screen.blit(i.obs, (i.pos.x, i.pos.y))

    def move(self):
        """ Moves the object along the gravity vector
        """
        self.speVec.y = GRAVITY * OBSTACLE_SPEED
        self.pos += self.speVec

    def update(self):
        """ Removes and adds the image to a new position
        """
        self.obstacle_list.clear()
        self.obstacle_list.append(Obstacles(self.pos))

class Fuel_sheet():
    """ The obstacle that is the note sheet.
    """
    def __init__(self):
        self.sheet = pg.image.load("Sprites/Music_fuel.png")
        self.music_sheet = pg.transform.rotozoom(self.sheet, 0, SHEET_SIZE)
        self.sheet_pos = Vector2D(SCREEN_WIDTH / 2, SCREEN_HEIGHT / 2)
        self.rect = self.music_sheet.get_rect()

    def update_pos(self):
        """ Gives a random spawn location.
        """
        self.sheet_pos = Vector2D(rand.randint( 0 + SIDE_DISTANCE + self.rect.width, 
                                                SCREEN_WIDTH - SIDE_DISTANCE - self.rect.width), 
                                                rand.randint(0 + self.rect.height,
                                                SCREEN_HEIGHT - self.rect.height))

    def draw(self, screen):
        """ Draws the image.
        """
        screen.blit(self.music_sheet, (self.sheet_pos.x, self.sheet_pos.y))

class Landing_pads():
    """ The obstacle that is the guitar stand.

    :param pos_1: the positional vector for the left corner
    :param pos_2: the positional vector for the right corner
    """
    def __init__(self, pos_1, pos_2):
        self.pit_stop = pg.image.load("Sprites/Stand.png")
        self.stop = pg.transform.rotozoom(self.pit_stop, 0, STAND_SIZE)
        self.pos_1 = pos_1
        self.pos_2 = pos_2

    def draw(self, screen):
        """ Draws the image.
        """
        screen.blit(self.stop, (self.pos_1.x - STAND_POS_DIFFER, self.pos_1.y))
        screen.blit(self.stop, (self.pos_2.x - STAND_POS_DIFFER, self.pos_2.y))
