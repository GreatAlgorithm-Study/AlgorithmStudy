import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

class SB_150366 {
    static String[] board = new String[2500];
    static int[] parents = new int[2500];
    static List<String> ans = new ArrayList<>();
    static String empty = "EMPTY";
    static int N = 2500;

    private static void print(String[] cmd) {
        int r = Integer.parseInt(cmd[1])-1;
        int c = Integer.parseInt(cmd[2])-1;
        int idx = r*50+c;
        int pIdx = find(idx);

        if (board[pIdx]==null) ans.add(empty);
        else ans.add(board[pIdx]);
    }
    private static void unmerge(String[] cmd) {
        int r = Integer.parseInt(cmd[1])-1;
        int c = Integer.parseInt(cmd[2])-1;

        int idx = r*50+c;             // 선택된 r,c 셀 인덱스
        int p = find(idx);           // 해당 셀의 부모 인덱스 (병합 대표)
        String val = board[p];      // 부모 인덱스의 값 (병합 대표 값)

        // 병합 해제될 셀 추적 리스트
        List<Integer> candi = new ArrayList<>();

        // 병합해제할 셀 후보로 담기
        for (int i = 0; i < N; i++) {
            if (find(i) == p) {
                candi.add(i);
            }
        }

        // 병합해제할 셀 값 복원
        for (Integer cell : candi){
            parents[cell] = cell;
            board[cell] = null;
        }

        // 타겟 병합해제 셀은 원래 값 유지
        board[idx] = val;
    }
    private static int find(int x) {
        if (x!=parents[x]) parents[x] = find(parents[x]);
        return parents[x];
    }

    private static void union(int a, int b, String val) {
        if (a != b) {
            parents[b] = a;     // b의 부모를 a로 설정
            board[a] = val;     // 병합 대표에만 값 설정

            // b와 연결된 모든셀에 새로운 부모 a설정
            for (int i = 0; i < N; i++) {
                if (find(i)==b) parents[i] = a;
            }
        }
    }

    private static void merge(String[] cmd) {
        int r1 = Integer.parseInt(cmd[1])-1;
        int c1 = Integer.parseInt(cmd[2])-1;
        int r2 = Integer.parseInt(cmd[3])-1;
        int c2 = Integer.parseInt(cmd[4])-1;

        // 각 셀의 부모 인덱스
        int p1 = find(50*r1+c1);
        int p2 = find(50*r2+c2);

        // 대표 셀 값 결정
        if (p1 != p2) {
            String val = board[p1] != null ? board[p1] : board[p2];
            union(p1, p2, val);
        }
    }

    private static void updateOne(String[] cmd) {
        int r = Integer.parseInt(cmd[1]) - 1;
        int c = Integer.parseInt(cmd[2]) - 1;

        // 선택한 셀의 부모 값 수정
        int idx = 50*r+c;           // 선택된 값의 인덱스
        int p = find(idx);           // 해당 인덱스의 부모(병합 대표)
        board[p] = cmd[3];          // 병합 대표값 업데이트
    }
    private static void updateAll(String[] cmd) {
        String target = cmd[1];
        String val = cmd[2];
        for (int i = 0; i < N; i++) {                               // 배열 순회하면서 값 변경
            if (Objects.equals(board[i], target)) board[i] = val;   // null값 처리(Objects.equals)
        }
    }
    public static String[] solution(String[] commands) {
        // 병합테이블 초기화
        for (int i = 0; i < N; i++) {
            parents[i] = i;
        }

        for (String cmds : commands) {
            String[] cmd = cmds.split(" ");
            switch (cmd[0]) {
                case "UPDATE":
                    if (cmd.length == 4) updateOne(cmd);
                    else updateAll(cmd);
                    break;
                case "MERGE":
                    merge(cmd);
                    break;
                case "UNMERGE":
                    unmerge(cmd);
                    break;
                case "PRINT":
                    print(cmd);
                    break;
            }
        }

        return ans.toArray(new String[0]);
    }
}
