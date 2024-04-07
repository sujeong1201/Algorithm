import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    int N = Integer.parseInt(br.readLine());
    int[] classRomms = new int[N];
    StringTokenizer st = new StringTokenizer(br.readLine());
    for (int i = 0; i < N; i++) classRomms[i] = Integer.parseInt(st.nextToken());
    st = new StringTokenizer(br.readLine());
    int B = Integer.parseInt(st.nextToken());
    int C = Integer.parseInt(st.nextToken());

    long answer = N;
    for (int i = 0; i < N; i++) {
      int remain = classRomms[i] - B;
      if (remain > 0) {
        answer += (int) Math.ceil((double) remain / C);
      }
    }

    System.out.println(answer);
  }
}
