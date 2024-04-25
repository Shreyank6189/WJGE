package GameLoop;

import models.RawModel;
import org.joml.Vector3f;
import org.lwjgl.opengl.GL30;
import org.lwjgl.system.MemoryStack;
import shaders.StaticShader;
import utils.*;

import javax.swing.text.html.parser.Entity;
import java.io.IOException;
import java.nio.IntBuffer;
import java.util.Objects;

import static org.lwjgl.glfw.Callbacks.glfwFreeCallbacks;
import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.glfw.GLFW.glfwPollEvents;
import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.system.MemoryStack.stackPush;


public class gameLoop {


    Loader loader = new Loader();

Renderer renderer = new Renderer();
WindowCreator windowcrt;

public long window;

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
            0, 1, // bottom-left
            0, 1, // top-left
            1, 1, // top-right
            1, 0  // bottom-right
    };
    public gameLoop(WindowCreator windowcrt) {
        this.windowcrt = windowcrt;
         window = windowcrt.getWindow();
        initWindow();
    }

    public void initWindow()  {
        RawModel model = loader.loadToVAO(vertices, textureCoords,indices);
        model.addTextureID(loader.loadTexture("C:\\WJGE PROJECT SAVE\\WJGE\\res\\OIP.png"));
        StaticShader shader = new StaticShader();
        Entities entity = new Entities("C:\\WJGE PROJECT SAVE\\WJGE\\res\\Burning_Cube[1].obj",new Vector3f(0.1f,0,0));
       // new ModelArray().addModel(model);
       // System.out.println(model);

        while (!glfwWindowShouldClose(window)) {


            renderer.prepare();
            shader.start();
            renderer.render(entity,shader);

            shader.stop();

            int width, height;
            try (MemoryStack stack = stackPush()) {
                IntBuffer pWidth = stack.mallocInt(1);
                IntBuffer pHeight = stack.mallocInt(1);
                glfwGetFramebufferSize(window, pWidth, pHeight);
                width = pWidth.get(0);
                height = pHeight.get(0);
            }
glEnable(GL_LIGHTING);
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
