package week13;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main_BJ_2878_캔디캔디 {
	
/*	
	먼저 부족한 사탕의 개수를 구합니다. (sum-M)
	그리고 못 주는 사탕을 공평하게 배분 (부족한 사탕 / 나누어 줄 친구 수 => sum / N ) 하여 사탕을 나눕니다.

	가장 사탕을 덜 받고 싶어하는 친구부터 배분을 시작합니다.
	이 때 사탕을 받고 싶어하는 갯수가, 못 주는 사탕의 개수보다 작다면
	굳이 못 주는 사탕의 수를 크게 할 필요가 없으므로 min값으로 사탕을 분배하고 분노수치를 구한다.



	- 어려웠던점
	많이 요구한 사람에게는 많이주고, 조금 요구한사람한테는 조금 주기위해
	가장 많이받고싶은 사람에게 먼저 분배하는 식으로 구현했으나 실패했습니다.

	해당 로직을 구현하기가 어려워서 풀이를 참고했고
	사탕을 나누지 않고, 부족한 사탕의 개수를 적절히 분배해도 된다는 점을 깨달았습니다.



	- 결과
	21824	256
	
	*/
	static int M,N,want[];
	static long sum,ans;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		M = Integer.parseInt(st.nextToken()); //사탕 개수
		N = Integer.parseInt(st.nextToken()); //나누어 줄 친구 수
		
		//원하는 사탕 개수 정보. -> 못받으면 분노
		//분노 수치 = 못 받는 사탕 개수의 제곱
		
		want = new int[N];
		
		for (int i = 0; i < N; i++) {
			want[i] = Integer.parseInt(br.readLine()); //각 친구들이 받고 싶어하는 사탕의 수
			sum += want[i];
		}
		
		//사탕을 적절히 나누어 주어 친구들의 분노의 합을 최소화
		
		sum -= M; //못주는 사탕의 수
		Arrays.sort(want); //오름차순 정렬
		
		
		for (int i = 0; i < want.length; i++) {
			long w = Math.min(want[i], sum / N--);
			//갖고싶은 사탕의 수가 분배값보다 작다면? 이 값으로 바꾼다
			
			ans += w*w; //분노수치 갱신
			sum -= w; //사탕 수 갱신
		}
		
		System.out.println(ans%((long)Math.pow(2, 64)));
	}
}
