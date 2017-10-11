package test;

// all the elements in key is how far the node contributes, but notice that
// although this is the MST, global optimism doesn't mean that each node will 
// have a minimum key!!
public class MinimumSpanningTree {
    
    private final static int V = 5;
    
    private void printMST(int[][] graph, int[] parent) {
        System.out.println("Edges\tWeight");
        for (int i = 1; i < V; i++) {
            System.out.println(parent[i] + " - " + i + "\t" + graph[parent[i]][i]);
        }
    }
    
    public void primMST(int[][] graph) {
        int[] parent = new int[V];
        boolean[] visited = new boolean[V];
        int[] key = new int[V];
        
        key[0] = 0; parent[0] = -1;
        for (int i = 1; i < V; i++) {
            key[i] = Integer.MAX_VALUE;
        }
        
        for (int i = 0; i < V - 1; i++) {
            int u = findMin(key, visited);
            visited[u] = true;
            for (int v = 0; v < V; v++) {
                if (graph[u][v] != 0 && !visited[v] && (graph[u][v] < key[v])) { // ATTN: not key[v] < graph[u][v]
                    key[v] = graph[u][v];
                    parent[v] = u;
                }
            }
        }
        
        printMST(graph, parent);
    }
    
    private int findMin(int[] key, boolean[] visited) {
        int min = Integer.MAX_VALUE, minIdx = -1;
        for (int i = 0; i < V; i++) {
            if (!visited[i] && key[i] < min) { // no need if minIdx == -1
                min = key[i];
                minIdx = i;
            }
        }
        return minIdx;
    }

    public static void main(String[] args) {
        /* Let us create the following graph
        2    3
        (0)--(1)--(2)
        |    / \   |
        6| 8/   \5 |7
        | /      \ |
        (3)-------(4)
             9          */
        MinimumSpanningTree t = new MinimumSpanningTree();
        int graph[][] = new int[][] {{0, 2, 0, 6, 0},
                                    {2, 0, 3, 8, 5},
                                    {0, 3, 0, 0, 7},
                                    {6, 8, 0, 0, 9},
                                    {0, 5, 7, 9, 0},
                                   };
 
        // Print the solution
        t.primMST(graph);
    }

}
