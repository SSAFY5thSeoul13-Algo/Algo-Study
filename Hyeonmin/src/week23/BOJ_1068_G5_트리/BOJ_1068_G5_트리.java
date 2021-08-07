package week23.BOJ_1068_G5_트리;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class BOJ_1068_G5_트리 {
	static List<Integer>[] tree = new ArrayList[50];	
	static int N, root, remove, count;
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		
		for (int i = 0; i < 50; i++) {
			tree[i] = new ArrayList<>();
		}
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		//노드 번호
		int idx = 0;
		
		//입력
		while(st.hasMoreTokens()) {
			int num = Integer.parseInt(st.nextToken());
			
			//루트 노드 번호 저장
			if(num == -1) {
				root = idx;
				idx++;
				
				continue;
			}
			
			//해당 번호의 노드에 하위 노드 저장
			tree[num].add(idx);
			
			idx++;
		}
		
		//삭제할 노드
		remove = Integer.parseInt(br.readLine());
		
		//탐색
		dfs(root);
		
		System.out.println(count);
	}
	
	static void dfs(int num) {
		if(num == remove) {
			return;
		}
		
		int size = tree[num].size();
		
		//삭제한 노드가 하위에 있는 경우
		if(tree[num].contains(remove)) {
			size--;
		}
		
		//하위 노드가 없으면 리프노드
		if(size == 0) {
			count++;
			return;
		}
		
		//계속해서 하위 탐색
		for (Integer idx : tree[num]) {
			if(idx != remove)
				dfs(idx);
		}
	}
}
