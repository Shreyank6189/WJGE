package utils;

import org.joml.Matrix4f;
import org.joml.Vector3f;

import java.awt.*;
import java.util.ArrayList;

public class Camera {

    public float yaw = 0;
    public float pitch = 0;

    Vector3f position = new Vector3f();
    Vector3f lookDir = new Vector3f();
    Vector3f orientation = new Vector3f();


    public Camera(float x, float y, float z, long window, EntitieList list){
        position = new Vector3f(x,y,z);
        orientation = new Vector3f(0,1,0);

        new KeyInvoke(window, list.entetieslist);

    }

    public void translate(float x, float y, float z){
        position.x += x;
        position.y += y;
        position.z += z;


    }

    private void lookAt(float x, float y){
        yaw = x;
        pitch = y;


    }

    public Matrix4f getMatrix(){
        Vector3f lookPoint = new Vector3f();

        lookPoint.rotateX((float) java.lang.Math.toRadians(pitch), lookPoint);
        lookPoint.rotateY((float) java.lang.Math.toRadians(yaw), lookPoint);

        lookPoint.x = position.x + lookDir.x;
        lookPoint.y = position.y+ lookDir.y;
        lookPoint.z = position.z + lookDir.z;

        Matrix4f matrix = new Matrix4f();
        matrix.lookAt(position, lookPoint, orientation, matrix);
        return matrix;


    }

}