class Solution {
    public int solution(int n) {
        int oneCnt = getBinaryOneCnt(n);
       
        int answer = n + 1;
        while(true) {
            int curCnt = getBinaryOneCnt(answer);
            if(oneCnt == curCnt) break;
            answer++;
        }
        
        return answer;
    }
    
    public int getBinaryOneCnt(int n) {
        int cnt = 0;
        
        while(n > 0) {
            if(n % 2 == 1) cnt++;
            n /= 2;
        }
        
        return cnt;
    }
}