import java.util.Stack;

class Solution {
    public String solution(String new_id) {
       String answer = new_id.toLowerCase();  // 1단계 : 대문자->소문자
        
        StringBuffer sb = new StringBuffer();
        sb.append(answer);
         
        // 2단계: 소문자 숫자 - _ . 제외 모두 제거
        for(int i=sb.length()-1; i>=0; --i) {
        	char ch = sb.charAt(i);
        	
        	if(ch - 97 >= 0 && ch - 97 < 26) continue;
        	if(ch - 48 >= 0 && ch - 48 < 10) continue;
        	if(ch == '-' || ch == '.' || ch == '_') continue;
        	
        	sb.deleteCharAt(i);
        }
        
        // 3단계: 마침표 2번 이상 연속된 부분 하나의 마침표로 치환
        Stack<Character> stack = new Stack<>();
        for(int i=sb.length()-1; i>=0; --i) {
        	char ch = sb.charAt(i);
        	
        	if(ch == '.') {
        		stack.push(ch);
        		sb.deleteCharAt(i);
        	} else if(!stack.isEmpty()) {
        		stack.clear();
        		sb.insert(i + 1, '.');
        	}
        }
        // 처음에 있던 마침표는 자동적으로 제거됨
        
        // 4단계: 처음이나 끝에 있는 마침표 제거
        if(sb.length() > 0 && sb.charAt(sb.length() - 1) == '.') sb.deleteCharAt(sb.length() - 1);
        
        // 5단계: 빈 문자열인 경우 "a" 대입
        if(sb.length() == 0) sb.append("a");
        
        // 6단계: 16자 이상인 경우 15개만 남기고, 마지막이 마침표라면 제거
        if(sb.length() >= 16) {
        	if(sb.charAt(14) == '.') answer = sb.substring(0, 14);
        	else answer = sb.substring(0, 15);
        } else {
        	answer = sb.toString();
        }
        
        // 7단계: 2자 이하이면 길이가 3이 될 때까지 마지막 문자를 붙이기
        if(answer.length() == 1) {
        	String tmp = answer;
        	answer = answer + tmp + tmp;
        } else if(answer.length() == 2) {
        	char tmp = answer.charAt(1);
        	answer = answer + tmp;
        }
        
        return answer;
    }
}