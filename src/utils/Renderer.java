package utils;

import models.RawModel;
import org.joml.Matrix4f;
import org.lwjgl.opengl.*;
import shaders.StaticShader;

import javax.swing.*;
import java.util.ArrayList;
import java.lang.Math;

import static org.lwjgl.opengl.GL11.GL_COLOR_BUFFER_BIT;
import static org.lwjgl.opengl.GL11.GL_DEPTH_BUFFER_BIT;

public class Renderer {

    ModelArray modelArray = new ModelArray();

    private static final float FOV = 70;
    private static final float NEAR_PLANE = 0.1f;
    private static final float FAR_PLANE = 1000;

    WindowCreator window;

    Matrix4f transformationMatrix;

    public Matrix4f projectionMatrix;

    public Renderer(StaticShader shader, WindowCreator window, ProjectionManager cam){
        projectionMatrix = cam.getProjectionMatrix();
        shader.start();
        shader.loadprojectionMatrix(projectionMatrix);
        shader.stop();
    }
    // private ArrayList<RawModel> arrayModel = new ArrayList<>();


    public void prepare(){
        GL30.glEnable(GL30.GL_DEPTH_TEST);
        GL11.glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT); // clear the framebuffer
        GL11.glClearColor(1,0,0,1);

    }

    public void render(ArrayList<Entities> entities, StaticShader shader){
        for (Entities entitie: entities) {
            RawModel model = entitie.getModel();
            GL30.glBindVertexArray(model.getVaoID());
            GL20.glEnableVertexAttribArray(0);
            GL20.glEnableVertexAttribArray(1);
            transformationMatrix = entitie.getTransformationMatrix();

            shader.loadTransformationMatrix(transformationMatrix);
            GL13.glActiveTexture(GL13.GL_TEXTURE);
            GL11.glBindTexture(GL11.GL_TEXTURE_2D, model.getTextureID());
            GL11.glDrawElements(GL11.GL_TRIANGLES, model.getVertexCount(), GL11.GL_UNSIGNED_INT, 0);
        }


        GL20.glDisableVertexAttribArray(0);
        GL20.glDisableVertexAttribArray(1);
        GL30.glBindVertexArray(0);
    }


    private void createProjectionMatrix(){
        projectionMatrix = new Matrix4f();
       projectionMatrix.identity();
        float fov = (float) Math.toRadians(90.0f);
        float zFar = 0.1f;
        float zNear = 100.0f;
        projectionMatrix.perspective(fov, (float) window.getWindowWidth() / (float) window.getWindowHeight(),zNear, zFar);

    }


}
