#version 430 core

in layout(location=0) vec3 position;
in layout(location=6) vec4 colors;
in layout(location=7) vec3 normal;
// in layout(location=3) vec4 color;


out layout(location=1) vec4 rgba;
out layout(location=6) vec4 vColor;
out layout(location=7) vec3 vNormal;


uniform layout(location=4) mat4 matrix;
uniform layout(location=5) mat4 node_matrix;
// uniform mat4 matrix[2];

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

    vColor = colors;
    vNormal = normalize(mat3(node_matrix) * normal);

    // With a negative value the position will flip the shape both horisontally and vertically
    gl_Position = matrix * vec4(position, 1.0f);

    //Color(new) = Color(Source) * Alpha(Source) + Color(Destination) * (1 - Alpha(Source))
    rgba = vec4(colors.rgb, colors.a);
}