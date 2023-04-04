class Solution {
    public int[] solution(int[] lottos, int[] win_nums) {
        int[] answer = new int[2];
        int[] rank = {6, 6, 5, 4, 3, 2, 1};  // 등수 매칭
        
        int same = 0;  // 일치하는 번호의 개수
        int unknown = 0;  // 알아볼 수 없는 수의 개수
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
        
        // 최고 순위는 unknown 수가 모두 일치하는 경우
        answer[0] = rank[same + unknown];
        // 최저 순위는 unknown 수가 모두 일치하지 않는 경우
        answer[1] = rank[same];
        
        return answer;
    }
}
