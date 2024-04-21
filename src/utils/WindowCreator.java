package utils;

import GameLoop.gameLoop;
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
            gameLoop loop = new gameLoop(this);
        } // the stack frame is popped automatically
    }

    public long getWindow(){
        return window;
    }


    public void initApp() {
        createWindow();

    }






}
