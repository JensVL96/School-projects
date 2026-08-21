#version 430 core

in vec4 vertex_color;
in vec3 vertex_normal;
out vec4 color;

vec3 light_dir = normalize(vec3(0.8, -0.5, 0.6));
void main()
{
    color = vec4(vertex_color.rgb*max(0.0, -dot(vertex_normal, light_dir)), vertex_color.a);
}