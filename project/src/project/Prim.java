package project;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Scanner;

public class Prim {

    String vertexName = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    Scanner sc;
    int n,m,graghtype;
    boolean inTree[];
    int thePrevious[];
    int[] distance;
    int a[][];
    int oo= 100000;

    public void readData(String filename) throws FileNotFoundException {
        sc = new Scanner(new File(filename));
        n = sc.nextInt();
        graghtype = sc.nextInt();
        m = sc.nextInt();

        a = new int [n][n];
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

    public void printGragh() {
        System.out.printf("The gragh %dx%d is :\n",n,n);
        for (int r = 0 ; r < n ; r++) {
            for (int c= 0 ; c < n ; c++) {
                System.out.printf("%5d", a[r][c]);
            }
            System.out.println("");
        }
    }

    public void Prim_init() {
        inTree = new boolean[n];
        Arrays.fill(inTree,false);
        distance = new int[n];
        thePrevious = new int[n];

        for (int i = 0 ; i < n ; i++) {
            thePrevious[i] = i;
            distance[i] = oo;
        }
        distance[0] = 0;
    }

    public int nextNearestVertex() {
        int minDistance = oo, minIndex=-1;
        for(int i=0; i<n; ++i)
            if (minDistance>distance[i] && !inTree[i]) {
                minDistance = distance[i];
                minIndex = i;
            }
        return minIndex;
    }

    public void updateDistance(int from) {
        for(int to=0; to<n; ++to)
            if (inTree[to] ==false
            && a[from][to] >0
            && distance[to] > a[from][to]) {
                distance[to] = a[from][to];
                thePrevious[to] = from;
            }
    }

    public void Prim() {
        int from;
        for (int i = 1; i < n;i++) {
            from = nextNearestVertex();
            if (from == -1) {
                break;

            } else {
                inTree[from] = true;
                updateDistance(from);
            }
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


    public void Prim_DisplayResult(){
        int cost;
        cost = 0;
        for (int i = 0 ; i< n;i++) {
            cost += distance[i];
        }
        System.out.printf("The minimum spanning tree for a weight (cost: %s): \n",convertVertex(cost));

        for (int i = 1 ; i < n; i++ ) {
            checkint("%d,%d\n" , i ,thePrevious[i]);
        }
    }

    public void checkint(String format, int x , int y) {
        if (x > y) {
            System.out.printf(format, y,x);
        } else {
            System.out.printf(format,x,y);
        }
    }


    public static void main(String args[]) {
        try {
            Prim p = new Prim();

            p.readData("src\\data\\Prim\\Prim2.txt");
            p.printGragh();
            p.Prim_init();
            p.Prim();
            p.Prim_DisplayResult();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

    }
}
