package project;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Scanner;
import java.util.Stack;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Dijkstra_v1 {
    static public int oo = 100000;
    Scanner sc;
    int n;
    int a[][];
    boolean shortest[];
    int thePrevious[];
    int distance[];
    int start, end;
    boolean isConnected;


    public void readMatrix(String fileName) throws FileNotFoundException {
        System.out.println(1);
        sc = new Scanner(new File(fileName));
        n = sc.nextInt();
        a = new int[n][n];
        for (int r = 0; r < n; r++) {
            for (int c = 0; c < n; c++) {
                a[r][c] = sc.nextInt();
            }
        }
        System.out.println(1 + " +");
    }

    public void printGraph() {
        System.out.println(2);
        System.out.printf("The graph %dx%d is:\n", n, n);
        for (int r = 0; r < n; r++) {
            for (int c = 0; c < n; c++) {
                System.out.printf("%5d", a[r][c]);
            }
            System.out.println();
        }
    }
    public void Dijkstra_init() {
        shortest = new boolean[n];
        Arrays.fill(shortest, false);
        distance = new int[n];
        thePrevious = new int[n];
        for(int i=0; i<n; ++i) {
            thePrevious[i] = i;
            distance[i] = oo;
        }
        start = 0;
        end = 6;
        distance[start] = 0;
        isConnected = false;
    }

    public int nextNearestVertex() {
        int minDistance = oo, minIndex=-1;
        for(int i=0; i<n; ++i)
            if (minDistance>distance[i] && shortest[i]==false) {
                minDistance = distance[i];
                minIndex = i;
            }
        return minIndex;
    }

    public void updateDistance(int from) {
        for(int to=0; to<n; ++to)
            if (shortest[to]==false && //chua duoc to mau vang
                    a[from][to]>0 && //co duong di truc tiep tu "from" den "to"
                    distance[to] > distance[from] + a[from][to]) {
                distance[to] = distance[from] + a[from][to];
                thePrevious[to] = from;
            }
    }

    public void Dijkstra() {
        int from;
        while (true) {
            from = nextNearestVertex();
            if (from != -1) {
                shortest[from] = true; //to mau vang cho dinh "from"

                if (from == end) {
                    isConnected = true;
                    break;
                } else if (from == -1) { //do thi khong lien thong
                    break;
                } else {
                    updateDistance(from);
                }
            }

        }
    }

    public void Dijkstra_displayPath() {
        Stack<Integer> s = new Stack<>();
        int vertex = end;
        s.push(vertex);
        do {
            vertex = thePrevious[vertex];
            s.push(vertex);
        }while(vertex != start);

        //hien thi duong di tu dinh from den dinh to
        vertex = s.pop();
        System.out.printf("%d", vertex);
        while(!s.empty()) {
            vertex = s.pop();
            System.out.printf(" -> %d", vertex);
        }
    }

    public void Dijkstra_displayResult() {
        if (isConnected == false) {
            System.out.printf("Don't have any path from %d to %d!", start, end);
        } else {
            System.out.printf("The shortest path from %d to %d (Cost: %d):\n",
                    start, end, distance[end]);
            Dijkstra_displayPath();
            /*
            for(int to=0; to<n; ++to)
                System.out.printf("%5d", thePrevious[to]);
            System.out.println();
            for(int to=0; to<n; ++to)
                System.out.printf("%5d", distance[to]);
             */
        }
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        try {
            Dijkstra_v1 app = new Dijkstra_v1();
            app.readMatrix("src\\data\\DFS3.txt");
            app.printGraph();
            app.Dijkstra_init();
            app.Dijkstra();
            app.Dijkstra_displayResult();
        } catch (FileNotFoundException ex) {
            ex.printStackTrace();
        }
    }
}
