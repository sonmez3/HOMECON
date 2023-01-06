/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Classes;

/**
 * 
 * @author baransonmez
 */
public abstract class AItems {
    
    private String name;
    double amount;
    private double price;
    
    
    
    public AItems(String name, double amount, double price) {
        this.name = name;
        this.amount = amount;
        this.price = price;
    }
    
    public AItems(double amount, double price) {
       
        this.amount = amount;
        this.price = price;
    }
    
    public AItems( double price) {
       
      
        this.price = price;
    }
    public AItems(){
        
    }
    
    
    
    
    
    public String getName() {
        return name;
    }

    public double getAmoung() {
        return amount;
    }

    public double getPrice() {
        return price;
    }
    
 
    
}
