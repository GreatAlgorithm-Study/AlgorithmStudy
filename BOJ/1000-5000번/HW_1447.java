import java.io.*;
import java.util.*;

// 시간복잡도 : O(log N)
// 완전

// 휴게소가 없는 구간의 길이의 최댓값을 최소로
public class HW_1447 {
    static int N, M, L; // 현재 휴게소의 개수, 더 지으려는 휴게소 개수, 고속도로의 길이
    static int[] location;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        L = Integer.parseInt(st.nextToken());
        List<Integer> list = new ArrayList<>();

        list.add(0); // 고속도로 시작
        list.add(L);

        st = new StringTokenizer(br.readLine());
        for(int i=0; i<N; i++){
            list.add(Integer.parseInt(st.nextToken()));
        }

        Collections.sort(list);

        int left = 1; // 휴게소가 없는 구간의 최소 길이
        int right = L;

        while(left <= right){
            int mid = (left + right) /2;
            int cnt = 0;
            for(int i=1; i<list.size(); i++){
                cnt += (list.get(i) - list.get(i-1) - 1) / mid;
            }
            if(cnt > M) left = mid + 1;
            else right = mid - 1;
        }
        System.out.println(left);
    }
}

