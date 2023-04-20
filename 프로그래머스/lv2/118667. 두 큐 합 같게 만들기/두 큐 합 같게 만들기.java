import java.util.ArrayDeque;
import java.util.Queue;

class Solution {
	static int queueMerge[], answer, size;
	static long sum;
    
    public int solution(int[] queue1, int[] queue2) {
		size = queue1.length;
		queueMerge = new int[size * 2];
		for(int i = 0; i < size; i++) {
			queueMerge[i] = queue1[i];
			sum += queue1[i];
		}
		for(int i = 0; i < size; i++) {
			queueMerge[size + i] = queue2[i];
			sum += queue2[i];
		}

        answer = Integer.MAX_VALUE;
		makeSame();
        if(answer == Integer.MAX_VALUE) answer = -1;
		return answer;
    }
    
	private static void makeSame() {
		int cnt = 0, idx = 0;
		long plus = 0;
		Queue<Integer> queue = new ArrayDeque<>();
		while(true) {
            if(idx + cnt >= size * 2) break;
			if(plus == sum / 2) {
				int poll = idx + cnt - (size - idx);
				if(idx + cnt < size) poll += size * 2;
				answer = Math.min(answer, poll);
			}
			
			if(plus <= sum / 2) {
				plus += queueMerge[idx + cnt];
 				queue.offer(queueMerge[idx + cnt]);
				cnt++;
			} else {
				plus -= queueMerge[idx];
				queue.poll();
				idx++;
				cnt--;
			}
		}
	}
}