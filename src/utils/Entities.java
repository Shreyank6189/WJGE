package utils;

import models.RawModel;
import org.joml.Matrix4f;
import org.joml.Vector3f;

public class Entities {

    private RawModel model;

    private Matrix4f transformationMatrix;

    public Entities(RawModel model, Vector3f pos) {
        this.model = model;
        transformationMatrix = new Matrix4f().identity();
        transformationMatrix.translate(pos, transformationMatrix);
        this.model = model;
    }

    public void setPosition(Vector3f pos) {
        transformationMatrix.identity().translate(pos, transformationMatrix);
    }

    public void scale(float scaleX, float scaleY, float scaleZ) {
        Matrix4f scaleMatrix = new Matrix4f().scale(scaleX, scaleY, scaleZ);
        this.transformationMatrix.mul(scaleMatrix);
    }

    public Matrix4f getTransformationMatrix() {
        return this.transformationMatrix;
    }


 public RawModel getModel(){

     return model;
 }

 public Matrix4f getScale(){
        return transformationMatrix ;
 }
}
