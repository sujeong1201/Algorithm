import java.util.*;

class Solution {
    public String solution(String s) {
        String[] splitStr = s.split(" ");
        
        PriorityQueue<Integer> asc = new PriorityQueue<>();  // 오름차순 우선순위큐(맨앞은 최소값)
        PriorityQueue<Integer> desc = new PriorityQueue<>(Collections.reverseOrder());  // 내림차순 우선순위큐(맨앞은 최대값)
        for(int i = 0; i < splitStr.length; i++) {
            int num = Integer.parseInt(splitStr[i]);
            asc.offer(num);
            desc.offer(num);
        }
        
        String answer = asc.poll() + " " + desc.poll();
        return answer;
    }
}