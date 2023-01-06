/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Classes;

/**
 *
 * @author baransonmez
 */
public class Shop extends AItems{
    String type;
     
    
    public Shop(String type,String name, double amount, double price) {
        super(name, amount, price);
        this.type = type;
    }
    
    public Shop(String type){
         
        this.type = type;
    }
  
}
