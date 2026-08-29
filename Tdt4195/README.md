# TDT4195 — Visual Computing Fundamentals

Coursework for NTNU's introductory graphics and image-processing course.
The course ran two halves: real-time rendering with OpenGL (in Rust), and
image processing / neural networks (in Python). Assignments were numbered
continuously across both halves.

## Assignments

### Assignment 1 — OpenGL Triangles, VAOs & the Rendering Pipeline

**Task:** Set up a Rust/OpenGL program and draw geometry through vertex array
objects, exploring the core pipeline.

**What I built:** A VAO holding multiple distinct triangles, with written
analysis of clipping (vertices outside the [-1, 1] clip cube), index-buffer
winding order (counter-clockwise front faces), the depth buffer, and the roles
of the vertex vs. fragment shader.

**Key concepts:** VAOs/VBOs, index buffers, clipping, winding order, depth
testing, vertex/fragment shaders
**Tools:** Rust, OpenGL (gl), GLSL

### Assignment 2 — Color, Depth & Alpha Blending

**Task:** Render overlapping colored geometry and investigate interpolation,
depth ordering, and transparency.

**What I built:** Triangles with per-vertex colors (smoothly interpolated across
fragments), overlapping shapes at varying depths, and experiments with draw
order and alpha blending — plus an analysis of a set of vertex-shader
transformations and their visual effects.

**Key concepts:** color interpolation, depth ordering, alpha blending,
back-to-front rendering, vertex transformations
**Tools:** Rust, OpenGL, GLSL

### Assignment 3 — Scene Graph: Helicopter over Lunar Terrain

**Task:** Build a scene graph and animate a hierarchical 3D model with lighting
over a terrain mesh.

**What I built:** A scene-graph structure loading OBJ meshes (helicopter and
lunar surface), with normal-based coloring and lighting, multiple helicopters
animated along a path, and hierarchical transforms for the rotors and body.

**Key concepts:** scene graphs, hierarchical transforms, OBJ mesh loading,
normal-based lighting, animation
**Tools:** Rust, OpenGL, GLSL, nalgebra-glm

### Assignment 4 — Image Processing & Neural Networks (Python)

**Task:** First of the image-processing half — spatial filtering and a basic
neural network classifier.

**What I built:** Greyscale conversion and inversion, Sobel edge detection and
smoothing filters applied to a sample image, and a PyTorch neural network
trained on an image dataset (with tasks exploring the model and its results).

**Key concepts:** spatial filtering, convolution, edge detection, image
normalization, neural network training
**Tools:** Python, PyTorch, NumPy, Jupyter

> **Note:** This course had six assignments (three OpenGL, three image
> processing). Assignments 5 and 6 (the remaining image-processing work) are
> not preserved in this repository.
