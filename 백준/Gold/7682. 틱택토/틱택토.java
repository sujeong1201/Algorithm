import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringBuilder sb = new StringBuilder();

    while (true) {
      String input = br.readLine();
      if (input.equals("end")) break;

      char[][] map = new char[3][3];
      for (int i = 0; i < 3; i++) {
        for (int j = 0; j < 3; j++) {
          map[i][j] = input.charAt(3 * i + j);
        }
      }

      if (checkIsValid(map)) sb.append("valid\n");
      else sb.append("invalid\n");
    }

    System.out.println(sb);
  }

  private static boolean checkIsValid(char[][] map) {
    int[][][] lines = {
      {{0, 0}, {0, 1}, {0, 2}},
      {{1, 0}, {1, 1}, {1, 2}},
      {{2, 0}, {2, 1}, {2, 2}},
      {{0, 0}, {1, 0}, {2, 0}},
      {{0, 1}, {1, 1}, {2, 1}},
      {{0, 2}, {1, 2}, {2, 2}},
      {{0, 0}, {1, 1}, {2, 2}},
      {{0, 2}, {1, 1}, {2, 0}}
    };

    int first = 0;
    int second = 0;
    for (int i = 0; i < 3; i++) {
      for (int j = 0; j < 3; j++) {
        if (map[i][j] == 'X') first++;
        else if (map[i][j] == 'O') second++;
      }
    }
    if (first != second && first != second + 1) return false;

    int xCnt = 0;
    int oCnt = 0;
    for (int[][] line : lines) {
      char ch = map[line[0][0]][line[0][1]];
      if (map[line[1][0]][line[1][1]] == ch && map[line[2][0]][line[2][1]] == ch) {
        if (ch == 'X') xCnt++;
        else if (ch == 'O') oCnt++;
      }
    }
    if (first == second && oCnt > 0 && xCnt == 0) return true;
    if (first == second + 1 && xCnt > 0 && oCnt == 0) return true;

    for (int i = 0; i < 3; i++) {
      for (int j = 0; j < 3; j++) {
        if (map[i][j] == '.') return false; // 빙고가 없는데 빈칸이 있으면 invalid
      }
    }
    if (xCnt > 0 || oCnt > 0) return false;
    return true; // 빙고가 없는데 빈칸도 없으면 valid
  }
}
