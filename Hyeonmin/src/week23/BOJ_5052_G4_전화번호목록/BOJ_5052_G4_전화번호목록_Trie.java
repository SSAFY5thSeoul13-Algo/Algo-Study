package week23.BOJ_5052_G4_전화번호목록;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class BOJ_5052_G4_전화번호목록_Trie {
	static int T, N;
	
	static class Trie{
		boolean isEnd;
		boolean isContinue;
		Trie[] children;
		
		public Trie() {
			this.isEnd = false;
			this.isContinue = false;
			this.children = new Trie[10];
		}
		
		public boolean add(int idx, String tel) {
			if(isEnd) {
				return false;
			}
			
			if(idx == tel.length()) {
				isEnd = true;
				
				if(isContinue) {
					return false;
				}
				else {
					return true;
				}
			}
			
			int num = Character.getNumericValue(tel.charAt(idx));
			
			if(children[num] == null) {
				children[num] = new Trie();
				isContinue = true;
			}
			
			return children[num].add(idx+1, tel);
		}
	}

	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		T = Integer.parseInt(br.readLine());
		
		StringBuilder sb = new StringBuilder();
		
		for (int t = 0; t < T; t++) {
			N = Integer.parseInt(br.readLine());
			
			boolean isPrefix = false;
			Trie trie = new Trie();
			
			for (int i = 0; i < N; i++) {
				String tel = br.readLine();
				
				//n개만큼 읽어야 하니까 break를 하면 안된다
				if(!isPrefix && !trie.add(0, tel)) {
					isPrefix = true;
				}
			}
			
			if(isPrefix) {
				sb.append("NO").append("\n");
			}
			else {
				sb.append("YES").append("\n");
			}
			
		}
		
		System.out.println(sb.toString());
	}
}
