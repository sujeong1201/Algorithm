import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

class Solution {
    public String[] solution(String[] record) {
        Map<String, String> users = new HashMap<>();
		ArrayList<String[]> msg = new ArrayList<>();
		
		for(int i=0; i<record.length; i++) {
			String[] tmp = record[i].split(" ");
			switch(tmp[0]) {
			case "Enter":
				users.put(tmp[1], tmp[2]);
				msg.add(new String[] {tmp[0], tmp[1]});
				break;
			case "Leave":
				msg.add(new String[] {tmp[0], tmp[1]});
				break;
			case "Change":
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