import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
  static int r, c, k;

  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st = new StringTokenizer(br.readLine());
    r = Integer.parseInt(st.nextToken());
    c = Integer.parseInt(st.nextToken());
    k = Integer.parseInt(st.nextToken());

    int[][] arr = new int[3][3];
    for (int i = 0; i < 3; i++) {
      st = new StringTokenizer(br.readLine());
      for (int j = 0; j < 3; j++) arr[i][j] = Integer.parseInt(st.nextToken());
    }

    System.out.println(solve(arr));
  }

  public static int solve(int[][] arr) {
    int[][] a = arr;

    int time = 0;
    while (true) {
      int rCnt = a.length;
      int cCnt = a[0].length;
      if (r <= rCnt && c <= cCnt && a[r - 1][c - 1] == k) return time;

      PriorityQueue<int[]> pq =
          new PriorityQueue<>(
              (arr1, arr2) -> {
                if (arr1[1] == arr2[1]) return arr1[0] - arr2[0];
                else return arr1[1] - arr2[1];
              });

      if (rCnt >= cCnt) { // 행 개수 >= 열 개수 (R연산)

        List<Integer> resultArr[] = new ArrayList[rCnt];
        int newCCnt = 0;

        for (int i = 0; i < rCnt; i++) {
          Map<Integer, Integer> cntMap = new HashMap<>();
          resultArr[i] = new ArrayList<>();

          // 수의 카운트 세기
          for (int j = 0; j < cCnt; j++) {
            if (a[i][j] == 0) continue;
            if (cntMap.containsKey(a[i][j])) {
              cntMap.put(a[i][j], cntMap.get(a[i][j]) + 1);
            } else {
              cntMap.put(a[i][j], 1);
            }
          }
          // 등장횟수->수의크기 오름차순으로 정렬
          for (int num : cntMap.keySet()) {
            pq.offer(new int[] {num, cntMap.get(num)});
          }
          // 새로운 배열 결과 만들기
          while (!pq.isEmpty()) {
            int[] cur = pq.poll();
            resultArr[i].add(cur[0]);
            resultArr[i].add(cur[1]);
          }

          if (resultArr[i].size() > newCCnt) newCCnt = resultArr[i].size();
        }

        int[][] newArr = new int[rCnt][newCCnt > 100 ? 100 : newCCnt];
        for (int i = 0; i < rCnt; i++) {
          for (int j = 0; j < resultArr[i].size(); j++) {
            newArr[i][j] = resultArr[i].get(j);
          }
          for (int j = resultArr[i].size(); j < newArr[i].length; j++) {
            newArr[i][j] = 0;
          }
        }
        a = newArr;

      } else { // 행 개수 < 열 개수 (C연산)

        List<Integer> resultArr[] = new ArrayList[cCnt];
        int newRCnt = 0;

        for (int i = 0; i < cCnt; i++) {
          Map<Integer, Integer> cntMap = new HashMap<>();
          resultArr[i] = new ArrayList<>();

          // 수의 카운트 세기
          for (int j = 0; j < rCnt; j++) {
            if (a[j][i] == 0) continue;
            if (cntMap.containsKey(a[j][i])) {
              cntMap.put(a[j][i], cntMap.get(a[j][i]) + 1);
            } else {
              cntMap.put(a[j][i], 1);
            }
          }
          // 등장횟수->수의크기 오름차순으로 정렬
          for (int num : cntMap.keySet()) {
            pq.offer(new int[] {num, cntMap.get(num)});
          }
          // 새로운 배열 결과 만들기
          while (!pq.isEmpty()) {
            int[] cur = pq.poll();
            resultArr[i].add(cur[0]);
            resultArr[i].add(cur[1]);
          }

          if (resultArr[i].size() > newRCnt) newRCnt = resultArr[i].size();
        }

        int[][] newArr = new int[newRCnt > 100 ? 100 : newRCnt][cCnt];
        for (int i = 0; i < cCnt; i++) {
          for (int j = 0; j < resultArr[i].size(); j++) {
            newArr[j][i] = resultArr[i].get(j);
          }
          for (int j = resultArr[i].size(); j < newArr.length; j++) {
            newArr[j][i] = 0;
          }
        }
        a = newArr;
      }

      time++;
      if (time > 100) break;
    }

    return -1;
  }
}
