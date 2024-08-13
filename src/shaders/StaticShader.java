package shaders;


import org.joml.Matrix4f;
import org.lwjgl.opengl.GL15;

public class StaticShader extends ShaderProgram{
 public static final String workingDirectory = System.getProperty("user.dir");
    private static final String VERTEX_FILE = "src/shaders/vertexShader.glsl";
    private static final String FRAGMENT_FILE = "src/shaders/fragmentShader.glsl";

    private int location_transformationMatrix;
    private int location_projectionMatrix;
    private int location_viewMatrix;
    public StaticShader() {
        super(VERTEX_FILE, FRAGMENT_FILE);

    }

    @Override
    protected void getAllUniformLocation() {
        location_transformationMatrix = super.getUniformLocation("transformationMatrix");
        location_projectionMatrix = super.getUniformLocation("projectionMatrix");
        location_viewMatrix = super.getUniformLocation("viewMatrix");
    }

    @Override
    protected void bindAttributes() {
        super.bindAttribute(0, "position");
        super.bindAttribute(1, "textureCoords");
    }
public void loadTransformationMatrix(Matrix4f matrix4f){
        super.loadMatrix(location_transformationMatrix,matrix4f);
}

public void loadprojectionMatrix(Matrix4f matrix4f){
        super.loadProjectionMatrix(location_projectionMatrix,matrix4f);
    }

}
