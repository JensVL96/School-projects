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

// const NUMBER_OF_TRIANGLES: i32 = 3;

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

    // * Generate a IBO and bind it colors
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
    gl::EnableVertexAttribArray(6);
    gl::VertexAttribPointer(6, 4, gl::FLOAT, gl::FALSE, 0, ptr::null());

    // Generate normals
    let mut normalVec: u32 = 0;
    gl::GenBuffers(1, &mut normalVec);
    gl::BindBuffer(gl::ARRAY_BUFFER, normalVec);
    gl::BufferData(gl::ARRAY_BUFFER, byte_size_of_array(normals), pointer_to_array(normals), gl::STATIC_DRAW);
    gl::EnableVertexAttribArray(7);
    gl::VertexAttribPointer(7, 3, gl::FLOAT, gl::FALSE, 0, ptr::null());

    // * Return the ID of the VAO
    return arrayVAO;
}

unsafe fn vao_from_mesh(mesh: &mesh::Mesh) -> u32{
    return create_vao(&mesh.vertices,&mesh.normals, &mesh.indices, &mesh.colors);
}

unsafe fn draw_scene(node: &scene_graph::SceneNode, view_projection_matrix: &glm::Mat4, transformation_so_far: &glm::Mat4, shader: i32, time: f32) {
    let mut trans_node: glm::Mat4 = glm::identity();
    let temp_ref = -node.reference_point;
    trans_node = glm::translation(&temp_ref) * trans_node;
    trans_node = rotate_ref(&node.rotation) * trans_node;
    
    // let heading = toolbox::simple_heading_animation(time);
    
    // if node.vao_id == 5 {
        //     trans_node = glm::rotation((time * 10.0)%6.28, &glm::vec3(1.0, 0.0, 0.0)) * trans_node;
    // }
    // if node.vao_id == 4 {
        //     trans_node = glm::rotation((time * 10.0)%6.28, &glm::vec3(0.0, 1.0, 0.0)) * trans_node;
        // }
        // if node.vao_id == 2 {
            //     // print!("helicopters {}", node[1].vao_id);
    //     // for i in 0..5 {
        //     //     node.position = glm::vec3(heading.)
        //     // }
        //     // trans_node = glm::translation((i*), 0.0, 0.0) * trans_node;
        //     trans_node = glm::rotation(heading.roll, &glm::vec3(0.0, 0.0, 1.0)) * trans_node;
        //     trans_node = glm::rotation(heading.pitch, &glm::vec3(1.0, 0.0, 0.0)) * trans_node;
        //     trans_node = glm::rotation(heading.yaw, &glm::vec3(0.0, 1.0, 0.0)) * trans_node;
        //     trans_node = glm::translation(&glm::vec3(heading.x, 0.0, heading.z)) * trans_node;
    // }
    // print!("")
    trans_node = glm::translation(&node.reference_point) * trans_node;
    trans_node = glm::translation(&node.position) * trans_node;
    trans_node = transformation_so_far * trans_node;
    
    // Check if node is drawable, if so: set uniforms and draw
    if node.index_count > -1 {
        // let matrix = [(view_projection_matrix * trans_node), trans_node];
        gl::UniformMatrix4fv(4, 1, gl::FALSE, (view_projection_matrix * trans_node).as_ptr());
        gl::UniformMatrix4fv(5,1, gl::FALSE, trans_node.as_ptr());
        gl::BindVertexArray(node.vao_id);
        gl::DrawElements(gl::TRIANGLES, node.index_count, gl::UNSIGNED_INT, ptr::null());
    }
    // Recurse
    for &child in &node.children {
        draw_scene(&*child, view_projection_matrix, &trans_node, shader, time);
    }
}

unsafe fn update_node(node: &scene_graph::SceneNode, curr_trans: &glm::Mat4) -> glm::Mat4 {

    let mut trans_node: glm::Mat4 = glm::identity();
    let temp_ref = -node.reference_point;
    trans_node = glm::translation(&temp_ref) * trans_node;
    trans_node = glm::rotation(2.0, &glm::vec3(0.0, 0.0, 1.0)) * trans_node;
    trans_node = glm::translation(&node.reference_point) * trans_node;
    trans_node = trans_node * curr_trans;

    return trans_node;
}

unsafe fn rotate_ref(rotation: &glm::Vec3) -> glm::Mat4 {
    let mut trans  = glm::identity();
    trans = glm::rotation(rotation.z, &glm::vec3(0.0, 0.0, 1.0)) * trans;
    trans = glm::rotation(rotation.y, &glm::vec3(0.0, 1.0, 0.0)) * trans;
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


        let TerrainMesh: mesh::Mesh = mesh::Terrain::load("./resources/lunarsurface.obj");
        let Helicopter = mesh::Helicopter::load("./resources/helicopter.obj");
        let TerrainVAO = unsafe {vao_from_mesh(&TerrainMesh)};
        let Heli_Body = unsafe {vao_from_mesh(&Helicopter.body)};
        let Heli_Door = unsafe {vao_from_mesh(&Helicopter.door)};
        let Heli_MRot = unsafe {vao_from_mesh(&Helicopter.main_rotor)};
        let Heli_TRot = unsafe {vao_from_mesh(&Helicopter.tail_rotor)};

        print!("helicopter body vao id{}", Heli_TRot);

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


        let mut scene_node;
        // let mut heli_body_node;
        let mut helicopters: Vec<scene_graph::Node> = Vec::new();
        let mut helicopter_main_rotors: Vec<scene_graph::Node> = Vec::new();
        let mut helicopter_tail_rotors: Vec<scene_graph::Node> = Vec::new();
        unsafe{
            scene_node = scene_graph::SceneNode::new();
            
            let mut terrain_node = scene_graph::SceneNode::from_vao(TerrainVAO, TerrainMesh.index_count);

            scene_node.add_child(&terrain_node);
            
            let mut heli_door_node = scene_graph::SceneNode::from_vao(Heli_Door, Helicopter.door.index_count);
            
            let mut iter = 0.0;
            for i in 0..5 {
                let mut helicopter_node = scene_graph::SceneNode::new();
                let mut heli_body_node = scene_graph::SceneNode::from_vao(Heli_Body, Helicopter.body.index_count);
                let mut heli_MRot_node = scene_graph::SceneNode::from_vao(Heli_MRot, Helicopter.main_rotor.index_count);
                let mut heli_TRot_node = scene_graph::SceneNode::from_vao(Heli_TRot, Helicopter.tail_rotor.index_count);
                heli_TRot_node.reference_point = glm::vec3(0.35, 2.3, 10.4);
                // helicopter_node.position = glm::vec3((iter*10.0), 20.0, 0.0);
                iter = iter + 5.0;

                helicopter_node.add_child(&heli_body_node);
                heli_body_node.add_child(&heli_door_node);
                heli_body_node.add_child(&heli_MRot_node);
                heli_body_node.add_child(&heli_TRot_node);
                scene_node.add_child(&helicopter_node);

                // helicopter_node.print();
                helicopters.push(heli_body_node);
                helicopter_main_rotors.push(heli_MRot_node);
                helicopter_tail_rotors.push(heli_TRot_node);
            }

            // scene_node.print();
            // terrain_node.print();
        }

        // Camera movement
        let mut motion_x_axis: f32 = 0.0; // movement along the x-axis
        let mut motion_y_axis: f32 = 0.0; // movement along the y-axis
        let mut motion_z_axis: f32 = 0.0; // movement along the z-axis
        let mut rotation_x_axis: f32 = 0.0; // rotation around the x-axis
        let mut rotation_y_axis: f32 = 0.0; // rotation around the y-axis
        let mut rotation_z_axis: f32 = 0.0; // rotation around the z-axis

        // Helicopter movement
        let mut motion_forward: f32 = 0.0; // movement along the main-axis
        let mut turn_x_axis: f32 = 0.0; // turn around the x-axis
        let mut turn_y_axis: f32 = 0.0; // turn around the y-axis
        let mut turn_z_axis: f32 = 0.0; // turn around the z-axis
        let mut rotation_tail_rotor: f32 = 0.0; // rotation of the tail rotor
        let mut rotation_main_rotor: f32 = 0.0; // rotation of the main rotor

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
            if let Ok(keys) = pressed_keys.lock() {
                for key in keys.iter() {
                    match key {
                        // The `VirtualKeyCode` enum is defined here:
                        //    https://docs.rs/winit/0.25.0/winit/event/enum.VirtualKeyCode.html

                        VirtualKeyCode::A => {
                            motion_x_axis += delta_time * 30.0;
                        }
                        VirtualKeyCode::D => {
                            motion_x_axis -= delta_time * 30.0;
                        }
                        VirtualKeyCode::W => {
                            motion_z_axis += delta_time * 30.0;
                        }
                        VirtualKeyCode::S => {
                            motion_z_axis -= delta_time * 30.0;
                        }
                        VirtualKeyCode::LShift => {
                            motion_y_axis += delta_time;
                        }
                        VirtualKeyCode::Space => {
                            motion_y_axis -= delta_time;
                        }
                        VirtualKeyCode::Up => {
                            rotation_x_axis += delta_time;
                        }
                        VirtualKeyCode::Down => {
                            rotation_x_axis -= delta_time;
                        }
                        VirtualKeyCode::Left => {
                            rotation_y_axis += delta_time;
                        }
                        VirtualKeyCode::Right => {
                            rotation_y_axis -= delta_time;
                        }
                        VirtualKeyCode::Numpad4 => {
                            rotation_z_axis += delta_time;
                        }
                        VirtualKeyCode::Numpad6 => {
                            rotation_z_axis -= delta_time;
                        }
                        VirtualKeyCode::Numpad1 => {
                            rotation_tail_rotor += delta_time;
                        }
                        VirtualKeyCode::Numpad3 => {
                            rotation_tail_rotor -= delta_time;
                        }
                        VirtualKeyCode::Numpad7 => {
                            rotation_main_rotor += delta_time;
                        }
                        VirtualKeyCode::Numpad9 => {
                            rotation_main_rotor -= delta_time;
                        }
                        // default handler:
                        _ => { }
                    }
                }
            }
            // Handle mouse movement. delta contains the x and y movement of the mouse since last frame in pixels
            if let Ok(mut delta) = mouse_delta.lock() {
                // == // Optionally access the acumulated mouse movement between
                // == // frames here with `delta.0` and `delta.1`
                // rotation_x_axis += delta.0 * 0.01;
                // rotation_y_axis += delta.1 * 0.01;
                // println!("delta.0: {}", delta.0);

                *delta = (0.0, 0.0); // reset when done
            }

            // == // Please compute camera transforms here (exercise 2 & 3)
            // heli_body_node.rotation = glm::vec3(-15.0 * elapsed, 0.0, 0.0);
            // print!("helicopters info {}", helicopters.body);
            // scene_node.children[1].children[2].rotation = glm::vec3(-15.0 * elapsed, 0.0, 0.0);

            unsafe {
                // Clear the color and depth buffers
                gl::ClearColor(0.035, 0.046, 0.078, 1.0); // night sky, full opacity
                gl::Clear(gl::COLOR_BUFFER_BIT | gl::DEPTH_BUFFER_BIT);

                // Time values for color shifting
                // let now = std::time::Instant::now();
                // let elapsed = now.duration_since(first_frame_time).as_secs_f32();
                // let variable = elapsed.sin();
                // gl::Uniform1f(2, variable);

                // Matrix location in shader
                let local = simple_shader.get_uniform_location("matrix");

                // Empty transformation
                let mut trans: glm::Mat4 = glm::identity();
                trans = glm::rotation(rotation_x_axis, &glm::vec3(1.0, 0.0, 0.0))  * trans;    // Apply rotation
                trans = glm::rotation(rotation_y_axis, &glm::vec3(0.0, 1.0, 0.0))  * trans;    // Apply rotation
                trans = glm::rotation(rotation_z_axis, &glm::vec3(0.0, 0.0, 1.0))  * trans;    // Apply rotation
                trans = glm::scaling(&glm::vec3(1.0, 1.0, 1.0))                                                          * trans;    // Apply scaling
                trans = glm::translation(&glm::vec3(motion_x_axis * 10.0, motion_y_axis * 10.0, (motion_z_axis-3.0) * 10.0))* trans;    // Apply translation
                trans = glm::perspective(window_aspect_ratio, 45.0f32.to_radians(), 1.0, 1000.0)          * trans;    // Apply perspective

                // Send as uniform to shader
                // gl::UniformMatrix4fv(local, 1, gl::TRUE, trans.as_ptr());

                // == // Issue the necessary gl:: commands to draw your scene here
                // mode specifies type of primitive (string, point, triangle, etc). Count for triangles should always be in a multiple of 3.
                // datatype: data types of values in index buffer. Indices specifies the start index

                // unsafe {
                //     // Check if node is drawable, if so: set uniforms and draw
                //     if root_node.index_count > -1 {
                //         // gl::UniformMatrix4fv(local, 1, gl::FALSE, trans.as_ptr());
                //         gl::BindVertexArray(root_node.vao_id);
                //         gl::DrawElements(gl::TRIANGLES, root_node.index_count, gl::UNSIGNED_INT, ptr::null());
                //     }
                // }
                // for i in 0..helicopters.len(){
                //     //let heading = toolbox::simple_heading_animation(elapsed + i as f32);
                //     let movement = loop_ty_loop(elapsed + i as f32);
                //     helicopters[i].rotor.rotation = glm::vec3(0.0, 10.0*elapsed, 0.0);
                //     helicopters[i].tail.rotation = glm::vec3(-15.0*elapsed, 0.0, 0.0);
                //     helicopters[i].main.position = glm::vec3(spin.x, spin.y, spin.z);
                //     helicopters[i].main.rotation = glm::vec3(spin.pitch, spin.yaw, spin.roll);
                // }
                // print!("{}", helicopters[0].get_child(3).vao_id);
                for i in 0..5 {
                    let heading = toolbox::simple_heading_animation(elapsed + i as f32 * 0.8);
                    helicopter_main_rotors[i].rotation = glm::vec3(0.0, (elapsed * 10.0)%6.28, 0.0);
                    helicopter_tail_rotors[i].rotation = glm::vec3((elapsed * 10.0)%6.28, 0.0, 0.0);
                    helicopters[i].rotation = glm::vec3(heading.pitch, heading.yaw, heading.roll);
                    helicopters[i].position = glm::vec3(heading.x, 20.0, heading.z);
                }
                
                // trans_so_far = transformatio_so_far(scene_node.reference_point)
                
                // update_nodes(&scene_node, glm::identity());
                let mut trans_ref: glm::Mat4 = glm::identity();
                draw_scene(&scene_node, &trans, &trans_ref, local, elapsed);

                // unsafe {gl::BindVertexArray(TerrainVAO)}
                // gl::DrawElements(gl::TRIANGLES, TerrainMesh.index_count, gl::UNSIGNED_INT, ptr::null());
                // unsafe {gl::BindVertexArray(Heli_Body)}
                // gl::DrawElements(gl::TRIANGLES, Helicopter.body.index_count, gl::UNSIGNED_INT, ptr::null());
                // unsafe {gl::BindVertexArray(Heli_Door)}
                // gl::DrawElements(gl::TRIANGLES, Helicopter.door.index_count, gl::UNSIGNED_INT, ptr::null());
                // unsafe {gl::BindVertexArray(Heli_MRot)}
                // gl::DrawElements(gl::TRIANGLES, Helicopter.main_rotor.index_count, gl::UNSIGNED_INT, ptr::null());
                // unsafe {gl::BindVertexArray(Heli_TRot)}
                // gl::DrawElements(gl::TRIANGLES, Helicopter.tail_rotor.index_count, gl::UNSIGNED_INT, ptr::null());
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
