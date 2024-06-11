import java.util.Scanner;

public class Main {
  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);
    int X = sc.nextInt();

    int row = 0;
    int col = 0;
    boolean direction = true; // true-오른쪽위로, false-왼쪽아래로
    for (int i = 1; i < X; i++) {
      if (direction) {
        if (row == 0) {
          col++;
          direction = false;
        } else {
          row--;
          col++;
        }
      } else {
        if (col == 0) {
          row++;
          direction = true;
        } else {
          row++;
          col--;
        }
      }
    }

    System.out.println((row + 1) + "/" + (col + 1));
  }
}
