from Spaceship import Spaceship
from Resources import *
from objects import *
from classes import *
from Config import *
import random as rand
import pygame as pg
import time

class Game():
    def __init__(self):
        self.clock = pg.time.Clock()
        self.obs = Obstacles(Vector2D(rand.randint(0 + SIDE_DISTANCE, SCREEN_WIDTH - SIDE_DISTANCE - OBS_LENGTH), 0 - OBS_LENGTH))
        self.p1_ship = Spaceship("el_guitar", START_POS_1, pg.image.load("Sprites/Ship.png"), EL_SIZE, CONTROL_PAD_1)
        self.p2_ship = Spaceship("box_guitar", START_POS_2, pg.image.load("Sprites/Guitar.png"), BOX_SIZE, CONTROL_PAD_2)
        self.land = Landing_pads(START_POS_1, START_POS_2)
        self.sheet = Fuel_sheet()
        self.gameLoop()

    def gameLoop(self):
        pg.init()
        self.screen = pg.display.set_mode(SCREEN_RES)
        screen_name = pg.display.set_caption("Guitar showdown!")

        self.flag = 0

        group = [self.p1_ship, self.p2_ship]

        self.background_image = pg.image.load("Sprites/Back.png").convert()
        self.background = pg.transform.rotozoom(self.background_image, 0, BACK_SIZE)

        while(1):
            for event in pg.event.get():
                if event.type == pg.QUIT or (event.type == pg.KEYDOWN and event.key == pg.K_ESCAPE):
                    exit()

            pg.draw.rect(self.screen, BLACK, (0, 0, self.screen.get_width(), self.screen.get_height()))
            self.screen.blit(self.background, (0,0))

            self.sheet.draw(self.screen)
            self.land.draw(self.screen)
            self.obs.draw(self.screen)
            self.obs.update()
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

            Bullets.update()
            Bullets.draw(self.screen)

            self.clock.tick(30) # limit to 30FPS
            pg.display.update()

if __name__ == '__main__':
    Game()