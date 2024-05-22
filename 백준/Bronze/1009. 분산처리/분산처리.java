import java.io.*;
import java.util.StringTokenizer;

public class Main {
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    int T = Integer.parseInt(br.readLine());

    while (T-- > 0) {
      StringTokenizer st = new StringTokenizer(br.readLine());
      int a = Integer.parseInt(st.nextToken());
      int b = Integer.parseInt(st.nextToken());

      int result = 1;
      for (int i = 0; i < b; i++) {
        result *= a;
        result = (result % 10 == 0) ? 10 : result % 10;
      }

      bw.write(result + "\n");
    }

    bw.flush();
    bw.close();
    br.close();
  }
}
