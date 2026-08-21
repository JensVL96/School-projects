extern crate nalgebra_glm as glm;
use gl::types::*;
use std::{
    mem,
    ptr,
    str,
    os::raw::c_void,
};
use std::thread;
use std::sync::{Mutex, Arc, RwLock};

mod shader;
mod util;
mod mesh;
mod scene_graph;
mod toolbox;

use glutin::event::{Event, WindowEvent, KeyboardInput, ElementState::{Pressed, Released}, VirtualKeyCode::{self, *}};
use glutin::event_loop::ControlFlow;

const SCREEN_W: u32 = 800;
const SCREEN_H: u32 = 600;



pub struct Helicopter{
    pub main: scene_graph::Node,
    pub rotor: scene_graph::Node,
    pub tail: scene_graph::Node
}

// Helper functions to make interacting with OpenGL a little bit prettier. You will need these!
// The names should be pretty self explanatory
fn byte_size_of_array<T>(val: &[T]) -> isize {
    std::mem::size_of_val(&val[..]) as isize
}

// Get the OpenGL-compatible pointer to an arbitrary array of numbers
fn pointer_to_array<T>(val: &[T]) -> *const c_void {
    &val[0] as *const T as *const c_void
}

// Get the size of the given type in bytes
fn size_of<T>() -> i32 {
    mem::size_of::<T>() as i32
}

// Get an offset in bytes for n units of type T
fn offset<T>(n: u32) -> *const c_void {
    (n * mem::size_of::<T>() as u32) as *const T as *const c_void
}

pub struct transform{
    pub x: f32,
    pub y: f32,
    pub z: f32,
    pub pitch: f32,
    pub yaw: f32,
    pub roll: f32,
}

pub fn loop_ty_loop(time: f32) -> transform{
    return transform{
        x: 0.0,
        y: 50.0 + 50.0*time.cos(),
        z: -50.0*time.sin(),
        pitch: -time,
        yaw: 0.0,
        roll: time*5.0 
    }
}
// == // Modify and complete the function below for the first task
// unsafe fn FUNCTION_NAME(ARGUMENT_NAME: &Vec<f32>, ARGUMENT_NAME: &Vec<u32>) -> u32 { } 

unsafe fn make_vao_from_vertices(vertex_vector: &Vec<f32>, color_vector: &Vec<f32>, normal_vector: &Vec<f32>,index_vector: &Vec<u32>) -> u32{
    let mut array_id: u32 = 0;

    gl::GenVertexArrays(1, &mut array_id);
    gl::BindVertexArray(array_id);

    let mut vertex_buffer_id: u32 = 0;

    gl::GenBuffers(1, &mut vertex_buffer_id);
    gl::BindBuffer(
        gl::ARRAY_BUFFER,
        vertex_buffer_id);

    gl::BufferData(
        gl::ARRAY_BUFFER, 
        byte_size_of_array::<f32>(vertex_vector), 
        pointer_to_array::<f32>(vertex_vector), 
        gl::STATIC_DRAW);
    
    gl::VertexAttribPointer(
        0, 
        3, 
        gl::FLOAT, 
        gl::FALSE, 
        size_of::<f32>()*3  as gl::types::GLint, 
        std::ptr::null()
    );
    gl::EnableVertexAttribArray(0);
    let mut triangle_buffer_id: u32 = 0;

    gl::GenBuffers(1, &mut triangle_buffer_id);
    gl::BindBuffer(gl::ELEMENT_ARRAY_BUFFER, triangle_buffer_id);
    gl::BufferData(gl::ELEMENT_ARRAY_BUFFER, byte_size_of_array::<u32>(index_vector), pointer_to_array::<u32>(index_vector), gl::STATIC_DRAW);
    
    gl::VertexAttribPointer(
        1,
        3,
        gl::UNSIGNED_INT, 
        gl::FALSE, 
        size_of::<u32>()*3  as gl::types::GLint, 
        std::ptr::null());
    
    gl::EnableVertexAttribArray(1);

    let mut color_buffer_id: u32 = 0;
    gl::GenBuffers(1, &mut color_buffer_id);
    gl::BindBuffer(gl::ARRAY_BUFFER, color_buffer_id);
    gl::BufferData(
        gl::ARRAY_BUFFER, 
        byte_size_of_array::<f32>(color_vector), 
        pointer_to_array::<f32>(color_vector),
        gl::STATIC_DRAW
    );
    gl::VertexAttribPointer(
        2,
        4,
        gl::FLOAT, 
        gl::FALSE, 
        size_of::<f32>()*4  as gl::types::GLint, 
        std::ptr::null());
    
    gl::EnableVertexAttribArray(2);

    let mut normal_buffer_id: u32 = 0;
    gl::GenBuffers(1, &mut normal_buffer_id);
    gl::BindBuffer(gl::ARRAY_BUFFER, normal_buffer_id);
    gl::BufferData(
        gl::ARRAY_BUFFER,
        byte_size_of_array::<f32>(normal_vector),
        pointer_to_array::<f32>(normal_vector),
        gl::STATIC_DRAW
    );
    gl::VertexAttribPointer(
        3,
        3,
        gl::FLOAT,
        gl::TRUE,
        size_of::<f32>()*3 as gl::types::GLint,
        std::ptr::null()
    );
    gl::EnableVertexAttribArray(3);

    return array_id
}


unsafe fn make_vao_from_mesh(mesh: &mesh::Mesh) -> u32{
    return make_vao_from_vertices(&mesh.vertices, &mesh.colors, &mesh.normals, &mesh.indices);
}
fn make_rotation_matrix(rot: &glm::Vec3) -> glm::Mat4 {
    /*
    Rotates in following order: yaw, pitch, roll
    */
    let mut matrix = glm::rotation(rot[1], &glm::vec3(0.0,1.0,0.0));
    matrix = glm::rotation(rot[2], &glm::vec3(0.0,0.0,1.0)) * matrix;
    matrix = glm::rotation(rot[0], &glm::vec3(1.0,0.0,0.0)) * matrix;
    
    return matrix;
}

fn make_camera_transform(pos: [f32; 3], rot: &glm::Vec3) -> glm::Mat4{
    
    let mut matrix: glm::Mat4 = glm::identity();
    //Translates the position so the camara is at the world origin
    matrix = glm::translation(&glm::vec3(pos[0], pos[1], pos[2])) * matrix;

    //Rotates the world so the camera faces the new z-direction
    matrix = make_rotation_matrix(rot) * matrix;

    //Adds perspective
    matrix = glm::perspective(1.3, 1.5, 1.0, 1000.0) * matrix;

    return matrix;
} 

unsafe fn draw_scene(root: &scene_graph::SceneNode, view_projection_matrix: &glm::Mat4){
    //Only try to draw node if it contains a mesh
    if root.index_count > -1 {
        let mat = [(view_projection_matrix*root.current_transformation_matrix), root.current_transformation_matrix];
        gl::UniformMatrix4fv(6 , 2, gl::FALSE, mat.as_ptr() as *const f32);
        gl::BindVertexArray(root.vao_id);
        gl::DrawElements(gl::TRIANGLES, root.index_count, gl::UNSIGNED_INT,  std::ptr::null());
    }
    for &child in &root.children{
        draw_scene(&*child, view_projection_matrix);
    }
}
unsafe fn update_node_transformations(root: &mut scene_graph::SceneNode, current_transform: &glm::Mat4){
    let mut new_transform: glm::Mat4 = glm::identity();
    //First rotate it around its reference point
    new_transform = glm::translation(&root.reference_point) * make_rotation_matrix(&root.rotation) * glm::translation(&-root.reference_point) * new_transform;
    //Add local position
    new_transform = glm::translation(&root.position) * new_transform;
    //Add previous transform
    new_transform = current_transform * new_transform;

    root.current_transformation_matrix = new_transform;
    for &child in &root.children{
        update_node_transformations(&mut *child, &root.current_transformation_matrix);
    }
}
fn main() {
    // Set up the necessary objects to deal with windows and event handling
    let el = glutin::event_loop::EventLoop::new();
    let wb = glutin::window::WindowBuilder::new()
        .with_title("Gloom-rs")
        .with_resizable(false)
        .with_inner_size(glutin::dpi::LogicalSize::new(SCREEN_W, SCREEN_H));
    let cb = glutin::ContextBuilder::new()
        .with_vsync(true);
    let windowed_context = cb.build_windowed(wb, &el).unwrap();
    
    let speed = 30.0;

    // Set up a shared vector for keeping track of currently pressed keys
    let arc_pressed_keys = Arc::new(Mutex::new(Vec::<VirtualKeyCode>::with_capacity(10)));
    // Send a copy of this vector to send to the render thread
    let pressed_keys = Arc::clone(&arc_pressed_keys);

    let lunarMesh: mesh::Mesh = mesh::Terrain::load("resources/lunarsurface.obj");
    let helicopter = mesh::Helicopter::load("resources/helicopter.obj");
    let heli_body: mesh::Mesh = helicopter.body;
    let heli_rot: mesh::Mesh = helicopter.main_rotor;
    let heli_door: mesh::Mesh = helicopter.door;
    let heli_tail: mesh::Mesh = helicopter.tail_rotor;


    // Spawn a separate thread for rendering, so event handling doesn't block rendering
    let render_thread = thread::spawn(move || {
        // Acquire the OpenGL Context and load the function pointers. This has to be done inside of the renderin thread, because
        // an active OpenGL context cannot safely traverse a thread boundary
        let context = unsafe {
            let c = windowed_context.make_current().unwrap();
            gl::load_with(|symbol| c.get_proc_address(symbol) as *const _);
            c
        };

        // Set up openGL
        unsafe {
            gl::Enable(gl::CULL_FACE);
            gl::Disable(gl::MULTISAMPLE);
            gl::Enable(gl::BLEND);
            gl::BlendFunc(gl::SRC_ALPHA, gl::ONE_MINUS_SRC_ALPHA);
            gl::Enable(gl::DEBUG_OUTPUT_SYNCHRONOUS);
            gl::DebugMessageCallback(Some(util::debug_callback), ptr::null());
            gl::Enable(gl::DEPTH_TEST);
            gl::DepthFunc(gl::LESS);
        }

        // == // Set up your VAO here
        let mut VAO: u32 = 0;
        
        // Basic usage of shader helper
        // The code below returns a shader object, which contains the field .program_id
        // The snippet is not enough to do the assignment, and will need to be modified (outside of just using the correct path)
        // shader::ShaderBuilder::new().attach_file("./path/to/shader").link();
        //let program_id: u32 = gl::CreateProgram();

        //Setting up the adress for the camera transform matrix
        
        let mut scene_node;
        let mut helicopters: Vec<Helicopter> = Vec::new();
        //let mut helicopter_rotor_node;
        //let mut helicopter_tail_node;
        unsafe{
            let program: u32 = shader::ShaderBuilder::new().attach_file("./shaders/simple.vert").attach_file("./shaders/simple.frag").link().program_id;
            gl::UseProgram(program);
            //model_matrix_loc = gl::GetUniformLocation(program, "model_transform".as_ptr() as *const i8);
            scene_node = scene_graph::SceneNode::new();
            let mut terrain_node = scene_graph::SceneNode::from_vao(make_vao_from_mesh(&lunarMesh), lunarMesh.index_count);
            
            let mut helicopter_door_node = scene_graph::SceneNode::from_vao(make_vao_from_mesh(&heli_door), heli_door.index_count);
            
            
            scene_node.add_child(&terrain_node);
            
            for i in 0..5{
                let mut helicopter_node = scene_graph::SceneNode::from_vao(make_vao_from_mesh(&heli_body), heli_body.index_count);
                let helicopter_rotor_node = scene_graph::SceneNode::from_vao(make_vao_from_mesh(&heli_rot), heli_rot.index_count);
                let mut helicopter_tail_node = scene_graph::SceneNode::from_vao(make_vao_from_mesh(&heli_tail), heli_tail.index_count);
                helicopter_tail_node.reference_point = glm::vec3(0.35, 2.3, 10.4);
                helicopter_node.add_child(&helicopter_door_node);
                helicopter_node.add_child(&helicopter_rotor_node);
                helicopter_node.add_child(&helicopter_tail_node);
                scene_node.add_child(&helicopter_node);
                let mut heli = Helicopter{
                    main: helicopter_node,
                    tail: helicopter_tail_node,
                    rotor: helicopter_rotor_node
                };
                helicopters.push(heli);
            }
        }

        

        // Used to demonstrate keyboard handling -- feel free to remove
        let mut _arbitrary_number = 0.0;

        let first_frame_time = std::time::Instant::now();
        let mut last_frame_time = first_frame_time;


        //Values to keep track of camera position and rotation
        let mut cam_world_pos: [f32; 3] = [0.0,0.0,-3.0];
        let mut cam_rotation: glm::Vec3 = glm::vec3(0.0,0.0,0.0);
        // The main rendering loop
        loop {
            let now = std::time::Instant::now();
            let elapsed = now.duration_since(first_frame_time).as_secs_f32();
            let delta_time = now.duration_since(last_frame_time).as_secs_f32();
            last_frame_time = now;
            
            let mut delta_pos: [f32; 3] = [0.0,0.0,0.0];

            // Handle keyboard input
            if let Ok(keys) = pressed_keys.lock() {
                for key in keys.iter() {
                    match key {
                        VirtualKeyCode::A => {
                            delta_pos[0] += speed*delta_time;
                        },
                        VirtualKeyCode::D => {
                            delta_pos[0] -= speed*delta_time;
                        },
                        VirtualKeyCode::W => {
                            delta_pos[2] += speed*delta_time;
                        },
                        VirtualKeyCode::S => {
                            delta_pos[2] -= speed*delta_time;
                        },
                        VirtualKeyCode::E => {
                            delta_pos[1] += speed*delta_time;
                        },
                        VirtualKeyCode::Q => {
                            delta_pos[1] -= speed*delta_time;
                        },
                        VirtualKeyCode::Up => {
                            cam_rotation[0] -= delta_time;
                        },
                        VirtualKeyCode::Down => {
                            cam_rotation[0] += delta_time;
                        },
                        VirtualKeyCode::Left => {
                            cam_rotation[1] -= delta_time;
                        },
                        VirtualKeyCode::Right => {
                            cam_rotation[1] += delta_time;
                        }

                        _ => { }
                    }
                }
            }

            //TASK 5a)

            //Multiplying the position change vector with the inverse of the camera rotation matrix to get the real-world direction of the change
            let delta_vec: glm::Vec4 = glm::inverse(&make_rotation_matrix(&cam_rotation)) * glm::vec4(delta_pos[0], delta_pos[1], delta_pos[2], 0.1);
            
            //Adding the modified vector to the camera position
            cam_world_pos[0] += delta_vec.data[0];
            cam_world_pos[1] += delta_vec.data[1];
            cam_world_pos[2] += delta_vec.data[2];
            let elapsed = now.duration_since(first_frame_time).as_secs_f32();
            //helicopter_rotor_node.rotation = ;
            //helicopter_tail_node.rotation = 

            for i in 0..helicopters.len(){
                //let heading = toolbox::simple_heading_animation(elapsed + i as f32);
                let spin = loop_ty_loop(elapsed + i as f32);
                helicopters[i].rotor.rotation = glm::vec3(0.0, 10.0*elapsed, 0.0);
                helicopters[i].tail.rotation = glm::vec3(-15.0*elapsed, 0.0, 0.0);
                helicopters[i].main.position = glm::vec3(spin.x, spin.y, spin.z);
                helicopters[i].main.rotation = glm::vec3(spin.pitch, spin.yaw, spin.roll);
            }
            
            //helicopter_node.position = 
            //helicopter_node.rotation = glm::vec3(0.0, elapsed, 0.0);;//;


            unsafe {
                gl::ClearColor(0.163, 0.163, 0.163, 1.0);
                gl::Clear(gl::COLOR_BUFFER_BIT | gl::DEPTH_BUFFER_BIT);

                // Issue the necessary commands to draw your scene here
                let cam_matrix: glm::Mat4 = make_camera_transform(cam_world_pos, &cam_rotation);
                //gl::UniformMatrix4fv(matrix_loc,1,gl::FALSE, cam_matrix.as_ptr() as *const f32);
                update_node_transformations(&mut scene_node, &glm::identity());
                draw_scene(&scene_node, &cam_matrix);

                /*
                gl::BindVertexArray(lunar_terrain.VAO);
                gl::DrawElements(gl::TRIANGLES, lunar_terrain.indices, gl::UNSIGNED_INT,  std::ptr::null());
                for part in &helicopter{
                    gl::BindVertexArray(part.VAO);
                   
                }
                
                */
                
            }

            context.swap_buffers().unwrap();
        }
    });

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

    // Start the event loop -- This is where window events get handled
    el.run(move |event, _, control_flow| {
        *control_flow = ControlFlow::Wait;

        // Terminate program if render thread panics
        if let Ok(health) = render_thread_healthy.read() {
            if *health == false {
                *control_flow = ControlFlow::Exit;
            }
        }

        match event {
            Event::WindowEvent { event: WindowEvent::CloseRequested, .. } => {
                *control_flow = ControlFlow::Exit;
            },
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

                // Handle escape separately
                match keycode {
                    Escape => {
                        *control_flow = ControlFlow::Exit;
                    },
                    _ => { }
                }
            },
            _ => { }
        }
    });
}
