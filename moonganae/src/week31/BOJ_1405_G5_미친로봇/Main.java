package week31.BOJ_1405_G5_미친로봇;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    // 이동횟수
    static int N;
    // 단순이동 확률
    static double ans;
    // 동서남북 이동확률
    static double[] percents = new double[4];

    // delta array (동,서,남,북)
    static int[] dy={0,0,1,-1}, dx={1,-1,0,0};
    // dfs용 방문배열
    static boolean[][] vis;
    public static void main(String[] args) throws Exception{

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt((st.nextToken()));
        // 동서남북 이동확률 입력
        for(int i=0; i<4; i++){
            percents[i] = (double) Integer.parseInt((st.nextToken())) / 100;
        }

        // 특정 위치에서 N번씩만 이동할 수 있으므로 2N+1 크기면 벗어나지 않음.
        vis = new boolean[N*2 + 1][N*2 + 1];

        vis[N][N] = true;
        dfs(N, N, 1, 0);

        System.out.println(ans);
    }


    static void dfs(int y, int x, double percent, int num){

        // 단순이동 N번 수행시
        if(num == N){
            // 확률 더하기
            ans += percent;
            return;
        }

        for(int z=0; z<4; z++){
            // 갈 확률이 없을경우
            if(percents[z] == 0.0) continue;
            int zy = y + dy[z];
            int zx = x + dx[z];
            // 단순하지 않은 경로일 경우
            if(vis[zy][zx]) continue;

            vis[zy][zx] = true;
            dfs(zy,zx, percent * percents[z], num+1);
            vis[zy][zx] = false;
        }
    }
}

