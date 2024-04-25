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
    public RawModel OBJLoader(String path) throws IOException {


        try {


        FileReader fr = new FileReader(path);
        BufferedReader bufferedReader = new BufferedReader(fr);

        String line = null;
        while (line == bufferedReader.readLine()) {
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
              faces.add(Integer.parseInt(tokens[1]));
                faces.add(Integer.parseInt(tokens[2]));
                faces.add(Integer.parseInt(tokens[3]));
            } else if (line.startsWith("vt ")) {
                String[] tokens = line.split("\\s+");
                texturevertexes.add(Float.parseFloat(tokens[1]));
                texturevertexes.add(Float.parseFloat(tokens[2]));
                texturevertexes.add(Float.parseFloat(tokens[3]));

            }
        }

            float[] vertice = new float[vertices.size()];
            Float[] vertexnormal = new Float[vertexnormals.size()];
            float[] texturevertices = new float[texturevertexes.size()];
            int[] indeces = new int[faces.size()];
            Loader loader = new Loader();
            RawModel rawModel = new RawModel(loader.loadToVAO(vertice, texturevertices, indeces));
            return rawModel;

        } catch (IOException | NumberFormatException e) {
            throw new RuntimeException(e);

        }


    }


}

