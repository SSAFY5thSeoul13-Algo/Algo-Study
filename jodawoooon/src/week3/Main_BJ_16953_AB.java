package week3;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_BJ_16953_AB {
	
/*	bfs로 풀었습니다
	한번은 곱하기 2를 하고
	한번은 StringBuilder를 이용해 수의 맨 오른쪽에 1을 붙여
	B값이 나오면 cnt의 최솟값을 구했습니다.
	B가 되지 못하는 경우엔 return하여 초기값인 -2에 1을 더한 -1이 출력되도록 했습니다.
	result 변수를 long형이 아닌 int형으로 사용해서 오버플로우가 발생했었습니다.

	메모리 : 23772kb	시간 : 160ms*/
	
	static int A,B, min, ans;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		ans = -2;
		min = Integer.MAX_VALUE;
		
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		A = Integer.parseInt(st.nextToken());
		B = Integer.parseInt(st.nextToken());
		
		/*
		 * 정수A를B로 바꾼다.
		2를 곱하거나? 1를 수의 가장 오른쪽의 추가하기.
		2를 16으로 바꿀려면
		2*2 = 4 => 
		4*2 = 8 => 
		8의 오른쪽에 1추가 => 
		81*2 = 162
		
		계속 2는 곱하고.
		1을 뒤에 추가한 경우로 재귀를 돌려보면
		
		만들수 없는 경우에는 -1를 출력
		미리 ans에 -1을 넣어놓고 (+1값이니까 -2 넣어놓기)
		만들수 있는 경우에만 값을 갱신
		
		만들수없는 경우 -> 곱한 값이 return되지 못하고 이미 B를 넘어버리면. (같지않다)
		
		*/
		
		solve(0,A);
		System.out.println(ans+1);
	}
	
	private static void solve(int cnt, long res) {
		//기저조건
		if(res==B) {//A가 B가 되면
			min = Math.min(min, cnt); 
			ans = min;
			return;
		}
		if(res>B) {//A가 B가 되지 못하는 경우
			return;
		}

		
		solve(cnt+1,res*2); //2를 곱한경우
		
		StringBuilder sb = new StringBuilder();
		sb.append(res).append(1); //수의 맨 오른쪽에 1을 붙인경우
		
		solve(cnt+1,Long.parseLong(sb.toString())); //A,B의 범위 10^9 => int형이 아니라 long형
		
	}
}
