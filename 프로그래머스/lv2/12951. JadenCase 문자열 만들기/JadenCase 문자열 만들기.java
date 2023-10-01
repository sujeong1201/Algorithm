class Solution {
    public String solution(String s) {
        char[] charArr = s.toCharArray();
        
        charArr[0] = changeFirstChar(charArr[0]);  // 첫번째 문자 처리
        
        boolean first = false;  // 공백 다음 문자인지 판단하기 위한 값
        for(int i = 1; i < charArr.length; i++) {
            if(charArr[i] == ' ') {
                first = true;
            } else if(first) {
                charArr[i] = changeFirstChar(charArr[i]);
                first = false;
            } else {
                charArr[i] = changeOtherChar(charArr[i]);
            }
        }
        
        String answer = "";
        for(int i = 0; i < charArr.length; i++) {
            answer += charArr[i];
        }
        return answer;
    }
    
    public static char changeFirstChar(char in) {
        int diff = 'A' - 'a';
        
        if(in >= 'a' && in <= 'z') {
            char out = (char)(in + diff);
            return out;
        } 
        
        return in;
    }
    
    public static char changeOtherChar(char in) {
        int diff = 'a' - 'A';
        
        if(in >= 'A' && in <= 'Z') {
            char out = (char)(in + diff);
            return out;
        }
        
        return in;
    }
}