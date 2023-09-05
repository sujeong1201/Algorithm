class Solution {
    static int answer;
    static int op[];
    
    public int solution(int[] numbers, int target) {
        op = new int[numbers.length];
        recur(0, numbers.length, numbers, target);
        
        return answer;
    }
    
    public void recur(int cnt, int length, int[] numbers, int target) {
        if(cnt == length) {
            int sum = 0;
            for(int i = 0; i < length; i++) {
                if(op[i] == 0) sum += numbers[i];
                else sum -= numbers[i];
            }
            
            if(sum == target) answer++;
            return;
        }
        
        for(int i = 0; i < 2; i++) {
            op[cnt] = i;
            recur(cnt + 1, length, numbers, target);
        }
    }
}