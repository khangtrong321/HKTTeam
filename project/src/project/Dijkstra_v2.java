package project;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;


public class Dijkstra_v2 {
    static public int oo = 100000;
    Scanner sc;
    int n;
    int[][] a;
    boolean[] shortest;
    ArrayList<Integer>[] thePrevious;
    int[] distance;
    int start, end;
    boolean isConnected;
    int pathCount;

    public void readMatrix(String fileName) throws FileNotFoundException {
        sc = new Scanner(new File(fileName));
        n = sc.nextInt();
        a = new int[n][n];
        for (int r = 0; r < n; r++) {
            for (int c = 0; c < n; c++) {
                a[r][c] = sc.nextInt();
            }
        }
    }

    public void printGraph() {
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
        thePrevious = new ArrayList[n];
        for(int i=0; i<n; ++i) {
            thePrevious[i] = new ArrayList<>();
            thePrevious[i].add(i);

            distance[i] = oo;
        }
        start = 0;
        end = 5;
        distance[start] = 0;
        isConnected = false;
    }

    public int nextNearestVertex() {
        int minDistance = oo, minIndex=-1;
        for(int i=0; i<n; ++i)
            if (minDistance>distance[i] && !shortest[i]) {
                minDistance = distance[i];
                minIndex = i;
            }
        return minIndex;
    }

    public void updateDistance(int from) {
        for(int to=0; to<n; ++to)
            if (a[from][to] > 0 &&
            distance[to] >= distance[from] + a[from][to] &&
                    (!shortest[to] || to == end)
            ) {
                if (distance[to] > distance[from] + a[from][to]) {
                    thePrevious[to].clear();
                }
                distance[to] = distance[from]  + a[from][to];
                thePrevious[to].add(from);
            }
    }

    public void Dijkstra() {
        int from;
        while (true) {
            from = nextNearestVertex();
            if (from == -1) {
                break;
            } else {
                shortest[from] = true; //to mau vang cho dinh "from"

                if (from == end) {
                    isConnected = true;
                    break;

                } else {
                    updateDistance(from);
                }
            }
        }
    }

    public void Dijkstra_displayPath(String path, int vertex) {
        if (vertex != end) {
            path = vertex + " -> " + path;

        }
        if (vertex == start)
            System.out.printf("#%02d. %s\n",++pathCount, path);
        else
            for (int i = 0; i < thePrevious[vertex].size() ; ++i)
                Dijkstra_displayPath(path, thePrevious[vertex].get(i));
    }

    public void Dijkstra_displayResult() {
        if (!isConnected) {
            System.out.printf("Don't have any path from %d to %d!", start, end);
        } else {
            System.out.printf("The shortest path from %d to %d (Cost: %d):\n",
                    start, end, distance[end]);

            int vertex  = end;
            String path = end + "";
            pathCount = 0;

            Dijkstra_displayPath(path, vertex);
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
            Dijkstra_v2 app = new Dijkstra_v2();
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
