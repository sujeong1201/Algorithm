import java.util.StringTokenizer;

class Solution {
    public int solution(int n, int k) {
		String kJinsoo = makeKJinsoo(n, k);
		return findPrime(kJinsoo);
    }
    
    private String makeKJinsoo(int n, int k) {
		String kJinsoo = "";
		int pos = 0;
		while (true) {
			int remain = n % k;
			n /= k;
			kJinsoo = remain + kJinsoo;

			if (n == 0)
				break;
		}

		return kJinsoo;
	}

	private int findPrime(String kJinsoo) {
		int cnt = 0;
		StringTokenizer st = new StringTokenizer(kJinsoo, "0");

		while (st.hasMoreTokens()) {
			if (isPrime(Long.parseLong(st.nextToken())))
				cnt++;
		}

		return cnt;
	}

	private boolean isPrime(long num) {
		if(num == 1) return false;
		for (int i = 2; i <= (int) Math.sqrt(num); i++) {
			if (num % i == 0) return false;
		}

		return true;
	}
}