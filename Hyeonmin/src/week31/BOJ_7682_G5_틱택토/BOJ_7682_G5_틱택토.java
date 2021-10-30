package week31.BOJ_7682_G5_틱택토;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class BOJ_7682_G5_틱택토 {
	static List<String> list = new ArrayList<>();

	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringBuilder sb = new StringBuilder();
		
		makeCase(0, "");

		while(true) {
			String str = br.readLine();
			
			if("end".equals(str))	break;
			
			if(list.contains(str)) {
				sb.append("valid").append("\n");
			}
			else {
				sb.append("invalid").append("\n");
			}
		}
		
		System.out.println(sb.toString());
	}
	
	static void makeCase(int length, String str) {
		if(length == 9) {
			checkCase(str);
			return;
		}
		
		makeCase(length+1, str+".");
		makeCase(length+1, str+"X");
		makeCase(length+1, str+"O");
	}
	
	static void checkCase(String str) {
		char[][] map = new char[3][3];
		
		int xVerCnt = 0;
		int xHorCnt = 0;
		int xCnt = 0;
		
		int oVerCnt = 0;
		int oHorCnt = 0;
		int oCnt = 0;
		
		int pointCnt = 0;
		
		for (int i = 0; i < 9; i++) {
			char c = str.charAt(i);
			map[i/3][i%3] = c;
			
			if('.' == c)	pointCnt++;
			else if('X' == c)	xCnt++;
			else if('O' == c)	oCnt++;
		}
		
		// x - o 는 0아니면 1이어야 함
		if(xCnt - oCnt > 1) {
			return;
		}
		if(oCnt > xCnt)	return;
		
		//가로, 세로 빙고 확인
		for (int i = 0; i < 3; i++) {
			char c1 = map[i][0];
			char c2 = map[i][1];
			char c3 = map[i][2];
			
			if( c1 == 'X' && c2 == 'X' && c3 == 'X' ) {
				xVerCnt++;
			}
			if( c1 == 'O' && c2 == 'O' && c3 == 'O' ) {
				oVerCnt++;
			}
			
			char c4 = map[0][i];
			char c5 = map[1][i];
			char c6 = map[2][i];
			
			if( c4 == 'X' && c5 == 'X' && c6 == 'X' ) {
				xHorCnt++;
			}
			if( c4 == 'O' && c5 == 'O' && c6 == 'O' ) {
				oHorCnt++;
			}
			

		}
		
		//같은 방향의 빙고는 2개가 있을 수 없음
		if(oHorCnt > 1 || xHorCnt > 1 || oVerCnt > 1 || xVerCnt > 1)	return;
		
		//대각선 빙고 확인
		char c1 = map[0][0];
		char c2 = map[1][1];
		char c3 = map[2][2];
		char c5 = map[2][0];
		char c6 = map[0][2];
		
		int xBingoCnt = xHorCnt + xVerCnt;
		int oBingoCnt = oHorCnt + oVerCnt;
		
		if( c1 == 'X' && c2 == 'X' && c3 == 'X' )	xBingoCnt++;
		if( c1 == 'O' && c2 == 'O' && c3 == 'O' )	oBingoCnt++;
		
		if( c5 == 'X' && c2 == 'X' && c6 == 'X' )	xBingoCnt++;
		if( c5 == 'O' && c2 == 'O' && c6 == 'O' )	oBingoCnt++;
		
		// x == o 이면 x빙고가 없어야 함
		if(xCnt == oCnt && xBingoCnt > 0) {
			return;
		}
		
		// x > o 이면 o빙고가 없어야 함
		if(xCnt > oCnt && oBingoCnt > 0) {
			return;
		}
		
		//빈칸이 없으면 valid
		if(pointCnt == 0) {
			list.add(str);
		}
		
		//빈 칸이 있는데 빙고가 없으면 invalid
		if(xBingoCnt + oBingoCnt == 0)	return;
		
		//나머지는 모두 valid
		list.add(str);
	}

}
