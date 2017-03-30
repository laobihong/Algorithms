public void sortIntegers2(int[] A) {
        if (A == null || A.length == 0) {
            return;
        }
        sortHelper(A, 0, A.length - 1);
    }
    
    private void sortHelper(int[] A, int start, int end) {
        if(start == end) {
            return;
        }
        int mid = start + (end - start) / 2;
        sortHelper(A, start, mid);
        sortHelper(A, mid + 1, end);
        merge(A, start, mid, end);
    }
    
    private void merge(int[] A, int start, int mid, int end) {
        int[] temp = new int[mid - start + 1];
        for (int i = 0; i < temp.length; i++) {
            temp[i] = A[start + i];
        }
        
        for(int i = start, p1 = 0, p2 = mid + 1; i <= end; i++) {
            if(p1 < temp.length && p2 <= end) {
                if( temp[p1] <= A[p2]) {
                    A[i] = temp[p1];
                    p1++;
                }
                else {
                    A[i] = A[p2];
                    p2++;
                }
            }
            else if(p1 < temp.length) {
                A[i] = temp[p1];
                p1++;
            }
            else {
                A[i] = A[p2];
                p2++;
            }
            
        }
    }

// =============================================================
// not working, since Java is "pass by value" rather than
// "pass by reference", which is the case in, say, c++
    public void sortIntegers2(int[] A) {
        if (A == null || A.length == 0) {
            return;
        }        
        A = helper(A, 0, A.length - 1);
    }
    
    private int[] helper(int[] A, int start, int end) {
        if(start == end) {
            return null;
        }
        int mid = start + (end - start) / 2;
        int[] m1 = helper(A, start, mid);
        int[] m2 = helper(A, mid + 1, end);
        return merge(m1, m2);
    }
    
    private int[] merge(int[] m1, int[] m2) {
        if (m1 == null || m1.length == 0) {
            return m2;
        }
        if (m2 == null || m2.length == 0) {
            return m1;
        }
        int size = m1.length + m2.length;
        int index = 0;
        int i = 0, j = 0; 
        int[] res = new int[size];
        while(i < m1.length && j < m2.length) {
            if(m1[i] < m2[j]) {
                res[index++] = m1[i++];
            }
            else {
                res[index++] = m2[j++];
            }
        }
        if(i == m1.length) {
            while(j < m2.length) {
                res[index++] = m2[j++];
            }
        }
        if(j == m2.length) {
            while(i < m1.length) {
                res[index++] = m1[i++];
            }
        }
        
        return res;
    }

