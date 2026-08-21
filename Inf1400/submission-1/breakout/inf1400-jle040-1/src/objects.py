from Resources import *
import pygame as pg

screen_res = (1600, 900)

class Paddle(object):

    def __init__ (self, x, y, width, height, screen):
        self.pos = Vector2D(x, y)
        self.width = width
        self.height = height
        self.screen = screen

    def draw(self):
        pg.draw.rect(self.screen, (58,108,165), (self.pos.x, self.pos.y, self.width, self.height)) # Paddle

    def move_Arrows(self):
        if ((pg.key.get_pressed()[pg.K_RIGHT] != 0) and (self.pos.x <= 1370)):
            self.pos.x += 10
        if ((pg.key.get_pressed()[pg.K_LEFT] != 0) and (self.pos.x >= 30)):
            self.pos.x -= 10

    def bounce(self, ball):
        impulse = intersect_rectangle_circle  (self.pos,self.width,self.height,ball.pos,ball.radius,ball.speedv)
        
        if(impulse):
            collisionpoint = Vector2D(ball.pos.x, self.pos.y)
            paddlemiddle = Vector2D(self.pos.x + self.width/2, self.pos.y + self.height/2)
            ball.speedv.x =  (collisionpoint.x - paddlemiddle.x) / 20
            ball.speedv.y =  -collisionpoint.y + paddlemiddle.y

            factor = 8 / abs(ball.speedv)
            ball.speedv.x *= factor
            ball.speedv.y *= factor
            ball.speedv.y = -ball.speedv.y
            pg.mixer.Sound("audio/pong.ogg").play()


class Ball(object):
    def __init__ (self, x, y, radius, speedv, screen):
        self.pos = Vector2D(x, y)
        self.radius = radius
        self.screen = screen
        self.speedv = speedv

    def draw(self):
        pg.draw.circle(self.screen, (255,255,255), (int(self.pos.x), int(self.pos.y)), self.radius) # ball
        self.image = self.pos.x

    def move(self):
        self.pos.x += self.speedv.x
        self.pos.y += self.speedv.y

    def border(self):
        if (self.pos.x >= 1575 - self.radius) or (self.pos.x <= 25 + self.radius):
            self.speedv.x = -self.speedv.x
            pg.mixer.Sound("audio/pong.ogg").play()
        if (self.pos.y <= 25 + self.radius):
            self.speedv.y = -self.speedv.y
            pg.mixer.Sound("audio/pong.ogg").play()

    def draw_vec(self, vec, col):
            pg.draw.line(self.screen, col,  (self.pos.x, self.pos.y), 
                                            (self.pos.x + self.speedv.x * 20, 
                                             self.pos.y + self.speedv.y * 20), 5)


class Brick(object):

    def __init__ (self, x, y, width, height, color, screen):
        self.pos = Vector2D(x, y)
        self.width = width
        self.height = height
        self.screen = screen
        self.color = color

    def draw(self):
        for k in range (5):
            pg.draw.rect(self.screen, self.color, (self.pos.x, self.pos.y, self.width, self.height))

    def hit_brick(self, ball):
        if intersect_rectangle_circle  (self.pos,self.width,self.height,ball.pos,ball.radius,ball.speedv):
            ball.speedv.y = -ball.speedv.y
            return 1
        elif():
            return 0  


class Walls(object):

    def __init__ (self, screen):
        self.screen = screen

    def draw(self):
        pg.draw.rect(self.screen, (178,185,196), (0, 0, 20, screen_res[1]))
        pg.draw.rect(self.screen, (178,185,196), (screen_res[0] - 20, 0, 20, screen_res[1]))
        pg.draw.rect(self.screen, (178,185,196), (0, 0, screen_res[0], 25))


class Align():
    def center(size, offsetX, offsetY):
        cen_res = (screen_res[0]/2, screen_res[1]/2)
        return ((cen_res[0] - size[0]/2) + offsetX, (cen_res[1] - size[1]/2) + offsetY)

    def bot(size, offsetX, offsetY):
        bot_res = ((screen_res[0]/2), (screen_res[1]))
        return ((bot_res[0] - size[0]/2) + offsetX, (bot_res[1] - size[1]) + offsetY)

    def top(size, offsetX, offsetY):
        top_res = (0, 0)
        return (top_res[0], top_res[1])

class Pictures():
    def __init__(self):
        self.img_path = {'win' : "img_res/arwin.png", 
                        'lose' : "img_res/arli.png", 
                        'again' : "img_res/Trying.png",
                        'intro' : "img_res/intro.png",
                        'cont' : "img_res/cont.png",
                        'welcome' : "img_res/Welcome.png"} # Stores image paths

    def imageLoader(self, screen, name, alignment, size, offset=(0,0)):
        if alignment == "center":
            self.pos = Align.center(size, offset[0], offset[1])
        if alignment == "bot":
            self.pos = Align.bot(size, offset[0], offset[1])
        if alignment == "top":
            self.pos = Align.top(size, offset[0], offset[1])

        self.img_load = pg.image.load(self.img_path[name]) # Fetch image
        self.img_trans = pg.transform.scale(self.img_load, size) # Transform image
        screen.blit(self.img_trans, self.pos) # Displays image