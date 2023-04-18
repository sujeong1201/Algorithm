import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Set;
import java.util.Stack;
import java.util.TreeSet;

public class Main {
	static Set<String> strList;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		strList = new TreeSet<String>();
		
		recur(br.readLine());
		for(String str : strList) System.out.println(str);
	}

	private static void recur(String str) {
		Stack<Character> stack = new Stack<>();
		
		for(int i = 0; i < str.length(); i++) {
			if(str.charAt(i) == '(') {
				stack.push(str.charAt(i));
				int j = i + 1;
				for( ; j < str.length(); j++) {
					if(str.charAt(j) == '(') stack.push('(');
					else if(str.charAt(j) == ')') stack.pop();
					
					if(stack.isEmpty()) break;
				}
				
				StringBuffer strB = new StringBuffer(str);
				strB.deleteCharAt(j);
				strB.deleteCharAt(i);
				
				String newStr = strB.toString();
				if(!strList.contains(newStr)) {
					strList.add(newStr);
					recur(newStr);
				}
			}
		}
	}
}
