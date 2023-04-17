import java.util.Scanner;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		String str = sc.nextLine();
		
		StringTokenizer st = new StringTokenizer(str, "<> ", true);
		boolean isTag = false;
		StringBuffer answer = new StringBuffer();
		while(st.hasMoreTokens()) {
			String token = st.nextToken();
			if(!isTag) {
				answer.append(new StringBuffer(token).reverse());
				if("<".equals(token)) isTag = true;
			} else {
				answer.append(token);
				if(">".equals(token)) isTag = false;
			}
		}
		
		System.out.println(answer.toString());
	}
}
