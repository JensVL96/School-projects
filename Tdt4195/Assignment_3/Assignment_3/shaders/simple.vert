#version 430 core

layout(location = 2) in vec4 v_color;
layout(location = 3) in vec3 v_normal;
in vec3 position;

out vec4 vertex_color;
out vec3 vertex_normal;
//Found it easier to combine the uniform matrices in an array
layout(location = 6) uniform mat4 transform_matrix[2];
/*
//Matrix used to test multiplication
mat4 transform_matrix = mat4(
    1.0, 0.0, 0.0, 0.0,
    0.0, 1.0, 0.0, 0.0,
    0.0, 0.0, 1.0, 0.0,
    0.0, 0.0, 0.0, 1.0
);
*/
void main()
{
    gl_Position = transform_matrix[0]*vec4(position, 1.0f);
    vertex_color = v_color;
    vertex_normal = normalize(mat3(transform_matrix[1])*v_normal);
}