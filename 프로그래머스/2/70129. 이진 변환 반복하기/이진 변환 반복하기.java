import java.util.*;

class Solution {
    public int[] solution(String s) {
        StringBuffer sb = new StringBuffer(s);
        int countZero = 0, countTrans = 0;
        
        while(true) {
            for(int i = sb.length() - 1; i >= 0; i --) {
                if(sb.charAt(i) == '0') {
                    countZero++;
                    sb.deleteCharAt(i);
                }
            }
            
            sb = transformBinary(sb);
            countTrans++;
            if(sb.toString().equals("1")) break;
        }
        
        int[] answer = {countTrans, countZero};
        return answer;
    }
    
    public static StringBuffer transformBinary(StringBuffer sb) {
        int length = sb.length();
        
        StringBuffer newSb = new StringBuffer();
        
        while(length > 0) {
            newSb.insert(0, length % 2);
            length /= 2;
        }
        
        return newSb;
    }
}