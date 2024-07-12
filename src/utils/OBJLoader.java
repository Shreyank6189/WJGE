package utils;

import models.RawModel;
import org.joml.Vector3f;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class OBJLoader {


    ArrayList<Float> vertices = new ArrayList<>();
    ArrayList<Float> vertexnormals = new ArrayList<>();
    ArrayList<Integer> faces = new ArrayList<>();
    ArrayList<Float> texturevertexes = new ArrayList<>();
    RawModel model;

    public RawModel loadOBJ(String path) throws IOException {

        return null;

    }
}