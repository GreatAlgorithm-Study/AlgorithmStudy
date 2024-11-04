import java.io.*;
import java.util.*;

public class YJ_5021 {
    static Map<String,ArrayList<String>> families = new HashMap<>();    //<자식,자식의부모 리스트>
    static Map<String,Double> bloodRatio = new HashMap<>();  //<계승후보,혈통비율>

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] data = br.readLine().split("\\s");
        final int N = Integer.parseInt(data[0]);
        final int M = Integer.parseInt(data[1]);

        final String king = br.readLine();
        for(int i=0; i<N; i++){
            String[] family = br.readLine().split("\\s");
            //위상정렬 그래프 초기화
            String child = family[0];
            families.computeIfAbsent(child, k -> new ArrayList<>());
            String dad = family[1];
            String mom = family[2];
            families.get(child).add(dad);
            families.get(child).add(mom);

            //모든 사람들 혈통 1 초기화
            bloodRatio.put(child, 1.0);
            bloodRatio.put(dad, 1.0);
            bloodRatio.put(mom, 1.0);
        }
        //왕은 최상위 혈통으로 0
        bloodRatio.put(king, 0.0);

        //후보자 별로 혈통 계산하기
        for(String name : bloodRatio.keySet()){
            findAnHeir(name);
        }

        //계승자 찾기
        String nextKing = br.readLine();
        for(int i=1; i<M; i++){
            String candidate = br.readLine();
            if(bloodRatio.getOrDefault(nextKing,-1.0) < bloodRatio.getOrDefault(candidate,-1.0)){
                nextKing = candidate;
            }
        }
        System.out.println(nextKing);
    }

    static double findAnHeir(String name){
        //왕이거나, 이미 혈통을 계산했을 경우
        if(!bloodRatio.get(name).equals(1.0)){
            return bloodRatio.get(name);
        }

        ArrayList<String> parent = families.get(name);
        //왕족이 아닌 부모는 상위 부모가 없음
        if(Objects.isNull(parent)){
            bloodRatio.put(name,-1.0);
            return bloodRatio.get(name);
        }
        //깊이 탐색을 통해 자식의 부모로 올라가면서 현재 자식의 혈통을 계산
        double dad = findAnHeir(parent.get(0));
        double mom = findAnHeir(parent.get(1));

        //모든 사람은 아버지의 혈통과 어머니의 혈통을 반 씩 받게 된다
        //dfs 탐색이 깊어질수록 혈통이 옅어짐 (1/2 > 1/4 > 1/16 ...)
        bloodRatio.put(name,(dad+mom)/2.0);

        return bloodRatio.get(name);
    }
}
