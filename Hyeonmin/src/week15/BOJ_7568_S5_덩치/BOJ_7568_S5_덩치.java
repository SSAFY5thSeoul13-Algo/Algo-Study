package week15.BOJ_7568_S5_덩치;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_7568_S5_덩치 {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		//사람 수
		int N= Integer.parseInt(br.readLine());
		
		//몸무게, 키, 순위 를 담을 배열들
		int weight[] = new int[N];
		int height[] = new int[N];
		int rank[] = new int[N];
		
		//몸무게, 키, 순위를 입력받고 저장
		for(int i=0 ; i<N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			weight[i]=Integer.parseInt(st.nextToken());
			height[i]=Integer.parseInt(st.nextToken());
			rank[i]=1;
		}
		
		//첫 사람부터 1대1로 비교
		for(int i=0; i<N; i++) {
			for(int j=0; j<N; j++) {
				//자기 자신은 비교하지 않고 넘김
				if(j==i)
					continue;
				//몸무게와 키가 둘다 작을 경우 해당하는 사람의 순위를 조정
				if(weight[i]<weight[j] && height[i]<height[j]) {
					rank[i]++;
				}
			}
		}
		
		//출력
		for(int result : rank) {
			System.out.print(result+" ");
		}
	}
}
