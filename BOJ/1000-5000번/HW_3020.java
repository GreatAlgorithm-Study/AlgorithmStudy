import java.util.Scanner;

// 시간 복잡도 : 시간 제한 1초, 장애물의 크기 : O(NlogN)
public class HW_3020 {
    public static void main(String[] args) {
        // 개똥벌레가 파괴해야 하는 장애물의 최솟값과 그러한 구간의 수 출력
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt(); // 동굴 크기 (장애물의 개수)
        int H = sc.nextInt(); // 동굴 높이

        int[] up = new int[H+1]; // 석순 장애물
        int[] down = new int[H+1]; // 종유석 장애물

        for (int n = 0; n < N; n++) {
            int height = sc.nextInt();  // 장애물의 높이를 입력 받음
            if (n % 2 == 0) {
                up[height]++;
            } else {
                down[height]++;
            }
        }

        // 석순과 종유석 누적합 계산
        for(int i=H-1; i>=1; i--) {
            up[i] += up[i + 1];
            down[i] += down[i + 1];
        }

        int min = N;
        int minCount = 0;

        for(int i=1; i<H+1; i++){
            int total = up[i] + down[H - i + 1];
            if(total < min){
                min = total; // 최솟값 갱신
                minCount = 1;
            } else if (total == min) {
                minCount++;
            }
        }
        System.out.println(min + " " + minCount);
    }
}