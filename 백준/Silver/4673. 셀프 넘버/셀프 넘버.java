public class Main {
    public static void main(String[] args) {
        final int maxNum = 10_000;
        boolean[] exist = new boolean[maxNum +1];

        for(int i = 1; i <= maxNum; i++) {
            int selfNum = makeSelfNumber(i);
            if(selfNum <= 10000) exist[selfNum] = true;
        }

        StringBuilder sb = new StringBuilder();
        for(int i = 1; i <= maxNum; i++) {
            if(!exist[i]) sb.append(i + "\n");
        }
        System.out.println(sb);
    }

    private static int makeSelfNumber(int i) {
        int result = i;
        while(i > 0) {
            result += (i % 10);
            i /= 10;
        }

        return result;
    }
}
