/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package flower;

import java.util.Scanner;

/**
 * Class to store information flower
 * @author ROG
 */
public class Flower {

    Scanner sc = new Scanner(System.in);
    private int fID;
    private String fName;
    private long fPrice;
    private int fAmount;

    //Constructor

    /**
     * New Flower
     */
    public Flower() {
    }

    /**
     * New flower with ID, name, price, amount
     * @param fID
     * @param fName
     * @param fPrice
     * @param fAmount
     */
    public Flower(int fID, String fName, long fPrice, int fAmount) {
        this.fID = fID;
        this.fName = fName;
        this.fPrice = fPrice;
        this.fAmount = fAmount;
    }

    //Getter & Setter

    /**
     * return ID 
     * @return
     */
    public int getfID() {
        return fID;
    }

    /**
     * set ID
     * @param Id
     */
    public void setID(int Id) {
        this.fID=Id;
    }
    
    /**
     * Set name
     * @param Name
     */
    public void setName(String Name) {
        this.fName=Name;
    }
    
    /**
     * Set price
     * @param pr
     */
    public void setPrice(Long pr) {
        this.fPrice=pr;
    }
    
    /**
     * set Amount
     * @param am
     */
    public void setAmount (int am) {
        this.fAmount = am;
    }
    
    /**
     * set ID with try catch Exception
     */
    public void setfID() {
        boolean ck = false;
        while (ck == false) {
            try {
                System.out.println("Enter ID: ");
                int a  = Integer.parseInt(sc.nextLine());
                this.fID = a;
                if(fID<=0){
                    System.out.println("ID must be greater than 0!!!");
                }
                else{
                    ck = true;
                }
            } catch (Exception Ex) {
                System.out.println("ID must be Integer!!!");
                ck = false;
            }
        }
    }

    /**
     * return Name
     * @return
     */
    public String getfName() {
        return fName;
    }

    /**
     * Set Name with try catch Exception
     */
    public void setfName() {
        boolean ck = false;
        while (ck == false) {
            try {
                if (fName.trim().equalsIgnoreCase("") == false) {
                    ck = true;
                }
                System.out.println("Enter name: ");
                String a = sc.nextLine();
                this.fName = a.trim();
            } catch (Exception Ex) {
                ck = false;
            }
        }
    }

    /**
     * Return Price
     * @return
     */
    public long getfPrice() {
        return fPrice;
    }

    /**
     * set Price with try catch Exception
     */
    public void setfPrice() {
        boolean ck = false;
        while (ck == false) {
            try {
                System.out.println("Enter price: ");
                long a  = Long.parseLong(sc.nextLine());
                this.fPrice = a;
                if (fPrice > 0) {
                    ck = true;
                } else {
                    System.out.println("Price must be greater than 0!!!");
                }
            } catch (Exception Ex) {
                System.out.println("Price must be Integer!!!");
                ck = false;
            }
        }
    }

    /**
     * return Amount
     * @return
     */
    public int getfAmount() {
        return fAmount;
    }

    /**
     * Set Amount with try catch Exception
     */
    public void setfAmount() {
        boolean ck = false;
        while (ck == false) {
            try {
                System.out.println("Enter amount: ");
                int a  = Integer.parseInt(sc.nextLine());
                this.fAmount = a;
                if (fAmount > 0) {
                    ck = true;
                } else {
                    System.out.println("Amount must be greater than 0!!!");
                }
            } catch (Exception Ex) {
                System.out.println("Amout must be Integer!!!");
                ck = false;
            }
        }
    }

    //output flower

    
}
