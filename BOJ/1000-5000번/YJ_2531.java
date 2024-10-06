import java.util.*;

/**
 * 알고리즘: 투 포인터
 * 시간복잡도: O(n)
 * 아이디어:
 * 처음 접시부터 마지막 접시까지의 하나의 회전을 하기 위해서 N-1번 회전이 필요
 * 처음 접시부터 마지막 접시까지의 이어진 k 개의 연속된 묶음을 고려했을 때 횟수가 N-1 번 회전하기 때문
 * N = 5, k = 3인 경우
 *   1 2
 * 0     3
 *    4
 * 초기 셋팅:   [0, 1, 2]
 * 1번째 이동:  [1, 2, 3]
 * 2번째 이동:  [2, 3, 4]
 * 3번째 이동:  [3, 4, 0]
 * 4번째 이동:  [4, 0, 1]
 *
 * 회전을 반복할 때는 처음과 끝을 index를 기준으로 하나의 k 로 묶어서 확인해야함
 */
public class YJ_2531 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String[] first = sc.nextLine().split("\\s");

        int N = Integer.parseInt(first[0]); //접시의 수
        int d = Integer.parseInt(first[1]); //초밥의 가짓수
        int k = Integer.parseInt(first[2]); //연속해서 먹는 접시의 수
        int c = Integer.parseInt(first[3]); //쿠폰번호(=초밥종류)

        int[] belt = new int[N];
        for(int i = 0 ; i < N ; i++) {
            belt[i] = Integer.parseInt(sc.nextLine());
        }
        System.out.println(susiBelt(belt,N,d,k,c));
    }

    static int susiBelt(int[] belt, int N, int d, int k, int c){
        int[] susi = new int[d+1];   //먹은 초밥 종류 관련 배열
        susi[c] = 3001;  //무료 초밥 종류(최대 3000)

        int free = 1;
        for(int i=0;i<k;i++){   //초기셋팅: 회전초밥을 순회하며 다른 종류의 k 개의 초밥 담기
            int idx = belt[i];
            if(susi[idx]==0)    //★belt[i]를 스시에 넣어서 회전초밥에서 중복된 초밥 종류를 공통적으로 처리
                free++;
            susi[idx]++;
        }

        //★susi[i] 가 1일 경우 이미 먹음, 0일 경우 이미 먹었기 때문에 제외시킴
        int max = free;
        for(int i = 0; i < N-1; i++) {  //O(n)
            int start = belt[i];
            int end = belt[((i+k) < N) ? (i+k) : (i+k) % N];   //★조합 묶음 +1 (== 마지막 접시 > 처음접시 회전)

            // 조합 묶음 +1번째 초밥을 처음 먹을 경우
            if(++susi[end] == 1){
                free++;
            }
            // 이미 먹은 초밥 제외
            if(--susi[start] == 0){
                free--;
            }
            max = Math.max(max, free);
        }
        return max;
    }

}
