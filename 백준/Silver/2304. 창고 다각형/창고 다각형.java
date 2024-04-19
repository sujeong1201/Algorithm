import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    int N = Integer.parseInt(br.readLine());
    List<int[]> pillarList = new ArrayList<>();
    int maxHeight = 0;
    int maxHeightIdx = -1;
    for (int i = 0; i < N; i++) {
      StringTokenizer st = new StringTokenizer(br.readLine());
      int l = Integer.parseInt(st.nextToken());
      int h = Integer.parseInt(st.nextToken());
      if (h > maxHeight) {
        maxHeight = h;
      }
      pillarList.add(new int[] {l, h});
    }
    Collections.sort(pillarList, (arr1, arr2) -> arr1[0] - arr2[0]);

    boolean[][] storage = new boolean[1001][1001];
    Stack<int[]> stack = new Stack<>();
    // maxHeight 왼쪽은 순서대로
    stack.push(pillarList.get(0));
    for (int i = 0; i < N; i++) {
      int[] cur = pillarList.get(i);
      if (i == 0) {
        stack.push(cur);
        if (cur[1] == maxHeight) {
          maxHeightIdx = i;
          break;
        }
      }

      int[] prev = stack.peek();

      if (cur[1] > prev[1]) {
        for (int r = 0; r < prev[1]; r++) {
          for (int c = prev[0]; c < cur[0]; c++) {
            storage[r][c] = true;
          }
        }

        stack.pop();
        stack.push(cur);
      }

      if (cur[1] == maxHeight) {
        maxHeightIdx = i;
        break;
      }
    }

    stack.clear();

    // maxHeight 오른쪽은 역순으로
    stack.push(pillarList.get(pillarList.size() - 1));
    for (int i = pillarList.size() - 2; i >= maxHeightIdx; i--) {
      int[] prev = stack.peek();
      int[] cur = pillarList.get(i);
      if (cur[1] > prev[1]) {
        for (int r = 0; r < prev[1]; r++) {
          for (int c = prev[0]; c > cur[0]; c--) {
            storage[r][c] = true;
          }
        }

        stack.pop();
        stack.push(cur);
      }
    }

    int[] last = stack.pop();
    for (int r = 0; r < maxHeight; r++) {
      for (int c = last[0]; c >= pillarList.get(maxHeightIdx)[0]; c--) {
        storage[r][c] = true;
      }
    }

    int answer = 0;
    for (int r = 0; r <= 1000; r++) {
      for (int c = 0; c <= 1000; c++) {
        if (storage[r][c]) answer++;
      }
    }
    System.out.println(answer);
  }
}
