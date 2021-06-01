/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package flower;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * BST class include method to work with Binary tree Node root is the root of
 * tree min, max is the temp value
 *
 * @author ROG
 */
public class BST {

    Scanner sc = new Scanner(System.in);

    private Node root;
    long min, max;
    String fNa;
    int fI, fn = 0;
    int maxle = 6;



    /**
     * Getter root
     * @return
     */
    public Node getRoot() {
        return root;
    }

    /**
     * Setter root
     * @param root
     */
    public void setRoot(Node root) {
        this.root = root;
    }

    /**
     * Find max width of the column table
     * @param root
     */
    public void maxlength(Node root) {
        if (root == null) {
            return;
        }
        if (Integer.toString(root.getKey().getfID()).length() > maxle) {
            maxle = Integer.toString(root.getKey().getfID()).length();
        }
        if ((root.getKey().getfName()).length() > maxle) {
            maxle = (root.getKey().getfName()).length();
        }
        if (Long.toString(root.getKey().getfPrice()).length() > maxle) {
            maxle = Long.toString(root.getKey().getfPrice()).length();
        }
        if (Integer.toString(root.getKey().getfAmount()).length() > maxle) {
            maxle = Integer.toString(root.getKey().getfAmount()).length();
        }
        maxlength(root.getLeftChild());
        maxlength(root.getRightChild());

    }

     /**
     * Call Search Price
     */
    public void SearchByPrice() {
        searchPrc(root);
    }

    /**
     * Search flower by Price
     * @param node
     */
    public void searchPrc(Node node) {
        if (node == null) {
            return;
        }
        searchPrc(node.getLeftChild());

        if (node.getKey().getfPrice() >= min && node.getKey().getfPrice() <= max) {
            System.out.print("|");
            System.out.print(node.getKey().getfID());
            sp1(Integer.toString(node.getKey().getfID()));
            System.out.print("|" + node.getKey().getfName());
            sp1(node.getKey().getfName());
            System.out.print("|" + node.getKey().getfPrice());
            sp1(Long.toString(node.getKey().getfPrice()));
            System.out.print("|" + node.getKey().getfAmount());
            sp1(Integer.toString(node.getKey().getfAmount()));
            System.out.print("|");
            System.out.println("");
            fn++;
        }

        searchPrc(node.getRightChild());
    }

    /**
     * Call Search flower by ID
     */
    public void SearchByID1() {
        searchID1(root);
    }
   
    /**
     * Search flower by ID
     */
    public void searchID1(Node node) {
        if (node == null) {
            return;
        }
        searchID1(node.getLeftChild());

        if (node.getKey().getfID() == fI) {
            fn++;
        }

        searchID1(node.getRightChild());
    }

    

    /**
     * Call search by name
     */
    public void SearchByName() {
        SearchName(root);
    }
    

    /**
     * Search ny name
     * @param node
     */
    public void SearchName(Node node) {
        if (node == null) {
            return;
        }
        SearchName(node.getLeftChild());

        if (node.getKey().getfName().equalsIgnoreCase(fNa.trim())) {
            System.out.print("|");
            System.out.print(node.getKey().getfID());
            sp1(Integer.toString(node.getKey().getfID()));
            System.out.print("|" + node.getKey().getfName());
            sp1(node.getKey().getfName());
            System.out.print("|" + node.getKey().getfPrice());
            sp1(Long.toString(node.getKey().getfPrice()));
            System.out.print("|" + node.getKey().getfAmount());
            sp1(Integer.toString(node.getKey().getfAmount()));
            System.out.print("|");
            System.out.println("");
            fn++;
        }

        SearchName(node.getRightChild());
    }
    


    /**
     * add spacing
     * @param t
     */
    public void sp(String t) {
        System.out.print(t);
        for (int i = 0; i < maxle - t.length(); i++) {
            System.out.print(" ");
        }
    }

    /**
     * add spacing
     * @param t
     */
    public void sp1(String t) {
        for (int i = 0; i < maxle - t.length(); i++) {
            System.out.print(" ");
        }
    }
    
    /**
     * Call PreOrder
     */
    public void preOrder_traversal() {

        preOrder(root);
    }

    /**
     * Output flower by PerOrder table format
     * @param node
     */
    public void preOrder(Node node) {
        if (node == null) {
            return;
        }
        System.out.print("|");
        sp(Integer.toString(node.getKey().getfID()));
        System.out.print("|");
        sp(node.getKey().getfName());
        System.out.print("|");
        sp(Long.toString(node.getKey().getfPrice()));
        System.out.print("|");
        sp(Integer.toString(node.getKey().getfAmount()));
        System.out.print("|");
        System.out.println("");

        preOrder(node.getLeftChild());

        preOrder(node.getRightChild());
    }

    

    

    /**
     * Call deleteKey
     * @param key
     */
    public void deleteKey(int key) {
        root = delete_Recursive(root, key);
    }

    //recursive delete function

    /**
     * Delete Key 
     * @param root
     * @param key
     * @return
     */
    public Node delete_Recursive(Node root, int key) {
        //tree is empty
        if (root == null) {
            return root;
        }

        //traverse the tree
        if (key < root.getKey().getfID()) //traverse left subtree 
        {
            root.setLeftChild(delete_Recursive(root.getLeftChild(), key));
        } else if (key > root.getKey().getfID()) //traverse right subtree
        {
            root.setRightChild(delete_Recursive(root.getRightChild(), key));
        } else {
            // node contains only one child
            if (root.getLeftChild() == null) {
                return root.getRightChild();
            } else if (root.getRightChild() == null) {
                return root.getLeftChild();
            }

            // node has two children; 
            //get inorder successor (min value in the right subtree) 
            root.setKey(minValue(root.getRightChild()));

            // Delete the inorder successor 
            root.setRightChild(delete_Recursive(root.getRightChild(), root.getKey().getfID()));
        }
        return root;
    }

    /**
     * Find min value
     * @param root
     * @return
     */
    public Flower minValue(Node root) {
        //initially minval = root
        Flower minval = root.getKey();
        //find minval
        while (root.getLeftChild() != null) {
            minval = root.getLeftChild().getKey();
            root = root.getLeftChild();
        }
        return minval;
    }

    /**
     * Print all table
     */
    public void List() {
        System.out.println("");
        System.out.print("|ID");
        sp1("ID");
        System.out.print("|Name");
        sp1("Name");
        System.out.print("|Price");
        sp1("Price");
        System.out.print("|Amount");
        sp1("Amount");
        System.out.print("|");
        System.out.println("");
        preOrder_traversal();
        System.out.println("");
    }

    /**
     * Print table while search Price
     */
    public void List1() {
        System.out.println("");
        System.out.print("|ID");
        sp1("ID");
        System.out.print("|Name");
        sp1("Name");
        System.out.print("|Price");
        sp1("Price");
        System.out.print("|Amount");
        sp1("Amount");
        System.out.print("|");
        System.out.println("");
        SearchByPrice();
        System.out.println("");
    }

    /**
     * Print table while search Name
     */
    public void List2() {
        System.out.println("");
        System.out.print("|ID");
        sp1("ID");
        System.out.print("|Name");
        sp1("Name");
        System.out.print("|Price");
        sp1("Name");
        System.out.print("|Amount");
        sp1("Price");
        System.out.print("|");
        System.out.println("");
        SearchByName();
        System.out.println("");
    }

    /**
     * Search flower by ID
     * @param key ID flower need to find
     * @return the flower 
     */
    public Flower searchID(int key) {
        Node k = root;
        return search_RecursiveID(k, key).getKey();

    }

    

    /**
     * Search Node have flower ID need 
     * @param root
     * @param key ID need to find
     * @return node have that ID
     */
    public Node search_RecursiveID(Node root, int key) {
        if (root == null || root.getKey().getfID() == key) {
            return root;
        }
        if (root.getKey().getfID() > key) {
            return search_RecursiveID(root.getLeftChild(), key);
        }
        return search_RecursiveID(root.getRightChild(), key);
    }

    //find the flower by ID ->> return flower

    /**
     * Search in the tree, if have flower with key, return true else false
     * @param key
     * @return
     */
    public boolean search(int key) {
        Node node = new Node();

        node = search_Recursive(root, key);
        if (node != null) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * Search Node by the ID flower
     * @param root
     * @param key
     * @return the node
     */
    public Node search_Recursive(Node root, int key) {
        // Base Cases: root is null or key is present at root 
        if (root == null || root.getKey().getfID() == key) {
            return root;
        }
        // val is greater than root's key 
        if (root.getKey().getfID() > key) {
            return search_Recursive(root.getLeftChild(), key);
        }
        // val is less than root's key 
        return search_Recursive(root.getRightChild(), key);
    }

    /**
     * MENU 
     * @throws FileNotFoundException
     * @throws IOException
     */
    public void Menu() throws FileNotFoundException, IOException {

        File GF = new File("");
        String path = GF.getAbsolutePath();
        File f = new File(path + "\\src\\flower\\flower.txt");
        BufferedReader reader = new BufferedReader(new FileReader(f));
        String line = reader.readLine();
        try {
            while (line != null) {
                Flower nh = new Flower();
                nh.setID(Integer.parseInt(line));
                line = reader.readLine();
                line.trim();
                nh.setName(line);
                line = reader.readLine();
                nh.setPrice(Long.parseLong(line));
                line = reader.readLine();
                nh.setAmount(Integer.parseInt(line));

                insert(nh);
                line = reader.readLine();
            }
            reader.close();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Flower.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
        }
        if (getRoot() != null) {
            System.out.println("Connect sucess!!!");
        } else {
            System.out.println("Fail!!!");
        }
        //Finish
        maxlength(root);

        boolean ck = false;
        while (ck == false) {
            try {
                System.out.println("\t=====Flower manager=====");
                System.out.println("1-Insert new flower");
                System.out.println("2-Search flower by name");
                System.out.println("3-Search flower by price");
                System.out.println("4-Delete flower by ID");    
                System.out.println("5-Save and exit");
                System.out.println("Enter your choice: ");
                int n = Integer.parseInt(sc.nextLine());
                if (n > 5 || n < 1) {
                    System.out.println("The choice only 1->5!");
                    ck = false;
                } else if (n == 5) {
                    ck = true;
                }
                switch (n) {
                    case 1:
                        List1();
                        preOrder_traversal();
                        Flower a = new Flower();
                        boolean cki1 = false;
                        while (cki1 == false) {
                            try {
                                System.out.println("Enter ID: ");
                                a.setID(Integer.parseInt(sc.nextLine()));
                                if (a.getfID() > 0) {
                                    cki1 = true;
                                } else {
                                    System.out.println("ID must be an greater than 0 integer!!!");
                                }
                            } catch (Exception ex) {
                                System.out.println("ID must be an greater than 0 integer!!!");
                            }
                        }

                        if (search(a.getfID()) == true) {

                            System.out.println("The ID " + a.getfID() + " had been added!!!");
                            
                            System.out.printf("Do you want to update the old flower or cancel? (Y/N): \n");
                            String c = sc.next();

                            sc.nextLine();

                            while (c.equalsIgnoreCase("Y") == false && c.equalsIgnoreCase("N") == false) {
                                System.out.printf("Your entered is wrong!!!\nEnter Y or N!\n");
                                c = sc.next();
                                sc.nextLine();
                            }
                            if (c.equalsIgnoreCase("Y") == true) {

                                boolean t = false;
                                int m = 0;
                                Long b = null;
                                //check enter the price
                                while (t == false) {
                                    try {

                                        System.out.println("Please enter new price for old flower: ");
                                        b = Long.parseLong(sc.nextLine());
                                        System.out.println("Please enter the new amount added");
                                        m = Integer.parseInt(sc.nextLine());
                                        if (b > 0 && m > 0) {
                                            t = true;
                                        }
                                    } catch (NumberFormatException e) {
                                        t = false;
                                        System.out.println("Error number format!!!");
                                    }
                                }
                                a.setName(searchID(a.getfID()).getfName());
                                a.setPrice(b);
                                a.setAmount(searchID(a.getfID()).getfAmount() + m);
                                insert(a);
                                System.out.println("Update flower success");
                                maxlength(root);
                            } else {
                                System.out.println("Cancel insert new flower!!!!");
                            }
                        } else {
                            System.out.println("Enter name: ");
                            a.setName(sc.nextLine());
                            while (a.getfName().length() == 0) {
                                System.out.println("Please enter the name flower!!!");
                                System.out.println("Enter name: ");
                                    
                                a.setName(sc.nextLine());
                            }
                            
                            fn =0 ; 
                            fNa = a.getfName();
                            SearchName(root);
                            if (fn != 0 ) {
                                System.out.println("The name had been added!!!");
                                System.out.println("Please enter another name!!!");
                                System.out.println("Enter name: ");
                                a.setName(sc.nextLine());
                            }
                            
                            a.setName(a.getfName().trim());
                                
                            
                            boolean cki2 = false;
                            while (cki2 == false) {
                                try {
                                    System.out.println("Enter Price: ");
                                    a.setPrice(Long.parseLong(sc.nextLine()));
                                    if (a.getfPrice() > 0) {
                                        cki2 = true;
                                    } else {
                                        System.out.println("Price must be an greater than 0 integer!!!");
                                    }
                                } catch (Exception ex) {
                                    System.out.println("Price must be an greater than 0 integer!!!");
                                    cki2 = false;
                                }
                            }
                            boolean cki3 = false;
                            while (cki3 == false) {
                                try {
                                    System.out.println("Enter Amount: ");
                                    a.setAmount(Integer.parseInt(sc.nextLine()));
                                    if (a.getfAmount() > 0) {
                                        cki3 = true;
                                    } else {
                                        System.out.println("Amount must be an greater than 0 integer!!!");
                                    }
                                } catch (Exception ex) {
                                    System.out.println("Amount must be an greater than 0 integer!!!");
                                    cki3 = false;
                                }
                            }
                            System.out.println("Add new flower success!!!");
                            insert(a);
                        }

                        break;
                    case 2:
                        fn = 0;
                        maxlength(root);
                        boolean ck2 = false;
                        while (ck2 == false) {
                            try {
                                
                                System.out.println("Enter name to search: ");
                                fNa = sc.nextLine();
                                List2();
                                if (fn == 0) {
                                    System.out.println("Can't found this item!!!");
                                }
                                ck2 = true;
                            } catch (Exception Ex) {
                                ck2 = false;
                            }
                        }
                        break;
                    case 3:
                        fn = 0;
                        maxlength(root);
                        boolean ck3 = false;
                        while (ck3 == false) {
                            try {
                                System.out.println("Enter min price: ");
                                min = Long.parseLong(sc.nextLine());
                                System.out.println("Enter max price: ");
                                max = Long.parseLong(sc.nextLine());
                                
                                if (min >= max) {
                                    System.out.println("Max price must be greater than min price!!!");
                                } else if (min <= 0 || max <= 0) {
                                    System.out.println("Min and max price mus be greater than 0!!!");
                                } else {
                                    ck3 = true;
                                }
                            } catch (Exception Ex) {
                                System.out.println("Please enter an Integer!!!");
                                ck3 = false;
                            }
                        }
                        List1();
                        if (fn == 0) {
                            System.out.println("Can't found this item!!!");
                        }
                        break;
                    case 4:
                        fn = 0;
                        maxlength(root);
                        boolean ck4 = false;
                        while (ck4 == false) {
                            try {
                                System.out.println("Enter ID to delete: ");
                                fI = Integer.parseInt(sc.nextLine());
                                SearchByID1();
                                if (fn == 0) {
                                    System.out.println("Can't found this item!!!");
                                } else {
                                    System.out.println("Delete successful!!!");
                                }
                                deleteKey(fI);
                                ck4 = true;
                            } catch (Exception Ex) {
                                System.out.println("ID must be an Integer!!!");
                                ck4 = false;
                            }
                        }
                        System.out.println("The list: ");
                        List();
                        break;
                    case 5:
                        BufferedWriter writer = new BufferedWriter(new FileWriter(f));
                        writeP(writer, root);
                        writer.close();
                        System.out.println("Autosaving successful...");
                        System.out.println("=================================================");
                        break;
                    default:
                        break;
                }
            } catch (Exception Ex) {
                System.out.println("The choice only 1->5!");
                ck = false;
            }
        }
    }

    /**
     *
     * @param key
     */
    public void insert(Flower key) {
        root = insert_Recursive(root, key);
    }

    //recursive insert function

    /**
     * Insert new node to the tree, if the ID had been added, it's will update information
     * @param root
     * @param key
     * @return
     */
    public Node insert_Recursive(Node root, Flower key) {
        //tree is empty
        if (root == null) {

            root = new Node(key);
            return root;
        }
        //traverse the tree
        if (key.getfID() < root.getKey().getfID()) //insert in the left subtree
        {
            root.setLeftChild(insert_Recursive(root.getLeftChild(), key));
        } else if (key.getfID() > root.getKey().getfID()) //insert in the right subtree
        {
            root.setRightChild(insert_Recursive(root.getRightChild(), key));
        } else if (key.getfID() == root.getKey().getfID()) {
            root.setKey(key);

        }
        // return pointer
        return root;
    }

    /**
     * Call Inorder
     * @param node
     */
    public void inOrder(Node node) {
        if (node == null) {
            return;
        }
        //first traverse left subtree recursively
        inOrder(node.getLeftChild());

        //then go for root node
        System.out.print(node.getKey() + " ");

        //next traverse right subtree recursively
        inOrder(node.getRightChild());
    }

    /**
     * Inorder
     */
    public void inOrder_traversal() {
        inOrder(root);
    }

    /**
     * Write tree to the file
     * @param writer
     * @param sty
     * @throws IOException
     */
    public void writeP(BufferedWriter writer, Node sty) throws IOException {

        if (sty == null) {

            return;
        }
        writer.write(Integer.toString(sty.getKey().getfID()));
        writer.write((char) '\n');
        writer.write(sty.getKey().getfName());
        writer.write((char) '\n');
        writer.write(Long.toString(sty.getKey().getfPrice()));
        writer.write((char) '\n');
        writer.write(Integer.toString(sty.getKey().getfAmount()));
        writer.write((char) '\n');

        writeP(writer, sty.getLeftChild());
        writeP(writer, sty.getRightChild());

    }

}
