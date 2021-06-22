package week10;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main_BJ_1030_프렉탈평면2 {
	
	
	static int s, N, K, R1, R2, C1, C2, size;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		s = Integer.parseInt(st.nextToken()); //시간
		N = Integer.parseInt(st.nextToken()); //흰색 정사각형이 나뉘어 지는 단위 정사각형 크기
		K = Integer.parseInt(st.nextToken()); //가운데 K*K 정사각형 검정색
		R1 = Integer.parseInt(st.nextToken());
		R2 = Integer.parseInt(st.nextToken());
		C1 = Integer.parseInt(st.nextToken());
		C2 = Integer.parseInt(st.nextToken()); //R1행 C1열부터 R2행 C2열 까지 출력
		
		
		
		//1초마다 N배씩 크기가 커지고, 가운데 K*K는 검은색으로 색칠됨
		//x,y좌표 값이 뭔지 찾는다
		
		size = (int)Math.pow(N,s); //s초 후 map의 사이즈
		StringBuilder sb = new StringBuilder();
		
		for (int i = R1; i <= R2; i++) { //R1행 C1열부터 R2행 C2열 까지 출력
			for (int j = C1; j <= C2; j++) {
				
				//i,j가 검은색인지 하얀색인지 찾는다
				
				int n = size; 
				
				int x = i;
				int y = j;
				
				int tx = 0;
				int ty = 0;
				
				
				boolean flag = false; //k*k박스안에 속하는지 확인하는 flag
				
				
				//처음엔 size*size인 정사각형
				
				
				while(true) {
					//N*N개로 나뉘어지기 전을 확인한다.
					
					n /= N; //변의 길이 n
					
					if(n<1) break;
					
					//n*n사이즈에서 (x,y)의 위치를 확인한다. 
					tx = x / n;
					ty = y / n;
					
					int s = N/2 - K/2;
					//가운데 검정색 체크
					if(( tx >= s && tx < s+K ) && ( ty >= s && ty < s+K) ) {
						//tx,ty가 K*K안에 포함된다면
						sb.append(1);
						flag = true;
						break;

					}
					else {
						// 아니면 이제 현재칸에 대해서 다시
						x -= tx * n;
						y -= ty * n;
					}
					
				}
				
				if(!flag) sb.append(0);
				
			}sb.append("\n");
		}
		
		System.out.println(sb.toString());
		
		
	}

}
