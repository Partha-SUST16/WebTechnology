/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author user
 */
public class Owner {
    private String name,brand,model;
    private String year;
    private String using;

    public Owner() {
    }

    public Owner(String name, String brand, String model, String year, String using) {
        this.name = name;
        this.brand = brand;
        this.model = model;
        this.year = year;
        this.using = using;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String isUsing() {
        return using;
    }

    public void setUsing(String using) {
        this.using = using;
    }
    public String toString(){
        return this.name+" "+this.brand+" "+this.model+" "+this.year;
    }
}
