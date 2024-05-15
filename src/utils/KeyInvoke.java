package utils;

import org.lwjgl.glfw.GLFW;

import java.util.ArrayList;

import static org.lwjgl.glfw.GLFW.GLFW_PRESS;

public class KeyInvoke {

    public KeyInvoke(long window, ArrayList<Entities> entitiesArrayList){
        if (GLFW.glfwGetKey(window, GLFW.GLFW_KEY_RIGHT) == GLFW_PRESS || GLFW.glfwGetKey(window, GLFW.GLFW_KEY_D) == GLFW_PRESS) {
            for (Entities entities: entitiesArrayList) {
                entities.increasPos(-0.01f,0,0);

            }

        } else if(GLFW.glfwGetKey(window, GLFW.GLFW_KEY_LEFT) == GLFW_PRESS || GLFW.glfwGetKey(window, GLFW.GLFW_KEY_A) == GLFW_PRESS){
            for (Entities entities : entitiesArrayList){
                entities.increasPos(0.01f,0,0);
            }
        } else if(GLFW.glfwGetKey(window, GLFW.GLFW_KEY_UP) == GLFW_PRESS || GLFW.glfwGetKey(window, GLFW.GLFW_KEY_W) == GLFW_PRESS){
            for (Entities entities : entitiesArrayList){
                entities.increasPos(0f,0,0.01f);
            }
        } else if (GLFW.glfwGetKey(window, GLFW.GLFW_KEY_DOWN) == GLFW_PRESS || GLFW.glfwGetKey(window, GLFW.GLFW_KEY_S) == GLFW_PRESS) {
            for (Entities entities : entitiesArrayList) {
                entities.increasPos(0f, 0, -0.01f);
            }
        }



    }
}

