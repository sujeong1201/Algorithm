class Solution {
    static int userN, emoN;
	static int[] rate = {10, 20, 30, 40};
	static int[] rateSelect;
	static int[] answer = new int[2];
    
    public int[] solution(int[][] users, int[] emoticons) {
    	userN = users.length;
    	emoN = emoticons.length;
        rateSelect = new int[emoN];
        
        perm(0, emoticons, users);
        
        return answer;
    }
    
    // 중복 순열
    private static void perm(int cnt, int[] emoticons, int[][] users) {
		if(cnt == emoN) {
			int num = 0, sales = 0;
			for(int i = 0; i < userN; i++) {
				// 사용자별로 이모티콘 구매 비용과 이모티콘플러스 가입 여부 구하기
				int sum = 0;
				for(int j = 0; j < emoN; j++) {
					if(rateSelect[j] >= users[i][0]) 
						sum += (int) (emoticons[j] - (emoticons[j] * rateSelect[j] / 100.0));
				}
				
				if(sum >= users[i][1]) num++;
				else sales += sum;
			}
			
			if(num > answer[0] || (num == answer[0] && sales > answer[1])) {
				answer[0] = num;
				answer[1] = sales;
			} 
			return;
		}
		
		for(int i = 0; i < 4; i++) {
			rateSelect[cnt] = rate[i];
			perm(cnt + 1, emoticons, users);
		}
	}
}