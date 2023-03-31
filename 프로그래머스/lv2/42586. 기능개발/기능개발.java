import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Queue;

class Solution {
    public int[] solution(int[] progresses, int[] speeds) {
    	Queue<Integer> queue = new ArrayDeque<>();
    	List<Integer> count = new ArrayList<>();  // 한번에 배포하는 기능의 개수 리스트
        
        for(int i=0; i<progresses.length; i++) {
            // 각 작업별 필요한 일수
        	int day = (int) Math.ceil((100 - progresses[i]) / (double)speeds[i]);
        	
        	if(queue.isEmpty() || queue.peek() >= day) {  
                // 큐가 비거나 아직 배포되지 않은 제일 앞 기능보다 필요한 일수가 작거나 같으면 
                // 큐에 넣기
        		queue.offer(day);
        	} else {
                // 아직 배포되지 않은 제일 앞 기능보다 필요한 일수가 더 크면
                // 이전 기능 모두 배포
        		count.add(queue.size());
        		queue.clear();
        		queue.offer(day);
        	}
        }
        if(!queue.isEmpty()) count.add(queue.size());  // 큐에 남아있는 값들 처리
        
        int[] result = new int[count.size()];
        for(int i=0; i<count.size(); i++) result[i] = count.get(i);
        
        return result;
    }
}