package week18.Programmers_LV3_네트워크;

public class Programmers_LV3_네트워크 {
	static int n;
	static int[][] computers = {
			{1,1,0},
			{1,1,0},
			{0,0,1}
	};
	//count: 네트워크 수, length: 컴퓨터 수
	static int count, length;
	static boolean[] visited;
	
	public static void main(String[] args) {
		solution();
	}
	
	static void solution() {
		length = computers.length;
		//방문 여부
		visited = new boolean[length];
		
		//dfs실행
		for (int i = 0; i < length; i++) {
			dfs(i, 0);
		}
		
		System.out.println(count);
	}
	
	static void dfs(int idx, int depth) {
		//이미 방문한 곳이면 스킵
		if(visited[idx])
			return;
		
		//방문 체크
		visited[idx]= true;
		
		//해당 네트워크 첫 방문이면 네트워크 카운트 증가
		if(depth ==0)
			count++;
		
		//연결된 다른 컴퓨터 확인
		for (int i = 0; i < length; i++) {
			if(computers[idx][i] ==  1)
				dfs(i, depth+1);
		}
	}
}
