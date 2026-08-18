import pygame as pg
import os

class Game:
    def __init__(self):
        pg.init()
        self.screen = pg.display.set_mode((800, 600))
        self.clock = pg.time.Clock()
        self.running = True
        self.load_music()

    def load_music(self):
        music_file = os.path.join("Sprites", "cartoon.mp3")
        pg.mixer.music.load(music_file)
        pg.mixer.music.play(-1)

    def main_loop(self):
        while self.running:
            self.handle_events()
            self.update()
            self.draw()
            self.clock.tick(60)

    def handle_events(self):
        for event in pg.event.get():
            if event.type == pg.QUIT:
                self.running = False

    def update(self):
        pass

    def draw(self):
        self.screen.fill((0, 0, 0))
        pg.display.flip()

    def play_music(self):
        pg.mixer.music.unpause()

    def pause_music(self):
        pg.mixer.music.pause()

if __name__ == '__main__':
    game = Game()
    game.main_loop()
