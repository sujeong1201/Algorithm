import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// 벡터의 교차곱을 이용하라고 함.. 수학문제...
public class Main {
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    int[][] points = new int[3][2];
    for (int i = 0; i < 3; i++) {
      StringTokenizer st = new StringTokenizer(br.readLine());
      for (int j = 0; j < 2; j++) points[i][j] = Integer.parseInt(st.nextToken());
    }

    int[] vector1 = {points[1][0] - points[0][0], points[1][1] - points[0][1]};
    int[] vector2 = {points[2][0] - points[1][0], points[2][1] - points[1][1]};

    int product = vector1[0] * vector2[1] - vector2[0] * vector1[1];
    if (product == 0) {
      System.out.println(0);
    } else if (product > 0) {
      System.out.println(1);
    } else {
      System.out.println(-1);
    }
  }
}
