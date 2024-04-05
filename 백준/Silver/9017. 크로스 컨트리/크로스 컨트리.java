import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringBuilder sb = new StringBuilder();
    int t = Integer.parseInt(br.readLine());

    while (t-- > 0) {
      int N = Integer.parseInt(br.readLine());
      int[] rank = new int[N];
      Map<Integer, Integer> teamCnt = new HashMap<>();
      StringTokenizer st = new StringTokenizer(br.readLine());
      for (int i = 0; i < N; i++) {
        int cur = Integer.parseInt(st.nextToken());
        rank[i] = cur;
        if (!teamCnt.containsKey(cur)) teamCnt.put(cur, 1);
        else teamCnt.put(cur, teamCnt.get(cur) + 1);
      }

      Map<Integer, List<Integer>> teamMap = new HashMap<>();
      int cnt = 1;
      for (int i = 0; i < N; i++) {
        if (teamCnt.get(rank[i]) < 6) continue;
        if (!teamMap.containsKey(rank[i])) teamMap.put(rank[i], new ArrayList<>());
        teamMap.get(rank[i]).add(cnt++);
      }

      int minScore = Integer.MAX_VALUE;
      int minScoreTeam = -1;
      for (int teamNo : teamMap.keySet()) {
        List<Integer> teamScore = teamMap.get(teamNo);

        int sum = 0;
        for (int i = 0; i < 4; i++) sum += teamScore.get(i);

        if (sum < minScore) {
          minScore = sum;
          minScoreTeam = teamNo;
        } else if (sum == minScore && teamScore.get(4) < teamMap.get(minScoreTeam).get(4)) {
          minScoreTeam = teamNo;
        }
      }

      sb.append(minScoreTeam + "\n");
    }

    System.out.print(sb);
  }
}
