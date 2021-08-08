package week23.BOJ_5639_S1_이진검색트리;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {

	public static void main(String[] args) throws Exception{

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		// 루트노드 설정 전위순회에서는 무조건 첫번째가 루트임
		Node root = new Node(Integer.parseInt(br.readLine()));
		while(true) {
			String input = br.readLine();
			
			// 입력이 끝나면 종료
			if(input == null) break;
			
			// 내 예시 입력시 -1d을 종료조건으로 둠
//			if(input.equals("-1")) break;
			
			// 트리 추가
			root.add(Integer.parseInt(input));
		}
		
		// 후위순회(왼 -> 오 -> 루트)
		root.postorder();
		
	}

	
	static class Node{
		int num;
		
		Node left;
		Node right;
		
		Node(int num){
			this.num = num;
		}
		
		void add(int input) {
			
			// 크면 오른쪽
			if(num < input) {
				// 비어있으면 넣기
				if(right == null) right = new Node(input);
				else right.add(input);
			}
			// 작으면 왼쪽
			else {
				// 비어있으면 넣기
				if(left == null) left = new Node(input);
				else left.add(input);
			}
		}
		
		void postorder() {
			
			// 왼쪽
			if(left != null)
				left.postorder();
			
			// 오른쪽
			if(right != null)
				right.postorder();
			
			// 루트
			System.out.println(this.num);
		}
	}
}
