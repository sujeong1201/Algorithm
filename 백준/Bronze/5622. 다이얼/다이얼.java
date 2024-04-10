import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Main {
  public static void main(String[] args) {
    char[][] alphabet = {
      {'A', 'B', 'C'},
      {'D', 'E', 'F'},
      {'G', 'H', 'I'},
      {'J', 'K', 'L'},
      {'M', 'N', 'O'},
      {'P', 'Q', 'R', 'S'},
      {'T', 'U', 'V'},
      {'W', 'X', 'Y', 'Z'}
    };
    Map<Character, Integer> dialMap = new HashMap<>();
    for (int i = 0; i < alphabet.length; i++) {
      for (int j = 0; j < alphabet[i].length; j++) {
        dialMap.put(alphabet[i][j], i + 3);
      }
    }

    Scanner sc = new Scanner(System.in);
    String word = sc.nextLine();

    int time = 0;
    for (int i = 0; i < word.length(); i++) {
      time += dialMap.get(word.charAt(i));
    }

    System.out.println(time);
  }
}
