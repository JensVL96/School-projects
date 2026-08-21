#version 430 core

out vec4 colors;

in layout(location=1) vec4 rgba;

out vec4 color;

void main()
{
    color = vec4(rgba);
}