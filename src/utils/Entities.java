package utils;

import models.RawModel;
import org.joml.Matrix4f;
import org.joml.Quaternionfc;
import org.joml.Vector3f;

import java.io.IOException;

public class Entities {

    private RawModel model;

    Vector3f position = new Vector3f();
    private Matrix4f transformationMatrix;
boolean scaledtrue = false;
    Vector3f prevScale = new Vector3f();

    Vector3f scalenotallow = new Vector3f(0,0,0);
    public Entities(String model, Vector3f pos, EntitieList list){
        try {
           OBJLoader loader = new OBJLoader();
           loader.loadOBJ(model);
            this.model = loader.model;
            list.entetieslist.add(this);
            System.out.println(this);
            position = pos;
            transformationMatrix = new Matrix4f().identity();
            transformationMatrix.translate(position, transformationMatrix);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public Entities(RawModel model, Vector3f pos, EntitieList list) {
        this.model = model;
        list.entetieslist.add(this);
        System.out.println(this);
        position = pos;
        transformationMatrix = new Matrix4f().identity();
        transformationMatrix.translate(position, transformationMatrix);
    }

    public void setPosition(Vector3f pos) {
        transformationMatrix.identity().translate(pos, transformationMatrix);
    }

    public void scale(Vector3f scale) {
        transformationMatrix.identity();
        prevScale = scale;
        scaledtrue = true;
        transformationMatrix.scale(scale);
        transformationMatrix.translate(position, transformationMatrix);

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
    System.out.println("h");


    transformationMatrix.identity();
    if (scaledtrue != false) {
        transformationMatrix.scale(prevScale);
        System.out.println("Hq");
    }


    position.x+=x;
    position.y+=y;
    position.z+=z;


    transformationMatrix.translate(position, transformationMatrix);


}


}











