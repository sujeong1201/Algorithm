import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
  static int N, p[];

  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st = new StringTokenizer(br.readLine());
    N = Integer.parseInt(st.nextToken());
    int M = Integer.parseInt(st.nextToken());

    boolean[] knowTruth = new boolean[N + 1];
    st = new StringTokenizer(br.readLine());
    int cnt = Integer.parseInt(st.nextToken());
    for (int i = 0; i < cnt; i++) {
      knowTruth[Integer.parseInt(st.nextToken())] = true;
    }

    p = new int[N + 1];
    makeSet();

    List<Integer> partyList[] = new ArrayList[N + 1];
    for (int i = 1; i <= N; i++) partyList[i] = new ArrayList<>();

    for (int i = 0; i < M; i++) {
      st = new StringTokenizer(br.readLine());
      cnt = Integer.parseInt(st.nextToken());
      int[] party = new int[cnt];
      for (int j = 0; j < cnt; j++) {
        party[j] = Integer.parseInt(st.nextToken());
        partyList[party[j]].add(i);
      }
      for (int j = 0; j < cnt - 1; j++) {
        union(party[j], party[j + 1]);
      }
    }

    boolean[] noLieParty = new boolean[M];
    for (int i = 1; i <= N; i++) {
      if (knowTruth[i]) {
        int root = find(i);
        for (int j = 1; j <= N; j++) {
          if (root == find(j)) {
            knowTruth[j] = true;
            for (Integer party : partyList[j]) noLieParty[party] = true;
          }
        }
      }
    }

    int answer = 0;
    for (int i = 0; i < M; i++) if (!noLieParty[i]) answer++;
    System.out.println(answer);
  }

  private static boolean union(int a, int b) {
    int aRoot = find(a);
    int bRoot = find(b);

    if (aRoot == bRoot) return false;

    p[bRoot] = aRoot;
    return true;
  }

  private static int find(int a) {
    if (p[a] == a) return a;

    return p[a] = find(p[a]);
  }

  private static void makeSet() {
    for (int i = 1; i <= N; i++) {
      p[i] = i;
    }
  }
}
