#get the leftmost vertex in the list of the vertices
#this functions checks the x coordinates of list of vertex and return the vertex with the minimum x coordinate
def get_leftmost_vertex(vertices):
    min_x = int()
    left_most = tuple()
    for vertex in vertices:
        if vertex[0] <= min_x:
            min_x = vertex[0]
            left_most = vertex
    return left_most

#get the rightmost vertex in the list of the vertices
#this functions checks the x coordinates of list of vertex and return the vertex with the maximum x coordinate

def get_rightmost_vertex(vertices):
    max_x = int()
    right_most = tuple()
    for vertex in vertices:
        if vertex[0]>= max_x:
            max_x = vertex[0]
            right_most = vertex
    return right_most

#this function tells you whether the point lie above the edge or below
#it takes the determinant and sees the value id determinant is less than 0 lies below else lies above
"""
@params: edge --list of tuple of vertex coordinates
@params: point -- test point
"""
def is_above(edge, point):
    ax = edge[0][0]
    bx = edge[1][0]
    cx = point[0]
    ay = edge[0][1]
    by = edge[1][1]
    cy = point[1]
    det = ((ax - cx)*(by - cy)) - ((bx - cx)*(ay - cy))
    if det < 0:
        return False
    else:
        return True

#this function returns the list of vertices above the certain edge coordinates

def get_above_list(edge, vertices):
    above_list = list()
    for vertex in vertices:
        if(edge[0]!= vertex and edge[1] != vertex) and is_above(edge,vertex):
            above_list.append(vertex)
        else:
            pass
    return above_list

#it checks whether the vertex present in the get_above_list if not it adds to the list_below and returns it

def get_below_list(edge, vertices):
    list_above = get_above_list(edge, vertices)
    list_below = list()
    for vertex in vertices:
        if vertex not in list_above and (edge[0] != vertex and edge[1] != vertex):
            list_below.append(vertex)
    return list_below

#this functio uses above functions to generate the ordered list of vertices
#if taken 2 adjacent in pair gives you an edge and carrying on this process
#gives the polygon

def generate_polygon(vertices):

    left_vertex = get_leftmost_vertex(vertices)
    right_vertex = get_rightmost_vertex(vertices)
    edge = [left_vertex,right_vertex]

    above_list = get_above_list(edge, vertices)
    below_list = get_below_list(edge, vertices)

    above_list.sort(key=lambda dat : dat[0])
    below_list.sort(key=lambda dat: dat[0], reverse =True)
    print(above_list)
    print(below_list)

    final_list = [left_vertex]
    [final_list.append(value) for value in above_list]
    final_list.append(right_vertex)
    [final_list.append(value) for value in below_list]
    final_list.append(left_vertex)
    return final_list


#testing functions and data
'''vertices = [(0,0), (10,10),(0,10),(10,0),(-5,5),(15,5),(0,-2)]
print(get_leftmost_vertex(vertices))
print(get_rightmost_vertex(vertices))
print(is_above([(0,0),(10,0)], (5,-5)))
print(get_below_list([(0,0),(10,0)],vertices))'''

vertices = [(2,7),(5,-20),(-5,-18),(-6,10),(5,13),(10,6)]
print(generate_polygon(vertices))
