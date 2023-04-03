import java.util.StringTokenizer;

/*
    np 로 모든 순열을 구하여 각 순열에 대한 결과값을 구한다.
    결과값을 구할 때는 우선순위가 제일 낮은 연산자부터 잘라주고, 
    계산은 마지막에 자른 우선순위가 가장 높은 연산자부터 수행해준다.
*/

class Solution {
    public long solution(String expression) {
        long answer = 0;
        int[] perm = {0, 1, 2};  // 0:+, 1:-, 2:*, 우선순위 내림차순
        String[] op = {"+", "-", "*"};
        
        do {
        	answer = Math.max(answer, calc(expression, perm, op));
        } while(np(perm));
        
        return answer;
    }
    
    
	private static long calc(String expression, int[] perm, String[] op) {
        	// 첫번째로 우선순위가 가장 낮은 연산자로 자르기
		StringTokenizer splitFirst = new StringTokenizer(expression, op[perm[2]]);
		boolean isFirst3 = true;
		long res3 = 0;
		
		while(splitFirst.hasMoreTokens()) {
            		// 잘라진 토큰에 대하여 두번째 우선순위의 연산자로 자르기
			StringTokenizer splitSecond = new StringTokenizer(splitFirst.nextToken(), op[perm[1]]);
			boolean isFirst2 = true;
			long res2 = 0;
			
			while(splitSecond.hasMoreTokens()) {
                		// 잘라진 토큰에 대하여 우선순위가 가장 높은 연산자로 자르기
				StringTokenizer splitThird = new StringTokenizer(splitSecond.nextToken(), op[perm[0]]);
				
                		// 우선순위가 가장 높은 연산자에 대한 계산
				long res = Long.parseLong(splitThird.nextToken());  // 첫번째 수인 경우 그냥 대입
				while(splitThird.hasMoreTokens()) {
					long num = Long.parseLong(splitThird.nextToken());
					
                    			// 연산자의 종류에 따라 계산
					if("+".equals(op[perm[0]])) res += num;
					else if("-".equals(op[perm[0]])) res -= num;
					else res *= num;
				}
				
                		// 우선순위가 두번째인 연산자에 대한 계산
				if(isFirst2) {
					res2 = res;
					isFirst2 = false;
				}
				else {
					if("+".equals(op[perm[1]])) res2 += res;
					else if("-".equals(op[perm[1]])) res2 -= res;
					else res2 *= res;
				}
			}
			
            		// 우선순위가 가장 낮은 연산자에 대한 계산
			if(isFirst3) {
				res3 = res2;
				isFirst3 = false;
			} else {
				if("+".equals(op[perm[2]])) res3 += res2;
				else if("-".equals(op[perm[2]])) res3 -= res2;
				else res3 *= res2;
			}
		}
		
		return Math.abs(res3);
	}

	private static boolean np(int[] op) {
		int n = op.length;
		
        	// 꼭대기에서 꺾이는 지점 찾기
		int i = n - 1;
		while(i > 0 && op[i - 1] >= op[i]) --i;
		if(i == 0) return false;
		
        	// i-1보다 작은 j 찾아 swap
		int j = n - 1;
		while(op[i - 1] >= op[j]) --j;
		swap(op, i - 1, j);
		
        	// i ~ k 오름차순으로 바꿔주기
		int k = n - 1;
		while(i < k) swap(op, i++, k--);
		
		return true;
	}

	private static void swap(int[] op, int i, int j) {
		int temp = op[i];
		op[i] = op[j];
		op[j] = temp;
	}
}
