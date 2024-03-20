import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder str = new StringBuilder(br.readLine());
        Stack<Character> left = new Stack<>();
        Stack<Character> right = new Stack<>();
        for(int i = 0; i < str.length(); i++) left.push(str.charAt(i));

        int M = Integer.parseInt(br.readLine());
        for(int i = 0; i < M; i++) {
            String in = br.readLine();

            switch(in.charAt(0)) {
                case 'L':
                    if(!left.isEmpty()) right.push(left.pop());
                    break;
                case 'D':
                    if(!right.isEmpty()) left.push(right.pop());
                    break;
                case 'B':
                    if(!left.isEmpty()) left.pop();
                    break;
                case 'P':
                    left.push(in.charAt(2));
                    break;
            }
        }

        StringBuilder sb = new StringBuilder();
        while(!left.isEmpty()) {
            sb.append(left.pop());
        }
        sb.reverse();
        while(!right.isEmpty()) {
            sb.append(right.pop());
        }
        System.out.println(sb);
    }
}
