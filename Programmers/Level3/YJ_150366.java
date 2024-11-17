import java.io.*;
import java.util.*;

public class YJ_150366 {
    static final int MAX = 50*50;
    static String[] table = new String[MAX+1];
    static int[] parents = new int[MAX+1];
    static List<String> prints = new ArrayList<>();

    public String[] solution(String[] commands) throws IOException {
        //자기자신으로 초기화
        for(int i=0; i<MAX; i++){
            parents[i] = i;
        }
        Arrays.fill(table,"");

        for(String command : commands){
            StringTokenizer st = new StringTokenizer(command);
            String message = st.nextToken();

            switch(message){
                case "UPDATE":
                    String data = st.nextToken();
                    boolean isNumber = data!= null && data.chars().allMatch(Character::isDigit);
                    if(isNumber){
                        update(Integer.parseInt(data),
                                Integer.parseInt(st.nextToken()),
                                st.nextToken());
                    }else{
                        update(data,st.nextToken());
                    }
                    break;

                case "MERGE":
                    merge(Integer.parseInt(st.nextToken()),
                            Integer.parseInt(st.nextToken()),
                            Integer.parseInt(st.nextToken()),
                            Integer.parseInt(st.nextToken()));
                    break;

                case "UNMERGE":
                    unmerge(Integer.parseInt(st.nextToken()),
                            Integer.parseInt(st.nextToken()));
                    break;

                case "PRINT":
                    print(Integer.parseInt(st.nextToken()),
                            Integer.parseInt(st.nextToken()));
                    break;
            }//switch
        }//for

        return prints.toArray(new String[0]);
    }

    //UPDATE r,c,value
    public void update(int r, int c, String value){
        int parent = find(getIndex(r, c));
        table[parent] = value;
    }

    //UPDATE value1 value2
    public void update(String value1, String value2){
        for(int i=0; i<MAX; i++){
            int parent = find(i);
            if(Objects.equals(value1,table[parent])){
                table[parent] = value2;
            }
        }
    }

    //MERGE r1 c1 r2 c2
    public void merge(int r1, int c1, int r2, int c2){
        if(r1==r2 && c1 ==c2){
            return;
        }
        union(getIndex(r1, c1), getIndex(r2, c2));
    }

    //UNMERGE r c
    public void unmerge(int r, int c){
        for(int i=0; i<MAX; i++){
            find(i);
        }

        int index = getIndex(r, c);     //표 인덱스
        int parent = parents[index];    //병합된 부모찾기
        String value = table[parent];   //부모의 value

        for(int i=0; i<table.length; i++){
            if(parents[i] == parent){
                parents[i] = i;     //부모에서 벗어나기
                table[i] = "";    //병합 해제 후 셀 초기화
            }
        }
        table[index] = value;
    }

    //PRINT r c
    public void print(int r, int c){
        int parent = find(getIndex(r, c));
        String result = table[parent].isBlank()? "EMPTY" : table[parent];
        prints.add(result);
    }

    //★병합된 셀 중 부모찾기
    public int find(int num){
        if(parents[num] == num){
            return parents[num];
        }
        return parents[num] = find(parents[num]);
    }

    //셀 병합하기
    public void union(int num1, int num2){
        num1 = find(num1);
        num2 = find(num2);

        if(num1 == num2){   //이미 병합된 경우
            return;
        }

        table[num1] = table[num1].isBlank()? table[num2] : table[num1];
        parents[num2] = num1;
        table[num2] = "";
    }

    private int getIndex(int r, int c){
        return (r-1)*50 + c-1;
    }

}
