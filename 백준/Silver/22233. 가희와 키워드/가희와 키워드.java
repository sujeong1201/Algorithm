import java.io.*;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

public class Main {
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st = new StringTokenizer(br.readLine());
    int N = Integer.parseInt(st.nextToken());
    int M = Integer.parseInt(st.nextToken());

    boolean[] erased = new boolean[N];
    Map<String, Integer> keywordMap = new HashMap<>();
    for (int i = 0; i < N; i++) {
      keywordMap.put(br.readLine(), i);
    }

    int remain = N;
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    for (int i = 0; i < M; i++) {
      st = new StringTokenizer(br.readLine(), ",");
      while (st.hasMoreTokens()) {
        String keyword = st.nextToken();
        if (keywordMap.containsKey(keyword) && !erased[keywordMap.get(keyword)]) {
          erased[keywordMap.get(keyword)] = true;
          remain--;
        }
      }

      bw.write(remain + "\n");
    }

    bw.flush();
    bw.close();
  }
}
