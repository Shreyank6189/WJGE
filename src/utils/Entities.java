package utils;

import models.RawModel;
import org.joml.Matrix4f;
import org.joml.Quaternionfc;
import org.joml.Vector3f;

public class Entities {

    private RawModel model;

    Vector3f position = new Vector3f();
    private Matrix4f transformationMatrix;

    public Entities(RawModel model, Vector3f pos, EntitieList list) {
        this.model = model;
        list.entetieslist.add(this);
        System.out.println(this);
        position = pos;
        transformationMatrix = new Matrix4f().identity();
        transformationMatrix.translate(position, transformationMatrix);
        this.model = model;
    }

    public void setPosition(Vector3f pos) {
        transformationMatrix.identity().translate(pos, transformationMatrix);
    }

    public void scale(Vector3f scale) {
        transformationMatrix.scale(scale);
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

public void increasPos(float x, float y, float z){
        position.x+=x;
        position.y+=y;
        position.z+=z;
    transformationMatrix.identity();
    transformationMatrix.translate(position, transformationMatrix);


}


}
