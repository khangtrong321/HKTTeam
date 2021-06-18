/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package project;

import java.io.File;
import java.io.FileNotFoundException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author khang
 */
public class Project {
    Scanner sc = new Scanner(System.in);
    int n;
    int a[][];
    int visited[];
    String path[];
    Queue<Integer> q;
    int[] temp;
    int graghtype;
    int m = 0;
    String vertexName = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    int connectedComponent;
    int[] distance;


    public void readData(String filename) throws FileNotFoundException {
        sc = new Scanner(new File(filename));
        n = sc.nextInt();
        graghtype = sc.nextInt();
        m = sc.nextInt();

        a = new int [n][n];
        temp = new int[n];
        int from, to ,cost =1;


        for (int r = 0 ; r < m ; r++) {
            from = sc.nextInt();
            to = sc.nextInt();
            cost = sc.nextInt();

            if (graghtype ==1) {
                --from;
                --to;
            }
            //cost = sc.nextInt();

            a[from][to] = cost;
            a[to][from] = cost;
        }
    }

    public String convertVertex(int v) {
        if (graghtype == 1)
            return (v + 1) + "";
        else if (graghtype == 2) {
            return vertexName.charAt(v) + "";
        }
        return v + "";
    }
    
    public void printGragh() {
        System.out.printf("The gragh %dx%d is :\n",n,n);
        for (int r = 0 ; r < n ; r++) {
            for (int c= 0 ; c < n ; c++) {
                System.out.printf("%5d", a[r][c]);
            } 
            System.out.println("");
        }
    }
    
    public void BFS_init() {
        visited = new int[n];
        
        path = new String[n];
        distance = new int[n];
        Arrays.fill(visited,-1);
        Arrays.fill(distance,0);
        
        q = new LinkedList<Integer>();
        connectedComponent = -1;
    }

    public int getFreeVertex() {
        for (int i = 0; i < n ;i++) {
            if (visited[i]==-1) {
                return i;
            }
        }
        return -1;

    }

    public void printAllConnectedComponent() {
        BFS_init();
        int vertex = getFreeVertex();
        while (vertex >=0 ){
            BFS(vertex);
            vertex = getFreeVertex();

        }
    }

    public void BFS_printResult(int start) {
        connectedComponent++;
        System.out.printf("The connected component start with vertex %s : \n", convertVertex(connectedComponent));
        for (int i = 0; i < n; i++) {
            if (visited[i] == start) {
                System.out.printf("%s -> %s (cost %d) : %s\n",convertVertex(start),convertVertex(i),temp[i],path[i]);
            }
        }
    }

    
    public void BFS(int start) {

        q.add(start);
        visited[start] = start;
        path[start] = convertVertex(start) + "";

        while (!q.isEmpty()) {
            int from = q.poll();


            for (int to = 0; to < n; to++) {

                if (visited[to]==-1 && a[from][to] > 0) {
                    q.add(to);
                    visited[to] = start;
                    path[to] = path[from] + " -> " + convertVertex(to);

                    temp[to] = temp[from] + a[from][to];


                }
            }
        }
        // Print value
        BFS_printResult(start);
    }


    
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        
        try {
            File g = new File("");
            String path = g.getAbsolutePath();
            Project pj  = new Project();

            pj.readData(path+ "//src//data//data9.txt");
            pj.printGragh();
//            pj.BFS_init();
//            pj.BFS(0);
            pj.printAllConnectedComponent();

        } catch (FileNotFoundException e) {
            Logger.getLogger(Project.class.getName()).log(Level.SEVERE,null,e);
        }
        
       
    }
}
