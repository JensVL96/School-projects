import time
import pygame as pg
from math import hypot, cos, sin, radians, pi
from Resources import Vector2D, intersect_rectangle_circle
from objects import *

def Set_MODE(arg):
    print("in Set_MODE")
    if arg == "Play":
        localMode = "Play"
    elif arg == "Menu":
        localMode = "Menu"
    elif arg == "Game_over":
        localMode = "Game_over"
    elif arg == "You_win":
        localMode = "You_win"
    else:
        raise SystemExit("ERROR: MODE not found")
    print(localMode)
    return localMode

def Game():
    # Startmode
    MODE = "Menu"

    #Screen data
    screen_res = (1600, 800)
    pg.init()

    screen = pg.display.set_mode(screen_res)
    screen_name = pg.display.set_caption("Breakout EXTREME!")
    clock = pg.time.Clock()
    #pg.display.toggle_fullscreen()
    pg.mouse.set_visible(False)

    #Definitions
    paddle = Paddle((screen_res[0] / 2) - 100, screen_res[1] - 20, 200, 8, screen)
    ball = Ball((screen_res[0] / 2), screen_res[1] - 31, 10, Vector2D(0,1), screen)
    walls = Walls(screen)
    pictures = Pictures()

    #States
    active = 0      #state of gameplay
    draw = 1        #removal of enemy

    #Ball vector
    myTheta = 45
    myRadius = 0.009

    #Vector start position
    xStart = paddle.pos.x + (paddle.width / 2)
    yStart = paddle.pos.y

    #Create list
    opp_list = []

    #Music & Sounds
    pg.mixer.Sound("audio/music.ogg").play(999)

    while True:

        #Make screen
        pg.draw.rect(screen, (0,0,0), (0, 0, screen.get_width(), screen.get_height()))
        time_passed = clock.tick(100) # limit to 100FPS
        time_passed_seconds = time_passed / 1000.0   # convert to seconds

        #Plays the game
        if MODE == "Play":
            for event in pg.event.get():
                if event.type == pg.QUIT or (event.type == pg.KEYDOWN and event.key == pg.K_ESCAPE):
                    print("ESC pressed")
                    exit()

            #Game command
            if event.type == pg.KEYDOWN:
                if event.key == pg.K_SPACE: #Start
                    active = 1

            #Called functions
            paddle.bounce(ball)
            paddle.draw()
            ball.draw()
            ball.border()
            walls.draw()
            
            #Adds enemies to list
            if draw == 1:
                for y in range(5):
                        for x in range(20):
                            opp = Brick(70 + 74*x,80 + (70 * y), 50, 20, ((10 + 20 * y),(135 + 20 * x/4),200 * (x/20)), screen)
                            opp_list.append(opp)
                draw = 0

            #Removes hit enemy
            for opp in opp_list:
                opp.draw()
                if opp.hit_brick(ball):
                    pg.mixer.Sound("audio/pong.ogg").play()
                    opp_list.remove(opp)

            #Changes start angle for ball vector
            if pg.key.get_pressed()[pg.K_UP] and myTheta <= 46.7:
                myTheta += .04
            if pg.key.get_pressed()[pg.K_DOWN] and myTheta >= 44.4:
                myTheta -= .04

            cos_theta, sin_theta = cos(myTheta), sin(myTheta)

            xEnd = xStart * myRadius * cos_theta
            yEnd = -yStart * myRadius * sin_theta
            end_vector = Vector2D(xEnd, yEnd)

            #Activates game features at pressed start
            if active == 1:
                paddle.move_Arrows()
                ball.move()

            #Shows start vector after a reset game
            if active != 1:
                ball.draw_vec(ball.speedv, (255,255,0))
                ball = Ball(paddle.pos.x + 105, screen_res[1] - 31, 10, end_vector, screen)
                pictures.imageLoader(screen, 'intro', "top", (1600,900))

            #Ends game if won
            if not(opp_list):
                MODE = Set_MODE("You_win")

            #Ends game if lost
            if ball.pos.y > screen_res[1]:
                MODE = Set_MODE("Game_over")


        elif MODE == "Menu":     
            #Loads the intro images       
            pictures.imageLoader(screen, 'welcome', "top", (1600, 900))
            pictures.imageLoader(screen, 'cont', "top", (1600, 900))

            #Changes the game mode
            for event in pg.event.get():
                if event.type == pg.KEYDOWN and event.key == pg.K_RIGHT:
                    MODE = Set_MODE("Play")

            pg.display.update()


        elif MODE == "Game_over":
            #Loads the endgame images if lost
            pictures.imageLoader(screen, 'lose', "center", (600,200))
            pictures.imageLoader(screen, 'again', "bot", (800,50), (0, -200))
            opp_list.clear()

            #Restart game
            for event in pg.event.get():
                if event.type == pg.KEYDOWN and event.key == pg.K_r:
                        paddle = Paddle((screen_res[0] / 2) - 100, screen_res[1] - 20, 200, 8, screen)
                        active = 0
                        draw = 1 
                        MODE = Set_MODE("Play")
                if event.type == pg.KEYDOWN and event.key == pg.K_ESCAPE:
                    exit()

            pg.display.update()


        elif MODE == "You_win":
            #Loads the endgame images if won
            pictures.imageLoader(screen, 'win', "center", (600,200))
            pictures.imageLoader(screen, 'again', "bot", (800,50), (0, -200))
            opp_list.clear()
            pg.display.update()

            #Restart game
            for event in pg.event.get():
                if event.type == pg.KEYDOWN and event.key == pg.K_r:
                        paddle = Paddle((screen_res[0] / 2) - 100, screen_res[1] - 20, 200, 8, screen)
                        active = 0
                        draw = 1    
                        MODE = Set_MODE("Play")
                if event.type == pg.KEYDOWN and event.key == pg.K_ESCAPE:
                    exit()

        pg.display.update()

if __name__ == '__main__':
    Game()
