package Programmers.Level2;

import java.util.HashMap;

public class SB_롤케이크_자르기 {
    static int cnt = 0;

    public static int solution(int[] topping) {
        int N = topping.length;
        HashMap<Integer, Integer> l_map = new HashMap<>();
        HashMap<Integer, Integer> r_map = new HashMap<>();

        for (int i : topping) {
            l_map.put(i, l_map.getOrDefault(i, 0) + 1);
        }

        for (int i = N - 1; i >= 0; i--) {
            l_map.put(topping[i], l_map.getOrDefault(topping[i], 1) - 1);
            if (l_map.get(topping[i])==0) l_map.remove(topping[i]);
            r_map.put(topping[i], l_map.getOrDefault(topping[i], 0) + 1);
            if (l_map.size()==r_map.size()) cnt++;
        }
        return cnt;
    }
    public static void main(String[] args) {
        int[] topping = {1, 2, 3, 1, 4};
        solution(topping);
        System.out.println(cnt);
    }
}

/*
* 처음에 반 가르고, 거기서 부터 left, right 각각 움직이는 코드로 짰는데 그건 틀림..
* */
