import java.util.HashMap;
import java.util.Map;

class Solution {
	public String solution(int[] numbers, String hand) {
		/*
		 * 1, 2, 3
		 * 4, 5, 6
		 * 7, 8, 9
		 * -1, 0, -2
		 */
		Map<Integer, int[]> keypad = new HashMap<>();  // 번호에 해당하는 위치를 저장
		int num = 1;
		for(int i=0; i<3; i++) {
			for(int j=0; j<3; j++) {
				keypad.put(num++, new int[] {i, j});
			}
		}
		keypad.put(-1, new int[] {3, 0});
		keypad.put(0, new int[] {3, 1});
		keypad.put(-2, new int[] {3, 2});
		
		int[] left = {3, 0};  // 왼손가락 위치
		int[] right = {3, 2};  // 오른손가락 위치
		
		String answer = "";
		for(int i=0; i<numbers.length; i++) {
			switch(numbers[i]) {
			case 1: case 4: case 7:
                // 1, 4, 7인 경우 왼손 엄지손가락 사용
				left = keypad.get(numbers[i]);
				answer += "L";
				break;
			case 3: case 6: case 9:
                // 3, 6, 9인 경우 오른쪽 엄지손가락 사용
				right = keypad.get(numbers[i]);
				answer += "R";
				break;
			case 2: case 5: case 8: case 0:
                // 2, 5, 8, 0인 경우 거리 계산하여 결정
				int disL = distance(keypad.get(numbers[i]), left);
				int disR = distance(keypad.get(numbers[i]), right);
				if(disL < disR || (disL == disR && hand.equals("left"))) {
					left = keypad.get(numbers[i]);
					answer += "L";
				} else if(disL > disR || (disL == disR && hand.equals("right"))) {
					right = keypad.get(numbers[i]);
					answer += "R";
				} 
				break;
			}
		}
		
		return answer;
	}

	private static int distance(int[] now, int[] left) {
		return Math.abs(now[0] - left[0]) + Math.abs(now[1] - left[1]);
	}
}
