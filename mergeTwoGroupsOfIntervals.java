import java.util.*;

class mergeTwoGroupsOfIntervals {
    // not used! see merge2
    public List<Interval> merge(List<Interval> l1, List<Interval> l2) {
        /** Assuming they are sorted 
        // Step1: sort the two lists separately, O(log(l1)) + O(log(l2))
        Comparator<Interval> comp = new Comparator<Interval>(){
            public int compare(Interval a, Interval b){
                if (a.start == b.start){
                    return a.end - b.end;
                }
                return a.start - b.start;
            }
        }

        Collections.sort(l1, comp);
        Collections.sort(l2, comp);
        */

        // Step2: merge the two sorted lists into one, O (l1 + l2)
        List<Interval> list = new ArrayList<>();
        int size1 = l1.size(), size2 = l2.size();
        int i1 = 0, i2 = 0;
        for(; i1 < size1 && i2 < size2;){ // Fixed error: i2 -> l2, solution write the full name list2 instead!
            Interval interval1 = l1.get(i1);
            Interval interval2 = l2.get(i2);
            if (interval1.compareTo(interval2) <= 0){
                list.add(interval1);
                i1++;
            } else {
                list.add(interval2);
                i2++;
            }
        }
        
        while (i1 < size1) {
            Interval interval1 = l1.get(i1);
            list.add(interval1);
            i1++;
        }

        while (i2 < size2) {
            Interval interval2 = l2.get(i2);
            list.add(interval2);
            i2++;
        }
        return null;
    }

    public static Interval merge2(Interval h1, Interval h2){ // Fixed error: Interval -> head
        // Step1: merge sort
        Interval dummy = new Interval();
        Interval cur = dummy;
        while (h1 != null && h2 != null){
            if (h1.compareTo(h2) <= 0) {
                cur.next = h1;
                h1 = h1.next;
            } else {
                cur.next = h2;
                h2 = h2.next;
            }
            cur = cur.next;
        }
        if (h1 != null) {
            cur.next = h1;
        } 
        else if (h2 != null) {
            cur.next = h2;
        }

        cur = dummy.next;
        
        // Step2: merge intervals
        int lastStart = cur.start;
        int lastEnd = cur.end;
        cur = cur.next;
        Interval result = new Interval();
        Interval resultCur = result;
        while (cur != null){
            int start = cur.start, end = cur.end;
            if (start > lastEnd){
                resultCur.next = new Interval(lastStart, lastEnd);
                resultCur = resultCur.next;
                lastStart = start;
                lastEnd = end;
            } else {
                lastEnd = Math.max(lastEnd, end);
            }
            cur = cur.next;
        }

        resultCur.next = new Interval(lastStart, lastEnd);
        return result.next;
    }    

    private static Interval mergeIntervalHelper(Interval head){
        Interval result = new Interval();
        Interval cur = result;
        int lastStart = head.start;
        int lastEnd = head.end;
        head = head.next;
        while (head != null) {
            int start = head.start, end = head.end;
            if (start > lastEnd){
                cur.next = new Interval(lastStart, lastEnd);
                cur = cur.next;
                lastStart = start;
                lastEnd = end;
            } else {
                lastEnd = Math.max(lastEnd, end);
            }
            head = head.next;
        }
        cur.next = new Interval(lastStart, lastEnd);
        return result.next;
    }

    // intersection case
    public static Interval merge3(Interval h1, Interval h2){
        // step 1: merge self intervals first
        // so that in the future, overlapping must be from the other
        h1 = mergeIntervalHelper(h1);
        printList(h1, " 3 after preprocessing");
        h2 = mergeIntervalHelper(h2); 
        printList(h2, " 4 after preprocessing");

        // step 2: merge intersection
        Interval result = new Interval();
        Interval cur = result;
        int lastStart, lastEnd;
        if (h1.compareTo(h2) <= 0){
            lastStart = h1.start;
            lastEnd = h1.end;
            h1 = h1.next;
        } else {
            lastStart = h2.start;
            lastEnd = h2.end;
            h2 = h2.next;
        }

        while (h1 != null && h2 != null){
            int start, end;
            if (h1.compareTo(h2) <= 0) {
                start = h1.start;
                end = h1.end;
                h1 = h1.next;
            } else {
                start = h2.start;
                end = h2.end;
                h2 = h2.next;
            }

            // no overlapping, then update
            if (start > lastEnd) {
                lastStart = start;
                lastEnd = end;
            } else {
                // IMPORTANT: if overlapping exists, this must be from the other one
                cur.next = new Interval(start, Math.min(end, lastEnd));
                cur = cur.next;
                lastStart = start;
                lastEnd = end;
            }
        }

        Interval lastNode = h1 == null? h2: h1;
        if(lastNode != null) {
            int start = lastNode.start, end = lastNode.end;
            if (start <= lastEnd) {
                cur.next = new Interval(start, Math.min(end, lastEnd));
            }
        }

        return result.next;
    }
    
    public static void printList(Interval head, String name){
        System.out.println("Printing list " + name);
        while (head != null) {
            System.out.println("[" + head.start + ", " + head.end + "]");
            head = head.next;
        }
    }

    public static void main(String[] args){
        // list 1: [1,3], [5,7], [9,11], [12,13], [15,17], [20, 21] in sorted order
        Interval head1 = new Interval(1,3);
        Interval i1 = new Interval(5,7);
        Interval i2 = new Interval(9,11);
        Interval i3 = new Interval(12,13);
        Interval i4 = new Interval(15,17);
        Interval i9 = new Interval(20,21);
        head1.next = i1; i1.next = i2; i2.next = i3; i3.next = i4; i4.next = i9;
        printList(head1, "1");

        // list 2: [3,5], [6,10], [12,13], [18,19], [20,22] in sorted order
        Interval head2 = new Interval(3,5);
        Interval i5 = new Interval(6,10);
        Interval i6 = new Interval(12,13);
        Interval i7 = new Interval(18,19);
        Interval i8 = new Interval(20,22);
        head2.next = i5; i5.next = i6; i6.next = i7; i7.next = i8;
        printList(head2, "2");

        // Expected output: [1,11], [12,13], [15,17], [18,19], [20,22]
        Interval newHead = merge2(head1, head2);
        printList(newHead, "result");

        // list 3: [1,3], [2,3], [5,6], [6,7], [9,11], [12,13], [15,17], [20,21] in sorted order
        // [1,3] and [2,3], and [5,6] and [6,7] need to be merged first
        Interval head3 = new Interval(1,3);
        Interval i10 = new Interval(2,3);
        Interval i11 = new Interval(5,6);
        Interval i12 = new Interval(6,7);
        Interval i13 = new Interval(9,11);
        Interval i14 = new Interval(12,13);
        Interval i15 = new Interval(15,17);
        Interval i16 = new Interval(20,21);
        head3.next = i10; i10.next = i11; i11.next = i12; i12.next = i13; i13.next = i14; i14.next = i15; i15.next = i16;
        printList(head3, "3");

        // list 4: [2,3], [3,4], [7,8], [8,9], [12, 17], [20,22] in sorted order
        // [2,3] and [3,4], and [7,8] and [8,9] need to be merged first
        // then the newly created [7,9] should have two corner cases:
        // in list 3 we have [6,7] and [9,11], so together these three will give us [7,7] and [9,9]
        Interval head4 = new Interval(2,3);
        Interval i17 = new Interval(3,4);
        Interval i18 = new Interval(7,8);
        Interval i19 = new Interval(8,9);
        Interval i20 = new Interval(12,17);
        Interval i21 = new Interval(20,22);
        head4.next = i17; i17.next = i18; i18.next = i19; i19.next = i20; i20.next = i21;
        printList(head4, "4");

        Interval intersection = merge3(head3, head4);
        printList(intersection, "intersection result");
    }
}

class Interval implements Comparable<Interval>{
    int start;
    int end;
    Interval next;
    public Interval(){
        start = 0;
        end = 0;
        next = null;
    }
    public Interval(int s, int e){
        start = s;
        end = e;
        next = null;
    }
    public Interval(int s, int e, Interval n) {
        start = s;
        end = e;
        next = n;
    }
    public int compareTo(Interval i2){ // Fixed error: missing return type
        if (this.start == i2.start){
            return this.end - i2.end;
        }
        return this.start - i2.start;
    }
}	
