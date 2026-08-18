from Resources import *
from Config import *
import pygame as pg

class Obstacles(pg.sprite.Sprite):
    """ The obstacle that is the thumb 

    :param pos: A positional vector.
    """
    def __init__(self, pos):
        super().__init__()
        self.obs_image = pg.image.load("Sprites/Thumb.png")
        self.image = pg.transform.rotozoom(self.obs_image, 0, THUMB_SIZE)
        self.rect = self.image.get_rect()
        self.speVec = Vector2D(0,0)
        # self.obstacle_list = []
        self.obs_flag = 0
        self.pos = pos
        self.rect.x = SCREEN_WIDTH/2

    def draw(self, screen):
        """ Draws the obstacle image.
        """
        screen.blit(self.image, self.rect)

    def update(self):
        """ Moves the object along the gravity vector and randomizes position periodically. """
        self.speVec.y = GRAVITY * OBSTACLE_SPEED
        self.rect.y += self.speVec.y  # Update the y-coordinate based on the gravity

        # Randomize the position if the delay has passed
        if pg.time.get_ticks() > self.obs_flag + OBSTACLE_DELAY:
            self.rect.x = rand.randint(0 + SIDE_DISTANCE, SCREEN_WIDTH - SIDE_DISTANCE - self.rect.width)
            self.rect.y = 0 - self.rect.height  # Reset the obstacle's y-coordinate
            self.obs_flag = pg.time.get_ticks()  # Reset the delay timer


class Fuel_sheet(pg.sprite.Sprite):
    """ The obstacle that is the note sheet.
    """
    def __init__(self):
        super().__init__()
        self.sheet = pg.image.load("Sprites/Music_fuel.png")
        self.image = pg.transform.rotozoom(self.sheet, 0, SHEET_SIZE)
        self.rect = self.image.get_rect()
        self.rect.topleft = (SCREEN_WIDTH / 2, SCREEN_HEIGHT / 2)
        self.hit = False

    def update(self):
        """ Gives a random spawn location.
        """
        if self.hit:
            # Update position only when hit
            self.rect.topleft = (rand.randint(0 + SIDE_DISTANCE + self.rect.width,
                                            SCREEN_WIDTH - SIDE_DISTANCE - self.rect.width),
                                rand.randint(0 + self.rect.height,
                                            SCREEN_HEIGHT - self.rect.height))
            self.hit = False  # Reset hit status

    def draw(self, screen):
        """ Draws the image.
        """
        screen.blit(self.image, self.rect)

    def on_hit(self):
        """ Function to call when the sprite is hit.
        """
        self.hit = True

class Landing_pads(pg.sprite.Sprite):
    """ The obstacle that is the guitar stand.

    :param pos_1: the positional vector for the left corner
    :param pos_2: the positional vector for the right corner
    """
    def __init__(self, pos):
        super().__init__()
        self.pit_stop = pg.image.load("Sprites/Stand2.png")
        self.image = pg.transform.rotozoom(self.pit_stop, 0, STAND_SIZE)
        self.rect = self.image.get_rect()
        self.pos = pos

    def draw(self, screen):
        """ Draws the image.
        """
        screen.blit(self.image, self.rect)

    def update(self):
        """ Update the positions of the sprite rects. 
        """
        self.rect.topleft = (self.pos.x - STAND_POS_DIFFER, self.pos.y)

class Bullets(pg.sprite.Sprite):
    """ The bullets that is the notes .

    :param pos: A positional vector.
    :param angle: the angle of the ship.
    :param vec: A directional vector.
    :param model: The name of the ship image to differentiate.
    """
    def __init__(self, pos, angle, vec, model, group, obs):
        super().__init__()
        self.model = model
        self.group = group
        self.obs = obs
        self.bullet_image = pg.image.load("Sprites/Bullet.png")
        self.image = pg.transform.rotozoom(self.bullet_image, STARTING_ANGLE + angle, BULLET_SIZE)
        self.rect = self.image.get_rect()
        self.vec = vec
        self.rect.center = (pos.x, pos.y)
        self.fill(self.image, pg.Color(rand.randint(0,255), rand.randint(0,255), rand.randint(0,255)))

    def update(self):
        """ Makes the bullet have a constant velocity and removes them once outside screen.
        """ 
        self.rect.x += self.vec.x
        self.rect.y += self.vec.y

        if not SCREEN_RECT.contains(self.rect):
            self.kill()
        else:
            # Check for collision with the obstacle sprite
            obstacle_collision = pg.sprite.collide_rect(self, self.obs)
            if obstacle_collision:
                self.kill()

    @staticmethod
    def fill(image, colour):
        """ Changes the colour of the bullets.  
        """
        r, g, b, _ = colour
        w, h = image.get_size()
        for x in range(w):
            for y in range(h):
                a = image.get_at((x, y))[3]
                image.set_at((x, y), pg.Color(r, g, b, a))

    def draw(self, screen):
        """ Draws the bullets.
        """
        screen.blit(self.image, self.rect)
