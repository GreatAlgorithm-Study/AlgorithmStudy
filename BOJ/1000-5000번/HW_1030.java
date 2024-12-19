import java.io.*;
import java.util.*;

// 프렉탈 평면이 단위 시간이 진행될 때 마다 크기가 증가함
// 프랙탈 한변 길이 : N^S
// 검정색 영역이 아닌 경우 색을 결정할 수 없음
// 현재 시간에서 해당 위치 어떤 색인지 결정 X
// 색을 결정하려면? 더 작은 블록(상위 패턴)의 좌표로 이동해서 재귀적으로 확인 해줘야함
public class HW_1030 {
    static int S, N, K, R1, R2, C1, C2;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        S = Integer.parseInt(st.nextToken()); // 시간
        N = Integer.parseInt(st.nextToken()); // 분할 크기
        K = Integer.parseInt(st.nextToken()); // 검정 영역 크기
        R1 = Integer.parseInt(st.nextToken());
        R2 = Integer.parseInt(st.nextToken());
        C1 = Integer.parseInt(st.nextToken());
        C2 = Integer.parseInt(st.nextToken());

        int size = (int) Math.pow(N, S); // 프랙탈 한변 길이 : N^S

        // 주어진 범위만 확인
        for (int r = R1; r <= R2; r++) {
            for (int c = C1; c <= C2; c++) {
                System.out.print(isCheck(r, c, size)); // 각 좌표의 색 계산
            }
            System.out.println();
        }
    }

    static int isCheck(int r, int c, int size) {
        if (size == 1) { // 단위 시간 0일 때
            return 0; // 흰색(0) 정사각형 한 개
        }

        int curSize = size / N; // 현재 블록 크기
        int s = curSize * (N - K) / 2; // 검정 영역 시작
        int e = curSize * (N + K) / 2; // 검정 영역 끝

        // 현재 좌표가 검정 영역에 속하면 색을 바로 반환(1)
        if (s <= r && r < e && s <= c && c < e) {
            return 1;
        }

        // 검정 영역에 속하지 않으면
        // 좌표를 상위 블록으로 변환하여 재귀적으로 확인
        return isCheck(r % curSize, c % curSize, curSize);
    }
}
