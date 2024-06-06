import java.io.*;
import java.util.Stack;
import java.util.StringTokenizer;

public class Main {
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    int N = Integer.parseInt(br.readLine());

    Stack<Integer> stack = new Stack<>();
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    while (N-- > 0) {
      String command = br.readLine();
      if (command.equals("2")) {
        if (stack.isEmpty()) bw.write("-1\n");
        else bw.write(stack.pop() + "\n");
      } else if (command.equals("3")) {
        bw.write(stack.size() + "\n");
      } else if (command.equals("4")) {
        if (stack.isEmpty()) bw.write("1\n");
        else bw.write("0\n");
      } else if (command.equals("5")) {
        if (stack.isEmpty()) bw.write("-1\n");
        else bw.write(stack.peek() + "\n");
      } else {
        StringTokenizer st = new StringTokenizer(command);
        st.nextToken();
        stack.push(Integer.parseInt(st.nextToken()));
      }
    }

    bw.flush();
    bw.close();
  }
}
