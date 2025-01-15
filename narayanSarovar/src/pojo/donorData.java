/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pojo;


public class donorData {
    
    String name;
    int gau,ann;

    public donorData(String name, int gau, int ann) {
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

    @Override
    public String toString() {
        return "donorData{" + "name=" + name + ", gau=" + gau + ", ann=" + ann + '}';
    }
    
    
    
    
}
