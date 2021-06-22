package week8;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main_BJ_1062_가르침 {
	
	/*
	 * DFS (comb)
	 * 
	 * 조합으로 K개의 글자를 선택해서 문제를 풀었습니다.
	 * K개의 글자 중 a,n,t,i,c은 항상 포함 되야 하므로
	 * K가 5미만이면 ,
	 * 답은 항상 0이 됩니다. 그래서 이를 먼저 확인하고 return했고
	 * K가 5이상이라면,
	 * 먼저 읽을 수 있는 K개의 글자 (check)에 a,n,t,i,c을 포함시킵니다.
	 * 그리고 조합(dfs)으로 k-5개의 글자를 선택하고
	 * 해당 글자로 단어들을 읽으며 단어의 첫 글자부터 끝까지 읽을 수 있다면 flag를 true를 두어 res++했습니다. 그리고 res의 max값인 ans를 출력합니다.
	 * 
	 * 메모리 13372	| 시간 348
	 */
	
	static int N, K, ans;
	static ArrayList<String> words;
	static boolean[] check;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		/*
		 * K개의 글자를 가르친다.
		 * 학생들은 K개의 글자로만 이루어진 단어를 읽을 수 있다.
		 * 모든 단어는 anta로 시작되고 tica로 끝난다.
		 * 남극언어에 단어는 N개밖에 없다.
		 * 학생들이 읽을 수 있는 단어의 최댓값
		 * 
		 */
		N = Integer.parseInt(st.nextToken()); 
		K = Integer.parseInt(st.nextToken()); 
		
		//읽을 수 있는 글자를 고른다.
		//anta/tica => a n t i c 은 항상 포함
		//k가 5미만이면 답은 0
		if(K<5) {
			System.out.println(0);
			return;
		}
		
		check = new boolean[26]; //배운 단어 체크하는 배열
		words = new ArrayList<>(); //단어 리스트
		
		for (int i = 0; i < N; i++) {
			words.add(br.readLine());
		}
		
		//k>=5인 경우. 일단 a n t i c 부터 읽기
		check[0] = true; //a
		check['n'-'a'] = true; //n
		check['t'-'a'] = true; //t
		check['i'-'a'] = true; //i
		check['c'-'a'] = true; //c
		
		
		//26개중에 이제 골라야된다
		ans = Integer.MIN_VALUE;
		comb(0, 0);
		
		System.out.println(ans);
		
		
	}
	private static void comb(int idx, int cnt) {
		
		if( cnt == K-5) {
			//글자를 다 골랐으면
			
			int res = 0;
			//단어를 읽는다
			for (int i = 0; i < N; i++) {
				String word = words.get(i);
				boolean flag = true;
				for (int j = 0; j < word.length(); j++) {
					char c = word.charAt(j);
					
					if(!check[c-'a']) {
						flag = false;
						//하나라도 못읽으면 flag는 false
						break;
					}
				}	
				//flag가 true이면 res++
				if(flag) res++;

			}
			
			
			
			ans = Math.max(res, ans);
			return;
		}
		
		
		
		for (int i = idx; i < 26; i++) {
			if(!check[i]) {
				check[i] = true;
				comb(i+1, cnt+1);
				check[i] = false;
			}
		}
		
		
	}
}