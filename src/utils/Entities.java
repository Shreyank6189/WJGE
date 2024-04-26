package utils;

import models.RawModel;
import org.joml.Matrix4f;
import org.joml.Quaternionfc;
import org.joml.Vector3f;

import java.io.FileInputStream;
import java.io.IOException;

public class Entities {

    private RawModel model;

    private Matrix4f transformationMatrix;

    public Entities(RawModel model, Vector3f pos) {
        this.model = model;
        transformationMatrix = new Matrix4f().identity();
        transformationMatrix.translate(pos, transformationMatrix);
        this.model = model;
    }

    public Entities(String modelPath, Vector3f pos) {
        try {
            OBJLoader objLoader = new OBJLoader();
            RawModel model = objLoader.loadOBJ(modelPath);
            this.model = model;
            transformationMatrix = new Matrix4f().identity().translate(pos);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void setPosition(Vector3f pos) {
        transformationMatrix.identity().translate(pos, transformationMatrix);
    }

    public void scale(Vector3f scale) {
        transformationMatrix.scale(scale);
    }

    public void addTexture(String path){
        Loader loader = new Loader();
       model.addTextureID(loader.loadTexture(path));
    }

    public Matrix4f getTransformationMatrix() {

        return transformationMatrix;

    }

    public void rotate(float rx,float ry, float rz){
        transformationMatrix.rotate((float) java.lang.Math.toRadians(rx),new Vector3f(1,0,0));
        transformationMatrix.rotate((float) java.lang.Math.toRadians(ry),new Vector3f(0,1,0));
        transformationMatrix.rotate((float) java.lang.Math.toRadians(rz),new Vector3f(0,0,1));


    }


 public RawModel getModel(){

     return model;
 }

 public Matrix4f getScale(){
         return transformationMatrix ;
 }
}

