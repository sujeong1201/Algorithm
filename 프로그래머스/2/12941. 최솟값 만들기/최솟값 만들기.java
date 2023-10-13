import java.util.*;

class Solution {
    
    public int solution(int[] A, int[] B)  {
        // 둘 다 오름차순으로 정렬
        Arrays.sort(A);
        Arrays.sort(B);
        
        // 작은수 * 큰수 조합으로 곱해야 함..?
        int sum1 = 0, sum2 = 0;
        for(int i = 0; i < A.length; i++) {
            sum1 += A[i] * B[B.length - i - 1];
            sum2 += A[A.length - i - 1] * B[i];
        }
        
        int answer = Math.min(sum1, sum2);
        return answer;
    }
    
}