/**
Heapify 

 Description
 Notes
 Testcase
 Judge
Given an integer array, heapify it into a min-heap array.

For a heap array A, A[0] is the root of heap, and for each A[i], A[i * 2 + 1] is the left child of A[i] and A[i * 2 + 2] is the right child of A[i].
Have you met this question in a real interview? Yes
Clarification
What is heap?

Heap is a data structure, which usually have three methods: push, pop and top. where "push" add a new element the heap, "pop" delete the minimum/maximum element in the heap, "top" return the minimum/maximum element.

What is heapify?
Convert an unordered integer array into a heap array. If it is min-heap, for each element A[i], we will get A[i * 2 + 1] >= A[i] and A[i * 2 + 2] >= A[i].

What if there is a lot of solutions?
Return any of them.
Example
Given [3,2,1,4,5], return [1,2,3,4,5] or any legal heap array.

Challenge 
O(n) time complexity

Tags 
LintCode Copyright Heap
*/

    private void siftdown(int[] A, int k) {
        while (k < A.length) {
            int smallest = k;
            if (k * 2 + 1 < A.length && A[k * 2 + 1] < A[smallest]) {
                smallest = k * 2 + 1;
            }
            if (k * 2 + 2 < A.length && A[k * 2 + 2] < A[smallest]) {
                smallest = k * 2 + 2;
            }
            if (smallest == k) {
                break;
            }
            int temp = A[smallest];
            A[smallest] = A[k];
            A[k] = temp;
            
            k = smallest;
        }
    }
    
    public void heapify(int[] A) {
        for (int i = A.length / 2; i >= 0; i--) {
            siftdown(A, i);
        } // for
    }


/**
Prove building a heap cost O(n) time.
Sol:
The height is k, and for each level that has a height (k-l), i.e. it is the lth level, it contains pow(2,l) nodes. For each of the nodes, at most it will perform siftdown (k-l)times, so the total time is calculated to be sum(pow(2,l) * (k - l)) = sum(pow(2, k)*(k - l)/pow(2, k - l)). pow(2, k) = n, and the other part is an arithmetico-geometric sequence, and Sn = a/(1-r) + [a + (n - 1)d]*pow(r,n)/(1 - r) + dr(1 - pow(r, n-1))/(1-r)^2 = 0/(1-1/2) - 0 + 1*1/2*(1-0)/(1-1/2)^2 = 0 - 0 + 1/2/(1/4) = 2. Therefore it's O(n).
*/
