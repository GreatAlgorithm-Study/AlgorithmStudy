import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class HW_23326 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();
        int N = Integer.parseInt(st.nextToken()); // 구역의 개수
        int Q = Integer.parseInt(st.nextToken()); // 쿼리의 개수

        TreeSet<Integer> treeSet = new TreeSet<>();
        st = new StringTokenizer(br.readLine());
        for(int i=0; i<N; i++){ // 쿼리 입력 받음
            int num = Integer.parseInt(st.nextToken());
            if(num==1){
                treeSet.add(i); // 명소인 인덱스만 추가
            }
        }
        int cur = 0; // 현재 위치

        while(Q-->0){
            st = new StringTokenizer(br.readLine());
            int command = Integer.parseInt(st.nextToken());
            if(command==1){ // (1)
                int idx = Integer.parseInt(st.nextToken())-1; // 1 based - 0 based (2 -1) = 1
                if(treeSet.contains(idx)){ // 이미 명소일 경우
                    treeSet.remove(idx); // 명소X로 변경
                } else{
                    treeSet.add(idx);
                }
            }
            else if(command==2){ // 이동 처리
                int x = Integer.parseInt(st.nextToken());
                cur = (cur+x)%N; // 원형 이동 처리
            } else if(command==3){
                if(treeSet.isEmpty()){
                    sb.append(-1).append('\n');
                } else{
                    Integer next = treeSet.ceiling(cur); // cur 이상인 원소 중 가장 작은 값을 돌려 받음
                    if(next==null){ // 없으면 null 반환
                        next = treeSet.first();
                    }
                    int min = cur <= next  ? next-cur : N-cur+next;
                    sb.append(min).append('\n');
                }
            }
        }
        System.out.println(sb);
    }
}