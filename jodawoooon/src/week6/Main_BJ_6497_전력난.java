package week6;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main_BJ_6497_전력난 {

	//가로등을 키면 길의 미터 수 만큼 돈이 들어감
	//일부를 소등해보자
	//어떤 두 집을 왕래할 때, 불이 켜져 있지 않은 길을 반드시 지나야 한다면 위험하다
	// 도시에 있는 모든 두 집 쌍에 대해, 불이 켜진 길만으로 서로를 왕래할 수 있어야함
	//조건을 만족하며 절약가능한 최대 액수
	
/*	일반적인 MST 문제라고 생각이 들어 크루스칼 알고리즘을 이용하여 풀었습니다.
	입력된 간선 비용을 오름차순 정렬 후, 사이클이 아니면 간선을 선택하여 총 비용을 구했습니다.
	그리고 전체비용에서 이 최소비용을 빼서 최종 결과값을 구했습니다.

	- 막혔던 부분
	입력 케이스 부분에서 0 0을 왜 입력받는건지 이해를 못하고 버리는 값이라고 생각을 했었습니다.

	- 결과
	메모리 239412	시간 832*/
	
	static class Edge implements Comparable<Edge>{
		int x, y, z;

		
		public Edge(int x, int y, int z) {
			super();
			this.x = x;
			this.y = y;
			this.z = z;
		}


		@Override
		public int compareTo(Edge o) {
			//return this.weight-o.weight;
			return Integer.compare(this.z, o.z);
		}
	}
	
	static int M, N, ans, all;
	static int parents[];
	static Edge[] edgeList;

	static void make() {
		for (int i = 0; i < M; i++) {
			parents[i] = i; //크기가 1인 단위집합 만들기
		}
	}
	
	static int findSet(int a) {
		if(parents[a]==a) return a;
		else return parents[a] = findSet(parents[a]);
	}
	
	static boolean union(int a, int b) {
		int aRoot = findSet(a);
		int bRoot = findSet(b);
		if(aRoot!=bRoot) {
			parents[bRoot] = aRoot;
			return true;
		}else return false;
	}
	
	public static void main(String[] args) throws Exception{

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		//입력은 여러 개의 테스트 케이스로 구분되어 있다.
		while(true) {
			ans = 0;
			all = 0;
			
			st = new StringTokenizer(br.readLine()," ");
			M = Integer.parseInt(st.nextToken()); //집의 수 (정점의 수)
			N = Integer.parseInt(st.nextToken()); //길의 수 (간선의 수)
			
			if(M==0&&N==0) break;
			
			
			parents = new int[M];
			edgeList = new Edge[N];

			
			for (int m = 0; m < N; m++) {
				st = new StringTokenizer(br.readLine()," ");
				
				int x = Integer.parseInt(st.nextToken());
				int y = Integer.parseInt(st.nextToken());
				int z = Integer.parseInt(st.nextToken());
				
				//x번집과 y번집 사이에 "양방향"도로가 있으며 그 거리가 z미터이다.
				//도시는 항상 연결그래프 형태임
				all += z;
				edgeList[m] = new Edge(x, y, z);
				
			} //간선 리스트 저장
//			st = new StringTokenizer(br.readLine()," ");
//			Integer.parseInt(st.nextToken());
//			Integer.parseInt(st.nextToken());
			
			
			
			//간선 리스트 정렬
			
			Arrays.sort(edgeList);
			
			make(); //단위집합 만들기
			
			int min = 0;
			int count = 0;
			
			for (Edge e : edgeList) {
				if(union(e.x, e.y)) {//싸이클 없으면
					min += e.z; //비용 누적
					count++;
					if(count==M-1) break; //카운트가 정점-1개되면 break;
				}
			}
			
			//절약할수 있는 최대 비용
			ans = all - min;
			System.out.println(ans);
			
			
		}
		
		
	}

}
