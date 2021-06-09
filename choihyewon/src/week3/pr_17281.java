package week3;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class pr_17281 {

	static int N,max = 0;		// 이닝 수, 결과 값 
	static int[][] result;
	static int[] tgt;
	static boolean[] visited;
	public static void main(String[] args)throws Exception {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		
		result = new int[N][10];		// 이닝에서 얻는 결과를 담는 배열 
		tgt = new int[10];				// 타자 순서를 담는 배열 
		visited = new boolean[10];
		tgt[4] = 1;						// 4번 타자는 1번 선수로 고정 
		visited[4] = true;
		
		for(int i=0; i<N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for(int j=1; j<=9; j++) {
				result[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		
		perm(2);
		
		System.out.println(max);

	}
	
	static void perm(int tgtIdx) {
		// 기저조건 
		if(tgtIdx==10) {
			solve();
			return;
		}
		
		for(int i=1; i<10; i++) {
			if(visited[i])	continue;
			
			visited[i] = true;
			tgt[i] = tgtIdx;
			perm(tgtIdx+1);
			visited[i] = false;
		}
		
	}
	
	static void solve() {
		int playerNum = 1;
		int score =0;
		for(int i=0; i<N; i++) {
			int outCnt =0;
			boolean[] base = new boolean[4];
			while(true) {
				if(outCnt==3) {
					break;
				}
				switch(result[i][tgt[playerNum]]) {
				case 0:
					outCnt++;
					break;
				case 1:
					if(base[3])	score++;
					base[3] = base[2];
					base[2] = base[1];
					base[1] = true;
					break;
				case 2 :
					if(base[3])	score++;
					if(base[2])	score++;
					base[3] = base[1];
					base[2] = true;
					base[1] = false;
					break;
				case 3:
					if(base[3])	score++;
					if(base[2])	score++;
					if(base[1])	score++;
					base[3] = true;
					base[2] = false;
					base[1] = false;
					break;
				case 4:
					score++;
					if(base[3])	score++;
					if(base[2])	score++;
					if(base[1])	score++;
					base[3] = false;
					base[2] = false;
					base[1] = false;
					break;
				}
				
				if(playerNum==9) {
					playerNum=1;
				}else {
					playerNum++;
				}
			}
			
			
			
		}
		
		max = Math.max(max, score);
	}
	

}
