/*
Find the Weak Connected Component in the Directed Graph 

 Description
 Notes
 Testcase
 Judge
Find the number Weak Connected Component in the directed graph. Each node in the graph contains a label and a list of its neighbors. (a connected set of a directed graph is a subgraph in which any two vertices are connected by direct edge path.)

 Notice

Sort the element in the set in increasing order

Have you met this question in a real interview? Yes
Example
Given graph:

A----->B  C
 \     |  | 
  \    |  |
   \   |  |
    \  v  v
     ->D  E <- F
Return {A,B,D}, {C,E,F}. Since there are two connected component which are {A,B,D} and {C,E,F}

Tags 
Union Find
Related Problems 
Medium Connecting Graph 39 %
Hard Number of Islands II 18 %
Medium Find the Weak Connected Component in the Directed Graph 25 %
Medium Connected Component in Undirected Graph
*/

/**
 * Definition for Directed graph.
 * class DirectedGraphNode {
 *     int label;
 *     ArrayList<DirectedGraphNode> neighbors;
 *     DirectedGraphNode(int x) { label = x; neighbors = new ArrayList<DirectedGraphNode>(); }
 * };
 */

// sol 1: canonical union find
public class Solution {
    /**
     * @param nodes a array of Directed graph node
     * @return a connected set of a directed graph
     */
    public List<List<Integer>> connectedSet2(ArrayList<DirectedGraphNode> nodes) {
        List<List<Integer>> res = new ArrayList<>();
        if (nodes == null || nodes.size() == 0) {
            return res;
        }
        
        int n = nodes.size();
        UnionFind uf = new UnionFind(n);
        for (DirectedGraphNode node: nodes) {
            ArrayList<DirectedGraphNode> neighbors = node.neighbors;
            for (DirectedGraphNode neighbor: neighbors) {
                uf.union(nodes.indexOf(node), nodes.indexOf(neighbor));
            }
        }
        
        Map<Integer, ArrayList<Integer>> map = new HashMap<>();
        for (int i = 0; i < n; i++) {
            int fa = uf.find(i);
            if (!map.containsKey(fa)) {
                map.put(fa, new ArrayList<Integer>());
            }
            map.get(fa).add(nodes.get(i).label);
        }
        
        for (ArrayList<Integer> list: map.values()) {
            Collections.sort(list); // RETURNS VOID!
            res.add(list);
        }
        
        return res;
    }
}

class UnionFind {
    
    int[] father;
    
    public UnionFind (int n) {
        father = new int[n];
        for (int i = 0; i < n; i++) {
            father[i] = i;
        }
    }
    
    public void union (int a, int b) {
        int fa_a = find(a);
        int fa_b = find(b);
        if (fa_a != fa_b) {
            father[fa_a] = fa_b;
        }
    }
    
    public int find (int a) {
        if (father[a] == a) {
            return a;
        }
        //return father[a] = find(father[a]);
    }
}

// sol 2: in sol 1, nodes.indexOf() takes O(logn) time, so if use map(free and unlimited keys) instead of array (fixed and limited keys), search O(logn) -> O(1)
 class UnionFind {
        
        Map<Integer, Integer> father;
        
        public UnionFind(Set<Integer> hashset) {
            father = new HashMap<Integer, Integer>();
            for (Integer i : hashset) {
                father.put(i,i);
            }
        } // end constructor
        
        public int find(int i) {
            int parent = father.get(i);
            while(parent != father.get(parent)){
                parent = father.get(parent);
            }
            
            return parent;
        }// end find
        
        public void union(int i, int j) {
            int parent_i = find(i);
            int parent_j = find(j);
            if (parent_i != parent_j) {
                father.put(parent_i, parent_j);
            }
            
        }// end union
        
        // not used here, just for practice
        public int compressed_find(int i) {
            int great_grand_parent = find(i);
            int grand_father = -1;
            int fa = this.father.get(i);
            while (fa != father.get(fa)) {
                grand_father = father.get(fa);
                father.put(fa, great_grand_parent);
                fa = grand_father;
            }
            return great_grand_parent;
        }
        
    } 
    
    private List<List<Integer>> printResult(Set<Integer> hashset, UnionFind uf) {
        
        List<List<Integer>> results = new ArrayList<List<Integer>>();
        HashMap<Integer, List<Integer>> father = new HashMap<Integer, List<Integer>>();
        
        for(Integer i : hashset) {
            int parent = uf.find(i);
            if (!father.containsKey(parent)) {
                father.put(parent, new ArrayList<Integer>());
            }
            List<Integer> cur_list = father.get(parent);
            cur_list.add(i);
            //father.put(parent, cur_list);
        }
        
        for(List<Integer> list : father.values()){
            Collections.sort(list);
            results.add(list);
        }
        
        return results;
    }
    
    public List<List<Integer>> connectedSet2(ArrayList<DirectedGraphNode> nodes) {
        Set<Integer> hashset = new HashSet<Integer>();
        for(DirectedGraphNode cur_node : nodes) {
            hashset.add(cur_node.label);
        }
        
        UnionFind uf = new UnionFind(hashset);
        
        for (DirectedGraphNode cur_node : nodes) {
            for (DirectedGraphNode neighbor: cur_node.neighbors) {
                uf.union(cur_node.label, neighbor.label);
            }
        }// end for
        
        return printResult(hashset, uf);
    }

