import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

// 별자리 만드는 최소 비용
class Star{
    double x, y;
    Star(double x, double y){
        this.x = x;
        this.y = y;
    }
}

class Edge implements Comparable<Edge>{
    int u, v;
    double weight;

    Edge(int u, int v, double weight){
        this.u = u;
        this.v = v;
        this.weight = weight;
    }

    @Override
    public int compareTo(Edge other){
        return Double.compare(this.weight, other.weight);
    }
}
public class HW_4386 {
    static int n; // 별의 개수
    static int[] map;
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        Star[] stars = new Star[n];


        for(int i=0; i<n; i++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            double x = Double.parseDouble(st.nextToken());
            double y = Double.parseDouble(st.nextToken());
            stars[i] = new Star(x, y);
        }

        List<Edge> edges = new ArrayList<>();

        for(int i=0; i<n; i++){
            for(int j=i+1; j<n; j++){
                double distance = getDistance(stars[i], stars[j]);
                edges.add(new Edge(i, j, distance));
            }
        }

        Collections.sort(edges);

        map = new int[n];
        for(int i=0; i<n; i++){ // Union-Find를 위한 초기화
            map[i] = i;
        }

        double totalCost = 0;
        for(Edge edge : edges){
            if(find(edge.u) != find(edge.v)){
                union(edge.u, edge.v);
                totalCost += edge.weight;
            }
        }
        System.out.printf("%.2f\n", totalCost);
    }
    static double getDistance(Star s1, Star s2){
        return Math.sqrt(Math.pow(s2.x - s1.x, 2) + Math.pow(s2.y - s1.y, 2));
    }

    static int find(int x){
        if(map[x]==x) return x;
        return map[x] = find(map[x]);
    }

    static void union(int u, int v){
        int rootU = find(u);
        int rootV = find(v);
        if(rootU != rootV){
            map[rootV] = rootU;
        }
    }
}

