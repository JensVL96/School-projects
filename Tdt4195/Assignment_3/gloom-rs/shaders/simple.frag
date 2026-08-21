#version 430 core

out vec4 colors;

in layout(location=1) vec4 vColor;
in layout(location=2) vec3 vNormal;

out vec4 color;

void main()
{
    vec3 LightDirection = normalize(vec3(0.8, -0.5, 0.6));

    // color = vec4(vColor.rgb * max(0, -dot(vNormal.xyz, LightDirection)), vColor.a);

    color = vec4(vNormal, 1.0f);
}   