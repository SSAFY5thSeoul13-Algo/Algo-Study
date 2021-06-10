package week8;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class BOJ_2156_S1_포도주시식 {
	static int N;
	static int[] map;
	//int[i][j] : j번째 잔을 포함해서 연달아 i번 마셨을 때의 최대 값  
	static int[][] result;
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		N = Integer.parseInt(br.readLine());
		
		map = new int[N];
		result = new int[3][N];
		
		for (int i = 0; i < N; i++) {
			map[i] = Integer.parseInt(br.readLine());
		}
		
		//잔이 1개이면 결과 출력
		if(N == 1) {
			System.out.println(map[0]);
			return;
		}
		
		//0번째 잔을 마신경우
		result[1][0] = map[0];
		
		//1번째 잔을 안먹는 경우
		result[0][1] = result[1][0];
		//1번쨰 잔을 첫잔으로 먹는 경우
		result[1][1] = result[0][0] + map[1];
		//1번째 잔을 이어서 먹는 경우
		result[2][1] = result[1][0] + map[1];
		
		//2번째 잔부터 결과를 구한다
		for (int i = 2; i < N; i++) {
			//와인을 마시는 경우
			for (int j = 0; j < 2; j++) {
				//j잔 연속으로 마시는 경우는 이전번째에서 j-1잔 연속으로 마신것에 현재잔을 더 마신것
				result[j+1][i] = result[j][i-1] + map[i];
			}
			//마시지 않는 경우는 앞서 마신 경우들 중에서 가장 큰 것을 값으로 넣는다
			result[0][i] = Math.max(Math.max(result[2][i-1], result[1][i-1]), result[0][i-1]);
		}
		
		int max = 0;
		
		//마지막 잔을 처리한 후 가장 큰 값을 저장
		for (int i = 0; i < 3; i++) {
			if(max < result[i][N-1])
				max = result[i][N-1];
		}
		
		System.out.println(max);
	}
}
