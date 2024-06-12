import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

public class Main {
  static class Value {
    int type; // 0: 괄호, 1: 숫자
  }

  static class Bracket extends Value {
    char value;

    public Bracket(char value) {
      this.type = 0;
      this.value = value;
    }
  }

  static class Number extends Value {
    int value;

    public Number(int value) {
      this.type = 1;
      this.value = value;
    }
  }

  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    String str = br.readLine();
    Stack<Value> stack = new Stack();

    outer:
    for (int i = 0; i < str.length(); i++) {
      char cur = str.charAt(i);

      if (cur == '(' || cur == '[') {
        stack.push(new Bracket(cur));
      } else if (cur == ')') {
        if (stack.isEmpty()) {
          System.out.println(0);
          System.exit(0);
        }

        int sum = 0;
        while (!stack.isEmpty()) {
          Value prev = stack.pop();
          if (prev.type == 1) {
            sum += ((Number) prev).value;
          } else if (((Bracket) prev).value == '(') {
            stack.push(new Number(sum == 0 ? 2 : sum * 2));
            continue outer;
          } else {
            break;
          }
        }

        System.out.println(0);
        System.exit(0);
      } else if (cur == ']') {
        if (stack.isEmpty()) {
          System.out.println(0);
          System.exit(0);
        }

        int sum = 0;
        while (!stack.isEmpty()) {
          Value prev = stack.pop();
          if (prev.type == 1) {
            sum += ((Number) prev).value;
          } else if (((Bracket) prev).value == '[') {
            stack.push(new Number(sum == 0 ? 3 : sum * 3));
            continue outer;
          } else {
            break;
          }
        }

        System.out.println(0);
        System.exit(0);
      }
    }

    int answer = 0;
    while (!stack.isEmpty()) {
      Value v = stack.pop();
      if (v.type == 0) {
        System.out.println(0);
        System.exit(0);
      }
      answer += ((Number) v).value;
    }

    System.out.println(answer);
  }
}
