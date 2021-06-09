package week9;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.StringTokenizer;

public class Main_BJ_2565_전깃줄 {
	
	//전깃줄의 교차 =>
	//1번전깃줄과 2번전깃줄 교차할 조건
	//1번의 a < 2번의 a && 1번의 b > 2번의 b
	static class Node implements Comparable<Node>{
		int a,b;

		public Node(int a, int b) {
			super();
			this.a = a;
			this.b = b;
		}

		@Override
		public int compareTo(Node o) {
			
			return this.a - o.a;
		}
		
	}
	static int N, dp[], ans;
	static ArrayList<Node> list;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		N= Integer.parseInt(br.readLine());
		
		list =new ArrayList<>();
		
		dp = new int[N];
		//dp는 설치 가능한 최대 개수.
		
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			
			list.add(new Node(a,b)); //i번째 전깃줄 : a-b로 전깃줄 이어짐
			
			dp[i] = 1;
		}
		//전깃줄 리스트 셋팅
		
		
		Collections.sort(list);
		//전깃줄의 시작점 (a전봇대) 기준으로 오름차순 정렬한다.
		
		
		//모든 전깃줄이 서로 교차하지 않게 하기 위해 없애야 하는 전깃줄의 최소 개수를 출력한다.
		
		
		for (int i = 0; i < N; i++) {

			int cur = list.get(i).b; //cur전봇대의 b위치값
			
			for (int j = i-1; j >= 0; j--) {
				// cur전봇대 이전의 전봇대 전선을 탐색한다
				
				int next = list.get(j).b; //next전봇대의 b위치값
				
				//j번째 전봇대의 b(next)는 i번째 전봇대의 b보다 작아야함 
				//=> 그래야 연결이 된다. 
				if(cur>next) {
					//작다면 연결한값(dp[j]+1)과 dp[i]중 큰 값으로 갱신
					dp[i] = Math.max(dp[i], dp[j]+1);
				}
				
			}
			
			ans = Math.max(ans, dp[i]);
		}
		
		
		//전체 전선갯수에서 설치가능한 갯수 빼주면 ans
		ans = N-ans;
		System.out.println(ans);
	}
}
