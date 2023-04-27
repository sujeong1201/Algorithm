import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

class Solution {
    public int solution(String[] lines) {
int answer = 0;
    	List<double[]> timeLine = new ArrayList<>();
    	int start = 0, end = 0;
        for(int i = 0; i < lines.length; i++) {
        	StringTokenizer st = new StringTokenizer(lines[i]);
        	st.nextToken();  // 날짜는 다 똑같아서 버림
        	String time = st.nextToken();
        	String duration = st.nextToken();
        	timeLine.add(calcSec(time, duration));
        }
        
        for(int i = 0; i < timeLine.size(); i++) {
        	double time = timeLine.get(i)[1];
        	int cnt = 1;
        	for(int j = 0; j < timeLine.size(); j++) {
        		if((i != j) && ((timeLine.get(j)[0] <= time && timeLine.get(j)[1] >= time) || 
        				(timeLine.get(j)[0] > time && timeLine.get(j)[0] < time + 1))) {
        			cnt++;
        		}
        	}
        	
        	if(cnt > answer) answer = cnt;
    	}
        
        return answer;
    }
    
    private static double[] calcSec(String time, String duration) {
		StringTokenizer st = new StringTokenizer(time, ":");
		int hour = Integer.parseInt(st.nextToken());
		int minute = Integer.parseInt(st.nextToken());
		double second = Double.parseDouble(st.nextToken());
		duration = duration.replace("s", "");
		
		double[] value = new double[2];
		value[0] = (hour * 3600 + minute * 60 + second) - Double.parseDouble(duration) + 0.001;
		value[1] = (hour * 3600 + minute * 60 + second);
		return value;
	}
}