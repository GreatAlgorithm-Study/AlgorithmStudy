import java.io.*;
import java.util.*;

/**
 * 알고리즘: 이분탐색
 * 시간복잡도: O(NlogN)
 * 아이디어:
 * 순서가 상관없다면 정렬을 활용하자 > 이분탐색 가능
 * 이분탐색 사용가능 방법: 위 아래 각각 구분해서 동시에 전체를 탐색하기
 */
public class YJ_3020 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] first = br.readLine().split("\\s");
        int N = Integer.parseInt(first[0]); //동굴길이
        int H = Integer.parseInt(first[1]); //높이

        int[] up = new int[N/2];
        int[] down = new int[N/2];
        for(int i=0; i<N/2; i++){
            int downNum = Integer.parseInt(br.readLine());
            int upNum = Integer.parseInt(br.readLine());
            down[i] = downNum;
            up[i] = upNum;
        }

        System.out.println(destruction(N,H,up,down));
    }

    static String destruction (int N, int H, int[] up, int[] down){
        int minDestroy = N; //초기값은 최대값으로 설정
        int spaceConut = 0;

        Arrays.sort(up);    //O(NlogN)
        Arrays.sort(down);

        for(int i=1; i<H+1; i++){   //O(H)
            int destroy = binary(0,N/2,i,down)  //★시작,끝,처음높이,아래장애물
                    + binary(0,N/2,H-i+1,up);   //★시작,끝,마지막높이,위장애물

            //최소 구간 카운팅
            if(destroy == minDestroy){
                spaceConut++;
                continue;
            }
            //파괴한 최소값 갱신
            if(destroy < minDestroy) {
                minDestroy = destroy;
                spaceConut = 1;
            }
        }
        return minDestroy + " " + spaceConut;
    }

    //down H: 1 2 3 4 5 6 7
    //up H:   7 6 5 4 3 2 1
    private static int binary(int left, int right, int current, int[] cave){  //O(logN)
        //찾는 값(H)이 중간 보다 클경우 중간 이하(왼쪽)의 값들 보다 무조건 큼
        while(left < right){
            int mid = (left + right)/2;
            if(cave[mid] >= current){ //★충돌
                right = mid;    //왼쪽으로 이동
            } else {
                left = mid + 1; //오른쪽으로 이동
            }
        }
        return cave.length-right;   //충돌 수
    }
}
