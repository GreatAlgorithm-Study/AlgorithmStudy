/*
방문 길이
 */

class DH_49994 {
    static class Point {
        int r, c;
        public Point(int r, int c) {
            this.r = r;
            this.c = c;
        }
    }
    static int dr[] = {-1, 0, 1, 0};
    static int dc[] = {0, -1, 0, 1};

    static boolean check(int r, int c) {
        return r >= 0 && r < 11 && c >= 0 && c < 11;
    }
    public int solution(String dirs) {
        boolean v[][][] = new boolean[4][11][11];
        int answer = 0;

        Point p = new Point(5, 5);

        for(int i = 0; i < dirs.length(); i++) {
            char ch = dirs.charAt(i);

            int dir = 0;
            if(ch == 'U') dir = 0;
            if(ch == 'L') dir = 1;
            if(ch == 'D') dir = 2;
            if(ch == 'R') dir = 3;

            int nr = p.r + dr[dir];
            int nc = p.c + dc[dir];

            if(!check(nr, nc)) continue;
            if(!v[dir][p.r][p.c] && !v[(dir + 2) % 4][nr][nc]) {
                v[dir][p.r][p.c] = true;
                v[(dir + 2) % 4][nr][nc] = true;
                answer++;
            }

            p.r = nr;
            p.c = nc;
        }
        return answer;
    }
}