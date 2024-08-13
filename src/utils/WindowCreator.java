package utils;

import GameLoop.gameLoop;
import org.lwjgl.glfw.*;
import org.lwjgl.opengl.GL;
import org.lwjgl.system.MemoryStack;
import org.lwjgl.system.Platform;

import java.nio.IntBuffer;

import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.system.MemoryStack.stackPush;

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


public int windowWidth;
public int windowHeight;
    private long window;

    public void createWindow() {
        GLFWErrorCallback.createPrint(System.err).set();
        if (Platform.get() == Platform.LINUX) {
            String sessionType = System.getenv("XDG_SESSION_TYPE");

            // Use .equals() for string comparison
            if (sessionType != null && sessionType.equalsIgnoreCase("wayland") &&
                    glfwPlatformSupported(GLFW_PLATFORM_WAYLAND)) {
                glfwInitHint(GLFW_PLATFORM, GLFW_PLATFORM_WAYLAND);
            } else {
                // Fallback to X11 if Wayland isn't supported or session is X11
                glfwInitHint(GLFW_PLATFORM, GLFW_PLATFORM_X11);
            }
        } else {
            // For non-Linux systems, use any platform
            glfwInitHint(GLFW_PLATFORM, GLFW_ANY_PLATFORM);
        }



        if (!glfwInit()) {// Sometimes, GLFW advertises Wayland support, although it doesn't really work...

        }
        // Get the resolution of the primary monitor
        GLFWVidMode vidmode = glfwGetVideoMode(glfwGetPrimaryMonitor());
        int screenWidth = vidmode.width();
        int screenHeight = vidmode.height();

        // Calculate the window size based on the maximum screen ratio
        windowWidth = screenWidth; // Adjust this ratio as needed
        windowHeight = screenHeight; // Adjust this ratio as needed

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

    public int getWindowHeight() {
        return windowHeight;
    }

    public int getWindowWidth() {
        return windowWidth;
    }
}
