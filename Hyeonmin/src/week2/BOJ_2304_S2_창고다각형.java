package week2;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

public class BOJ_2304_S2_창고다각형 {
	//기둥의 개수
	static int N;
	//기둥 정보 리스트
	static List<Pillar> list = new ArrayList<Pillar>();
	//가장 높이가 높은 기둥
	static int biggest = 0;
	//창고의 면적
	static int sum = 0;
	
	public static void main(String[] args) throws Exception{
		BufferedReader br =new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		//기둥 개수 입력
		N = Integer.parseInt(br.readLine());
		
		//기둥 정보 입력
		for (int i = 0; i < N; i++) {
			st =  new StringTokenizer(br.readLine());
			int pos = Integer.parseInt(st.nextToken());
			int h = Integer.parseInt(st.nextToken());
			list.add(new Pillar(pos, h));
			biggest = Math.max(biggest, h);
		}
		
		//리스트에 있는 기둥을 위치로 정렬
		Collections.sort(list, (o1, o2) -> o1.position - o2.position);
		
		//첫 기둥의 위치와 높이
		int height = list.get(0).height;
		int prev = list.get(0).position;
		
		//앞부분 부터의 마지막 가장 큰 기둥까지 이르는 부분의 넓이 더하기
		for (int i = 1; i < N; i++) {
			//가장 높은 기둥이 2개 이상인 경우
			if(height == biggest) {
				//가장 높은 2개의 기둥 사이의 넓이 더함
				if(list.get(i).height == biggest) {
					sum += (list.get(i).position - prev) * height;
					prev = list.get(i).position;
				}
				continue;
			}
			//다음 기둥이 높이가 더 높은 경우
			if(height < list.get(i).height) {
				//그 기둥 이전까지의 넓이를 더함
				sum += (list.get(i).position - prev) * height;
				prev = list.get(i).position;
				height = list.get(i).height;
			}
		}
		
		//마지막 기둥의 위치와 높이
		height = list.get(N-1).height;
		prev = list.get(N-1).position;
		//뒤에서부터 마지막 가장 큰 기둥까지 이르는 부분의 넓이 더하기
		for (int i = N-2; i >= 0; i--) {
			//다음 기둥이 높이가 더 높은 경우
			if(height < list.get(i).height) {
				sum += Math.abs(list.get(i).position - prev) * height;
				prev = list.get(i).position;
				height = list.get(i).height;
			}
			
			//가장 큰 기둥을 만나면 중단
			if(list.get(i).height == biggest) {
				break;
			}
		}
		
		//마지막 가장 큰 기둥이 있는곳의 높이만큼 면적에 더함
		sum += biggest;
		
		//출력
		System.out.println(sum);
	}
	
	//기둥 정보를 저장할 클래스
	static class Pillar{
		//기둥의 위치
		int position;
		//기둥의 높이
		int height;
		
		//생성자
		Pillar(int position, int height){
			this.position = position;
			this.height = height;
		}
	}
}