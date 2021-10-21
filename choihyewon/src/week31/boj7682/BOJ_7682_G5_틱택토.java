package week31.boj7682;

import java.util.*;
import java.io.*;
public class BOJ_7682_G5_틱택토 {
	static char[][] arr;
	public static void main(String[] args)throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		while(true) {
			String str = br.readLine();
			
			if(str.equals("end")) {
				break;
			}
			
			arr = new char[3][3];
			for(int i=0; i<9; i++) {
				arr[i/3][i%3] = str.charAt(i);
			}
			
			if(tictactoc()) {
				System.out.println("valid");
			}else {
				System.out.println("invalid");
			}
			
		}

	}
	private static boolean tictactoc() {
		int xCnt = 0;
		int oCnt = 0;
		int sum = 0;
		
		for(int i=0; i<3; i++) {
			for(int j=0; j<3; j++) {
				if(arr[i][j]=='X') {
					xCnt++;
				}else if(arr[i][j] == 'O') {
					oCnt++;
				}
			}
		}
		sum = xCnt + oCnt;
		
		if(xCnt<oCnt) {
			return false;
		}
		
		// 게임이 끝난 경우 X부터 시작하므로 X가 5개, O가 4개 여야한다.
		// 이 경우 마지막에 X로 끝나야 성립되므로 X가 이기거나 비겨야한다.
		if(sum==9 && xCnt==oCnt+1) {
			if(isWin('X') && !isWin('O')) {
				return true;
			}else if(!isWin('X') && !isWin('O')) {
				return true;
			}else {
				return false;
			}
		}else {
			// 두개의 개수가 같은 경우 
			if(xCnt == oCnt) {
				if(isWin('O') && !isWin('X')) {
					return true;
				}else {
					return false;
				}
			}
			// X가 O보다 1개 더 많은 경우 
			else if(xCnt==oCnt+1) {
				if(!isWin('O') && isWin('X')) {
					return true;
				}else {
					return false;
				}
			}
			
		}
		
		return false;
		
	}
	// 이겼으면 true return 
	private static boolean isWin(char c) {
		// 가로 빙고 
		for(int i=0; i<3; i++) {
			if(arr[i][0] == c && arr[i][1] == c && arr[i][2] == c) {
				return true;
			}
		}
		
		// 세로 빙고 
		for(int i=0; i<3; i++) {
			if(arr[0][i] == c && arr[1][i] == c && arr[2][i] == c) {
				return true;
			}
		}
		
		// 대각선 빙고 
		if(arr[0][0] == c && arr[1][1] == c && arr[2][2] == c) {
			return true;
		}
		if(arr[0][2] == c && arr[1][1] == c && arr[2][0] == c) {
			return true;
		}

		
		return false;
	}

}
