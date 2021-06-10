package week12;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_BJ_10836_G4_여왕벌 {
/*	
	  문제
	  크기가 M*M 벌집, 각 칸에 애벌레 1마리 첫날 아침 모든 애벌레들의 크기는 1이고, 이러한 과정을 N일 동안 반복 커지는 정도 ->
	  +0, +1, +2 중 하나
	  
	  1. 0행, 0열은 자신이 자라는 정도를 스스로 결정 입력으로 주어질 것 (왼쪽 제일 아래 칸에서 시작하여 위쪽으로 가면서 읽고, 제일
	  위쪽 칸에 도착하면 오른쪽으로 가면서 행의 끝까지 읽었다) 2. 나머지 애벌레들은 자신의 왼쪽(L), 왼쪽 위(D), 위쪽(U)의
	  애벌레들이 다 자란 다음, 그 날 가장 많이 자란 애벌레가 자란 만큼 자신도 자란다.
	  
	  
	  시뮬
	 
	주어진 조건에 맞게, 첫 날은 벌집(map)을 모두 1로 설정하였고
	그 후 N일동안
	0의개수, 1의개수, 2의개수를 입력받아서
	0열 맨 아래에서부터 시작해서 0,0에 도착하면 0행 맨 오른쪽까지 가면서
	입력받은 0,1,2의 수 만큼 애벌레들의 크기를 증가시켰습니다.
	그리고 N일동안 0행,0열의 애벌레들을 모두 증가시킨후,
	마지막날에 나머지(0행,0열을 제외한) 애벌레들의 크기를 설정해주었습니다
	이 때 나머지 애벌레의 크기는 매일 좌,좌상,상의 애벌레의 성장 max값만큼 자라므로
	마지막날 좌,좌상,상중의 가장 큰 값과 같으므로 max값을 찾아 설정했습니다.


	- 어려웠던 점
	처음에는 tmp배열을 만들어 
	N일동안 계속 0행,0열을 제외한 나머지 애벌레들도 같이 성장시켜주었는데
	이렇게 했을 때에는 시간초과가 발생하여 마지막날 한번에 크기를 설정해주는것으로 변경했습니다
	또한 시간초과를 해결하기 위해 StringBuilder를 사용했습니다.


	- 결과
	320944	4468
*/
	static int M, N, map[][], input[][];

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		M = Integer.parseInt(st.nextToken()); // 격자칸 크기
		N = Integer.parseInt(st.nextToken()); // 날짜 수

		map = new int[M][M]; // 벌집 map

		for (int i = 0; i < M; i++) {
			for (int j = 0; j < M; j++) {
				map[i][j] = 1;
			}
		}
		// 첫날은 모두 1

		for (int i = 0; i < N; i++) {

			int zero = 0;
			int one = 0;
			int two = 0;

			st = new StringTokenizer(br.readLine()); // 애벌레들이 자라는 정도 입력받음

			zero = Integer.parseInt(st.nextToken()); //0의개수
			one = Integer.parseInt(st.nextToken()); //1의개수
			two = Integer.parseInt(st.nextToken()); //2의개수

			for (int r = M - 1; r >= 0; r--) {
				if (zero != 0) {
					// map[r][0] += 0;
					zero--;
				} else if (one != 0) {
					map[r][0] += 1;
					one--;
				} else {
					map[r][0] += 2;
					two--;
				}
			}
			// 0열

			for (int c = 1; c < M; c++) {
				if (zero != 0) {
					// map[0][c] += 0;
					zero--;
				} else if (one != 0) {
					map[0][c] += 1;
					one--;
				} else {
					map[0][c] += 2;
					two--;
				}
			}
			// 0행

		}

		for (int c = 1; c < M; c++) {
			for (int r = 1; r < M; r++) {
				int max = Math.max(map[r - 1][c], Math.max(map[r][c - 1], map[r - 1][c - 1]));
				map[r][c] = max;
			}
		} // 나머지 (좌, 좌상, 상 중에서 가장 큰 값으로 바꾼다)

		print();

	}

	private static void print() {
		StringBuilder sb = new StringBuilder();

		for (int r = 0; r < M; r++) {
			for (int c = 0; c < M; c++) {
				sb.append(map[r][c] + " ");
			}
			sb.append("\n");
		}

		System.out.println(sb);
	}
}
