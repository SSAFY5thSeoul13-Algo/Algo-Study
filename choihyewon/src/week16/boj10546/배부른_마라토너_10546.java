package week16.boj10546;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;

public class 배부른_마라토너_10546 {

	public static void main(String[] args)throws Exception {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		
		HashMap<String, Integer> map = new HashMap<>();
		
		for(int i=0; i<N; i++) {
			String name = br.readLine();
			if(map.containsKey(name)) {
				map.put(name, map.get(name)+1);
			}else {
				map.put(name, 1);
			}
		}
		
		for(int i=0; i<N-1; i++) {
			String name = br.readLine();
			if(map.containsKey(name)) {
				// 동명이인이 있는 경우 
				if(map.get(name)>1) {
					map.replace(name,map.get(name)-1);
				}
				// 유일한 이름인 경우 
				else if(map.get(name)==1) {
					map.remove(name);
				}
			}
		}
		
		// HashMap에서 key값을 set에 저장 
		Set<String> set = map.keySet(); 
		
		Iterator<String> iter = set.iterator();
		while(iter.hasNext()) {
			System.out.println(iter.next());
		}
		
	}

}
