import java.util.*;
import java.io.*;

public class YJ_2110 {
    static int[] house;
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] data = br.readLine().split("\\s");
        int N = Integer.parseInt(data[0]);
        int C = Integer.parseInt(data[1]);

        house = new int[N];
        for(int i=0; i<N; i++){
            house[i] = Integer.parseInt(br.readLine());
        }
        Arrays.sort(house);
        System.out.println(binarySearch(C));
    }

    static int binarySearch(int C){
        int left = 0;
        int right = house[house.length-1] - house[0];   //최대간격

        while(left <= right){
            int mid = (left+right)/2;   //★공유기를 설치할 최소거리 후보!

            //공유기 설치하기
            int builtCount = 1;
            int current = house[0];
            for(int i=1; i<house.length; i++){
                if(house[i] - current >= mid){  //★거리 간격 >= mid 뜻은 house[i]가 mid 와 같은 위치에 있거나 mid 뒤에 있다는 뜻임
                    builtCount++;
                    current = house[i];
                }
            }

            //간격 조정하면서 최적의 최대값 찾기
            if(builtCount >= C){
                left = mid + 1; //upperBound: mid 값을 높여서 간격을 넓힘
            }else {
                right = mid-1;
            }
        }
        return right;
    }
}
