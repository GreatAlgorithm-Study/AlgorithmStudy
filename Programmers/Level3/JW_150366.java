import java.util.ArrayList;
import java.util.Arrays;
class JW_150366 {
    String[][] cells = new String[51][51];
    int[][][] parent = new int[51][51][2];

    public String[] solution(String[] commands) {
        // 빈 문자열로 초기화
        for (int i = 0; i < 51; i++)
            Arrays.fill(cells[i], "EMPTY");
        for (int i = 1; i < 51; i++)
            for (int j = 1; j < 51; j++)
                // 자기 자신을 부모로 같도록 초기화
                parent[i][j] = new int[] { i, j };
        ArrayList<String> result = new ArrayList<>();
        for (int i = 0; i < commands.length; i++) {
            String[] command = commands[i].split(" ");
            int r1, c1, r2, c2;
            String value1, value2;
            switch (command[0]) {
            case "UPDATE":
                if (command.length == 4) {
                    r1 = Integer.parseInt(command[1]);
                    c1 = Integer.parseInt(command[2]);
                    value1 = command[3];
                    updateCell(r1, c1, value1);
                } else {
                    value1 = command[1];
                    value2 = command[2];
                    updateArea(value1, value2);
                }
                break;
            case "MERGE":
                r1 = Integer.parseInt(command[1]);
                c1 = Integer.parseInt(command[2]);
                r2 = Integer.parseInt(command[3]);
                c2 = Integer.parseInt(command[4]);
                merge(r1, c1, r2, c2);
                break;
            case "UNMERGE":
                r1 = Integer.parseInt(command[1]);
                c1 = Integer.parseInt(command[2]);
                unmerge(r1, c1);
                break;
            case "PRINT":
                r1 = Integer.parseInt(command[1]);
                c1 = Integer.parseInt(command[2]);
                result.add(print(r1, c1));
                break;
            }
        }
        // 리스트를 배열로 변경 후 반환
        return result.toArray(new String[0]);
    }

    private void updateCell(int r, int c, String value) {
        // 바꾸려는 위치의 그룹의 부모
        int[] rootX = find(r, c);
        for (int i = 1; i < 51; i++)
            for (int j = 1; j < 51; j++) {
                int[] rootY = find(i, j);
                // 같은 그룹이라면 값 변경
                if (rootX[0] == rootY[0] && rootX[1] == rootY[1]) {
                    cells[i][j] = value;
                }
            }
    }

    private void updateArea(String value1, String value2) {
        for (int i = 1; i < 51; i++)
            for (int j = 1; j < 51; j++)
                // 동일한 값을 가지는 모든 값 변경
                if (cells[i][j].equals(value1))
                    cells[i][j] = value2;
    }

    private void merge(int r1, int c1, int r2, int c2) {
        if (r1 == r2 && c1 == c2) {
            return;
        }
        String value = cells[r1][c1];
        if (value.equals("EMPTY")) {
            value = cells[r2][c2];
        }
        union(r1, c1, r2, c2);      // 그룹 합치기
        updateCell(r1, c1, value);  // 해당 셀과 같은 그룹인 셀들 값 변경
    }

    private void unmerge(int r, int c) {
        // merge되어있는 그룹의 부모
        int[] rootX = find(r, c);
        // 해당 그룹의 값
        String value = cells[rootX[0]][rootX[1]];
        for (int i = 1; i < 51; i++)
            for (int j = 1; j < 51; j++) {
                int[] rootY = find(i, j);
                // 같은 그룹이라면
                if (rootX[0] == rootY[0] && rootX[1] == rootY[1]) {
                    // union되어있는 관계 풀어주고 빈 문자열 삽입
                    parent[i][j] = new int[] { i, j };
                    cells[i][j] = "EMPTY";
                }
            }
        // 병합을 시작한 부분의 값을 기존의 값으로 변경
        cells[r][c] = value;
    }

    // 해당 셀 반환
    private String print(int r, int c) {
        return cells[r][c];
    }
    
    // find 연산
    private int[] find(int r, int c) {
        int pR = parent[r][c][0];
        int pC = parent[r][c][1];
        if (r != pR || c != pC)
            parent[r][c] = find(pR, pC);
        return parent[r][c];
    }

    // union 연산
    private void union(int r1, int c1, int r2, int c2) {
        int[] rootX = find(r1, c1);
        int[] rootY = find(r2, c2);
        parent[rootX[0]][rootX[1]] = rootY;
    }
}