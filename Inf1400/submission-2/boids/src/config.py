#Colours
WHITE = [255,255,255]
RED = [255,0,0]
GREEN = [0,255,0]
BLUE = [0,0,255]
YELLOW = [255, 255, 0]
BLACK = [0,0,0]

#Drawing data
RIGHT_ANGLE = 90
LEFT_ANGLE = 90
EXTEND = 10
LINE_THICKNESS = 2
ANGLE_MULTIPLIER = 3
OBSTACLE_RADIUS = 10

#Screen
SCREEN_RES = (1600, 900)
SCREEN_WIDTH, SCREEN_HEIGHT = SCREEN_RES

# #Detection radius
# EAT_DISTANCE = 0            # How far away a hoik can eat a boid
# HUNT_DISTANCE = 50          # How far away a hoik can detect a boid
# REMOVE_DISTANCE = 10        # How close they can be before removal
# OBS_DISTANCE = 25           # How far away flyers wants to stay from eachother and obstacles
# HOIK_DISTANCE = 10          # How far away a boid can detect a hoik
# FAMILY_RADIUS = 50          # How close a boid has to be to follow the flock
# PREY_DISTANCE = 100         # How far away a hoik can detect a boid
# BAIT_DISTANCE = 25          # How far away a boid can detect a bait

# Detection radius
## HOIK
EAT_RANGE = 0               # How far away a hoik can eat a boid
COMMUNICATION_RANGE = 50    # How far away a hoik can pull another hoik into a hunt
## BOID
FAMILY_RADIUS = 50          # How close a boid has to be to follow the flock
HOIK_DETECTION_RANGE = 25   # Range within which a boid can detect a hoik
EATEN_RANGE = 10            # Minimum distance between flyers before one is removed
## BOTH
FOOD_DETECTION_RANGE = 100  # Range within which a boid can detect a bait and hoiks can detect boids
AVOIDANCE_RADIUS = 25       # Distance at which flyers try to avoid obstacles and each other


#Object limits
BOID_LIMIT = 20
HOIK_LIMIT = 5
BOID_SIZE = 3
HOIK_SIZE = 1
MAX_HOIK_SIZE = 5
GROWTH_RATE = 0.2

#Speed multipliers
BOID_SPEED_RANGE = (1, 3)
HOIK_SPEED_RANGE = (1, 3.5)