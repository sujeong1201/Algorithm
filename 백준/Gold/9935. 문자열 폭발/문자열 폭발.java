import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String str = br.readLine();
        String bomb = br.readLine();

        String result = solve(str, bomb);
        if(result.length() == 0) System.out.println("FRULA");
        else System.out.println(result);
    }

    private static String solve(String str, String bomb) {
        Stack<Character> stack = new Stack<>();

        int bombLength = bomb.length();
        char bombLastCh = bomb.charAt(bombLength - 1);
        for(int i = 0; i < str.length(); i++) {
            char curCh = str.charAt(i);
            stack.push(curCh);

            if(curCh == bombLastCh && stack.size() >= bombLength) {
                int cnt = 0;
                for(int j = 0; j < bombLength; j++) {
                    if(stack.get(stack.size() - bombLength + j) == bomb.charAt(j)) {
                        cnt++;
                    }
                }

                if(cnt == bombLength) {
                    for(int j = 0; j < bombLength; j++) stack.pop();
                }
            }
        }

        StringBuilder sb = new StringBuilder();
        for(Character ch : stack) {
            sb.append(ch);
        }

        return sb.toString();
    }
}
