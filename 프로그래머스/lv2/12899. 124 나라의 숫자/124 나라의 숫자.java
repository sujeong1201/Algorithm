class Solution {
	public static String solution(int n) {
		String ans = "";
		
        int length = 1;
        int sum = 0;
        while(true) {
        	sum += (int)Math.pow(3, length);
        	if(sum >= n) break;
        	else length++;
        }
        
        int count = sum - (int)Math.pow(3, length);
        for(int i=length; i>=1; i--) {
        	if(count + 2 * (int)Math.pow(3, i - 1) < n) {
        		count += 2 * (int)Math.pow(3, i - 1);
        		ans += "4";
        	} else if(count + (int)Math.pow(3, i - 1) < n) {
        		count += (int)Math.pow(3, i - 1);
        		ans += "2";
        	} else {
        		ans += "1";
        	}
        }
		
        return ans;
    }
}