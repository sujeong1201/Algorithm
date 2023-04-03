import java.util.StringTokenizer;

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
		StringTokenizer splitFirst = new StringTokenizer(expression, op[perm[2]]);
		boolean isFirst3 = true;
		long res3 = 0;
		
		while(splitFirst.hasMoreTokens()) {
			StringTokenizer splitSecond = new StringTokenizer(splitFirst.nextToken(), op[perm[1]]);
			boolean isFirst2 = true;
			long res2 = 0;
			
			while(splitSecond.hasMoreTokens()) {
				StringTokenizer splitThird = new StringTokenizer(splitSecond.nextToken(), op[perm[0]]);
				
				long res = Long.parseLong(splitThird.nextToken());
				while(splitThird.hasMoreTokens()) {
					long num = Long.parseLong(splitThird.nextToken());
					
					if("+".equals(op[perm[0]])) res += num;
					else if("-".equals(op[perm[0]])) res -= num;
					else res *= num;
				}
				
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
		
		int i = n - 1;
		while(i > 0 && op[i - 1] >= op[i]) --i;
		if(i == 0) return false;
		
		int j = n - 1;
		while(op[i - 1] >= op[j]) --j;
		swap(op, i - 1, j);
		
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