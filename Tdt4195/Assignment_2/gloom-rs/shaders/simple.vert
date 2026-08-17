#version 430 core

in layout(location=0) vec3 position;
in layout(location=1) vec4 colors;

out layout(location=1) vec4 rgba;

//uniform layout(location=2) float variable;
uniform mat4 matrix;

void main()
{
    // Identity matrix
    // mat4x4 matrix = mat4(1);
    // matrix[0][0] = variable;    //a
    // matrix[1][0] = variable;    //b
    // matrix[3][0] = variable;    //c
    // matrix[0][1] = variable;    //d
    // matrix[1][1] = variable;    //e
    // matrix[3][1] = variable;    //f

    // With a negative value the position will flip the shape both horisontally and vertically
    gl_Position = vec4(position, 1.0f) * matrix;

    //Color(new) = Color(Source) * Alpha(Source) + Color(Destination) * (1 - Alpha(Source))
    rgba = vec4(colors.rgb, colors.a);
}