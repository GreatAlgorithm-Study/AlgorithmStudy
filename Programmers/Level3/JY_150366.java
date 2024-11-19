import java.util.*;

class Solution {
    
    static final int N = 51;
    static String[][] g;
    static List<Integer>[] link;
    static List<String> aList;
    
    public String[] solution(String[] commands) {
        aList = new ArrayList<>();
        
        g = new String[N][N];
        for(int i=0; i<N; i++) {
            Arrays.fill(g[i], "");
        }
        
        int M = N*N + N;
        link = new ArrayList[M];
        for(int i=0; i<M; i++) {
            link[i] = new ArrayList<>();
        }
        
        for(String c: commands) {
            String[] crr = c.split(" ");
            
            // 1) UPDATE r c value
            if(crr[0].equals("UPDATE") && crr.length == 4) {
                insert(Integer.parseInt(crr[1]), Integer.parseInt(crr[2]), crr[3]);
            } 
            // 2) UPDATE value1, value2
            else if (crr[0].equals("UPDATE") && crr.length == 3) {
                change(crr[1], crr[2]);
            }
            // 3) MERGE r1 c1 r2 c2
            else if(crr[0].equals("MERGE")) {
                merge(Integer.parseInt(crr[1]), 
                      Integer.parseInt(crr[2]), 
                      Integer.parseInt(crr[3]), 
                      Integer.parseInt(crr[4]));
            }
            // 4) UNMERGE r c
            else if(crr[0].equals("UNMERGE")) {
                unmerge(Integer.parseInt(crr[1]), Integer.parseInt(crr[2]));
            }
            // 5) PRINT r c
            else if(crr[0].equals("PRINT")) {
                print(Integer.parseInt(crr[1]), Integer.parseInt(crr[2]));
            }
            
        }
        
        // System.out.println(aList);
        String[] answer = new String[aList.size()];
        for(int i=0; i<aList.size(); i++) {
            answer[i] = aList.get(i);
        }
        
        return answer;
    }
    public static void printG() {
        for(int i=0; i<N; i++) {
            System.out.println(Arrays.toString(g[i]));
        }
        System.out.println();
    }
    public static void linking(int r, int c, String v, boolean isUnmerge) {
        Queue<Integer> q = new LinkedList<>();
        boolean[][] visited = new boolean[N][N];
        
        q.add(r*N + c);
        visited[r][c] = true;
        
        while(!q.isEmpty()) {
            int now = q.poll();
            int x = now / N;
            int y = now % N;
            g[x][y] = v;
            for(int next: link[now]) {
                int nx = next / N;
                int ny = next % N;
                if(visited[nx][ny]) continue;
                
                visited[nx][ny] = true;
                q.add(next);
            }
            if(isUnmerge) link[now] = new ArrayList<>();
        }
    }
    public static void insert(int r, int c, String v) {
        g[r][c] = v;
        
        if(link[r*N+c].isEmpty()) return;
        
        linking(r, c, v, false);
    }
    public static void change(String v1, String v2) {
        for(int i=0; i<N; i++) {
            for(int j=0; j<N; j++) {
                if(g[i][j].equals(v1)) g[i][j] = v2;
            }
        }
    }
    public static void merge(int r1, int c1, int r2, int c2) {
        if(r1==r2 && c1 == c2) return;
        
        // (r1, c1)이 비어있고, (r2, c2)만 값을 가지고 있음 : (r2, c2)로 병합
        if(g[r1][c1].length() == 0 && g[r2][c2].length() != 0) {
            g[r1][c1] = g[r2][c2];
            linking(r1, c1, g[r1][c1], false);
        }
        // (r1, c1)로 병합
        else {
            g[r2][c2] = g[r1][c1];
            linking(r2, c2, g[r2][c2], false);
        }
        
        // 링크 생성
        int n1 = r1*N + c1;
        int n2 = r2*N + c2;
        link[n1].add(n2);
        link[n2].add(n1);
        
    }
    public static void unmerge(int r, int c) {
        String value = g[r][c];
        
        linking(r, c, "", true);
        
        g[r][c] = value;
    }
    public static void print(int r, int c) {
        if(g[r][c].length() == 0) aList.add("EMPTY");
        else aList.add(g[r][c]);
    }
}