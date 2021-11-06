package week32.BOJ_1956_G4_운동;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    static int V, E;
    static final int INF = 4000000;
    static int[][] A;
    public static void main(String[] args) throws Exception{

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());

        V = Integer.parseInt(st.nextToken());
        E = Integer.parseInt(st.nextToken());

        A = new int[V][V];
        for(int i=0; i<V; i++){
            Arrays.fill(A[i], INF);
        }
        for(int i=0; i<E; i++){
            st = new StringTokenizer(br.readLine());

            int from = Integer.parseInt(st.nextToken())-1;
            int to = Integer.parseInt(st.nextToken())-1;
            int w = Integer.parseInt(st.nextToken());

            A[from][to] = w;
        }
        // 플로이드 워샬알고리즘으로 최단거리를 먼저 구해준다.
        floydWashall();

        // 가장 짧은 사이클을 찾고 거리를 구한다.
        int ans = findCycle();

        if(ans == INF) System.out.println(-1);
        else System.out.println(ans);
    }
    static void floydWashall(){
        for(int k=0; k<V; k++){
            for(int i=0; i<V; i++){
                for(int j=0; j<V; j++){
                    if(A[i][k] + A[k][j] < A[i][j])
                        A[i][j] = A[i][k] + A[k][j];
                }
            }
        }
    }
    static int findCycle(){
        int ans = INF;
        for(int i=0; i<V; i++){
            for(int j=0; j<V; j++){
                if(i==j) continue;
                // 양방향으로 갈 수 있다 == 사이클이 존재한다.
                if(A[i][j] != INF && A[j][i] != INF){
                    ans = Math.min(ans, A[i][j] + A[j][i]);
                }
            }
        }
        return ans;
    }
}

