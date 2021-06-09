package week15.BOJ_10773_S4_제로;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class Main_BJ_10773_S4_제로 {

	
	static int K, ans;
	static ArrayList<Integer> list;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		K = Integer.parseInt(br.readLine());
		list = new ArrayList<>();
		
		for (int i = 0; i < K; i++) {
			int input = Integer.parseInt(br.readLine());
			if(input!=0) {
				list.add(input);
			}else {
				list.remove(list.size()-1);
			}
		}
		
		for (int i = 0; i < list.size(); i++) {
			ans += list.get(i);
		}
		
		System.out.println(ans);
	
	}

	
}
