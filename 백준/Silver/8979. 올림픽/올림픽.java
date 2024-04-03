import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
  static class Country implements Comparable {
    int countryNo;
    int goldCnt;
    int silverCnt;
    int bronzeCnt;

    public Country(int countryNo, int goldCnt, int silverCnt, int bronzeCnt) {
      this.countryNo = countryNo;
      this.goldCnt = goldCnt;
      this.silverCnt = silverCnt;
      this.bronzeCnt = bronzeCnt;
    }

    @Override
    public int compareTo(Object o) {
      Country that = (Country) o;
      if (this.goldCnt == that.goldCnt) {
        if (this.silverCnt == that.silverCnt) {
          return that.bronzeCnt - this.bronzeCnt;
        } else {
          return that.silverCnt - this.silverCnt;
        }
      } else {
        return that.goldCnt - this.goldCnt;
      }
    }

    @Override
    public String toString() {
      return countryNo + ": " + goldCnt + ", " + silverCnt + ", " + bronzeCnt;
    }
  }

  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st = new StringTokenizer(br.readLine());
    int N = Integer.parseInt(st.nextToken());
    int K = Integer.parseInt(st.nextToken());

    PriorityQueue<Country> pq = new PriorityQueue();
    while (N-- > 0) {
      st = new StringTokenizer(br.readLine());
      int no = Integer.parseInt(st.nextToken());
      int gold = Integer.parseInt(st.nextToken());
      int silver = Integer.parseInt(st.nextToken());
      int bronze = Integer.parseInt(st.nextToken());
      pq.offer(new Country(no, gold, silver, bronze));
    }

    int cnt = 0;
    int rank = cnt;
    Country prev = null;
    while (!pq.isEmpty()) {
      Country cur = pq.poll();
      cnt++;
      if (prev == null
          || (prev.goldCnt != cur.goldCnt
              || prev.silverCnt != cur.silverCnt
              || prev.bronzeCnt != cur.bronzeCnt)) {
        rank = cnt;
      }

      if (cur.countryNo == K) break;
      prev = cur;
    }

    System.out.println(rank);
  }
}
