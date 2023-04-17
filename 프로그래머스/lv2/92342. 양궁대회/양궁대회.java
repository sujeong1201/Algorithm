class Solution {
    static int ryan[], answer[], scoreDiff;
    
    public int[] solution(int n, int[] info) {
		ryan = new int[11];
		answer = new int[11];
		
		shoot(n, 0, n, info);
		
		if(scoreDiff == 0) return new int[] {-1};
		return answer;
    }
    
    public static void shoot(int n, int cnt, int remain, int[] info) {
		if(cnt == 11) {
			if(remain > 0) ryan[10] = remain;
			
			int apeachS = 0, ryanS = 0;
			for(int i = 0; i < 11; i++) {
				if((info[i] > ryan[i]) || (info[i] == ryan[i] && info[i] != 0)) apeachS += 10 - i;
				else if(ryan[i] > info[i]) ryanS += 10 - i;
			}
			
			int diff = ryanS - apeachS;
			if(diff > scoreDiff) {
				answer = ryan.clone();
				scoreDiff = diff;
			} else if(diff == scoreDiff) {
				if(check()) answer = ryan.clone();
			}
			
			return;
		}
		
		// 라이언이 해당 점수를 가져가는 경우
		if(remain > info[cnt]) { 
			ryan[cnt] = info[cnt] + 1;  
			shoot(n, cnt + 1, remain - info[cnt] - 1, info);
		}
		// 라이언이 해당 점수를 가져가지 않는 경우
		ryan[cnt] = 0;
		shoot(n, cnt + 1, remain, info);
	}
    
	private static boolean check() {
		for(int i = 10; i >= 0; i--) {
			if(ryan[i] == answer[i]) continue;
			
			if(ryan[i] > answer[i]) return true;
			else return false;
		}
		
		return false;
	}
}