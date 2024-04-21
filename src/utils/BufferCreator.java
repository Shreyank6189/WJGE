package utils;
import org.lwjgl.opengl.GL15;
import org.lwjgl.opengl.GL30;

import java.util.ArrayList;
import java.util.List;


public class BufferCreator {

    private List<Integer> vaos = new ArrayList<Integer>();
    private List<Integer> vbos = new ArrayList<>();
    private List<Integer> fbos = new ArrayList<Integer>();

    public static int FBO;
    public static int VAO;
    public static int VBO;




    public int genVAO() {
        VAO = GL30.glGenVertexArrays();
        GL30.glBindVertexArray(VAO);
        vaos.add(VAO);
        return VAO;

    }

    public int genFBO() {

        FBO = GL30.glGenFramebuffers();
        GL30.glMapBuffer(GL30.GL_FRAMEBUFFER, FBO);
        fbos.add(FBO);
        return FBO;
    }


    public int genVBO() {

        VBO = GL30.glGenBuffers();
        GL30.glBindBuffer(GL30.GL_ARRAY_BUFFER, VBO);
        vbos.add(VBO);
        return VBO;
    }

    public void unbindVBO(){
        GL30.glBindBuffer(0,0);
    }

    public void unbindVAO(){
        GL30.glBindVertexArray(0);
    }

    public void unbindFBO(){
        GL30.glMapBuffer(0,0);
    }

    public void cleanUp(){
        for (int vao:vaos){
            GL30.glDeleteVertexArrays(vao);
        }

        for (int vbo:vbos){
            GL30.glDeleteBuffers(vbo);
        }

        for (int fbo:fbos){
            GL30.glDeleteFramebuffers(fbo);
        }
    }
}