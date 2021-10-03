package week29.BOJ__2630_S3_색종이만들기;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	/* 색 종이 크기 */
	static int N;
	/* 색종이 */
	static int[][] map;
	/* 색깔별 색종이 수 */
	static int[] cnt = new int[3];
	/* index 0은 하얀색, 1은 파란색 */
	static final int WHITE=0, BLUE=1;
	public static void main(String[] args) throws Exception{
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		N = Integer.parseInt(br.readLine());
		
		map = new int[N][N];
		
		for(int i=0; i<N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for(int j=0; j<N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		cnt[divideConquer(0,0,N)]++;
		
		System.out.println(cnt[WHITE]);
		System.out.println(cnt[BLUE]);
	}
	
	static int divideConquer(int y, int x, int n) {
		
		if(n == 1) {
			return map[y][x];
		}
		int halfN = n/2;
		// 1영역
		int one = divideConquer(y,x, halfN);
		// 2영역
		int two = divideConquer(y, x+halfN, halfN);
		// 3영역
		int three = divideConquer(y+halfN, x, halfN);
		// 4영역
		int four = divideConquer(y+halfN, x+halfN, halfN);

		// 모두 같은 색일 경우 -> 해당 색깔 반환
		if(one == two && one==three && one==four) {
			return one;
		}
		// 아닐경우 -> 각각의 갯수를 카운팅
		cnt[one]++;
		cnt[two]++;
		cnt[three]++;
		cnt[four]++;
		
		return 2;
	}

}
