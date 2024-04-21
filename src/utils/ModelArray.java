package utils;

import models.RawModel;

import java.util.ArrayList;

public class ModelArray {

    private ArrayList<RawModel> modelArray = new ArrayList<>();

    public ArrayList<RawModel> getModelArray(){

        return modelArray;
    }

    public void addModel(RawModel model){
         modelArray.add(model);
    }
}
