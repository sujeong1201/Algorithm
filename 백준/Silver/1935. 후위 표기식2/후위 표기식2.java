import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

public class Main {
    static int N;
    static String expression;
    static Map<Character, Double> operandMap;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        expression = br.readLine();

        operandMap = new HashMap<>();
        for(int i = 0; i < N; i++) {
            char alphabet = (char) (65 + i);
            operandMap.put(alphabet,Double.parseDouble(br.readLine()));
        }

        Stack<Double> stack = new Stack<>();
        for(int i = 0; i < expression.length(); i++) {
            char curChar = expression.charAt(i);

            if(curChar >= 65 && curChar <= 90) {
                stack.push(operandMap.get(curChar));
            } else {
                double op1 = stack.pop();
                double op2 = stack.pop();

                double result;
                if(curChar == '+') result = op1 + op2;
                else if(curChar == '-') result = op2 - op1;
                else if(curChar == '*') result = op1 * op2;
                else result = op2 / op1;

                stack.push(result);
            }
        }

        System.out.printf("%.2f\n", stack.pop());
    }
}
