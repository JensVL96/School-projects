# TDT4230 — Graphics and Visualization

Final project for NTNU's real-time graphics course, built on an OpenGL 4.3 /
C++ scene-graph framework. The course covers the modern programmable GPU
pipeline, GLSL shaders, lighting, texturing, and advanced visualization
techniques.

## Final Project — Melting Icicle Scene (Fresnel Refraction, Cubemaps, Dynamic Texturing)

**Task:** Investigate an advanced visualization technique in depth and use it to
render a non-trivial scene, documenting the implementation, challenges, and what
the technique is good and bad at.

**What I built:** A real-time icicle scene rendered in OpenGL/GLSL, combining
three main techniques:
- **Fresnel refraction** on the ice — the icicle shader computes a view vector
  from the camera, refracts it through the surface normal using the air-to-ice
  index ratio (~1.0/1.309), samples the environment, and blends the result with
  alpha to fake light and background bending through the ice.
- **Cubemap environment mapping** — six skybox faces bound to a single cubemap
  texture, which also feeds the icicle shader as the environment sampled by the
  refraction.
- **Dynamic texturing** — a time-driven ("epoch") uniform scrolls a water-drop
  texture down the icicle to simulate melting, using per-vertex normals recomputed
  to wrap cylindrically around a Blender-modeled mesh (loaded via tinyobjloader).

Also added a multi-sprite lens-flare effect for the sun and a scrolling skybox.
The icicle count is data-driven — adding one is just adding a coordinate.

**Notable challenges:** getting the six cubemap faces oriented correctly (trial
and error at the seams); writing a custom OBJ loader path for the Blender mesh
and reassigning normals so the dynamic texture wraps cleanly.

**Key concepts:** GLSL shaders, Fresnel/Snell refraction, cubemap sampling,
environment mapping, dynamic/animated texturing, Phong lighting with attenuation,
lens flare, scene graphs
**Language/tools:** C++, OpenGL 4.3, GLSL, CMake, Blender, tinyobjloader
**Deliverables:** `src/` (application), `res/shaders/` (GLSL), `report.pdf`
