import java.io.*;
import java.util.*;

public class YJ_1911 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int L = Integer.parseInt(st.nextToken());

        TreeMap<Integer,Integer> map = new TreeMap<>();
        for(int i=0; i<N; i++){
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());

            map.put(start,Math.max(map.getOrDefault(start,0),end));
        }

        int result = 0;
        int length = 0;
        for(int start : map.keySet()){
            int end = map.get(start);
            //이미 널판지를 설치했을 경우
            if(end < length){
                continue;
            }
            //널판지가 겹쳐지게 될 경우
            if(length < start){
                length = start;
            }

            result += (end- length)/L;  //필수 널판지길이
            int reminder = (end- length)%L; //여분 널판지 길이
            length = end;

            if(reminder != 0){
                result++;
                length += L - reminder; //여분 널판지를 보충한 만큼 길이 추가
            }
        }
        System.out.println(result);
    }
}
