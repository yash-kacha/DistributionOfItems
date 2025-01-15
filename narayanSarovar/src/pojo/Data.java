/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pojo;

/**
 *
 * @author HP
 */
public class Data {
    String name;
    int kg;

    public Data(String name, int kg) {
        this.name = name;
        this.kg = kg;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getKg() {
        return kg;
    }

    public void setKg(int kg) {
        this.kg = kg;
    }

    @Override
    public String toString() {
        return "Data{" + "name=" + name + ", kg=" + kg + '}';
    }
    
    
}
