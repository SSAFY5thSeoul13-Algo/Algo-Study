package week2;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;


public class Main_BJ_2527_직사각형 {
	
	//두개의 직사각형이 겹치는 부분이 직사각형인지. 선분인지. 점인지. 전혀 없는지 판별하기
	//직사각형 => a
	//선분 => b
	//점 => c
	//없음 => d

	static int x1,x2,y1,y2,p1,p2,q1,q2;
	public static void main(String[] args) throws Exception {
		//선분이라면 => 2가지 경우
		//점이라면 => 
		
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		for (int i = 0; i < 4; i++) {

			//첫번째 직사각형 좌표

			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			x1 = Integer.parseInt(st.nextToken());
			y1 = Integer.parseInt(st.nextToken());	
			p1 = Integer.parseInt(st.nextToken());
			q1 = Integer.parseInt(st.nextToken());

			
			//두번째 직사각형 좌표

			x2 = Integer.parseInt(st.nextToken());
			y2 = Integer.parseInt(st.nextToken());	
			p2 = Integer.parseInt(st.nextToken());
			q2 = Integer.parseInt(st.nextToken());

			
			
	
			if((p1==x2&&q1==y2)||(x1==p2&&q1==y2)||(p1==x2&&y1==q2)||(x1==p2&&y1==q2)) {
				System.out.println("c");
			}
			
			else if((q1==y2)&&(x2<p1&&p2>x1)||(y1==q2)&&(x1<p2&&p1>x2)||(p1==x2)&&(y2<q1&&y1<q2)||(x1==p2)&&(y1<q2&&y2<q1)) {
				System.out.println("b");
			}


			else if((p1<x2)||(p2<x1)||(q1<y2)||(q2<y1)) {
				System.out.println("d");
			}
			
			else {
				System.out.println("a");


			}
		}
		
		
		
		
		
	}
}
