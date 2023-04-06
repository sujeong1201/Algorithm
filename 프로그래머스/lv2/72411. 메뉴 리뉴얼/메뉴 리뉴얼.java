import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

class Solution {
    static int[] combination;  // 조합
	static boolean[][] menu;  // 각 주문에 포함된 메뉴들 체크
    static boolean[] isAble;
	static int maxCount;
	static List<int[]> resultPerCnt;
    
    public String[] solution(String[] orders, int[] course) {
		menu = new boolean[orders.length][26];
        isAble = new boolean[26];
		for(int i=0; i<orders.length; i++) {
			for(int j=0; j<orders[i].length(); j++) {
				menu[i][orders[i].charAt(j) - 65] = true;
                isAble[orders[i].charAt(j) - 65] = true;
			}
		}
		
		List<String> resultList = new ArrayList<>();
		for(int i=0; i<course.length; i++) {
			combination = new int[course[i]];
			maxCount = 0;
			resultPerCnt = new ArrayList<int[]>();
			comb(orders, course[i], 0, 0);
			
			for(int j=0; j<resultPerCnt.size(); j++) {
				String str = "";
				for(int k=0; k<resultPerCnt.get(j).length; k++) str += (char)(resultPerCnt.get(j)[k] + 65);
				resultList.add(str);
			}
		}
		
		Collections.sort(resultList);
		String[] answer = new String[resultList.size()];
		for(int i=0; i<resultList.size(); i++) answer[i] = resultList.get(i);
		
		return answer;
    }
    
    	public static void comb(String[] orders, int r, int cnt, int start) {
		if (cnt == r) {
			// 몇명의 손님들에 포함되는지 확인
			int count = 0;
			for(int i=0; i<orders.length; i++) {
				boolean check = true;
				for(int j=0; j<combination.length; j++) {
					check = check && menu[i][combination[j]];
				}
				
				if(check) count++;
			}
			
			if(count >= 2 && count == maxCount) {
				resultPerCnt.add(combination.clone());
			} else if(count >= 2 && count > maxCount) {
				maxCount = count;
				resultPerCnt.clear();
				resultPerCnt.add(combination.clone());
			}
			
			return;
		}

		for (int i = start; i < 26; i++) {
            if(!isAble[i]) continue;
            
			combination[cnt] = i;
			comb(orders, r, cnt + 1, i + 1);
		}
	}
}