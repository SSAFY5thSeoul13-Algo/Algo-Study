## BOJ 1068 트리 
- Tree
- 🥇 Gold5
- 🔗[트리 문제 바로가기](https://www.acmicpc.net/problem/1068)



## 풀이

먼저 노드의 개수 N을 입력받고 그 크기 만큼의 배열을 만들어 두번째 줄의 입력을 차례로 받아 각 index의 부모노드번호를 parent 배열에 저장합니다.

그리고 지울 노드를 입력받고 노드와 그 자손모드를 모두 지우는 메소드를 실행합니다.

~~~java
	private static void delete(int node) {
		// 지워진 노드는 -2로 표시 
		parent[node] = -2;
		for(int i=0; i<N; i++) {
			if(parent[i] == node) {
				delete(i);
			}
		}
		
	}
~~~

재귀를 사용하여 지워야 하는 노드의 자식이라면 그 자식노드도 지웁니다. 이때 지워진 노드라는 것을 알기 위해 -2로 표시합니다.


~~~java
	private static void countLeaf(int node) {
		visited[node] = true;
		int childLeaf = 0;
		// root가 지워진 경우도 자식노드가 0이므로 지워진 노드인 경우는 메소드를 실행하지 않는다.
		if(parent[node]!=-2) {
			for(int i=0; i<N; i++) {
				if(!visited[i] && parent[i]==node) {
					childLeaf++;
					countLeaf(i);
				}
			}
			if(childLeaf == 0) {
				answer++;
			}
		}
		
	}
~~~

지우는 작업 후 남은 리프노드를 세는 메소드를 실행합니다. root부터 세는데 root가 지워진 노드라면 아무것도 하지 않습니다. 각 노드의 하위노드를 세 만약 리프노드가 0인경우 해당 트리의 리프노드 이므로 answer을 count해줍니다. 그렇게 메소드를 실행하여 구한 answer가 총 리프노드의 개수가 됩니다. 


## 소스코드
~~~java
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_1068_G5_트리 {
	static int N, deleteNode, answer;
	static int[] parent;
	static boolean[] visited;
	public static void main(String[] args)throws Exception {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		N = Integer.parseInt(br.readLine());
		parent = new int[N];
		visited = new boolean[N];
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		int root = 0;
		for(int i=0; i<N; i++) {
			parent[i] = Integer.parseInt(st.nextToken());
			if(parent[i]==-1) {
				root = i;
			}
		}
		
		deleteNode = Integer.parseInt(br.readLine());

		delete(deleteNode);
		
		int count = 0;
		countLeaf(root);
		
		System.out.println(answer);
		

	}
	// leafNode의 개수를 세준다.
	private static void countLeaf(int node) {
		visited[node] = true;
		int childLeaf = 0;
		// root가 지워진 경우도 자식노드가 0이므로 지워진 노드인 경우는 메소드를 실행하지 않는다.
		if(parent[node]!=-2) {
			for(int i=0; i<N; i++) {
				if(!visited[i] && parent[i]==node) {
					childLeaf++;
					countLeaf(i);
				}
			}
			if(childLeaf == 0) {
				answer++;
			}
		}
		
	}
	// 노드와 그 자손노드까지 모두 지우는 메소드 
	private static void delete(int node) {
		// 지워진 노드는 -2로 표시 
		parent[node] = -2;
		for(int i=0; i<N; i++) {
			if(parent[i] == node) {
				delete(i);
			}
		}
		
	}


}

~~~

## 결과 

| 메모리  | 시간 |
|----|----|
|11560kb|88ms|
