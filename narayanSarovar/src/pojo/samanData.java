/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pojo;

/**
 *
 * @author HP
 */
public class samanData {
    
    String name;
    int bill,kg;

    public samanData(String name, int kg, int bill) {
        this.name = name;
        this.bill = bill;
        this.kg = kg;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getBill() {
        return bill;
    }

    public void setBill(int bill) {
        this.bill = bill;
    }

    public int getKg() {
        return kg;
    }

    public void setKg(int Kg) {
        this.kg = Kg;
    }

    public double getPricePerKg(){return bill*1.00/kg;}

    @Override
    public String toString() {
        return "samanData{" + "name=" + name + ", bill=" + bill + ", Kg=" + kg + '}';
    }
    
    
}
