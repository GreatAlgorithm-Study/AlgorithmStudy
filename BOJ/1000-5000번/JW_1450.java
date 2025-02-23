import java.util.Map;
import java.util.TreeMap;

public class JW_1450 {

    static int n, c;
    static TreeMap<Long, Integer> tmA = new TreeMap<>();
    static TreeMap<Long, Integer> tmB = new TreeMap<>();
    static TreeMap<Long, Integer> prefixSumA = new TreeMap<>();
    static TreeMap<Long, Integer> prefixSumB = new TreeMap<>();

    public static void main(String[] args) throws Exception {
        n = read();
        c = read();
        int[] aArr = new int[n / 2];
        int[] bArr = new int[n - n / 2];

        for (int i = 0; i < aArr.length; i++)
            aArr[i] = read();
        for (int i = 0; i < bArr.length; i++)
            bArr[i] = read();
        recursive(0, 0, aArr, tmA);
        recursive(0, 0, bArr, tmB);

        makePrefixSum(tmA, prefixSumA);
        makePrefixSum(tmB, prefixSumB);

        System.out.println(calculate());
    }

    private static void recursive(int depth, long sum, int[] arr, TreeMap<Long, Integer> map) {
        if (sum > c)
            return;
        if (depth == arr.length) {
            map.put(sum, map.getOrDefault(sum, 0) + 1);
            return;
        }
        recursive(depth + 1, sum + arr[depth], arr, map);
        recursive(depth + 1, sum, arr, map);
    }

    private static void makePrefixSum(TreeMap<Long, Integer> tm, TreeMap<Long, Integer> prefixSum) {
        int sum = 0;
        for (Map.Entry<Long, Integer> entry : tm.entrySet()) {
            sum += entry.getValue();
            prefixSum.put(entry.getKey(), sum);
        }
    }

    private static long calculate() {
        long count = 0;

        for (Map.Entry<Long, Integer> entry : tmA.entrySet()) {
            long aSum = entry.getKey();
            int aWays = entry.getValue();
            Map.Entry<Long, Integer> bEntry = prefixSumB.floorEntry(c - aSum);
            if (bEntry != null) {
                count += (long) aWays * bEntry.getValue();
            }
        }

        return count;
    }

    private static int read() throws Exception {
        int c, n = System.in.read() & 15;
        while ((c = System.in.read()) >= 48)
            n = (n << 3) + (n << 1) + (c & 15);
        if (c == 13)
            System.in.read();
        return n;
    }
}