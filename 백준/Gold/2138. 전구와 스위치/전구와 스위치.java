import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    int N = Integer.parseInt(br.readLine());
    String initial = br.readLine();
    String target = br.readLine();

    int minCnt = Integer.MAX_VALUE;
    // 1번 스위치를 누르지 않는다
    int cnt = 0;
    char[] current = initial.toCharArray();
    for (int i = 1; i < N; i++) {
      if (current[i - 1] != target.charAt(i - 1)) {
        cnt++;
        current[i - 1] = current[i - 1] == '0' ? '1' : '0';
        current[i] = current[i] == '0' ? '1' : '0';
        if (i + 1 < N) current[i + 1] = current[i + 1] == '0' ? '1' : '0';
      }
    }
    boolean flag = true;
    for (int i = 0; i < N; i++) {
      if (current[i] != target.charAt(i)) {
        flag = false;
        break;
      }
    }
    if (flag) minCnt = cnt;

    // 1번 스위치를 누른다
    current = initial.toCharArray();
    current[0] = current[0] == '0' ? '1' : '0';
    current[1] = current[1] == '0' ? '1' : '0';
    int cnt2 = 1;
    for (int i = 1; i < N; i++) {
      if (current[i - 1] != target.charAt(i - 1)) {
        cnt2++;
        current[i - 1] = current[i - 1] == '0' ? '1' : '0';
        current[i] = current[i] == '0' ? '1' : '0';
        if (i + 1 < N) current[i + 1] = current[i + 1] == '0' ? '1' : '0';
      }
    }
    flag = true;
    for (int i = 0; i < N; i++) {
      if (current[i] != target.charAt(i)) {
        flag = false;
        break;
      }
    }
    if (flag) minCnt = Math.min(minCnt, cnt2);

    System.out.println(minCnt == Integer.MAX_VALUE ? -1 : minCnt);
  }
}
