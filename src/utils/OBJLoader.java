package utils;

import models.RawModel;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class OBJLoader {
    public static int myPublicVariable = 42;

    public RawModel getModel() {
        return model;
    }

    public RawModel model;

    public void loadOBJ(String path) throws IOException {
        ArrayList<Float> vertices = new ArrayList<>();
        ArrayList<Float> textureVertices = new ArrayList<>();
        ArrayList<Float> normals = new ArrayList<>();
        ArrayList<Integer> faces = new ArrayList<>();

        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(path))) {
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                if (line.startsWith("v ")) {
                    String[] tokens = line.split("\\s+");
                    vertices.add(Float.parseFloat(tokens[1]));
                    vertices.add(Float.parseFloat(tokens[2]));
                    vertices.add(Float.parseFloat(tokens[3]));
                } else if (line.startsWith("vt ")) {
                    String[] tokens = line.split("\\s+");
                    textureVertices.add(Float.parseFloat(tokens[1]));
                    textureVertices.add(Float.parseFloat(tokens[2]));
                }else if (line.startsWith("vn ")) { // Handling normals
                    String[] tokens = line.split("\\s+");
                    normals.add(Float.parseFloat(tokens[1]));
                    normals.add(Float.parseFloat(tokens[2]));
                    normals.add(Float.parseFloat(tokens[3]));
                }
                else if (line.startsWith("f ")) {
                    String[] tokens = line.split("\\s+");
                    for (int i = 1; i < tokens.length; i++) {
                        String[] parts = tokens[i].split("/");
                        faces.add(Integer.parseInt(parts[0]) - 1); // Convert to 0-based index
                    }
                }
            }
        }

        float[] verticesArray = new float[vertices.size()];
        float[] textureVerticesArray = new float[textureVertices.size()];
        float[] normalsArray = new float[normals.size()];
        int[] indicesArray = new int[faces.size()];

        for (int i = 0; i < vertices.size(); i++) {
            verticesArray[i] = vertices.get(i);
        }
        for (int i = 0; i < textureVertices.size(); i++) {
            textureVerticesArray[i] = textureVertices.get(i);
        }
        for (int i = 0; i < normals.size(); i++) {
            normalsArray[i] = normals.get(i); // Copy normals to array
        }
        for (int i = 0; i < faces.size(); i++) {
            indicesArray[i] = faces.get(i);
        }

        Loader loader = new Loader();
System.out.println("Vertices: " + verticesArray.length);
     model = new RawModel(loader.loadToVAO(verticesArray, textureVerticesArray, normalsArray, indicesArray));
    }
}
