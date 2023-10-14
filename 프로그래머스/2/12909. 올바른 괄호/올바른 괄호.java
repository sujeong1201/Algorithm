import java.util.*;

class Solution {
    boolean solution(String s) {
        Stack<Character> stack = new Stack<>();
        char[] charArr = s.toCharArray();
        
        for(int i = 0; i < charArr.length; i++) {
            if(charArr[i] == '(') {
                stack.push(charArr[i]);
            } else {
                if(stack.isEmpty()) return false;
                else stack.pop();
            }
        }

        if(stack.isEmpty()) return true;
        else return false;
    }
}