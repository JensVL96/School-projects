""" determines if two circles intersect.     
"""
def intersect_circles(a_pos, a_radius, b_pos, b_radius):
    
    # Distance between the centers of the two circles 
    distance = (b_pos - a_pos).length()
    
    # return true if the sum is larger than the sum of their radii. 
    return distance <= a_radius + b_radius
