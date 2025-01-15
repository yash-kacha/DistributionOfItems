/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pojo;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author HP
 */
public class Result {
    
    String name;
    int gau;
    int ann;
    
    List<Data> dataG = new ArrayList<>();
    List<Data> dataA = new ArrayList<>();

    public Result(String name, int gau, int ann) {
        this.name = name;
        this.gau = gau;
        this.ann = ann;
       
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getGau() {
        return gau;
    }

    public void setGau(int gau) {
        this.gau = gau;
    }

    public int getAnn() {
        return ann;
    }

    public void setAnn(int ann) {
        this.ann = ann;
    }

    public List<Data> getDataG() {
        return dataG;
    }

    public void setDataG(List<Data> dataG) {
        this.dataG = dataG;
    }

    public List<Data> getDataA() {
        return dataA;
    }

    public void setDataA(List<Data> dataA) {
        this.dataA = dataA;
    }

    @Override
    public String toString() {
        return "Result{" + "name=" + name + ", gau=" + gau + ", ann=" + ann + ", dataG=" + dataG + ", dataA=" + dataA + '}';
    }

    
    
    
    
    
    
    
           
}
