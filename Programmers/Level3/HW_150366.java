import java.util.*;

class HW_150366 {
    static int n = 2500;
    static int grp[];
    static String values[];

    public String[] solution(String[] commands) {
        grp = new int[n];
        values = new String[n];
        List<String> answers = new ArrayList<>();

        for(int i = 0; i < n; i++){
            grp[i] = i;
        }

        StringTokenizer st;
        for(int i = 0; i < commands.length; i++){
            st = new StringTokenizer(commands[i]);
            int r, c;
            String v;
            switch(st.nextToken()){
                case "UPDATE" : // UPDATE 추출
                    String v1 = st.nextToken(); // v1 추출
                    String v2 = st.nextToken(); // v2 추출

                    if(st.hasMoreTokens()){ // 남아있는 토큰이 있는 경우 (UPDATE v1 v2 value)
                        String value = st.nextToken(); // value 추출
                        r = Integer.parseInt(v1) - 1;
                        c = Integer.parseInt(v2) - 1;
                        values[find(r * 50 + c)] = value;
                    }
                    else{ // UPDATE v1 v2
                        for(int j = 0; j < n; j++){
                            if(values[find(j)] != null && values[find(j)].equals(v1)){
                                values[find(j)] = v2;
                            }
                        }
                    }
                    break;

                case "MERGE" :
                    int r1 = Integer.parseInt(st.nextToken()) - 1;
                    int c1 = Integer.parseInt(st.nextToken()) - 1;
                    int r2 = Integer.parseInt(st.nextToken()) - 1;
                    int c2 = Integer.parseInt(st.nextToken()) - 1;

                    int num1 = r1*50 + c1;
                    int num2 = r2*50 + c2;

                    // 값이 없는 표이 대표가 되지 않게 처리
                    if(values[find(num1)] == null && values[find(num2)] != null){
                        int temp = num1;
                        num1 = num2;
                        num2 = temp;
                    }
                    union(num1, num2); // 두 칸 병합
                    break;

                case "UNMERGE" :
                    r = Integer.parseInt(st.nextToken()) - 1;
                    c = Integer.parseInt(st.nextToken()) - 1;

                    int g = find(r*50 +c); // 대표 칸 찾기
                    v = values[g]; // 대표 칸의 값을 저장

                    // 경로 압축
                    for(int j = 0; j < n; j++){
                        find(j);
                    }

                    for(int j = 0; j < n; j++){
                        if(find(j) == g){
                            grp[j] = j; // 동일한 대표 칸을 가진 경우 초기화

                            if(j == r*50 + c){
                                values[j] = v; // 지정된 칸의 값 유지
                            }else{
                                values[j] = null; // 나머지 칸 초기화
                            }
                        }
                    }
                    break;

                case "PRINT" :
                    r = Integer.parseInt(st.nextToken()) - 1;
                    c = Integer.parseInt(st.nextToken()) - 1;

                    v = values[find(r*50 + c)];

                    if(v == null){
                        answers.add("EMPTY");
                    }
                    else answers.add(v);
                    break;
            }
        }
        String[] answer = new String[answers.size()];
        for(int i = 0; i < answers.size(); i++){
            answer[i] = answers.get(i);
        }
        return answer;
    }

    // 원소가 속한 그룹 알아내기
    static int find(int idx){
        if(idx == grp[idx]){
            return idx;
        }
        return grp[idx] = find(grp[idx]);
    }

    // 그룹 합치기
    static void union(int g1, int g2){
        g1 = find(g1);
        g2 = find(g2);

        if(g1 == g2){
            return;
        }
        values[g2] = null;
        grp[g2] = g1;
    }
}