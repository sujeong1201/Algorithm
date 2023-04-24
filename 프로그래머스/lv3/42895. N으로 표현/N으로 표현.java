import java.util.HashSet;
import java.util.Set;

class Solution {
    static Set<Integer> numList[];
	static int ans;
    
    public int solution(int N, int number) {
    	if(number == N) return 1;
    	numList = new HashSet[9];
    	for(int i = 0; i <= 8; i++) numList[i] = new HashSet<>();
    	numList[0].add(0);
    	
    	int cnt = 1;
    	numList[cnt].add(N);
    	
    	while(++cnt <= 8) {
    		String numStr = "";
    		for(int j = 0; j < cnt; j++) numStr += N;
    		int num = Integer.parseInt(numStr);
    		if(num == number) return cnt;
    		numList[cnt].add(num);
    		
    		recur(cnt, 1, N, number);
    		if(ans != 0) return ans;
    	}
    	
        return -1;
    }
    	
	private static void recur(int cnt, int i, int N, int number) {
		if(cnt - i <= 0) return;
		
		for(int cur : numList[cnt - i]) {
			for(int num : numList[i]) {
				if(num == 0) continue;
				for(int j = 0; j < 4; j++) {
					int nextNum = operate(cur, j, num);
					
					numList[cnt].add(nextNum);
					if(nextNum == number) {
						ans = cnt;
						return;
					}
				}
			}
		}
		recur(cnt, i + 1, N, number);
	}
    
    private static int operate(int cur, int i, int N) {
		switch(i) {
		case 0 :
			return cur + N;
		case 1 : 
			return cur - N;
		case 2 :
			return cur * N;
		case 3 :
			return cur / N;
		}
		return -1;
	}
}