package week16.BOJ_10546_S4_배부른마라토너;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class BOJ_10546_S4_배부른마라토너 {
	static Map<String, Integer> map = new HashMap<>();
	static int N;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		N = Integer.parseInt(br.readLine());
		
		for (int i = 0; i < N; i++) {
			String name = br.readLine();
			
			map.put(name, map.getOrDefault(name, 0)+1);
		}
		
		for (int i = 1; i < N; i++) {
			String name = br.readLine();
			
			map.replace(name, map.get(name)-1);
		}
		
		Set<String> set = map.keySet();
		
		for (String str : set) {
			if(map.get(str) == 1) {
				System.out.println(str);
				break;
			}
		}
	}
}
