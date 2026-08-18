""" Determines if the screen and a circle intersect. 
    If they do intersect, the function calculates and 
    returns an impulse vector to resolve the collision.
"""
def intersect_rectangle_circle(rec_pos, rec_width, rec_height, circle_pos, circle_radius, circle_speed):
    # Position of the walls relative to the circle
    top    = (rec_pos.y             ) - circle_pos.y
    bottom = (rec_pos.y + rec_height) - circle_pos.y 
    left   = (rec_pos.x             ) - circle_pos.x
    right  = (rec_pos.x + rec_width ) - circle_pos.x

    r = circle_radius 
    intersecting = left <= r and top <= r and right >= -r and bottom >= -r

    if intersecting:
        impulse = circle_speed.normalize()

        # flip the x and y of the circle
        if abs(left) <= r and impulse.x > 0:
            impulse.x = -impulse.x
        if abs(right) <= r and impulse.x < 0:
            impulse.x = -impulse.x
        if abs(top) <= r and impulse.y > 0:
            impulse.y = -impulse.y
        if abs(bottom) <= r and impulse.y < 0:
            impulse.y = -impulse.y

        return impulse.normalize()

""" determines if two circles intersect.     
"""
def intersect_circles(a_pos, a_radius, b_pos, b_radius):
    
    # Distance between the centers of the two circles 
    distance = (b_pos - a_pos).length()
    
    # return true if the sum is larger than the sum of their radii. 
    return distance <= a_radius + b_radius
