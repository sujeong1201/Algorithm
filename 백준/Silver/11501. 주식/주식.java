import java.io.*;
import java.util.StringTokenizer;

public class Main {
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    int T = Integer.parseInt(br.readLine());

    while (T-- > 0) {
      int N = Integer.parseInt(br.readLine());
      int[] dayPrice = new int[N];
      int[] priceCnt = new int[10_001];

      StringTokenizer st = new StringTokenizer(br.readLine());
      int max = 0;
      for (int i = 0; i < N; i++) {
        int price = Integer.parseInt(st.nextToken());
        dayPrice[i] = price;
        priceCnt[price]++;
        if (price > max) max = price;
      }

      long profitSum = 0;
      for (int i = 0; i < N; i++) {
        priceCnt[dayPrice[i]]--;

        for (int j = max; j > dayPrice[i]; j--) {
          if (priceCnt[j] > 0) {
            profitSum += (j - dayPrice[i]);
            break;
          }
        }
      }

      bw.write(profitSum + "\n");
    }

    bw.flush();
    bw.close();
  }
}
