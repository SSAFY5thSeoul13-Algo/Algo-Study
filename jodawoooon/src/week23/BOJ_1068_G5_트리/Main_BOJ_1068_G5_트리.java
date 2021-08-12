package week23.BOJ_1068_G5_트리;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * 
 * 
 * ✨ Algorithm ✨
 * @Problem : BOJ 1068 트리
 * @Author : Daun JO
 * @Date : 2021. 8. 7. 
 *
 */
public class Main_BOJ_1068_G5_트리 {
	
	static int N, root, tgt, ans;
	static ArrayList<Integer>[] tree;
	public static void main(String[] args) throws Exception {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		/**
		 * 노드 지우고, 리프노드 찾기
		 */
		
		N = Integer.parseInt(br.readLine()); //트리의 노드의 개수
		
		tree = new ArrayList[N];
		for(int i=0; i<N ; i++) {
			tree[i] = new ArrayList<>(); //이진트리아님
		}
		
		st = new StringTokenizer(br.readLine());
		for(int i=0; i<N ; i++) {
			int parent = Integer.parseInt(st.nextToken()); //각 노드의 부모
			
			if(parent==-1) root = i; //부모가 없다면 -1이 주어진다. 즉 루트이다.
			else tree[parent].add(i);
		}
		
		tgt = Integer.parseInt(br.readLine()); //지울 노드의 번호
		
		dfs(root); //root부터 탐색
		
		System.out.println(ans);
		
	}
	
	private static void dfs(int node) {
		if(node==tgt) return; //삭제할 노드이면 탐색 중지
		
		if(tree[node].size()==0) {
			//자식이 없다 => 리프노드이다.
			ans++;
			return;
		}
		
		for(int v : tree[node]) {

			if(v==tgt&&tree[node].size()==1) {
				//현재 node가 삭제할 노드만을 자식으로 가지고 있다. => 리프노드가 된다	
				ans++;
				return;	
			}
			dfs(v);
		}
		
		
	}
	
	
}
