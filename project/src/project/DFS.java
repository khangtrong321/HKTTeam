package project;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Scanner;

public class DFS {

    Scanner sc;
    int n;
    int a[][];
    boolean visited[];
    int path[];
    int pathIndex;
    int numberOfPaths;
    int start, end;

    public void readMatrix(String filename) throws FileNotFoundException {
        sc = new Scanner(new File(filename));
        n = sc.nextInt();

        a= new int[n][n];

        for (int r = 0 ; r < n ; r++) {
            for (int c = 0 ; c < n; c++) {
                a[r][c] = sc.nextInt();
            }
        }
    }

    public void printGragh() {
        System.out.printf("The gragh %dx%d is \n", n, n);
        for (int r = 0; r < n; r++) {
            for (int c = 0; c < n; c++)
                System.out.printf("%5d", a[r][c]);
            System.out.println();
        }
    }

    public void DFS_init(){
        visited = new boolean[n];
        Arrays.fill(visited,false);

        path  = new int[n+1];
        Arrays.fill(path,-1);
        pathIndex =0 ;
        numberOfPaths = 0;
        start = 0;
        end =6;

    }

    public void comeback() {
        int vertex = path[pathIndex -1];
        visited[vertex] = false;
        pathIndex--;
        path[pathIndex+1] = -1;
    }

    public void findAllPath(){
        path[0] = start;
        pathIndex++;

        int from,to;
        while (pathIndex > 0) {
            from = path[pathIndex-1];
            visited[from] = true;

            if (from == end) {
                displayPath();

                comeback();
            } else  {
                to = nextFreeVertex(from);
                if (to >= 0) {
                    path[pathIndex] = to;
                    pathIndex++;
                } else {
                    comeback();
                }
            }
        }
    }

    public int costOfPath() {
        int cost =0;
        for (int to = path[0]; to < pathIndex-1;  to++) {
            cost = cost + a[path[to]][path[to+1]];
        }
        return cost ;
    }

    public void displayPath() {
        numberOfPaths++;
        System.out.printf("\n#%d. (Cost: %d) # %d",
                numberOfPaths,costOfPath(), path[0]);
        for (int to = 1;  to < pathIndex; to++){
            System.out.printf(" -> %d", path[to]);
        }
    }

    public int nextFreeVertex(int from) {
        int netxVertex = path[pathIndex]+1;

        for (int to = netxVertex ; to < n; ++to) {
            if(a[from][to] > 0 && !visited[to])
                return to;
        }
        return -1;
    }
    public static void main(String[] args) {
        try {
            DFS app = new DFS();
            app.readMatrix("src\\data\\DFS2.txt");
            app.printGragh();
            app.DFS_init();
            app.findAllPath();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

}
