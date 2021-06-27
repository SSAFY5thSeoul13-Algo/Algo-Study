package week17.PRG_Lv2_카펫;

import java.util.Arrays;

public class Solution_PRG_Lv2_카펫 {
	public static void main(String[] args) {
		int brown = 8;
		int yellow = 1;
		System.out.println(Arrays.toString(solution(brown,yellow)));
	}
	
	public static int[] solution(int brown, int yellow) {
        int[] answer = new int[2];
        
        //카펫의 가로 세로 길이
        int sum = brown + yellow;
        
        for (int i = 3; i < sum; i++) { 
        	//1개 이상의 yellow 격자를 둘러싸고 있으므로 가로,세로 최소 3이상
        	for (int j = 3; j < sum; j++) {

				if(i*j==sum) { //가로 세로 경우의 수 i,j
					int cnt = 0;
					for (int x = 0; x < i; x++) {
						for (int y = 0; y < j; y++) {
							if(x==0||y==0||x==i-1||y==j-1) cnt++;
						}
					}
					//둘러 싸고 있는 격자의 개수가 brown과 같다면 가로,세로를 찾은 것
					if(cnt==brown) {
						answer[0]=Math.max(i, j); //더 긴게 가로길이
						answer[1]=Math.min(i, j); //더 작은게 세로길이
						
						return answer;
					}
					
				}
			}
		}

        return answer;
    }
}
