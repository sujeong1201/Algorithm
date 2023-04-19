import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;

class Solution {
    public int[] solution(String[] genres, int[] plays) {
    	Map<String, Integer> genreMap = new HashMap<>();
    	List<PriorityQueue<int[]>> genrePlay = new ArrayList<>();
    	List<int[]> sum = new ArrayList<>();
    	int no = 0;
    	
    	for(int i = 0; i < plays.length; i++) {
    		if(!genreMap.containsKey(genres[i])) {
    			genreMap.put(genres[i], no++);
    			genrePlay.add(new PriorityQueue<>((o1, o2) -> o2[1] - o1[1]));
    			sum.add(new int[] {no, 0});
    		}
    		int curNo = genreMap.get(genres[i]);
    		
    		genrePlay.get(curNo).add(new int[] {i, plays[i]});
    		int prevSum = sum.get(curNo)[1];
    		sum.set(curNo, new int[] {curNo, prevSum + plays[i]});
    	}
    	
    	Collections.sort(sum, (o1, o2) -> o2[1] - o1[1]);
    	List<Integer> ansList = new ArrayList<>();
    	for(int[] s : sum) {
    		int curNo = s[0];
    		int idx = 0;
    		int size = genrePlay.get(curNo).size();
    		while(idx < size && idx++ < 2) {
    			ansList.add(genrePlay.get(curNo).poll()[0]);
    		}
    	}
    	
    	int[] ans = new int[ansList.size()];
    	for(int i = 0; i < ansList.size(); i++) ans[i] = ansList.get(i);
        return ans;
    }
}