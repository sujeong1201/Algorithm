class Solution {
    public long solution(int w, int h) {
        if(w == h) return (long)w * (long)h - (long)w;
    	
        long gcd = gcd((w > h) ? w : h, (w < h) ? w : h);
        double divW = w / gcd;
        double divH = h / gcd;
        double gradient = divH / divW;
        
        double start = 0, end = 0;
        long count = 0;
        for(int i=1; i<=divW; i++) {
        	end = gradient * i;
        	count += (Math.ceil(end) - Math.floor(start));
        	start = end;
        }
        
        return (long)w * (long)h - gcd * count;
    }
    
    public static long gcd(int a, int b) {
    	if(a % b == 0) {
    		return b;
    	}
    	return gcd(b, a % b);
    }
}