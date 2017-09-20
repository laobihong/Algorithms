package test;
import java.util.*;

public class MeetingRoomsII {
    
    public static int minMeetingRooms(MeetingRoomsIIInterval[] intervals) {
        if (intervals == null || intervals.length == 0) {
            return 0;
        }
        
        List<MeetingRoomsIINode> times = new ArrayList<MeetingRoomsIINode>();
        int len = intervals.length;
        for (int i = 0; i < len; i++) {
            times.add(new MeetingRoomsIINode(intervals[i].start, true));
            times.add(new MeetingRoomsIINode(intervals[i].end, false));
        }
        Collections.sort(times);
        
        int max = 0;
        int cur = 0;
        for (MeetingRoomsIINode time: times) {
            if (time.start) {
                cur++;
                Math.max(max, cur);
            } else {
                cur--;
            }
        }
        
        return max;
    }

    public static void main(String[] args) {
        MeetingRoomsIIInterval[] test1 = new MeetingRoomsIIInterval[] {new MeetingRoomsIIInterval(2,7)};
        System.out.println(minMeetingRooms(test1));
    }

}


class MeetingRoomsIINode implements Comparable<MeetingRoomsIINode>{
    int time;
    boolean start;
    public MeetingRoomsIINode(int time, boolean start) {
        this.time = time;
        this.start = start;
    }
    public int compareTo(MeetingRoomsIINode n2) {
        if (this.time == n2.time) {
            return this.start ? 1:-1;
        }
        return this.time - n2.time;
    }
}

class MeetingRoomsIIInterval {
     int start;
     int end;
     MeetingRoomsIIInterval() { start = 0; end = 0; }
     MeetingRoomsIIInterval(int s, int e) { start = s; end = e; }
}