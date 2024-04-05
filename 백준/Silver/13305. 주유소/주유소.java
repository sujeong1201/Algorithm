import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    int N = Integer.parseInt(br.readLine());
    int[] distances = new int[N - 1];
    int[] prices = new int[N];
    StringTokenizer st = new StringTokenizer(br.readLine());
    for (int i = 0; i < N - 1; i++) distances[i] = Integer.parseInt(st.nextToken());
    st = new StringTokenizer(br.readLine());
    for (int i = 0; i < N; i++) prices[i] = Integer.parseInt(st.nextToken());

    int answer = 0;
    int prevPrice = prices[0];
    int distanceSum = distances[0];
    for (int i = 1; i < N - 1; i++) {
      if (prices[i] < prevPrice) {
        answer += prevPrice * distanceSum;
        prevPrice = prices[i];
        distanceSum = 0;
      }

      distanceSum += distances[i];
    }
    answer += prevPrice * distanceSum;

    System.out.println(answer);
  }
}
