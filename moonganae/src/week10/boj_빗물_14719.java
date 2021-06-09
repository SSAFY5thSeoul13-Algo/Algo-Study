package week10;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class boj_빗물_14719 {

	/* 블록세계의 가로, 세로길이 */
	static int H, W;
	/* 블록의 쌓인 높이 */
	static int[] height;
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer st = new StringTokenizer(br.readLine());
		
		H = Integer.parseInt(st.nextToken());
		W = Integer.parseInt(st.nextToken());
		height = new int[W];
		
		st = new StringTokenizer(br.readLine());
		
		/* 블록높이 입력 */
		for(int i=0; i<W; i++) {
			height[i] = Integer.parseInt(st.nextToken());
		}
		
		int ans = 0;
		/* 1번 블록부터 W-1번 블록까지*/
		for(int i=0; i<W-1; i++) {
			/* h: 현재 기준블록의 높이 */
			int h = height[i];
			/* 현재 블록의 높이부터 1이 될때까지 */
			for(int j=h; j>0; j--) {
			
				/* 가로를 확인하면서 고일수 있는 공간 확인 */
				int width=1;
				while(true) {
					int x = i + width;
					/* 공간밖으로 나갈 수 있음 == 고일수 없음 */
					if(x>=W) {
						width = 1;
						break;
					}
					/* 옆에가 높이가 같거나 크면 고일수있는 공간 */
					if(j <= height[x])break;
					
					/* 아니면 계속 가로공간 확인 */
					width++;
				}
				ans += width-1;
			}
		}
		
		System.out.println(ans);
	}

}
