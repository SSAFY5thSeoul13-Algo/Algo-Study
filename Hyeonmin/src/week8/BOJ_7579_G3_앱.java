package week8;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_7579_G3_앱 {
	static int N, M;
	//메모리 저장배열
	static int[] memory;
	//비용 저장 배열
	static int[] cost;
	//각 코스트에서 확보한 최대 메모리
	static int[] result;
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st= new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		memory = new int[N];
		cost = new int[N];
		
		st = new StringTokenizer(br.readLine());
		StringTokenizer st2 = new StringTokenizer(br.readLine());
		
		int size = 0;
		
		//메모리, 코스트 정보를 저장
		for (int i = 0; i < N; i++) {
			memory[i] = Integer.parseInt(st.nextToken());
			cost[i] = Integer.parseInt(st2.nextToken());
			//전체 코스트의 총을 구함
			size += cost[i];
		}
		
		//전체 코스트만큼의 결과 배열을 생성
		result = new int[size+1];
		
		
		//n번째 앱까지 고려 하는 경우
		for (int n = 0; n < N; n++) {
			//가장 큰 코스트부터 차례로 진행
			//현재 고려하는 앱의 비용보다 작은 배열값에는 영향을 주지 못해 cost[n]이상인 경우만 진행
			for (int c = size; c >= cost[n]; c--) {
				//현재까지 구한 코스트 c에서의 최대 확보 메모리보다 현재 고려하는 앱을 비활성화 했을 때 확보 메모리가 더 크면 저장
				if(result[c] < result[c-cost[n]] + memory[n]) {
					result[c] = result[c-cost[n]] + memory[n];
				}
			}
		}
		
		//배열의 앞에서부터 탐색해 메모리 비용이 M보다 큰 코스트를 출력하고 종료
		for (int c = 0; c <= size; c++) {
			if(result[c] >= M) {
				System.out.println(c);
				return;
			}
		}
		
	}
}