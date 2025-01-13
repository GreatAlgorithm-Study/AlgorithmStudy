import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
// k개 연속해서 먹으면 할인 있음
// k개 고른 초밥 중에서 임의로 하나 제공,

// 손님이 먹을 수 있는 초밥 가짓수의 최댓값을 구하는 프로그램 작성
// 시간 복잡도 : N<=30,000, O(N) : 배열 길이만큼 탐색 가능
// 알고리즘 : 슬라이딩 윈도우
public class HW_2531_2 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken()); // 회전 초밥 벨트에 놓인 접시의 수
        int d = Integer.parseInt(st.nextToken()); // 초밥의 가짓 수
        int k = Integer.parseInt(st.nextToken()); // 연속해서 먹는 접시의 수
        int c = Integer.parseInt(st.nextToken()); // 쿠폰 번호

        int[] rail = new int[N]; // 레일 위에 있는 전체 초밥
        int[] check = new int[d+1]; // 현재 상태 check 배열 (new int[k] (X) -> new int[d])
        for(int i=0; i<N; i++){
            rail[i] = Integer.parseInt(br.readLine());
        }

        // 떠올린 것 : 쿠폰 초밥을 최대한 포함하지 않는 초밥 가짓 수 찾기 -> k개 고정 -> 슬라이딩 윈도우, 원형 배열 (i%N)
        /* 참고한 부분 : 원형 배열로 만들기(i+k % N) + 슬라이딩 윈도우 구현 */

        // 슬라이딩 윈도우 구현
        // 1) check배열 초기화
        int cnt = 0; // 초밥 가짓수 카운트
        int maxCnt = 0; // 문제에서 구하는 최대 초밥 가짓 수
        int left=0, right = 0; // 투포인터 초기화

        while(right<k){
            if(check[rail[right]]==0){ // 새로운 종류 초밥을 먹을 경우
                cnt++;
            }
            check[rail[right]]++; // 초밥 종류 증가
            right++;
        }
        // 먹은 초밥 중에 쿠폰 초밥에 해당하는게 슬라이딩 윈도우에 없다면 +1
        maxCnt = (check[c]==0) ? cnt+1 : cnt; // 있으면 그냥 둠
        // 2) 투포인터로 슬라이딩 윈도우 구현하기
        while(left < N){ // 원형 배열 주의
            if(check[rail[right%N]]==0){ // 오른쪽 포인터를 이동하면서
                cnt++; // 새로운 초밥 추가
            }
            check[rail[right%N]]++;
            right++; // 오른쪽 포인터 이동

            // 왼쪽 포인터를 이동하면서
            check[rail[left]]--;
            if(check[rail[left]]==0){
                cnt--; // 기존 초밥 삭제
            }
            left++; // 왼쪽 포인터 이동
            maxCnt = Math.max(maxCnt, (check[c]==0) ? cnt+1 : cnt); // 윈도우 이동 시 maxCnt 갱신
        }
        System.out.println(maxCnt);
    }
}