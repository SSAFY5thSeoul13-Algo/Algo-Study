package week19.PROGRAMMERS_LV3_순위;

class Solution {
    public int solution(int n, int[][] results) {
        int answer = 0;
        /* win[i][j] : i가 j를 이겼음. */
        boolean[][] win = new boolean[n+1][n+1];
        
        // 파라미터로 넘어온거 체크
        for(int i=0; i<results.length; i++){
            win[results[i][0]][results[i][1]] = true;
        }
        
        /* 플로이드-워샬 */
        for(int i=1; i<=n; i++){
            for(int j=1; j<=n; j++){
                for(int k=1; k<=n; k++){
                    // j > i > k이면 j > k이다.
                    if(win[j][i] && win[i][k])
                        win[j][k] = true;
                }
            }
        }
        
        // 나보다 높거나 낮은 사람 찾기
        for(int i=1; i<=n; i++){
            int cnt = 0;
            for(int j=1; j<=n; j++){
                // 나에게 승리했거나, 진사람 수 확인
                if(win[i][j] || win[j][i])
                    cnt++;
            }
            // n-1이면 내 순위 확인 가능하다!
            if(cnt == n-1) answer++;
        }
        
        return answer;
    }
}

