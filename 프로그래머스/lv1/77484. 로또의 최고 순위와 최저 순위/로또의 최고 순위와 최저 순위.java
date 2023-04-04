class Solution {
    public int[] solution(int[] lottos, int[] win_nums) {
        int[] answer = new int[2];
        int[] rank = {6, 6, 5, 4, 3, 2, 1};
        
        int same = 0;
        int unknown = 0;
        for(int i=0; i<lottos.length; i++) {
        	if(lottos[i] == 0) {
        		unknown++;
        		continue;
        	}
        	
        	for(int j=0; j<win_nums.length; j++) {
        		if(lottos[i] == win_nums[j]) {
        			same++;
        			break;
        		}
        	}
        }
        
        answer[0] = rank[same + unknown];
        answer[1] = rank[same];
        
        return answer;
    }
}