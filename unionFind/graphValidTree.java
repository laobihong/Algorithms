/*
Graph Valid Tree 

 Description
 Notes
 Testcase
 Judge
Given n nodes labeled from 0 to n - 1 and a list of undirected edges (each edge is a pair of nodes), write a function to check whether these edges make up a valid tree.

 Notice

You can assume that no duplicate edges will appear in edges. Since all edges are undirected, [0, 1] is the same as [1, 0] and thus will not appear together in edges.

Have you met this question in a real interview? Yes
Example
Given n = 5 and edges = [[0, 1], [0, 2], [0, 3], [1, 4]], return true.

Given n = 5 and edges = [[0, 1], [1, 2], [2, 3], [1, 3], [1, 4]], return false.

Tags 
Depth First Search Facebook Zenefits Union Find Breadth First Search Google
Related Problems 
Medium Connecting Graph 40 %
Medium Connected Component in Undirected Graph
**/

//sol 1: canonical union find
    public boolean validTree(int n, int[][] edges) {
        if ( n <= 0 || edges == null) {
            return false;
        }
        
        if (edges.length == 0) {
            return n == 1;
        }
        
        if ( n != edges.length + 1) {
            return false;
        }
        
        int len = edges.length;
        UnionFind uf = new UnionFind(n);
        for (int i = 0; i < len; i++) {
            int n1 = edges[i][0];
            int n2 = edges[i][1];
            if (uf.find(n1) == uf.find(n2)){
                return false;
            }
            uf.union(n1, n2);
        }
        return true;
    }
    
    class UnionFind {
        int[] father;
        public UnionFind(int n) {
            father = new int[n];
            for (int i = 0; i < n; i++) {
                father[i] = i;
            }
        }
        
        public void union(int a, int b) {
            int fa_a = find(a);
            int fa_b = find(b);
            if (fa_a != fa_b) {
                father[fa_a] = fa_b;
            }
        }
        
        public int find(int a) {
            if (father[a] == a) {
                return a;
            }
            return father[a] = find(father[a]);
        }
    }

// sol 2: because in the second for loop, anyway we need to find the two fathers and examine if they are equal, so it could better more desirable to merge union in it. Save a function and no need to create new class UninoFind, and therefore save some time
    public boolean validTree(int n, int[][] edges) {
        if ( n <= 0 || edges == null) {
            return false;
        }
        
        if (edges.length == 0) {
            return n == 1;
        }
        
        if ( n != edges.length + 1) {
            return false;
        }
        
        int[] father = new int[n];
        for (int i = 0; i < n; i++) {
            father[i] = i;
        }
        
        for (int i = 0; i < edges.length; i++) {
            int father_0 = find(father, edges[i][0]);
            int father_1 = find(father, edges[i][1]);
            if (father_0 == father_1) {
                return false;
            }
            father[father_0] = father_1;
        }
        
        return true;
    }
    
    private int find(int[] father, int node) {
        if (father[node] == node) {
            return node;
        }
        
        return find(father, father[node]);
    }
// sol 3: bfs
    public boolean validTree(int n, int[][] edges) {
        if ( n <= 0 || edges == null) {
            return false;
        }
        
        if (edges.length == 0) {
            return n == 1;
        }
        
        if ( n != edges.length + 1) {
            return false;
        }
        
        Map<Integer, List<Integer>> map = new HashMap<>();
        int len = edges.length;
        for (int i = 0; i < len; i++) {
            int n1 = edges[i][0];
            int n2 = edges[i][1];
            if (!map.containsKey(n1)) {
                map.put(n1, new ArrayList<Integer>());
            }
            map.get(n1).add(n2);
            if (!map.containsKey(n2)) {
                map.put(n2, new ArrayList<Integer>());
            }
            map.get(n2).add(n1);
        }
        
        Queue<Integer> queue = new LinkedList<>();
        Set<Integer> set = new HashSet<>();
        queue.add(0);
        set.add(0);
        
        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                int cur = queue.poll();
                
                for (Integer neighbor: map.get(cur)) {
                    if (!set.contains(neighbor)) {
                        set.add(neighbor);
                        queue.add(neighbor);
                    }

                }
            }
        }
        
        return set.size() == n;
    }

// wront version 3:
   public boolean validTree(int n, int[][] edges) {
        if ( n <= 0 || edges == null) {
            return false;
        }
        
        if (edges.length == 0) {
            return n == 1;
        }
        
        if ( n != edges.length + 1) {
            return false;
        }
        
        Map<Integer, List<Integer>> map = new HashMap<>();
        int len = edges.length;
        for (int i = 0; i < len; i++) {
            int n1 = edges[i][0];
            int n2 = edges[i][1];
            if (!map.containsKey(n1)) {
                map.put(n1, new ArrayList<Integer>());
            }
            map.get(n1).add(n2);
            if (!map.containsKey(n2)) {
                map.put(n2, new ArrayList<Integer>());
            }
            map.get(n2).add(n1);
        }
        
        Queue<Integer> queue = new LinkedList<>();
        Set<Integer> set = new HashSet<>();
        queue.add(0);
        set.add(0);
        
        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                int cur = queue.poll();
                
                for (Integer neighbor: map.get(i)) {
                    if (set.contains(neighbor)) { // ####WRONG: e.g. when cur == 4, 1 has already been added into set
// given 5, [[0,1], [0,2], [0,3], [0,4]]
                        return false;
                    }
                    set.add(neighbor);
                    queue.add(neighbor);
                }
            }
        }
        
        return set.size() == n;
    }
