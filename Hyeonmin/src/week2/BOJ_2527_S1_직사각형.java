package week2;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_2527_S1_직사각형 {
	
	//왼쪽 아래를 0,0으로 기준
	/* 0: 왼쪽 아래 좌표, 1: 오른쪽 위 좌표 */
	//첫번째 직사각형의 좌표
	static int[] firstX = new int[2];
	static int[] firstY = new int[2];
	//두번째 직사각형의 좌표
	static int[] secondX = new int[2];
	static int[] secondY = new int[2];
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		//4번 반복
		for (int i = 0; i < 4; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			
			//첫번째 직사각형 왼 아래 좌표
			firstX[0] = Integer.parseInt(st.nextToken());
			firstY[0] = Integer.parseInt(st.nextToken());
			//첫번째 직사각형 오른 위 좌표
			firstX[1] = Integer.parseInt(st.nextToken());
			firstY[1] = Integer.parseInt(st.nextToken());
			
			//두번째 직사각형 왼 아래 좌표
			secondX[0] = Integer.parseInt(st.nextToken());
			secondY[0] = Integer.parseInt(st.nextToken());
			//두번째 직사각형 오른 위 좌표
			secondX[1] = Integer.parseInt(st.nextToken());
			secondY[1] = Integer.parseInt(st.nextToken());
			
			//선분으로 만나는 경우
			if(isB())
				System.out.println("b");
			//한 점에서 만나는 경우
			else if(isC())
				System.out.println("c");
			//만나지 않는 경우
			else if(isD())
				System.out.println("d");
			//직사각형으로 만나는 경우
			else
				System.out.println("a");
			
		}

	}
	//한 선분이 만나는 경우
	static boolean isB() {
		/*첫번째 직사각형 기준*/
		//오른쪽 선분이 만나는 경우
		if(secondX[0] == firstX[1] && secondY[0] < firstY[1] && secondY[1] > firstY[0])
			return true;
		//위쪽 선분이 만나는 경우
		if(secondX[0] < firstX[1] && secondY[0] == firstY[1] && secondX[1] > firstX[0])
			return true;
		//왼쪽 선분이 만나는 경우
		if(secondX[1] == firstX[0] && secondY[0] < firstY[1] && secondY[1] > firstY[0])
			return true;
		//아래쪽 선분이 만나는 경우
		if(secondX[1] > firstX[0] && secondY[1] == firstY[0] && secondX[0] < firstX[1])
			return true;
		
		//해당하지 않는 경우
		return false;
	}
	
	//한 점에서 만나는 경우
	static boolean isC() {
		/*첫번째 직사각형 기준*/
		//오른쪽 위 점에서 만나는 경우
		if(secondX[1] == firstX[0] && secondY[1] == firstY[0])
			return true;
		//오른쪽 아래 점에서 만나는 경우
		if(secondX[1] == firstX[0] && secondY[0] == firstY[1])
			return true;
		//왼쪽 위 점에서 만나는 경우
		if(secondX[0] == firstX[1] && secondY[1] == firstY[0])
			return true;
		//왼쪽 아래 점에서 만나는 경우
		if(secondX[0] == firstX[1] && secondY[0] == firstY[1])
			return true;
		
		//해당하지 않는 경우
		return false;
	}
	
	//만나지 않는 경우
	static boolean isD() {
		if(firstX[0] > secondX[1] || firstY[0] > secondY[1] || firstX[1] < secondX[0] || firstY[1] < secondY[0]) {
			return true;
		}
		
		//해당하지 않는 경우
		return false;
	}
}