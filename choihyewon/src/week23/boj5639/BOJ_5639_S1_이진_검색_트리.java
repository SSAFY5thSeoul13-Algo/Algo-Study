package week23.boj5639;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class BOJ_5639_S1_이진_검색_트리 {
	static class Node{
		int num;
		Node left;
		Node right;
		Node(int num){
			this.num = num;
		}
	}
	static class Tree{
		Node root;
		void add(Node node,int num) {
			if(node==null) {
				root = new Node(num);
			}else {
				if (num > node.num) {
                    if(node.right != null) add(node.right, num);
                    else node.right = new Node(num);
                } else if(num < node.num) {
                    if(node.left != null) add(node.left, num);
                    else node.left = new Node(num);
                }
			}
		}
		
		void postorder(Node node) {
			if(node.left!=null) {
				postorder(node.left);
			}
			if(node.right!=null) {
				postorder(node.right);
			}
			System.out.println(node.num);
		}
	}
	public static void main(String[] args)throws Exception{
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		Tree tree = new Tree();
		
		while(true) {
			String str = br.readLine();
			
			if(str==null || str.equals(""))
				break;
			
			int num = Integer.parseInt(str);
			tree.add(tree.root, num);
		}
		
		tree.postorder(tree.root);
	}

}
