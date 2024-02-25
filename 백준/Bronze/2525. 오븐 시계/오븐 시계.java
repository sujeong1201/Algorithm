import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int hour = sc.nextInt();
        int minute = sc.nextInt();
        int time = sc.nextInt();

        if(time >= 60) {
            hour += (time / 60);
            time = time % 60;
        }
        minute += time;

        if(minute > 59) {
            hour++;
            minute -= 60;
        }
        if(hour > 23) {
            hour -= 24;
        }

        System.out.println(hour + " " + minute);
    }
}
