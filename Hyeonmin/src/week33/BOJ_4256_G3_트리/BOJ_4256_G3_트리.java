package week33.BOJ_4256_G3_트리;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_4256_G3_트리 {
	static int T, N, idx;
	static int[] preOrder, inOrder;
	static StringBuilder sb = new StringBuilder();

	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		T = Integer.parseInt(br.readLine());
		
		for (int t = 0; t < T; t++) {
			N = Integer.parseInt(br.readLine());
			
			idx = 1;
			
			preOrder = new int[N];
			inOrder = new int[N];
			StringTokenizer st1 = new StringTokenizer(br.readLine());
			StringTokenizer st2 = new StringTokenizer(br.readLine());
			
			for (int i = 0; i < N; i++) {
				preOrder[i] = Integer.parseInt(st1.nextToken());
				inOrder[i] = Integer.parseInt(st2.nextToken());
			}
			
			int startIdx = 0;
			
			for (int i = 0; i < N; i++) {
				if(preOrder[0] == inOrder[i]) {
					startIdx = i;
					break;
				}
			}
			
			divide(startIdx, 0, N-1);
			
			sb.append("\n");
		}
		
		System.out.println(sb.toString());
	}
	
	static void divide(int nowIdx, int start, int end) {
		if(start == end) {
			sb.append(inOrder[start]).append(" ");
			return;
		}
		
		if(idx >= N) {	
			sb.append(inOrder[nowIdx]).append(" ");
			return;
		}
		
		int num = preOrder[idx];
		
		int nextIdx = start;
		
		while(nextIdx < nowIdx) {
			if(num == inOrder[nextIdx]) {
				idx++;
				divide(nextIdx, start, nowIdx-1);
				break;
			}
			
			nextIdx++;
		}
		
		if(idx >= N) {	
			sb.append(inOrder[nowIdx]).append(" ");
			return;
		}
		
		nextIdx = end;
		num = preOrder[idx];
		
		while(nextIdx > nowIdx) {
			if(num == inOrder[nextIdx]) {
				idx++;
				divide(nextIdx, nowIdx+1, end);
				break;
			}
			
			nextIdx--;
		}
		
		
		sb.append(inOrder[nowIdx]).append(" ");
		
	}

}

