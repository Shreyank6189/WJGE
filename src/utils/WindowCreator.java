package utils;

import models.RawModel;
import models.TexturedModel;
import org.lwjgl.glfw.*;
import org.lwjgl.opengl.GL;
import org.lwjgl.system.MemoryStack;
import shaders.StaticShader;
import textures.TextureModel;

import java.nio.IntBuffer;

import static org.lwjgl.glfw.Callbacks.glfwFreeCallbacks;
import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.system.MemoryStack.stackPush;



import java.util.Objects;

@SuppressWarnings("unused")
public class WindowCreator {

    float[] vertices = {
            -0.5f, 0.5f, 0f,//v0
            -0.5f, -0.5f, 0f,//v1
            0.5f, -0.5f, 0f,//v2
            0.5f, 0.5f, 0f,//v3
    };

    int[] indices = {
            0,1,3,//top left triangle (v0, v1, v3)
            3,1,2//bottom right triangle (v3, v1, v2)
    };

    float[] textureCoords = {
            0,1,
            0,1,
            1,1,
            1,0
    };

    Loader loader = new Loader();
    Renderer renderer = new Renderer();


    private long window;

    public void createWindow() {
        GLFWErrorCallback.createPrint(System.err).set();

        if (!glfwInit())
            throw new IllegalStateException("Unable to initialize GLFW");

        // Get the resolution of the primary monitor
        GLFWVidMode vidmode = glfwGetVideoMode(glfwGetPrimaryMonitor());
        int screenWidth = vidmode.width();
        int screenHeight = vidmode.height();

        // Calculate the window size based on the maximum screen ratio
        int windowWidth = screenWidth; // Adjust this ratio as needed
        int windowHeight = screenHeight; // Adjust this ratio as needed

        // Configure GLFW
        glfwDefaultWindowHints();
        glfwWindowHint(GLFW_VISIBLE, GLFW_FALSE);
        glfwWindowHint(GLFW_RESIZABLE, GLFW_TRUE);

        // Create the GLFW window
        window = GLFW.glfwCreateWindow(windowWidth, windowHeight, "WJGE Editor", 0, 0);
        if (window == 0) {
            glfwTerminate();
            throw new IllegalStateException("Failed to create GLFW window");
        }

        try {
            // Make the OpenGL context current for the window
            glfwSetKeyCallback(window, (window, key, scancode, action, mods) -> {
                if (key == GLFW_KEY_ESCAPE && action == GLFW_RELEASE)
                    glfwSetWindowShouldClose(window, true); // We will detect this in the rendering loop
            });
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        try (MemoryStack stack = stackPush()) {
            IntBuffer pWidth = stack.mallocInt(1); // int*
            IntBuffer pHeight = stack.mallocInt(1); // int*

            // Get the window size passed to glfwCreateWindow
            glfwGetWindowSize(window, pWidth, pHeight);

            // Center the window
            glfwSetWindowPos(
                    window,
                    (screenWidth - pWidth.get(0)) / 2,
                    (screenHeight - pHeight.get(0)) / 2
            );

            glfwMakeContextCurrent(window);
            // Enable v-sync
            glfwSwapInterval(1);

            // Make the window visible
            glfwShowWindow(window);
            GL.createCapabilities();
            initWindow();
        } // the stack frame is popped automatically
    }

    public void initWindow() {

        RawModel model = loader.loadToVAO(vertices, textureCoords,indices);
        StaticShader shader = new StaticShader();
        TextureModel texture = new TextureModel(loader.loadTexture("C:\\WJGE PROJECT SAVE\\WJGE\\res\\OIP.png"));
        TexturedModel texturedModel = new TexturedModel(model, texture);


        while (!glfwWindowShouldClose(window)) {


            renderer.prepare();
            shader.start();
            renderer.render(texturedModel);
            shader.stop();

            int width, height;
            try (MemoryStack stack = stackPush()) {
                IntBuffer pWidth = stack.mallocInt(1);
                IntBuffer pHeight = stack.mallocInt(1);
                glfwGetFramebufferSize(window, pWidth, pHeight);
                width = pWidth.get(0);
                height = pHeight.get(0);
            }

            // Update viewport
            glViewport(0, 0, width, height);


            glfwSwapBuffers(window); // swap the color buffers

            // Poll for window events. The key callback above will only be
            // invoked during this call.
            glfwPollEvents();
        }
        shader.cleanUp();
        cleanUp();
    }

    public void initApp() {
        createWindow();

    }

    public void cleanUp(){

        loader.cleanUp();
        glfwSetWindowShouldClose(window, true);
        glfwFreeCallbacks(window);
        glfwDestroyWindow(window);
        glfwTerminate();
        System.out.println("Closed!");
        Objects.requireNonNull(glfwSetErrorCallback(null)).free();
    }




}
