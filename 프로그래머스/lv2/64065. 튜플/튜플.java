import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

class Solution {
    public int[] solution(String s) {
        PriorityQueue<List<Integer>> pq = new PriorityQueue<>(
    			(list1, list2) -> (list1.size() - list2.size()));
    	
    	s = s.substring(1, s.length() - 1);
    	s = s.replace("{", "");
    	StringTokenizer st = new StringTokenizer(s, "}");
    	while(st.hasMoreTokens()) {
    		String temp = st.nextToken();
    		
    		List<Integer> list = new ArrayList<>();
    		StringTokenizer st2 = new StringTokenizer(temp, ",");
    		while(st2.hasMoreTokens()) {
    			list.add(Integer.parseInt(st2.nextToken()));
    		}
    		pq.offer(list);
    	}
    	
    	List<Integer> ansList = new ArrayList<>();
    	while(!pq.isEmpty()) {
    		List<Integer> cur = pq.poll();
    		for(int num : cur) {
    			if(!ansList.contains(num)) ansList.add(num);
    		}
    	}
    	
    	int[] answer = new int[ansList.size()];
    	for(int i = 0; i < ansList.size(); i++) answer[i] = ansList.get(i);
        return answer;
    }
}