import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

class Solution {
    public String[] solution(String[] record) {
        // 사용자 정보 <유저 아이디, 닉네임> 형태로 저장
        Map<String, String> users = new HashMap<>();
        // enter, leave 기록만 담을 list
		ArrayList<String[]> msg = new ArrayList<>();
		
		for(int i=0; i<record.length; i++) {
			String[] tmp = record[i].split(" ");
			switch(tmp[0]) {
			case "Enter":
                // 입장의 경우, 사용자 정보 저장과 기록 저장 필요
                // put 메서드는 키에 해당하는 값이 존재하는 경우 변경
				users.put(tmp[1], tmp[2]);
				msg.add(new String[] {tmp[0], tmp[1]});
				break;
			case "Leave":
                // 퇴장의 경우, 기록 저장만 필요
				msg.add(new String[] {tmp[0], tmp[1]});
				break;
			case "Change":
                // 닉네임 변경의 경우, 사용자 정보 저장만 필요
				users.put(tmp[1], tmp[2]);
				break;
			}
		}
		
		String[] answer = new String[msg.size()];
		for(int i=0; i<msg.size(); i++) {
			String str = users.get(msg.get(i)[1]) + "님이 ";
			if(msg.get(i)[0].equals("Enter")) {
				str += "들어왔습니다.";
			} else {
				str += "나갔습니다.";
			}
			
			answer[i] = str;
		}
		
		return answer;
    }
}