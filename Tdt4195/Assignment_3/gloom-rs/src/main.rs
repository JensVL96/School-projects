// Uncomment these following global attributes to silence most warnings of "low" interest:

#![allow(dead_code)]
#![allow(non_snake_case)]
#![allow(unreachable_code)]
#![allow(unused_mut)]
#![allow(unused_unsafe)]
#![allow(unused_variables)]

extern crate nalgebra_glm as glm;
use std::alloc::Layout;
//use core::num::dec2flt::float;
use std::str::CharIndices;
use std::{ mem, ptr, os::raw::c_void };
use std::thread;
use std::sync::{Mutex, Arc, RwLock};
use glm::perspective_fov;
use glutin::event::MouseButton;
use mesh::{Mesh, Helicopter};
use rand::Rng;

mod shader;
mod util;
mod mesh;
mod scene_graph;
mod toolbox;

use glutin::event::{Event, WindowEvent, DeviceEvent, KeyboardInput, ElementState::{Pressed, Released}, VirtualKeyCode::{self, *}};
use glutin::event_loop::ControlFlow;
use scene_graph::{SceneNode, Node};

// initial window size
const INITIAL_SCREEN_W: u32 = 800;
const INITIAL_SCREEN_H: u32 = 600;

// == // Helper functions to make interacting with OpenGL a little bit prettier. You *WILL* need these! // == //

// Get the size of an arbitrary array of numbers measured in bytes
// Example usage:  pointer_to_array(my_array)
fn byte_size_of_array<T>(val: &[T]) -> isize {
    std::mem::size_of_val(&val[..]) as isize
}

// Get the OpenGL-compatible pointer to an arbitrary array of numbers
// Example usage:  pointer_to_array(my_array)
fn pointer_to_array<T>(val: &[T]) -> *const c_void {
    &val[0] as *const T as *const c_void
}

// Get the size of the given type in bytes
// Example usage:  size_of::<u64>()
fn size_of<T>() -> i32 {
    mem::size_of::<T>() as i32
}

// Get an offset in bytes for n units of type T, represented as a relative pointer
// Example usage:  offset::<u64>(4)
fn offset<T>(n: u32) -> *const c_void {
    (n * mem::size_of::<T>() as u32) as *const T as *const c_void
}

// Get a null pointer (equivalent to an offset of 0)
// ptr::null()

// == // Generate your VAO here
unsafe fn create_vao(vertices: &Vec<f32>, normals: &Vec<f32>, indices: &Vec<u32>, colors: &Vec<f32>) -> u32 {
    // * Generate a VAO and bind it (spesify bitesize & i/u)
    let mut arrayVAO: u32 = 0;
    // Used to create a new Vertex Array object. The arrayIDs parameter requires a pointer to where it can be stored, and enough allocated space. The count can be 1 for simplicity
    gl::GenVertexArrays(1, &mut arrayVAO);
    // set configuration values while the VAO is active- Has to be bound before it can be modified
    gl::BindVertexArray(arrayVAO);

    // * Generate a VBO and bind it
    let mut arrayVBO: u32 = 0;
    gl::GenBuffers(1, &mut arrayVBO);
    gl::BindBuffer(gl::ARRAY_BUFFER, arrayVBO);
    // * Fill it with data. Sizeof can give the size of the pointer, so it's necessary to multiply the length of the array with the size of the datatype
    gl::BufferData(gl::ARRAY_BUFFER, byte_size_of_array(vertices), pointer_to_array(vertices), gl::STATIC_DRAW);
    // Specifies where the vertex shader can obtain the data for the attributes and it's formating. 
    // Index can be between 0 and GL_MAX_VERTEX_ATTRIBS.
    gl::EnableVertexAttribArray(0);
    gl::VertexAttribPointer(0, 3, gl::FLOAT, gl::FALSE, 0, ptr::null());

    // * Generate a IBO and bind it
    let mut arrayIBO: u32 = 0;
    gl::GenBuffers(1, &mut arrayIBO);
    gl::BindBuffer(gl::ELEMENT_ARRAY_BUFFER, arrayIBO);
    // * Fill it with data
    gl::BufferData(gl::ELEMENT_ARRAY_BUFFER, byte_size_of_array(indices), pointer_to_array(indices), gl::STATIC_DRAW);

    // Vertex attributes for colors
    let mut arrayColors: u32 = 0;
    gl::GenBuffers(1, &mut arrayColors);
    gl::BindBuffer(gl::ARRAY_BUFFER, arrayColors);
    gl::BufferData(gl::ARRAY_BUFFER, byte_size_of_array(colors), pointer_to_array(colors), gl::STATIC_DRAW);
    gl::EnableVertexAttribArray(1);
    gl::VertexAttribPointer(1, 4, gl::FLOAT, gl::FALSE, 0, ptr::null());

    // Generate normals
    let mut normalVec: u32 = 0;
    gl::GenBuffers(1, &mut normalVec);
    gl::BindBuffer(gl::ARRAY_BUFFER, normalVec);
    gl::BufferData(gl::ARRAY_BUFFER, byte_size_of_array(normals), pointer_to_array(normals), gl::STATIC_DRAW);
    gl::EnableVertexAttribArray(2);
    gl::VertexAttribPointer(2, 3, gl::FLOAT, gl::FALSE, 0, ptr::null());

    // * Return the ID of the VAO
    return arrayVAO;
}

unsafe fn vao_from_mesh(mesh: &mesh::Mesh) -> u32{
    return create_vao(&mesh.vertices,&mesh.normals, &mesh.indices, &mesh.colors);
}

unsafe fn draw_scene(node: &scene_graph::SceneNode, view_projection_matrix: &glm::Mat4, transformation_so_far: &glm::Mat4, shader: i32, time: f32) {
    // Empty transformation node
    let mut trans_node: glm::Mat4 = glm::identity();

    trans_node = glm::translation(&-node.reference_point) * trans_node; // Change the reference point
    trans_node = rotate_ref(&node.rotation) * trans_node;               // Apply the rotation
    trans_node = glm::translation(&node.reference_point) * trans_node;  // Reset origin reference
    trans_node = glm::translation(&node.position) * trans_node;         // Update position
    trans_node = transformation_so_far * trans_node;                    // Apply the parent transformation node to the child
    
    // Check if node is drawable, if so: set uniforms and draw
    if node.index_count > -1 {
        // Sends both transformation nodes to the shader
        gl::UniformMatrix4fv(4, 1, gl::FALSE, (view_projection_matrix * trans_node).as_ptr());
        gl::UniformMatrix4fv(5,1, gl::FALSE, trans_node.as_ptr());

        // Binds and draws
        gl::BindVertexArray(node.vao_id);
        gl::DrawElements(gl::TRIANGLES, node.index_count, gl::UNSIGNED_INT, ptr::null());
    }
    // Recurse
    for &child in &node.children {
        draw_scene(&*child, view_projection_matrix, &trans_node, shader, time);
    }
}

fn rotate_ref(rotation: &glm::Vec3) -> glm::Mat4 {
    let mut trans  = glm::identity();
    trans = glm::rotation(rotation.y, &glm::vec3(0.0, 1.0, 0.0)) * trans;
    trans = glm::rotation(rotation.z, &glm::vec3(0.0, 0.0, 1.0)) * trans;
    trans = glm::rotation(rotation.x, &glm::vec3(1.0, 0.0, 0.0)) * trans;
    return trans;
}

fn main() {
    // Set up the necessary objects to deal with windows and event handling
    let el = glutin::event_loop::EventLoop::new();
    let wb = glutin::window::WindowBuilder::new()
        .with_title("Gloom-rs")
        .with_resizable(true)
        .with_inner_size(glutin::dpi::LogicalSize::new(INITIAL_SCREEN_W, INITIAL_SCREEN_H));
    let cb = glutin::ContextBuilder::new()
        .with_vsync(true);
    let windowed_context = cb.build_windowed(wb, &el).unwrap();
    // Uncomment these if you want to use the mouse for controls, but want it to be confined to the screen and/or invisible.
    //windowed_context.window().set_cursor_grab(true).expect("failed to grab cursor");
    // windowed_context.window().set_cursor_visible(false);

    // Set up a shared vector for keeping track of currently pressed keys
    let arc_pressed_keys = Arc::new(Mutex::new(Vec::<VirtualKeyCode>::with_capacity(10)));
    // Make a reference of this vector to send to the render thread
    let pressed_keys = Arc::clone(&arc_pressed_keys);

    // Set up shared tuple for tracking mouse movement between frames
    let arc_mouse_delta = Arc::new(Mutex::new((0f32, 0f32)));
    // Make a reference of this tuple to send to the render thread
    let mouse_delta = Arc::clone(&arc_mouse_delta);

    // Set up shared tuple for tracking changes to the window size
    let arc_window_size = Arc::new(Mutex::new((INITIAL_SCREEN_W, INITIAL_SCREEN_H, false)));
    // Make a reference of this tuple to send to the render thread
    let window_size = Arc::clone(&arc_window_size);

    // Spawn a separate thread for rendering, so event handling doesn't block rendering
    let render_thread = thread::spawn(move || {
        // Acquire the OpenGL Context and load the function pointers.
        // This has to be done inside of the rendering thread, because
        // an active OpenGL context cannot safely traverse a thread boundary
        let context = unsafe {
            let c = windowed_context.make_current().unwrap();
            gl::load_with(|symbol| c.get_proc_address(symbol) as *const _);
            c
        };

        let mut window_aspect_ratio = INITIAL_SCREEN_W as f32 / INITIAL_SCREEN_H as f32;

        // Set up openGL
        unsafe {
            gl::Enable(gl::DEPTH_TEST);
            gl::DepthFunc(gl::LESS);
            gl::Enable(gl::CULL_FACE);
            gl::Disable(gl::MULTISAMPLE);
            gl::Enable(gl::BLEND);
            gl::BlendFunc(gl::SRC_ALPHA, gl::ONE_MINUS_SRC_ALPHA);
            gl::Enable(gl::DEBUG_OUTPUT_SYNCHRONOUS);
            gl::DebugMessageCallback(Some(util::debug_callback), ptr::null());  

            // Print some diagnostics
            println!("{}: {}", util::get_gl_string(gl::VENDOR), util::get_gl_string(gl::RENDERER));
            println!("OpenGL\t: {}", util::get_gl_string(gl::VERSION));
            println!("GLSL\t: {}", util::get_gl_string(gl::SHADING_LANGUAGE_VERSION));
        }

        // Loads the mesh resources
        let TerrainMesh: mesh::Mesh = mesh::Terrain::load("./resources/lunarsurface.obj");
        let Helicopter = mesh::Helicopter::load("./resources/helicopter.obj");

        // Uses the mesh to create VAOs
        let TerrainVAO = unsafe {vao_from_mesh(&TerrainMesh)};
        let Heli_Body = unsafe {vao_from_mesh(&Helicopter.body)};
        let Heli_Door = unsafe {vao_from_mesh(&Helicopter.door)};
        let Heli_MRot = unsafe {vao_from_mesh(&Helicopter.main_rotor)};
        let Heli_TRot = unsafe {vao_from_mesh(&Helicopter.tail_rotor)};

        // == // Set up your shaders here glClear()), you have to redraw the scene every single frame. Drawing a scene
        // Basic usage of shader helper:
        // The example code below creates a 'shader' object.
        // It which contains the field `.program_id` and the method `.activate()`.
        // The `.` in the path is relative to `Cargo.toml`.
        // This snippet is not enough to do the exercise, and will need to be modified (outside
        // of just using the correct path), but it only needs to be called once
        let simple_shader = unsafe {
            shader::ShaderBuilder::new()
                .attach_file("./shaders/simple.frag")
                .attach_file("./shaders/simple.vert")
                .link()
        };
        unsafe {simple_shader.activate();};

        // The root node for the scene
        let mut scene_node;
        
        // Scene node vectors for specifying transformations to parts of the mesh
        let mut helicopters: Vec<scene_graph::Node> = Vec::new();
        let mut helicopter_main_rotors: Vec<scene_graph::Node> = Vec::new();
        let mut helicopter_tail_rotors: Vec<scene_graph::Node> = Vec::new();
        unsafe{
            scene_node = scene_graph::SceneNode::new();

            // Creates the terrain node from the mesh and puts it as a child to the scene
            let mut terrain_node = scene_graph::SceneNode::from_vao(TerrainVAO, TerrainMesh.index_count);
            scene_node.add_child(&terrain_node);

            // Created seperately since it's the same for all helicopters as it is never transformed
            let mut heli_door_node = scene_graph::SceneNode::from_vao(Heli_Door, Helicopter.door.index_count);
            
            // Loop for handling multiple helicopters
            for i in 0..5 {
                // creates the helicopter nodes
                let mut helicopter_node = scene_graph::SceneNode::new();
                let mut heli_body_node = scene_graph::SceneNode::from_vao(Heli_Body, Helicopter.body.index_count);
                let mut heli_MRot_node = scene_graph::SceneNode::from_vao(Heli_MRot, Helicopter.main_rotor.index_count);
                let mut heli_TRot_node = scene_graph::SceneNode::from_vao(Heli_TRot, Helicopter.tail_rotor.index_count);

                // Spesifies the only relevant reference point
                heli_TRot_node.reference_point = glm::vec3(0.35, 2.3, 10.4);

                // Applies the relation to the parent nodes
                helicopter_node.add_child(&heli_body_node);
                heli_body_node.add_child(&heli_door_node);
                heli_body_node.add_child(&heli_MRot_node);
                heli_body_node.add_child(&heli_TRot_node);
                scene_node.add_child(&helicopter_node);

                // Saves the node instances in a scene node vector
                helicopters.push(heli_body_node);
                helicopter_main_rotors.push(heli_MRot_node);
                helicopter_tail_rotors.push(heli_TRot_node);
            }
        }

        // Camera values
        let mut cam_rotation: glm::Vec3 = glm::vec3(0.0, 0.0, 0.0); //rotation
        let mut cam_pos: glm::Vec3 = glm::vec3(0.0, 0.0, 0.0);

        // The main rendering loop
        let first_frame_time = std::time::Instant::now();
        let mut prevous_frame_time = first_frame_time;
        loop {
            // Compute time passed since the previous frame and since the start of the program
            let now = std::time::Instant::now();
            let elapsed = now.duration_since(first_frame_time).as_secs_f32();
            let delta_time = now.duration_since(prevous_frame_time).as_secs_f32();
            prevous_frame_time = now;

            // Handle resize events
            if let Ok(mut new_size) = window_size.lock() {
                if new_size.2 {
                    context.resize(glutin::dpi::PhysicalSize::new(new_size.0, new_size.1));
                    window_aspect_ratio = new_size.0 as f32 / new_size.1 as f32;
                    (*new_size).2 = false;
                    println!("Resized");
                    unsafe { gl::Viewport(0, 0, new_size.0 as i32, new_size.1 as i32); }
                }
            }

            // Handle keyboard input
            let mut cam_motion: glm::Vec3 = glm::vec3(0.0, 0.0, 0.0); // motion
            if let Ok(keys) = pressed_keys.lock() {
                for key in keys.iter() {
                    match key {
                        // The `VirtualKeyCode` enum is defined here:
                        //    https://docs.rs/winit/0.25.0/winit/event/enum.VirtualKeyCode.html

                        VirtualKeyCode::A => {
                            cam_motion[0] -= delta_time * 100.0;
                        }
                        VirtualKeyCode::D => {
                            cam_motion[0] += delta_time * 100.0;
                        }
                        VirtualKeyCode::W => {
                            cam_motion[2] -= delta_time * 100.0;
                        }
                        VirtualKeyCode::S => {
                            cam_motion[2] += delta_time * 100.0;
                        }
                        VirtualKeyCode::LShift => {
                            cam_motion[1] -= delta_time * 100.0;
                        }
                        VirtualKeyCode::Space => {
                            cam_motion[1] += delta_time * 100.0;
                        }
                        VirtualKeyCode::Up => {
                            cam_rotation[0] -= delta_time;
                        }
                        VirtualKeyCode::Down => {
                            cam_rotation[0] += delta_time;
                        }
                        VirtualKeyCode::Right => {
                            cam_rotation[1] += delta_time;
                        }
                        VirtualKeyCode::Left => {
                            cam_rotation[1] -= delta_time;
                        }
                        VirtualKeyCode::Numpad6 => {
                            cam_rotation[2] += delta_time;
                        }
                        VirtualKeyCode::Numpad4 => {
                            cam_rotation[2] -= delta_time;
                        }
                        // default handler:
                        _ => { }
                    }
                }
            }
            // Handle mouse movement. delta contains the x and y movement of the mouse since last frame in pixels
            if let Ok(mut delta) = mouse_delta.lock() {
                *delta = (0.0, 0.0); // reset when done
            }

            // == // Please compute camera transforms here (exercise 2 & 3)
            // Getting the real-world direction of the change
            let camera_vec: glm::Vec4 = glm::inverse(&rotate_ref(&cam_rotation)) * glm::vec4(cam_motion[0], cam_motion[1], cam_motion[2], 1.0);

            // Applying the vector to the camera position
            cam_pos.x += camera_vec[0];
            cam_pos.y += camera_vec[1];
            cam_pos.z += camera_vec[2];

            // Empty transformation
            let mut cam_trans: glm::Mat4 = glm::identity();
            // trans = glm::scaling(&glm::vec3(1.0, 1.0, 1.0)) * trans;                                                                 // Apply scaling
            cam_trans = glm::translation(&-cam_pos) * cam_trans;                                                                        // Apply translation
            cam_trans = rotate_ref(&cam_rotation) * cam_trans;                                                                          // Apply rotation
            cam_trans = glm::perspective(window_aspect_ratio, 45.0f32.to_radians(), 1.0, 1000.0) * cam_trans;    // Apply perspective

            unsafe {
                // Clear the color and depth buffers
                gl::ClearColor(0.035, 0.046, 0.078, 1.0); // night sky, full opacity
                gl::Clear(gl::COLOR_BUFFER_BIT | gl::DEPTH_BUFFER_BIT);

                // Matrix location in shader
                let local = simple_shader.get_uniform_location("matrix");

                // Applies object spesific transformations
                for i in 0..5 {
                    let heading = toolbox::simple_heading_animation(elapsed + i as f32 * 0.8);
                    helicopter_main_rotors[i].rotation = glm::vec3(0.0, (elapsed * 10.0)%6.28, 0.0);
                    helicopter_tail_rotors[i].rotation = glm::vec3((elapsed * 10.0)%6.28, 0.0, 0.0);
                    helicopters[i].rotation = glm::vec3(heading.pitch, heading.yaw, heading.roll);
                    helicopters[i].position = glm::vec3(heading.x, 20.0, heading.z);
                }

                // Empty transformation for the scene node
                let mut trans_ref: glm::Mat4 = glm::identity();

                // draws and updates the scene nodes to te transformations of the parents
                draw_scene(&scene_node, &cam_trans, &trans_ref, local, elapsed);
            }

            // Display the new color buffer on the display
            context.swap_buffers().unwrap(); // we use "double buffering" to avoid artifacts
        }
    });


    // == //
    // == // From here on down there are only internals.
    // == //


    // Keep track of the health of the rendering thread
    let render_thread_healthy = Arc::new(RwLock::new(true));
    let render_thread_watchdog = Arc::clone(&render_thread_healthy);
    thread::spawn(move || {
        if !render_thread.join().is_ok() {
            if let Ok(mut health) = render_thread_watchdog.write() {
                println!("Render thread panicked!");
                *health = false;
            }
        }
    });

    // Start the event loop -- This is where window events are initially handled
    el.run(move |event, _, control_flow| {
        *control_flow = ControlFlow::Wait;

        // Terminate program if render thread panics
        if let Ok(health) = render_thread_healthy.read() {
            if *health == false {
                *control_flow = ControlFlow::Exit;
            }
        }

        match event {
            Event::WindowEvent { event: WindowEvent::Resized(physical_size), .. } => {
                println!("New window size! width: {}, height: {}", physical_size.width, physical_size.height);
                if let Ok(mut new_size) = arc_window_size.lock() {
                    *new_size = (physical_size.width, physical_size.height, true);
                }
            }
            Event::WindowEvent { event: WindowEvent::CloseRequested, .. } => {
                *control_flow = ControlFlow::Exit;
            }
            // Keep track of currently pressed keys to send to the rendering thread
            Event::WindowEvent { event: WindowEvent::KeyboardInput {
                    input: KeyboardInput { state: key_state, virtual_keycode: Some(keycode), .. }, .. }, .. } => {

                if let Ok(mut keys) = arc_pressed_keys.lock() {
                    match key_state {
                        Released => {
                            if keys.contains(&keycode) {
                                let i = keys.iter().position(|&k| k == keycode).unwrap();
                                keys.remove(i);
                            }
                        },
                        Pressed => {
                            if !keys.contains(&keycode) {
                                keys.push(keycode);
                            }
                        }
                    }
                }

                // Handle Escape and Q keys separately
                match keycode {
                    Escape => { *control_flow = ControlFlow::Exit; }
                    Q      => { *control_flow = ControlFlow::Exit; }
                    _      => { }
                }
            }
            Event::DeviceEvent { event: DeviceEvent::MouseMotion { delta }, .. } => {
                // Accumulate mouse movement
                if let Ok(mut position) = arc_mouse_delta.lock() {
                    *position = (position.0 + delta.0 as f32, position.1 + delta.1 as f32);
                }
            }
            _ => { }
        }
    });
}
