package utils;

import models.RawModel;
import org.lwjgl.opengl.*;
import shaders.StaticShader;

import java.util.ArrayList;

import static org.lwjgl.opengl.GL11.GL_COLOR_BUFFER_BIT;
import static org.lwjgl.opengl.GL11.GL_DEPTH_BUFFER_BIT;

public class Renderer {

    ModelArray modelArray = new ModelArray();
   // private ArrayList<RawModel> arrayModel = new ArrayList<>();


    public void prepare(){
        GL11.glClearColor(1,0,0,1);
        GL11.glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT); // clear the framebuffer
        GL30.glEnable(GL30.GL_DEPTH_TEST);

    }

    public void render(Entities entities, StaticShader shader){

        RawModel model = entities.getModel();
            GL30.glBindVertexArray(model.getVaoID());
            GL20.glEnableVertexAttribArray(0);
            GL20.glEnableVertexAttribArray(1);
            shader.loadTransformationMatrix(entities.getTransformationMatrix());
            GL13.glActiveTexture(GL13.GL_TEXTURE);
            GL11.glBindTexture(GL11.GL_TEXTURE_2D, model.getTextureID());
            GL11.glDrawElements(GL11.GL_TRIANGLES, model.getVertexCount(), GL11.GL_UNSIGNED_INT, 0);

            GL20.glDisableVertexAttribArray(0);
            GL20.glDisableVertexAttribArray(1);
            GL30.glBindVertexArray(0);
        }


    }
