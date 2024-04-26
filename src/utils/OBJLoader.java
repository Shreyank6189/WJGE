package utils;

import models.RawModel;
import org.joml.Vector3f;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class OBJLoader{


    ArrayList<Float> vertices = new ArrayList<>();
    ArrayList<Float> vertexnormals = new ArrayList<>();
    ArrayList<Integer> faces = new ArrayList<>();
    ArrayList<Float> texturevertexes = new ArrayList<>();
    public RawModel loadOBJ (String path) throws IOException {


        try {


        FileReader fr = new FileReader(path);
        BufferedReader bufferedReader = new BufferedReader(fr);

        String line = null;
            while ((line = bufferedReader.readLine()) != null) {
            if (line.startsWith("v ")) {
                String[] tokens = line.split("\\s+");
                vertices.add(Float.parseFloat(tokens[1]));
                vertices.add(Float.parseFloat(tokens[2]));
                vertices.add(Float.parseFloat(tokens[3]));
            } else if (line.startsWith("vn ")) {
                String[] tokens = line.split("\\s+");
                vertexnormals.add(Float.parseFloat(tokens[1]));
                vertexnormals.add(Float.parseFloat(tokens[2]));
                vertexnormals.add(Float.parseFloat(tokens[3]));
            } else if (line.startsWith("f ")) {
                String[] tokens = line.split("\\s+");
                for (int i = 1; i < tokens.length; i++) {
                    String[] parts = tokens[i].split("/");
                    faces.add(Integer.parseInt(parts[0]));
                }
            } else if (line.startsWith("vt ")) {
                String[] tokens = line.split("\\s+");
                texturevertexes.add(Float.parseFloat(tokens[1]));
                texturevertexes.add(Float.parseFloat(tokens[2]));

            }
        }
            float[] verticesArray = new float[vertices.size()];
            int[] indicesArray = new int[faces.size()];
            float[] texturevertexArray = new float[texturevertexes.size()];

            for (int i = 0; i < vertices.size(); i++) {
                verticesArray[i] = vertices.get(i);
            }
            for (int i = 0; i < faces.size(); i++) {
                indicesArray[i] = faces.get(i);
            }
            for (int i = 0; i < texturevertexes.size(); i++) {
                texturevertexArray[i] = texturevertexes.get(i);
            }


            Loader loader = new Loader();
            RawModel rawModel = new RawModel(loader.loadToVAO(verticesArray, texturevertexArray, indicesArray));
            return rawModel;
        } catch (IOException | NumberFormatException e) {
            throw new RuntimeException(e);

        }


    }


}

