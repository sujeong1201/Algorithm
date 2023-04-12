import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;

class Solution {
    public int[] solution(int[] fees, String[] records) {
		Map<String, Integer> answer = new HashMap<>();
		Map<String, Integer> inOut = new HashMap<>();
		final int lastTime = 23 * 60 + 59;  // 23:59
		
		StringTokenizer st = null;
		for (int i = 0; i < records.length; i++) {
			st = new StringTokenizer(records[i], " ");
			String[] hm = st.nextToken().split(":");
			int time = Integer.parseInt(hm[0]) * 60 + Integer.parseInt(hm[1]);
			String carNo = st.nextToken();
			String type = st.nextToken();
			
			if("IN".equals(type)) {
				inOut.put(carNo, time);
			} else if("OUT".equals(type)) {
				int prevTime = 0;
				if(answer.containsKey(carNo)) prevTime = answer.get(carNo);
				
				answer.put(carNo, (time - inOut.get(carNo)) + prevTime);
				inOut.remove(carNo);
			}
		}
		
		for(String key : inOut.keySet()) {  // 출차내역이 없는 차에 대한 처리
			int prevTime = 0;
			if(answer.containsKey(key)) prevTime = answer.get(key);
			
			answer.put(key, (lastTime - inOut.get(key)) + prevTime);
		}
		
		int[] money = new int[answer.size()];
		List<String> keyList = new ArrayList<>(answer.keySet());
		keyList.sort((s1, s2) -> s1.compareTo(s2));
		for(int i = 0 ; i < keyList.size(); i++) {
			int time = answer.get(keyList.get(i));
			if(time <= fees[0]) {  // 기본 시간보다 작은 경우
				money[i] = fees[1];
			} else {
				time -= fees[0];
				money[i] = fees[1] + (int)Math.ceil((double)time / fees[2]) * fees[3];
			}
		}

		return money;
    }
}