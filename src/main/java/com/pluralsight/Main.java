package com.pluralsight;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Scanner;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static HashMap<Integer, Product> inventory = new HashMap<Integer, Product>();
    public static void main(String[] args) {
        // this method loads product objects into inventory
        loadInventory();

        Scanner scanner = new Scanner(System.in);
        System.out.print("What item # are you interested in? ");
        int id = scanner.nextInt();
        Product matchedProduct = inventory.get(id);
        if (matchedProduct == null) {
            System.out.println("We don't carry that product");
            return;
        }
        System.out.printf("We carry %s and the price is $%.2f",
                matchedProduct.getName(), matchedProduct.getPrice());
    }

    public static void loadInventory(){
        int productId;
        String productName;
        double productPrice;

        try {
            FileReader fileReader = new FileReader("inventory.csv");
            BufferedReader bufferedReader = new BufferedReader(fileReader);

            String line;

            while ((line = bufferedReader.readLine()) != null) {
                String[] parts = line.split("\\|");

                if (parts.length != 3) {
                    System.out.println("Invalid Entry.");
                    continue;
                }

                try {
                    productId = Integer.parseInt(parts[0]);
                    productName = parts[1];
                    productPrice = Double.parseDouble(parts[2]);

                    Product product = new Product(productId, productName, productPrice);
                    inventory.put(productId, product);
                } catch (NumberFormatException numE) {
                    System.out.println("Invalid Entry.");
                }

            }
            bufferedReader.close();
        } catch (IOException e){
            System.out.println("Issues Reading File");
        }

    }
}