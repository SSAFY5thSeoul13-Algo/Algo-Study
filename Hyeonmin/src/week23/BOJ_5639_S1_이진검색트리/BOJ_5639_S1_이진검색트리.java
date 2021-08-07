package week23.BOJ_5639_S1_이진검색트리;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class BOJ_5639_S1_이진검색트리 {
	static Node root;
	
	static class Node{
		Node left;
		Node right;
		int num;
		
		public Node(Node left, Node right, int num) {
			super();
			this.left = left;
			this.right = right;
			this.num = num;
		}
	}
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		while(true) {
			String str = br.readLine();
			
			if(str == null || str.equals(""))
				break;
					
			int num = Integer.parseInt(str);
			
			if(root == null) {
				root = new Node(null, null, num);
				continue;
			}
			
			insertNode(num, root);
		}
		
		printTree(root);
		
		
	}
	
	static void insertNode(int num, Node node) {
		if(node.num < num) {
			if(node.right == null) {
				node.right = new Node(null, null, num);
				return;
			}
			insertNode(num, node.right);
		}
		else if(node.num > num) {
			if(node.left == null) {
				node.left = new Node(null,null, num);
				return;
			}
			insertNode(num, node.left);
		}
		
		
	}
	
	static void printTree(Node node) {
		if(node.left != null) {
			printTree(node.left);
		}
		
		if(node.right != null) {
			printTree(node.right);
		}
		
		System.out.println(node.num);
	}
}
