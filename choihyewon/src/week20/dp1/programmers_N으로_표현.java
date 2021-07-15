package week20.dp1;

public class programmers_N으로_표현 {	
	static int answer = 9;
	public int solution(int N, int number) {
        dfs(N, number, 0, 0);
        
        if(answer == 9)
            return -1;
        return answer;
    }

	private void dfs(int N, int number, int cnt, int result) {
		// 최솟값이 8보다 크면 -1 return
		if(cnt > 8) {
			answer = -1;
			return;
		}
		
		if(result==number) {
			answer = Math.min(answer, cnt);
            return;
		}
		
		int tmp = N;
		for(int i=0; i<8-cnt; i++) {
			dfs(N, number, cnt+i+1, result+tmp);
			dfs(N, number, cnt+i+1, result-tmp);
			dfs(N, number, cnt+i+1, result*tmp);
			dfs(N, number, cnt+i+1, result/tmp);
            tmp = tmp * 10 + N;
		}
		
		
	}

}
