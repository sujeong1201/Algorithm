import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
  static class Team implements Comparable {
    int teamId;
    int totalScore;
    int submitCnt;
    int lastSubmit;
    Map<Integer, Integer> scoreMap;

    public Team(int teamId, int problemNo, int score, int lastSubmit) {
      scoreMap = new HashMap<>();
      scoreMap.put(problemNo, score);
      this.teamId = teamId;
      this.submitCnt = 1;
      this.lastSubmit = lastSubmit;
    }

    public void update(int problemNo, int score, int lastSubmit) {
      if (!scoreMap.containsKey(problemNo) || scoreMap.get(problemNo) < score) {
        scoreMap.put(problemNo, score);
      }
      this.submitCnt++;
      this.lastSubmit = lastSubmit;
    }

    public void calcTotalScore() {
      this.totalScore = 0;
      for (int problemNo : scoreMap.keySet()) {
        this.totalScore += scoreMap.get(problemNo);
      }
    }

    @Override
    public int compareTo(Object o) {
      Team opp = (Team) o;
      if (this.totalScore == opp.totalScore) {
        if (this.submitCnt == opp.submitCnt) {
          return this.lastSubmit - opp.lastSubmit; // 마지막 제출 인덱스 오름차순
        } else {
          return this.submitCnt - opp.submitCnt; // 제출 횟수 오름차순
        }
      } else {
        return opp.totalScore - this.totalScore; // 최종점수 내림차순
      }
    }
  }

  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringBuilder sb = new StringBuilder();

    int T = Integer.parseInt(br.readLine());
    while (T-- > 0) {
      StringTokenizer st = new StringTokenizer(br.readLine());
      int n = Integer.parseInt(st.nextToken()); // 팀의 개수 ~100
      int k = Integer.parseInt(st.nextToken()); // 문제의 개수 ~100
      int t = Integer.parseInt(st.nextToken()); // 우리팀 ID
      int m = Integer.parseInt(st.nextToken()); // 로그 엔트리 수 ~ 10,000

      int[][] logs = new int[m][3];
      for (int i = 0; i < m; i++) {
        st = new StringTokenizer(br.readLine());
        logs[i][0] = Integer.parseInt(st.nextToken()); // 팀ID
        logs[i][1] = Integer.parseInt(st.nextToken()); // 문제 번호
        logs[i][2] = Integer.parseInt(st.nextToken()); // 획득한 점수
      }
      sb.append(getRank(n, k, t, m, logs) + "\n");
    }

    System.out.print(sb);
  }

  private static int getRank(int n, int k, int t, int m, int[][] logs) {
    Map<Integer, Team> teamMap = new HashMap<>();
    for (int i = 0; i < m; i++) {
      if (teamMap.containsKey(logs[i][0])) {
        teamMap.get(logs[i][0]).update(logs[i][1], logs[i][2], i);
      } else {
        teamMap.put(logs[i][0], new Team(logs[i][0], logs[i][1], logs[i][2], i));
      }
    }

    PriorityQueue<Team> pq = new PriorityQueue();
    for (int teamId : teamMap.keySet()) {
      Team curTeam = teamMap.get(teamId);
      curTeam.calcTotalScore();
      pq.offer(curTeam);
    }

    int rank = 0;
    while (!pq.isEmpty()) {
      rank++;
      Team cur = pq.poll();
      if (cur.teamId == t) return rank;
    }

    return -1;
  }
}
