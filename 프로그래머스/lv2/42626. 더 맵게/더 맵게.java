import java.util.PriorityQueue;

class Solution {
    public int solution(int[] scoville, int K) {
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        for(int i=0; i<scoville.length; i++) {
        	pq.offer(scoville[i]);
        }
        
        int count = 0;
        while(!pq.isEmpty()) {
        	int first = pq.poll();
        	if(first >= K) return count;
        	if(pq.isEmpty()) return -1;
        	int second = pq.poll();
        	
        	pq.offer(first + second * 2);
        	count++;
        }
        
        return 0;
    }
}