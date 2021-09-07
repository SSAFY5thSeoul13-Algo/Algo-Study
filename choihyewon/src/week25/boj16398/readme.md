## BOJ 16398 행성 연결 
- Kruskal
- 🥇 Gold4
- 🔗[행성 연결 문제 바로가기](https://www.acmicpc.net/problem/16398)



## 풀이

마찬가지로 크루스칼 알고리즘을 이용해서 풀었습니다. 
i와 j가 같지 않은 경우에만 간선의 정보를 list에 담아주었고 이를 가중치순으로 오름차순 정렬을 해주었습니다.
그리고 싸이클이 발생한다면 다음으로 가중치가 낮은 간선을 선택하였고 선택된 간선이 N-1인 경우 break 해주었습니다.


## 막힌점 

처음에 결과의 타입을 int로 해주어 60%쯤에서 틀렸습니다로 결과가 출력되었는데 long으로 바꾸어 주었더니 해결할 수 있었습니다.

## 소스코드
~~~java
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

public class BOJ_16398_G4_행성_연결 {
	static int N;
	static int[] parents;
	static List<Edge> edgeList = new ArrayList<>();
	static class Edge implements Comparable<Edge>{
		int from;
		int to;
		int weight;
		public Edge(int from, int to, int weight) {
			this.from = from;
			this.to = to;
			this.weight = weight;
		}
		@Override
		public int compareTo(Edge o) {
			// TODO Auto-generated method stub
			return this.weight - o.weight;
		}
	}
	static int find(int a) {
		if(parents[a]==a) {
			return a;
		}
		
		return parents[a] = find(parents[a]);
	}
	static boolean union(int a, int b) {
		int aRoot = find(a);
		int bRoot = find(b);
		
		if(aRoot == bRoot) {
			return false;
		}
		
		parents[bRoot] = aRoot;
		
		return true;
	}
	public static void main(String[] args)throws Exception {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		
		parents = new int[N];
		
		for(int i=0; i<N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for(int j=0; j<N; j++) {
				int weight = Integer.parseInt(st.nextToken());
				if(i==j)	continue;
				edgeList.add(new Edge(i,j,weight));
			}
		}
		
		for(int i=0; i<N; i++) {
			parents[i] = i;
		}
		
		Collections.sort(edgeList);
		
		long result = 0;
		int count = 0;
		
		for (Edge e : edgeList) {
			if(union(e.from, e.to)){
				result += e.weight;
				
				if(++count == N-1)	break;
			}
		}
		System.out.println(result);

	}

}

~~~

## 결과 

| 메모리  | 시간 |
|----|----|
| 173576kb| 1224ms|
