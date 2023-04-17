import java.util.Scanner;
import java.util.Stack;

public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		String str = sc.nextLine();
		
		Stack<Character> output = new Stack<>();
		boolean isTag = false;
		String answer = "";
		
		for(int i = 0; i < str.length(); i++) {
			char ch = str.charAt(i);
			
			if(!isTag) {
				if(ch == ' ' || ch == '<') {
					while(!output.isEmpty()) {
						answer += output.pop();
					}
					answer += ch;
					if(ch == '<') isTag = true;
				} else {
					output.push(ch);
				}
			} else {
				answer += ch;
				if(ch == '>') isTag = false;
			}
		}
		
		while(!output.isEmpty()) {
			answer += output.pop();
		}
		System.out.println(answer);
	}
}
