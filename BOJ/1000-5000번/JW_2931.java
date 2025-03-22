import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class JW_2931 {

    static int r, c;
    static boolean[][][] board;
    static boolean[][] placed;

    static int[] dy = { -1, 1, 0, 0 }, dx = { 0, 0, -1, 1 };
    static int[] opposite = { 1, 0, 3, 2 };

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        r = Integer.parseInt(st.nextToken());
        c = Integer.parseInt(st.nextToken());
        board = new boolean[r][c][4];
        placed = new boolean[r][c];
        for (int i = 0; i < r; i++) {
            String line = br.readLine();
            for (int j = 0; j < c; j++) {
                char pipe = line.charAt(j);
                if (pipe != '.') {
                    place(i, j, pipe);
                    placed[i][j] = true;
                }
            }
        }
        int missingY = -1, missingX = -1;
        boolean[] deleted = new boolean[4];
        for (int i = 0; i < r; i++) {
            for (int j = 0; j < c; j++) {
                if (placed[i][j])
                    continue;
                boolean[] connections = new boolean[4];
                for (int k = 0; k < 4; k++) {
                    int ny = i + dy[k], nx = j + dx[k];
                    if (!isValid(ny, nx))
                        continue;
                    if (!placed[ny][nx])
                        continue;
                    if (board[ny][nx][opposite[k]]) {
                        connections[k] = true;
                    }
                }
                int count = 0;
                for (boolean conn : connections) {
                    if (conn)
                        count++;
                }
                if (count >= 2) {
                    missingY = i;
                    missingX = j;
                    deleted = connections;
                }
            }
        }
        String recoveredPipe = find(deleted);
        System.out.println((missingY + 1) + " " + (missingX + 1) + " " + recoveredPipe);
    }

    private static String find(boolean[] deleted) {
        if (deleted[0] && deleted[1] && !deleted[2] && !deleted[3])
            return "|";
        if (!deleted[0] && !deleted[1] && deleted[2] && deleted[3])
            return "-";
        if (deleted[0] && deleted[1] && deleted[2] && deleted[3])
            return "+";
        if (!deleted[0] && deleted[1] && !deleted[2] && deleted[3])
            return "1";
        if (deleted[0] && !deleted[1] && !deleted[2] && deleted[3])
            return "2";
        if (deleted[0] && !deleted[1] && deleted[2] && !deleted[3])
            return "3";
        return "4";
    }

    private static void place(int y, int x, char pipe) {
        switch (pipe) {
        case '|':
            board[y][x] = new boolean[] { true, true, false, false };
            break;
        case '-':
            board[y][x] = new boolean[] { false, false, true, true };
            break;
        case '+':
            board[y][x] = new boolean[] { true, true, true, true };
            break;
        case '1':
            board[y][x] = new boolean[] { false, true, false, true };
            break;
        case '2':
            board[y][x] = new boolean[] { true, false, false, true };
            break;
        case '3':
            board[y][x] = new boolean[] { true, false, true, false };
            break;
        case '4':
            board[y][x] = new boolean[] { false, true, true, false };
            break;
        }
    }

    private static boolean isValid(int y, int x) {
        return 0 <= y && y < r && 0 <= x && x < c;
    }
}