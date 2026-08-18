#version 430 core

in layout(location=0) vec3 position;
in layout(location=1) vec4 colors;
in layout(location=2) vec3 normal;


out layout(location=1) vec4 vColor;
out layout(location=2) vec3 vNormal;


uniform layout(location=4) mat4 matrix;
uniform layout(location=5) mat4 node_matrix;

void main()
{
    vColor = colors;
    // vNormal = normal;
    vNormal = normalize(mat3(node_matrix) * normal);

    // With a negative value the position will flip the shape both horisontally and vertically
    gl_Position = matrix * vec4(position, 1.0f);
}