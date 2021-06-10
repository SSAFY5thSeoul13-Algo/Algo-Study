package week10;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_14719_G5_빗물 {
	//H: 높이, W: 너비, sum: 빗물이 고인 영역 크기, max: 가장 높은 위치중 가장 오른쪽 index
	static int H, W, sum, max;
	//각 위치에 있는 블록 높이
	static int map[];
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		H = Integer.parseInt(st.nextToken());
		W = Integer.parseInt(st.nextToken());
		
		map = new int[W];
		
		st = new StringTokenizer(br.readLine());
		
		for (int i = 0; i < W; i++) {
			map[i] = Integer.parseInt(st.nextToken()); 
		}
		
		int idx = 0;
		
		//0이 아닌 가장 좌측 위치의 인덱스 구하기
		for (int i = 0; i < W; i++) {
			if(map[i] > 0) {
				idx = i;
				break;
			}
		}
		
		//각 영역별 고인 빗물의 크기를 저장할 변수
		int temp = 0;
		
		//왼쪽에서 오른쪽으로 영역을 탐색
		for (int i = idx+1; i < W; i++) {
			//기준위치와 높이가 같거나 더 큰 위치가 나타난 경우
			if(map[idx] <= map[i]) {
				//지금까지 구한 빗물이 고일 수 있는 영역 크기를 더함
				sum += temp;
				//크기 초기화
				temp = 0;
				//기준 위치를 변경
				idx = i;
				//항상 더 크거나 높이가 같은 곳의 인덱스를 저장해 가장 큰 것 중에서 우측에 있는 위치의 인덱스 저장
				max = i;
			}
			//기준 위치보다 낮은 경우 높이 차이만큼 고이는 빗물의 크기를 temp에 더함
			else {
				temp += map[idx] - map[i];
			}
		}
		
		//우측부터 탐색을 위해 변수를 초기화
		idx = -1;
		temp = 0;
		
		//0이 아닌 가장 우측 위치의 인덱스 구하기. 가장 우측이 max인 경우 if문에 들어가지 않아서 idx가 -1이 된다
		for (int i = W-1; i > max; i--) {
			if(map[i] > 0) {
				idx = i;
				break;
			}
		}
		
		//우측부터 왼쪽으로 영역 탐색. 이 때 max 인덱스 까지만 탐색 
		for (int i = idx-1; i >= max; i--) {
			//기준위치와 높이가 같거나 더 큰 위치가 나타난 경우
			if(map[idx] <= map[i]) {
				//지금까지 구한 빗물이 고일 수 있는 영역 크기를 더함
				sum += temp;
				//크기 초기화
				temp = 0;
				//기준 위치를 변경
				idx = i;
			}
			//기준 위치보다 낮은 경우 높이 차이만큼 고이는 빗물의 크기를 temp에 더함
			else {
				temp += map[idx] - map[i];
			}
		}
		
		
		System.out.println(sum);
	}
}
