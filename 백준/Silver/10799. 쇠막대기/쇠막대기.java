import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String str = br.readLine();
        str = str.replaceAll("\\(\\)", "1");  // 레이저는 X로 변환

        int answer = 0;

        Stack<String> stack = new Stack<>();
        for(int i = 0; i < str.length(); i++) {
            char cur = str.charAt(i);
            if(cur == '(') {
                stack.push(String.valueOf(cur));
            } else if(cur == ')') {
                int cnt = 0;
                while (true) {
                    String popChar = stack.pop();
                    if(popChar.equals("(")) {
                        answer += cnt + 1;
                        stack.push(cnt + "");
                        break;
                    }

                    cnt += Integer.valueOf(popChar);
                }
            } else if(cur == '1') {
                stack.push(String.valueOf(cur));
            }
        }

        System.out.println(answer);
    }
}
