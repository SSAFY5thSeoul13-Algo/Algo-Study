package week23.BOJ_5639_S1_이진검색트리;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

/**
 * 
 * 
 * ✨ Algorithm ✨
 * 
 * @Problem : BOJ 5639 이진검색트리
 * 이진 검색 트리를 전위 순회한 결과가 주어졌을 때, 이 트리를 후위 순회한 결과를 구하는 프로그램을 작성하시오.
 * 
 * @Author : Daun JO
 * @Date : 2021. 8. 8. 
 * 
 *
 */
public class Main_BOJ_5639_S1_이진검색트리 {
	

	static ArrayList<Integer> preTree;
	static ArrayList<Integer> postTree;
	static int N;
	public static void main(String[] args) throws Exception {
		
		//System.setIn(new FileInputStream("5639input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		preTree = new ArrayList<>();

		//트리를 전위 순회한 결과가 주어진다.
		 while(true) {
			 String input = br.readLine();
			 if(input == null || input.length()==0 ) {
				 break;
			 }
			 preTree.add(Integer.parseInt(input));
		}

		//전위순회 : root->left->right
		//후위순회 : left->right->root
		
		//left는 root보다 작고, right는 root보다 크다
		
		N = preTree.size();
		getPostOrderTree(0, N-1);
		
	}
	private static void getPostOrderTree(int start, int end) {

		if(start>end) return; //기저조건

		int root = preTree.get(start);

		int right = start+1;
		for(int i = start+1 ; i <= end ; i++) {
			if(root < preTree.get(i)) { //right를 찾는다.
				right = i;
				break;
			}
		}
		
		getPostOrderTree(start+1, right-1); //왼쪽 서브트리 
		getPostOrderTree(right, end); //오른쪽 서브트리
		
		System.out.println(root); //루트 출력
	}
	
}
