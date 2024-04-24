package utils;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;

public class OBJLoader{

    public OBJLoader(String path) throws IOException {
        FileReader fr = new FileReader(path);
        BufferedReader bufferedReader = new BufferedReader(fr);

        String line;
        while (line == bufferedReader.readLine() != null){
                if (line.startsWith(" vt")){

                }

        }



    }

}

