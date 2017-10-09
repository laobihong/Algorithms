package test;

public class Dijkstra {
    
    private static final int V = 9;
    public void d(int[][] graph, int src) {
        int[] dist = new int[V];
        boolean[] visited = new boolean[V];
        dist[src] = 0;
        for (int i = 1; i < V; i++) {
            dist[i] = Integer.MAX_VALUE;  
        }
        
        for (int count = 0; count < V - 1; count++) {
            int u = minDist(dist, visited);
            visited[u] = true;
            for (int v = 0; v < V; v++) {
                if (!visited[v] && graph[u][v] != 0 &&
                        dist[v] > dist[u] + graph[u][v]) {
                    dist[v] = dist[u] + graph[u][v];
                }
            }
        }
        
        printSolution(dist);
    }
    
    public int d(int[][] graph, int src, int des) {
        int[] dist = new int[V];
        boolean[] visited = new boolean[V];
        dist[src] = 0;
        for (int i = 1; i < V; i++) {
            dist[i] = Integer.MAX_VALUE;  
        }
        
        for (int count = 0; count < V; count++) { // ATTN: here if we use V - 1 instead of V, then we CANNOT get the distance for the farthest node
            int u = minDist(dist, visited);
            if (u == des) {
                // u == des means u has already been finalized
                System.out.println("Shortest distance between " + src + " and " + des + " is " + dist[u]);
                return dist[u];
            }
            visited[u] = true;
            for (int v = 0; v < V; v++) {
                if (!visited[v] && graph[u][v] != 0 &&
                        dist[v] > dist[u] + graph[u][v]) {
                    dist[v] = dist[u] + graph[u][v];
                }
            }
        }
        return dist[des];
    }
    
    private void printSolution(int[] dist) {
        System.out.println("Vertex " + "\tDistance");
        for (int i = 0; i < V; i++) {
            System.out.println(i + " \t" + dist[i]);
        }
    }
    
    private int minDist(int[] dist, boolean[] visited) {
        int minIdx = -1;
        for (int i = 0; i < V; i++) {
            if (!visited[i] && (minIdx == -1 || dist[i] < dist[minIdx])) {
                minIdx = i;
            }
        }
        return minIdx;
    }

    public static void main(String[] args) {
        int[][] graph = new int[][]{
            {0, 4, 0, 0, 0, 0, 0, 8, 0},
            {4, 0, 8, 0, 0, 0, 0, 11, 0},
            {0, 8, 0, 7, 0, 4, 0, 0, 2},
            {0, 0, 7, 0, 9, 14, 0, 0, 0},
            {0, 0, 0, 9, 0, 10, 0, 0, 0},
            {0, 0, 4, 14, 10, 0, 2, 0, 0},
            {0, 0, 0, 0, 0, 2, 0, 1, 6},
            {8, 11, 0, 0, 0, 0, 1, 0, 7},
            {0, 0, 2, 0, 0, 0, 6, 7, 0}
           };
        Dijkstra t = new Dijkstra();
        t.d(graph, 0);
        for (int i = 1; i < V; i++) {
            t.d(graph, 0, i);
        }
    }

}
