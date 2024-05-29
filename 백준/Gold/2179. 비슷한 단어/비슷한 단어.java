import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    int N = Integer.parseInt(br.readLine());
    String[] words = new String[N];
    Map<String, Integer> wordIdxMap = new HashMap<>();
    for (int i = 0; i < N; i++) {
      words[i] = br.readLine();
      wordIdxMap.put(words[i], i);
    }

    Arrays.sort(words);

    String S = null;
    String T = null;
    int maxPrefix = 0;

    for (int i = 0; i < N - 1; i++) {
      for (int j = i + 1; j < N; j++) {
        if (words[i].equals(words[j])) continue;
        int prefix = getPrefixLength(words[i], words[j]);

        String first, second;
        if (wordIdxMap.get(words[i]) < wordIdxMap.get(words[j])) {
          first = words[i];
          second = words[j];
        } else {
          first = words[j];
          second = words[i];
        }

        if (prefix < maxPrefix) break;

        if (prefix > maxPrefix) {
          maxPrefix = prefix;
          S = first;
          T = second;
        } else if (prefix == maxPrefix) {
          if (S == null
              || wordIdxMap.get(first) < wordIdxMap.get(S)
              || (wordIdxMap.get(first) == wordIdxMap.get(S)
                  && wordIdxMap.get(second) < wordIdxMap.get(T))) {
            S = first;
            T = second;
          }
        }
      }
    }

    System.out.println(S);
    System.out.println(T);
  }

  private static int getPrefixLength(String s, String t) {
    int idx = 0;
    while (true) {
      if (idx >= s.length() || idx >= t.length()) break;

      char ch1 = s.charAt(idx);
      char ch2 = t.charAt(idx);
      if (ch1 != ch2) break;
      else idx++;
    }

    return idx;
  }
}
