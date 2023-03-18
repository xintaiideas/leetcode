import java.util.PriorityQueue;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int n = Integer.parseInt(sc.nextLine());

        String[][] arr = new String[n][];
        for (int i = 0; i < n; i++) {
            arr[i] = sc.nextLine().split(" ");
        }

        getResult(n, arr);
    }

    public static void getResult(int n, String[][] arr) {
        PriorityQueue<int[]> pq =
                new PriorityQueue<>((a, b) -> a[0] != b[0] ? a[0] - b[0] : a[1] - b[1]);

        for (int i = 0; i < n; i++) {
            String[] tmp = arr[i];
            switch (tmp[0]) {
                case "a":
                    int num = Integer.parseInt(tmp[1]);
                    int x = Integer.parseInt(tmp[2]);
                    pq.offer(new int[] {x, i, num}); // i 是先来后到的顺序
                    break;
                case "p":
                    int[] poll = pq.poll();
                    if (poll != null) System.out.println(poll[2]);
                    else System.out.println("");
            }
        }
    }
}