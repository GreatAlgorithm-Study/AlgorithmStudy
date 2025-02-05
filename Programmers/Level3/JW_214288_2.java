import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
class JW_214288_2 {
    
    ArrayList<ArrayList<int[]>> al = new ArrayList<>();
    int[][] wArr;
    int[] tArr;
    int min = Integer.MAX_VALUE;

    public int solution(int k, int n, int[][] reqs) {
        wArr = new int[k + 1][n - k + 2];
        tArr = new int[k + 1];
        for (int i = 0; i < n + 1; i++)
            al.add(new ArrayList<>());
        for (int i = 0; i < reqs.length; i++)
            al.get(reqs[i][2]).add(new int[] { reqs[i][0], reqs[i][1] });
        makeInfo(n, k);
        Arrays.fill(tArr, 1);
        makeCombi(1, n, k, n - k, 0);
        return min;
    }

    private void makeInfo(int n, int k) {
        for (int i = 1; i < k + 1; i++)
            for (int j = 1; j < n - k + 2; j++) {
                PriorityQueue<Integer> pq = new PriorityQueue<>();
                for (int[] req : al.get(i)) {
                    if (pq.size() < j) {
                        pq.offer(req[0] + req[1]);
                    } else if (pq.peek() <= req[0]) {
                        pq.poll();
                        pq.offer(req[0] + req[1]);
                    } else {
                        int prev = pq.poll();
                        wArr[i][j] += prev - req[0];
                        pq.offer(prev + req[1]);
                    }
                }
            }
    }

    private void makeCombi(int depth, int n, int k, int p, int totalTime) {
        if (depth == k + 1) {
            min = Math.min(min, totalTime);
            return;
        }
        for (int i = 0; i <= p; i++) {
            tArr[depth] += i;
            makeCombi(depth + 1, n, k, p - i, totalTime + wArr[depth][1 + i]);
            tArr[depth] -= i;
        }
    }
}