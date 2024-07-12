package utils;

import org.joml.Matrix4f;

public class ProjectionManager {
    private Matrix4f projectionMatrix;
    private  WindowCreator windowmanager;

    private long window;




    public ProjectionManager (long window, EntitieList list, WindowCreator windows) {
        this.windowmanager = windows;

        createProjectionMatrix();
        new KeyInvoke(window, list.entetieslist);

    }



    private void createProjectionMatrix() {
        projectionMatrix = new Matrix4f();
        projectionMatrix.identity();
        float fov = (float) java.lang.Math.toRadians(90.0f);
        float zFar = 0.1f;
        float zNear = 100.0f;
        projectionMatrix.perspective(fov, (float) windowmanager.getWindowWidth() / (float) windowmanager.getWindowHeight(), zNear, zFar);

    }

    public Matrix4f getProjectionMatrix(){
        return projectionMatrix;
    }

}

