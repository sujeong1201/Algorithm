import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());

        StringBuilder sb = new StringBuilder();
        for(int t = 0; t < T; t++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            String binaryNo1 = st.nextToken();
            String binaryNo2 = st.nextToken();

            String result = calculate(binaryNo1, binaryNo2);
            sb.append(result + "\n");
        }
        System.out.println(sb);
    }

    private static String calculate(String binaryNo1, String binaryNo2) {
        int length1 = binaryNo1.length();
        int length2 = binaryNo2.length();
        int length = length1;
        if(length2 > length1) {
            length = length2;
            for(int i = 0; i < length2 - length1; i++) {
                binaryNo1 = "0" + binaryNo1;
            }
        } else if(length1 > length2) {
            for(int i = 0; i < length1 - length2; i++) {
                binaryNo2 = "0" + binaryNo2;
            }
        }

        String result = "";
        boolean additional = false;
        for(int i = length - 1; i >= 0; i--) {
            int sum = additional ? 1 : 0;
            sum += binaryNo1.charAt(i) - '0';
            sum += binaryNo2.charAt(i) - '0';

            if(sum == 0) {
                additional = false;
                result = "0" + result;
            } else if(sum == 1) {
                additional = false;
                result = "1" + result;
            } else if(sum == 2) {
                additional = true;
                result = "0" + result;
            } else {
                additional = true;
                result = "1" + result;
            }
        }

        if(additional) result = "1" + result;
        result = removeZero(result);

        return result;
    }

    private static String removeZero(String result) {
        StringBuffer resultSb = new StringBuffer(result);
        while(resultSb.length() > 0) {
            if(resultSb.charAt(0) == '1') break;

            resultSb.deleteCharAt(0);
        }

        if(resultSb.length() == 0) return "0";
        return resultSb.toString();
    }
}
