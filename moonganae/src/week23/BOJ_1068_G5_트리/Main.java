package week23.BOJ_1068_G5_트리;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
	/* 노드수, 삭제노드 번호, Leaf노드 수, 트리의 루트번호 */
	static int N, deleteIdx, answer, root;
	/* 노드 */
	static Node[] nodes;
	public static void main(String[] args) throws Exception{
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		N = Integer.parseInt(br.readLine());
		/* N개의 노드 생성 */
		nodes = new Node[N];
		for(int i=0; i<N; i++) {
			nodes[i] = new Node();
		}
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		deleteIdx = Integer.parseInt(br.readLine());
		
		// 노드 연결
		for(int i=0; i<N; i++) {
			int parent = Integer.parseInt(st.nextToken());
			// 루트노드일 경우
			if(parent == -1) root = i;
			// 삭제할 인덱스는 연결하지 않는다.
			else if(i==deleteIdx) continue;
			// 아니라면 노드를 부모노드의 child로 연결
			else {
				nodes[parent].childs.add(i);
			}
		}

		// 루트 == 삭제할 인덱스라면 Leaf노드는 0개
		if(deleteIdx != root) searchLeaf(root);
		
		
		System.out.println(answer);
	}
	
	public static class Node {
		List<Integer> childs = new ArrayList<>();
	}

	public static void searchLeaf(int idx) {
		// Leaf 노드라면 갯수증가
		if(nodes[idx].childs.size() == 0) {
			answer++;
			return;
		}
		// 자식순회
		for(int child : nodes[idx].childs) {
			searchLeaf(child);
		}
	}
}
