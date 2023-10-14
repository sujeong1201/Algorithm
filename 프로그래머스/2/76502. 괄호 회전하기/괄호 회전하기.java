import java.util.*;

class Solution {
    public int solution(String s) {
        int answer = 0;
        
        StringBuffer sb = new StringBuffer(s);
        if(sb.length() % 2 == 1) return 0;  // 길이가 홀수인 경우 올바른 괄호일 수 없음
        
        // 문자열의 길이만큼 회전 반복
        for(int i = 0; i < sb.length(); i++) {
            if(isRight(sb)) answer++;
            
            // 회전
            sb.append(sb.charAt(0));
            sb.deleteCharAt(0);
        }
        
        return answer;
    }
    
    public static boolean isRight(StringBuffer sb) {
        Stack<Character> stack = new Stack<>();
        
        for(int i = 0; i < sb.length(); i++) {
            char cur = sb.charAt(i);
            if(cur == '[' || cur == '{' || cur == '(') {
                stack.push(cur);
            } else {
                if(stack.isEmpty()) return false;
                
                char popChar = stack.pop();
                if((cur == ']' && popChar != '[') || 
                   (cur == '}' && popChar != '{') ||
                   (cur == ')' && popChar != '(')) return false;
            } 
        }
        
        if(stack.isEmpty()) return true;
        return false;
    }
}