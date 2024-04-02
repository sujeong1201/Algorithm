import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    int N = Integer.parseInt(br.readLine());
    int[] buildings = new int[N];
    StringTokenizer st = new StringTokenizer(br.readLine());
    for (int i = 0; i < N; i++) buildings[i] = Integer.parseInt(st.nextToken());

    double[][] gradient = new double[N][N];
    for (int i = 0; i < N; i++) {
      for (int j = i + 1; j < N; j++) {
        gradient[i][j] = (double) (buildings[j] - buildings[i]) / (j - i);
        gradient[j][i] = (double) (buildings[i] - buildings[j]) / (j - i);
      }
    }

    int[] visibleCnt = new int[N];
    for (int i = 0; i < N; i++) {
      for (int j = i + 1; j < N; j++) {
        boolean visible = true;
        for (int k = i + 1; k < j; k++) {
          if ((gradient[i][j] > 0 && gradient[i][k] > 0 && gradient[i][j] <= gradient[i][k])
              || (gradient[i][j] < 0 && gradient[i][j] <= gradient[i][k])
              || (gradient[i][j] == 0 && gradient[i][k] >= 0)) {
            visible = false;
            break;
          }
        }

        if (visible) {
          visibleCnt[i]++;
          visibleCnt[j]++;
        }
      }
    }

    System.out.println(Arrays.stream(visibleCnt).max().getAsInt());
  }
}
