class Solution {
    public int solution(String s) {
        int answer = Integer.MAX_VALUE;
        
		for(int i=1; i<=s.length(); i++) {
			String cur = "";
			int start = 0;  // 시작 지점
			int cnt = 1;  // 같은 문자열이 반복된 횟수
			int len = 0;  // 압축된 문자열의 길이
            
			while(start + i <= s.length()) {
				String tmp = s.substring(start, start + i);
 				if(start == 0) {  // 첫번째 문자열 단위인 경우
					cur = tmp;
					start += i;
					continue;
				}
				
				if(tmp.equals(cur)) { 
                    // 앞의 문자열 단위와 동일한 경우 cnt 증가
					cnt++;
				} else {
                    // 앞의 문자열 단위 다른 경우
                    // 지금까지 문자열을 압축해 길이를 더해주고 새로 시작
					len += i + (cnt == 1 ? 0 : ((int)Math.log10(cnt) + 1));
					cnt = 1;
					cur = tmp;
				}
				
				start += i;
			}
			
			// 반복문의 마지막 문자열 단위는 더해지지 않았으므로 더해주기
			len += i + (cnt == 1 ? 0 : (int)(Math.log10(cnt) + 1));
			// 마지막에 남은 문자열 붙여주기
			len += (s.length() - start > 0) ? s.length() - start : 0;
			
			if(len < answer) answer = len;
		}
		
		return answer;
    }
}