import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    int[][] numbers = new int[9][9];
    for (int i = 0; i < 9; i++) {
      StringTokenizer st = new StringTokenizer(br.readLine());
      for (int j = 0; j < 9; j++) numbers[i][j] = Integer.parseInt(st.nextToken());
    }

    int maxNum = 0;
    int[] maxPos = new int[2];
    for (int i = 0; i < 9; i++) {
      for (int j = 0; j < 9; j++) {
        if (numbers[i][j] > maxNum) {
          maxNum = numbers[i][j];
          maxPos[0] = i;
          maxPos[1] = j;
        }
      }
    }

    System.out.println(maxNum);
    System.out.println((maxPos[0] + 1) + " " + (maxPos[1] + 1));
  }
}
