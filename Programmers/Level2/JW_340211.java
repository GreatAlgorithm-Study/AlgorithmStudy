import java.util.HashMap;
class JW_340211 {
    HashMap<Integer, HashMap<Integer, Integer>> moves = new HashMap<>();
    int cnt = 0;

    public int solution(int[][] points, int[][] routes) {
        for (int i = 0; i < routes.length; i++) {
            int[] route = routes[i];
            int[] now = points[route[0] - 1].clone();
            int turn = 0;
            moves.putIfAbsent(turn, new HashMap<>());
            int pos = now[0] * 1000 + now[1];
            moves.get(turn).put(pos, moves.get(turn).getOrDefault(pos, 0) + 1);
            for (int j = 1; j < route.length; j++) {
                int[] to = points[route[j] - 1];
                while (now[0] != to[0] || now[1] != to[1]) {
                    if (now[0] < to[0])
                        now[0]++;
                    else if (now[0] > to[0])
                        now[0]--;
                    else if (now[1] < to[1])
                        now[1]++;
                    else if (now[1] > to[1])
                        now[1]--;
                    turn++;
                    moves.putIfAbsent(turn, new HashMap<>());
                    pos = now[0] * 1000 + now[1];
                    moves.get(turn).put(pos, moves.get(turn).getOrDefault(pos, 0) + 1);
                }
            }
        }
        int cnt = 0;
        for (int turn : moves.keySet())
            for (int move : moves.get(turn).keySet())
                if (moves.get(turn).get(move) > 1)
                    cnt++;
        return cnt;
    }
} 