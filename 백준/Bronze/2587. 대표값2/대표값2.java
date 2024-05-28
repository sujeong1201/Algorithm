import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int[] numbers = new int[5];
        for(int i = 0; i < 5; i++) numbers[i] = sc.nextInt();
        
        Arrays.sort(numbers);
        int sum = 0;
        for(int i = 0; i < 5; i++) sum += numbers[i];
        
        System.out.println(sum / 5);
        System.out.println(numbers[2]);
    }
}