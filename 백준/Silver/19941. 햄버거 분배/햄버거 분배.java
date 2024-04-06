import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st = new StringTokenizer(br.readLine());
    int N = Integer.parseInt(st.nextToken());
    int K = Integer.parseInt(st.nextToken());
    char[] peopleNHambugers = br.readLine().toCharArray();

    boolean[] isEaten = new boolean[N];
    int answer = 0;
    nextPerson:
    for (int i = 0; i < N; i++) {
      if (peopleNHambugers[i] == 'H') continue;

      for (int j = i - K; j <= i + K; j++) {
        if (j < 0 || j >= N || i == j || isEaten[j]) continue;
        if (peopleNHambugers[j] == 'H') {
          isEaten[j] = true;
          answer++;
          continue nextPerson;
        }
      }
    }

    System.out.println(answer);
  }
}
