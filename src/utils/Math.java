package utils;

import org.joml.Matrix4f;
import org.joml.Vector3f;



public class Math {

    public static Matrix4f createTransformationMatrix(Vector3f translation, float rx, float ry, float rz, float scale) {
        Matrix4f matrix = new Matrix4f();
        matrix.identity();
        matrix.translate(translation, matrix);
        matrix.rotate((float) java.lang.Math.toRadians(rx), new Vector3f(1,0,0), matrix);
        matrix.rotate((float) java.lang.Math.toRadians(ry), new Vector3f(0,1,0), matrix);
        matrix.rotate((float) java.lang.Math.toRadians(rz), new Vector3f(0,0,1), matrix);
        matrix.scale(new Vector3f(scale,scale,scale),matrix);
        return matrix;



    }

}
