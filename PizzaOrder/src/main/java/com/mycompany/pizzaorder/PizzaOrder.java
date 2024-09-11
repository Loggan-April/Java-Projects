/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.pizzaorder;

/**
 *
 * @author Loggan April
 */
import java.util.ArrayList;
import java.util.Scanner;

// Class to representa basic pizza
class Pizza {
    private ArrayList<String> pizzaToppings;
    private final int MAX_TOPPINGS = 10;
    private final double BASE_PRICE = 14.0;
    private final double TOPPING_PRICE = 2.0;

    public Pizza() {
        pizzaToppings = new ArrayList<>();
    }
    // Method to add a user's topping
    public void Toppings(String topping) throws Exception {
        if (pizzaToppings.size() >= MAX_TOPPINGS) {
            throw new Exception("Maximum number of toppings reached.");
        }
        pizzaToppings.add(topping);
    }
    
    // Method to calculate the price of the pizza 
    public double calculatePrice() {
        int size = pizzaToppings.size();
        return BASE_PRICE + (size * TOPPING_PRICE);
    }

    // Method to get the list of toppings
    public ArrayList<String> getToppings() {
        return pizzaToppings;
    }

    @Override
    public String toString() {
        StringBuilder description = new StringBuilder("\n");
        if (pizzaToppings.isEmpty()) {
            description.append("No toppings");
        } else {
            for (int i = 0; i < pizzaToppings.size(); i++) {
                description.append(pizzaToppings.get(i));
                if (i < pizzaToppings.size() - 1) {
                    description.append(", ");
                }
            }
        }
        description.append("    Price: $").append(calculatePrice());
        return description.toString();
    }
}
// Class to represent a Dilverypizza, which extends pizza 
class DeliveryPizza extends Pizza {
    private double deliveryFee;
    private String deliveryAddress;

    public DeliveryPizza(String deliveryAddress) {
        super();
        this.deliveryAddress = deliveryAddress;
    }

    @Override
    public double calculatePrice() {
        double pizzaPrice = super.calculatePrice();
        // Calculates the price of delivery based on the price of the pizza, if asked to deliver
        if (pizzaPrice > 18) {
            deliveryFee = 3.0;
        } else {
            deliveryFee = 5.0;
        }
        return pizzaPrice;
    }

    @Override
    public String toString() {
        return super.toString() + "\nDeliver to: " + deliveryAddress + ", Delivery Fee: $" + deliveryFee;
    }
}

public class PizzaOrder {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Pizza pizza = null;

        try {
            pizza = new Pizza();
            // Get pizza toppings from user or if the user wants to complete the order
            while (true) {
                System.out.print("Enter topping or Q to quit >> ");
                String topping = scanner.nextLine();
                if (topping.equalsIgnoreCase("Q")) {
                    break;
                }
                try {
                    pizza.Toppings(topping);
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        // Checks if the user wants the pizza to be delivered
        System.out.print("Is the pizza to be delivered? Y or N >> ");
        String delivery = scanner.nextLine();

        if (delivery.equalsIgnoreCase("y")) {
            
            // Gets the address that the user wants the pizza to be delivered to
            System.out.print("Enter delivery address >> ");
            String address = scanner.nextLine();
            
            // Create DeliveryPizza with existing toppings from pizza
            DeliveryPizza deliveryPizza = new DeliveryPizza(address);

            // Copy toppings from the original pizza to the delivery pizza
            for (String topping : pizza.getToppings()) {
                try {
                    deliveryPizza.Toppings(topping);
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }
            }
            pizza = deliveryPizza;
        }
        // Checks if the delivery address is empty or not 
        if (pizza != null) {
            System.out.println(pizza.toString());
        } else {
            System.out.println("Thank you for ordering with us");
        }
        // Closes the scanner variable
        scanner.close();
    }
}
