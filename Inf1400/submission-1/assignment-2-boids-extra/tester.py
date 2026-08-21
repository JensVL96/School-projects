import pygame as pg
import os

pg.init()
music_file = os.path.join("Sprites", "cartoon.mp3")
pg.mixer.music.load(music_file)
pg.mixer.music.play(-1)
running = True

while running:
    for event in pg.event.get():
        if event.type == pg.QUIT:
            running = False