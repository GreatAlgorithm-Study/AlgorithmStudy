import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * 알고리즘: DP 최장증가부분수열?
 * 시간복잡도: 1<=어린이 수<=1,000,000
 * 10^6 으로 단순 이중 반복문으로 풀 경우 시간초과 발생
 * 아이디어:
 * LIS의 의미: 해당 길이 만큼은 정렬이 되어 있기 때문에 고정하고 나머지 요소들만 이동 시키면 됨.
 * 맨앞과 맨뒤앞 움직일 수 있기 때문에 최장 증가 부분 수열에서 연속으로 증가할 때의 가장 긴 구간을 찾아야함
 */
public class YJ_7570 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int[] children = new int[N+1];

        String[] line = br.readLine().split("\\s");
        for(int i=0; i<N; i++){
            children[Integer.parseInt(line[i])] = i;
            //어린이 숫자를 기준으로 입력 순서를 정렬
        }

        int max = 1;
        int count = 1;
        for(int i=1; i<=N; i++){
            if(children[i] > children[i-1]){    //연속으로 오름차순인 최장증가수열인지 확인
                max= Math.max(max,++count);
            }else{
                count = 1;  //기본값 1
            }
        }
        System.out.println(N-max);
    }
}
