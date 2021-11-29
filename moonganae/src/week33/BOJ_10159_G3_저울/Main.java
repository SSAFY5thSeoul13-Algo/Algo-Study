package week33.BOJ_10159_G3_저울;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int N, M;
    static int[][] weight;
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        M = Integer.parseInt(br.readLine());
        weight = new int[N][N];

        // 무거운 것만 체크
        for(int i=0; i<M; i++){
            StringTokenizer st = new StringTokenizer(br.readLine());

            int heavier = Integer.parseInt(st.nextToken()) - 1;
            int lighter = Integer.parseInt(st.nextToken()) - 1;

            weight[heavier][lighter] = 1;
        }

        solve();
    }

    static void solve(){

        // 플로이드워샬
        // a->b, b->c => a->c 만 연결
        for(int k=0; k<N; k++){
            for(int i=0; i<N; i++){
                for(int j=0; j<N; j++){
                    if(weight[i][k] != 0 && weight[k][j] != 0){
                        weight[i][j] = 1;
                    }
                }
            }
        }

        // 비교우위를 알 수 없는 것의 갯수 카운팅
        for(int i=0; i<N; i++){
            int cnt = 0;
            for(int j=0; j<N; j++){
                if(i==j) continue;

                if(weight[i][j]==0 && weight[j][i]==0) cnt++;

            }
            System.out.println(cnt);
        }

    }

    static void printWeight(){
        System.out.println("--------------------");
        for(int i=0; i<N; i++){
            for(int j=0; j<N; j++){
                System.out.print(weight[i][j] + " ");
            }
            System.out.println();
        }
    }
}
