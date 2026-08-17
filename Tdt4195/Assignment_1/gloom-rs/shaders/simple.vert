#version 430 core

in layout(location=0) vec3 position;

void main()
{
    // With a negative value the position will flip the shape both horisontally and vertically
    gl_Position = vec4(position, 1.0f);
}