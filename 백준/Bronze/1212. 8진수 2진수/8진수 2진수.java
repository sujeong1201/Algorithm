import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String octalNum = sc.nextLine();
        int length = octalNum.length();

        StringBuilder binaryNum = new StringBuilder();
        for(int i = 0; i < length; i++) {
            int num = octalNum.charAt(i) - '0';
            binaryNum.append(getBinaryNum(num));
        }

        while(true) {
            if(binaryNum.charAt(0) == '1' || binaryNum.length() == 1) break;
            binaryNum.deleteCharAt(0);
        }

        System.out.println(binaryNum);
    }

    private static String getBinaryNum(int num) {
        String result = "";
        while(num > 0) {
            result = num % 2 + result;
            num /= 2;
        }

        while (result.length() < 3) {
            result = "0" + result;
        }

        return result;
    }
}
