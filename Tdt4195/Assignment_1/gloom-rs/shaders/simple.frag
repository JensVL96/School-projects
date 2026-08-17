#version 430 core

out vec4 color;
uniform layout(location=0) float red;
uniform layout(location=1) float green;
uniform layout(location=2) float blue;

void main()
{
    color = vec4(red, green, blue, 1.0f);
}