import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class Main {
  static StringBuilder sb = new StringBuilder();

  public static <List> void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    int T = Integer.parseInt(br.readLine());
    for (int t = 0; t < T; t++) {
      int N = Integer.parseInt(br.readLine());
      ArrayList<String> expression = new ArrayList<>();
      expression.add("1");
      solve(2, N, expression);
      sb.append("\n");
    }

    System.out.println(sb);
  }

  public static void solve(int cnt, int N, ArrayList<String> expression) {
    if (cnt > N) {
      int sum = Integer.parseInt(expression.get(0));
      int index = 1;
      while (index < expression.size()) {
        if (expression.get(index).equals("+")) {
          sum += Integer.parseInt(expression.get(++index));
        } else if (expression.get(index).equals("-")) {
          sum -= Integer.parseInt(expression.get(++index));
        }

        index++;
      }

      if (sum == 0) {
        for (String e : expression) {
          if (e.length() >= 2) {
            for (int i = 0; i < e.length() - 1; i++) sb.append(e.charAt(i) + " ");
            sb.append(e.charAt(e.length() - 1));
          } else {
            sb.append(e);
          }
        }
        sb.append("\n");
      }

      return;
    }

    int size = expression.size();
    String prevNum = expression.get(size - 1);
    expression.set(size - 1, prevNum + cnt);
    solve(cnt + 1, N, expression);
    expression.set(size - 1, prevNum);

    expression.add("+");
    expression.add(cnt + "");
    solve(cnt + 1, N, expression);

    expression.set(size, "-");
    solve(cnt + 1, N, expression);
    expression.remove(size + 1);
    expression.remove(size);
  }
}
