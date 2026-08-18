#Colours
WHITE = [255,255,255]
RED = [255,0,0]
GREEN = [0,255,0]
BLUE = [0,0,255]
YELLOW = [255, 255, 0]
BLACK = [0,0,0]
BOID_COLORS = [BLUE, RED, YELLOW, GREEN]
HOIK_COLORS = [BLUE, RED, GREEN]

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
PANEL_WIDTH = SCREEN_WIDTH // 10

# Rule Weights
OBSTACLE_AVOIDANCE_WEIGHT   = 0.05
FAMILY_AVOIDANCE_WEIGHT     = 0.02
CENTRALIZE_WEIGHT           = 0.05
MATCH_SPEED_WEIGHT          = 0.4
COLLISION_WEIGHT            = 0.5
EVADE_WEIGHT                = 0.01
EATING_WEIGHT               = 0.02
EXPLORE_WEIGHT              = 0.01
DEVIATION_WEIGHT            = 0.1

# Detection radius
## HOIK
EAT_RANGE = 0               # How far away a hoik can eat a boid
COMMUNICATION_RANGE = 50    # How far away a hoik can pull another hoik into a hunt
## BOID
FAMILY_RADIUS = 50          # How close a boid has to be to follow the flock
HOIK_DETECTION_RANGE = 40   # Range within which a boid can detect a hoik
EATEN_RANGE = 10            # Minimum distance between flyers before one is removed
## BOTH
FOOD_DETECTION_RANGE = 100  # Range within which a boid can detect a bait and hoiks can detect boids
AVOIDANCE_RADIUS = 25       # Distance at which flyers try to avoid obstacles and each other
OBSTACLE_AVOIDANCE = 10
# ACTIVE DETECTIONS
BOID_RANGES = [FAMILY_RADIUS, HOIK_DETECTION_RANGE, FOOD_DETECTION_RANGE, AVOIDANCE_RADIUS]
HOIK_RANGES = [COMMUNICATION_RANGE, FOOD_DETECTION_RANGE, AVOIDANCE_RADIUS]

#Object limits
BOID_LIMIT = 50
HOIK_LIMIT = 5
BOID_SIZE = 3
HOIK_SIZE = 1
MAX_HOIK_SIZE = 5
GROWTH_RATE = 0.2
SATURATION_FACTOR = 0.001
BOID_TURN_ANGLE = 10

#Speed multipliers
BOID_SPEED_RANGE = (1, 3)
HOIK_SPEED_RANGE = (1, 3.5)