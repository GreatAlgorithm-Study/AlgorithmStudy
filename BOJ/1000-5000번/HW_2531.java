import java.io.*;
import java.util.StringTokenizer;

// 손님이 먹을 수 있는 초밥 가짓수의 최댓값을 구하는 프로그램
public class HW_2531 {
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken()); // 초밥 벨트에 놓인 접시의 수
        int d = Integer.parseInt(st.nextToken()); // 초밥의 가짓 수
        int k = Integer.parseInt(st.nextToken()); // 연속해서 먹는 접시의 수
        int c = Integer.parseInt(st.nextToken()); // 쿠폰 번호

        int rail[] = new int[N]; // 레일배열

        for(int i=0; i<N; i++) {
            rail[i] = Integer.parseInt(br.readLine());
        }

        // 연속해서 먹는 접시의 크기가 변하지 않음 -> 슬라이딩 윈도우
        // window : k, if(c) 쿠폰 번호 여부 확인하여 count++
        int checkArr[] = new int[d+1]; // 초밥 종류 배열
        int count = 0; // 경우의 수 카운트
        int max = 0;
        // 윈도우 배열 초기화
        for(int i=0; i<k; i++) {
            int a = rail[i];
            if(checkArr[a] ==0) {
                count++; // 새로운 종류 초밥
            }
            checkArr[a]++; // 초밥 개수 증가
        }

        max = count; // 먹을 수 있는 초밥 최대 가짓 수

        for(int i=0; i<N; i++) { // window : k, window내 중복X
            int start = i;
            int end = (i+k)%N;

            if(count >= max) {
                if(checkArr[c]==0) {
                    max = count+1;
                }
                else {
                    max = count;
                }
            }
            checkArr[rail[start]]--;
            if(checkArr[rail[start]]==0) {
                count--;
            }
            if(checkArr[rail[end]]==0) {
                count++;
            }
            checkArr[rail[end]]++;
        }
        System.out.println(max);
    }
}
