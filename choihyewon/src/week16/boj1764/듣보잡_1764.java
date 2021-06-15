package week16.boj1764;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.StringTokenizer;

public class 듣보잡_1764 {
	static int N,M;
	public static void main(String[] args)throws Exception {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		HashSet<String> notHear = new HashSet();
		List<String> notLook = new ArrayList<String>();
		List<String> result = new ArrayList<String>();
		
		for(int i=0; i<N; i++) {
			notHear.add(br.readLine());
		}
		
		for(int i=0; i<M; i++) {
			String s = br.readLine();
			notLook.add(s);
			// 보도못한 사람이 듣도못한 사람 리스트에 속해있으면 result에 넣어준다.
			if(notHear.contains(s)) {
				result.add(s);
			}
		}
		
		Collections.sort(result);
		
		System.out.println(result.size());
		for(int i=0; i<result.size(); i++) {
			System.out.println(result.get(i));
		}
		
	}

}
