import java.io.*;
import java.util.*;

public class YJ_2352 {
    static List<Integer> lis = new ArrayList<>();
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        //초기화
        int[] connection = new int[n+1];
        for(int i=1; i<n+1; i++){
            connection[i] = Integer.parseInt(st.nextToken());
        }

        lis.add(0);
        //LIS
        for(int i = 1; i<connection.length; i++){
            if(lis.get(lis.size()-1) < connection[i]){
                lis.add(connection[i]); //최장 증가 원소 추가
            }else{
                int index = binarySearch(lis.size()-1, connection[i]);
                lis.set(index, connection[i]);  //★현재 원소가 들어갈 위치를 찾았다면 현재 원소와 가장 가깝고 큰 원소를 제거
            }
        }
        int result = lis.size()-1;
        System.out.println(result == 0 ? 1 : result);
    }

    //타겟이 최장 증가 원소들 중 어느 위치에 들어가면 적절한지 매개변수 탐색
    public static int binarySearch(int end, int target){
        int start = 1;
        while(start < end){
            int mid = (start + end)/2;  //mid 가 선을 연결할 수 있는지, 없는지
            if(lis.get(mid) < target){
                start = mid + 1;    //start 간격을 조정하면 mid 가 커진다 > 더 넓은 범위 에서 연결할 수 있는 선을 찾는다
            }else{
                end = mid;  //end 간격을 조정하면 mid 가 작아진다 > 연결을 더 많이 할 수 있다
            }
        }
        return end;
    }
    //lower bound: mid 값보다 크거나 같을때 end 는 중간값과 같은 위치에 있음
}