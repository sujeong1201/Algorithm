import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
  static int N, C, houses[];
  static int maxDist;

  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st = new StringTokenizer(br.readLine());
    N = Integer.parseInt(st.nextToken());
    C = Integer.parseInt(st.nextToken());
    houses = new int[N];
    for (int i = 0; i < N; i++) {
      houses[i] = Integer.parseInt(br.readLine());
    }
    Arrays.sort(houses);

    maxDist = 0;
    binarySearch();
    System.out.println(maxDist);
  }

  private static void binarySearch() {
    int min = 1;
    int max = houses[N - 1] - houses[0];

    while (min <= max) {
      int middle = (min + max) / 2;

      int wifiCnt = installWifi(middle);
      if (wifiCnt < C) {
        max = middle - 1;
      } else {
        if (middle > maxDist) maxDist = middle;
        min = middle + 1;
      }
    }
  }

  private static int installWifi(int middle) {
    int prev = houses[0];
    int wifiCnt = 1;

    for (int i = 1; i < N; i++) {
      if (houses[i] - prev >= middle) {
        prev = houses[i];
        wifiCnt++;
      }
    }

    return wifiCnt;
  }
}
