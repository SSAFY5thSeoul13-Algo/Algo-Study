package week23.boj1068;

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
