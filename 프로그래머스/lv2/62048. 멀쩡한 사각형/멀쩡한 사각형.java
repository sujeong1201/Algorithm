class Solution {
    // 숫자가 크므로 오버플로우 나지 않게 주의!!!
    public long solution(int w, int h) {
        // 정사각형인 경우
        if(w == h) return (long)w * (long)h - (long)w;
    	
        // 같은 비율의 최소 크기 사각형으로 줄이기
        long gcd = gcd((w > h) ? w : h, (w < h) ? w : h);
        double divW = w / gcd;
        double divH = h / gcd;
        
        // 가로의 각 점을 기준으로 세로의 어떤 지점에서 만나는지를 계산
        // 바로 직전 지점 ~ 이번 지점이 포함되는 사각형의 개수를 세어준다.
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
    
    // 최대공약수
    public static long gcd(int a, int b) {
    	if(a % b == 0) {
    		return b;
    	}
    	return gcd(b, a % b);
    }
}