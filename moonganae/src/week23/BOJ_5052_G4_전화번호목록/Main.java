package week23.BOJ_5052_G4_전화번호목록;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Main {

	/* 테스트케이스 수, 전화번호 수 */
	static int T, N;
	/* 전화번호 목록 */
	static List<String> numbers;
	
	public static void main(String[] args) throws Exception{
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		T = Integer.parseInt(br.readLine());
		
		
		for(int t=0; t<T; t++) {
		
			N = Integer.parseInt(br.readLine());
			
			numbers = new ArrayList<>();
		
			// 전화번호 목록 추가
			for(int i=0; i<N; i++) {
				numbers.add(br.readLine());
			}
			// 전화번호 정렬
			Collections.sort(numbers);
			
			// 일관성여부 확인
			if(isConsistence()) System.out.println("YES");
			else System.out.println("NO");
			
		}
	}
	
	static boolean isConsistence() {
		// 트라이 자료구조
		Trie trie = new Trie();
		
		// 모든 전화번호 순회
		for(String word : numbers) {
			
			// 전화번호 내에서 하나씩 입력해보기
			for(int i=0; i<word.length(); i++) {
				// 다른 전화번호가 prefix라면 일관성 실패
				if(trie.contains(word.substring(0, i+1))) {
					return false;
				}
			}
			
			// 트라이 자료구조에 현재 전화번호 넣가
			trie.insert(word);;
		}
		return true;
	}


	static class Trie{
		TrieNode root = new TrieNode();
		
		void insert(String word) {
			
			TrieNode thisNode = this.root;
			
			for(int i=0; i<word.length(); i++) {
				thisNode = ( thisNode.childs[Character.getNumericValue(word.charAt(i))] = new TrieNode() );
			}
			
			thisNode.setLast(true);
		}
		
		boolean contains(String word) {
			TrieNode thisNode = this.root;
			
			for(int i=0; i<word.length(); i++) {
				
				int idx = Character.getNumericValue(word.charAt(i));
				TrieNode node = thisNode.childs[idx];
				if(node == null) return false;
				
				thisNode = node;
			}
			
			return thisNode.isLast();
		}
	}
	static class TrieNode {
		
		TrieNode[] childs = new TrieNode[10];
		
		boolean isLastChar;
		
		boolean isLast() {
			return this.isLastChar;
		}
		
		void setLast(boolean isLastChar) {
			this.isLastChar = isLastChar;
		}
	}

}

