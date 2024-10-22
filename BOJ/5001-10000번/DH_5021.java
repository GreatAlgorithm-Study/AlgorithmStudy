import java.io.*;
import java.util.*;

/*
 * 왕위계승
 */

public class DH_5021 {
    static HashMap<String, Integer> nameToIdx; // 이름을 idx로 바꿔주는 HashMap
    static HashMap<Integer, String> idxToName; // idx를 이름으로 바꿔주는 HashMap
    static double[] score; // 각 사람들의 혈통 점수를 저장하는 변수
    static ArrayList<Integer>[] adj; // 인접리스트 저장
    static int[] degree; // 진입차수 저장
    static boolean[] select; // 왕위를 계승받기를 주장하는 사람인지 저장 //
    static int start;

    public static void main(String[] args) throws Exception {
        initInput();
        solution();
    }

    // 위상정렬
    static void solution() {

        Deque<Integer> q = new ArrayDeque<Integer>();

        int idx = 0;
        double s = 0;

        // 진입차수가 0일 때
        // idx == start라면(왕) 본인의 혈통: 1
        // 왕이 아니라면 본인의 혈통: 0.5
        for(int i = 0; i < degree.length; i++) {
            if(degree[i] != 0) continue;
            q.add(i);
            if(i == start) score[i] = 1;
            else score[i] = 0.5;
        }

        while(!q.isEmpty()) {
            int currentIdx = q.poll();

            // 왕위 계승을 주장하는 사람이면서 혈통 점수가 크다면
            // 왕위계승을 받는 자식
            if(select[currentIdx] && s < score[currentIdx]) {
                idx = currentIdx;
                s = score[currentIdx];
            }

            // 진입차수의 값을 1씩 빼주면서
            // 자손의 혈통 점수 update
            // 진입차수의 값이 0이라면 queue에 넣어주기
            for(int next: adj[currentIdx]) {
                degree[next] -= 1;
                score[next] += score[currentIdx] / 2;
                if(degree[next] == 0) {
                    q.add(next);
                }
            }
        }

        System.out.println(idxToName.get(idx));
    }

    static void initInput() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken()); // N개의 가족정보
        int M = Integer.parseInt(st.nextToken()); // 왕위를 계승받기 위한 사람의 이름

        nameToIdx = new HashMap<String, Integer>();
        idxToName = new HashMap<Integer, String>();

        // N줄에 걸쳐 가족 정보가 주어지므로
        // 사람의 수는 최대 3N명일 것이라고 생각
        adj = new ArrayList[N * 3];
        select = new boolean[N * 3];
        score = new double[N * 3];

        for(int i = 0; i < adj.length; i++) adj[i] = new ArrayList<Integer>();

        degree = new int[N * 3];

        String startName = br.readLine();
        setHashMap(startName);
        start = nameToIdx.get(startName);

        for(int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());

            String s1 = st.nextToken();
            String s2 = st.nextToken();
            String s3 = st.nextToken();

            setHashMap(s1);
            setHashMap(s2);
            setHashMap(s3);

            // 간선리스트 생성
            adj[nameToIdx.get(s2)].add(nameToIdx.get(s1));
            adj[nameToIdx.get(s3)].add(nameToIdx.get(s1));

            // 자식은 진입차수 2개
            degree[nameToIdx.get(s1)] = 2;
        }

        // 왕위 계승을 주장하는 사람들 정보 저장
        for(int i = 0; i < M; i++) {
            String s = br.readLine();
            if(!nameToIdx.containsKey(s)) continue;
            select[nameToIdx.get(s)] = true;
        }
    }

    static void setHashMap(String name) {
        if(!nameToIdx.containsKey(name)) {
            int idx = nameToIdx.size();
            nameToIdx.put(name, idx);
            idxToName.put(idx, name);
        }
    }
}
