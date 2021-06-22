package week1;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

//컨베이어 정보가 필요

public class BOJ_20055_S1_컨베이어벨트위의로봇 {
	static int N, K;
	//컨베이어 벨트
	static List<Node> list = new ArrayList<Node>();
	//head: 올라가는 곳, tail: 내려가는 곳
	static Node head, tail;
	//내구도가 다 닳은 곳 개수
	static int finish =0;
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		
		st = new StringTokenizer(br.readLine());
		
		//첫 번째 칸을 올라가는 곳으로 지정
		head = new Node(Integer.parseInt(st.nextToken()));
		
		Node temp = head;
		//각 칸의 내구도 입력
		for (int i = 1; i < 2*N; i++) {
			temp.next = new Node(Integer.parseInt(st.nextToken()));
			temp.next.prev = temp;
			temp = temp.next;
			//내려가는 곳을 tail로 설정
			if(i == N-1) {
				tail = temp;
			}
			//마지막 칸의 다음을 head로 설정
			if(i == 2*N-1) {
				temp.next = head;
				head.prev = temp;
			}
		}
		
		//단계
		int step = 0;
		//반복 실행
		do {
			//단계 1상승
			step++;
			
			//벨트가 한칸 회전한 후의 head와 tail 지정
			head = head.prev;
			tail = tail.prev;
			
			//내려가는 곳에 있는 로봇 제거
			tail.robot = false;
			
			//내려가는 곳에서 부터 역순으로 로봇의 이동
			temp = tail.prev;
			//내려가는 곳으로 이동하는 경우
			if(temp.robot == true && temp.next.robot == false && temp.next.durability > 0) {
				temp.robot = false;
				temp.next.durability--;
				//내구도 확인
				if(temp.next.durability == 0 ) {
					finish++;
				}
			}
			
			if(finish >= K)
				break;
			
			temp = temp.prev;
			
			//헤드를 제외한 나머지 부분들
			//현재 칸에 로봇이 존재 할 경우, 로봇이 이동할 위치에 로봇이 없고 내구도가 0보다 크면 로봇 이동
			while(temp!= head) {
				if(temp.robot == true && temp.next.robot == false && temp.next.durability > 0) {
					temp.robot = false;
					temp.next.robot = true;
					temp.next.durability--;
					
					if(temp.next.durability == 0 ) {
						finish++;
					}
				}
				
				if(finish >= K)
					break;
				//다음로 이동
				temp = temp.prev;
			}
			
			if(finish >= K)
				break;
			
			//올라가는 곳의 로봇 이동
			if(temp.robot == true && temp.next.robot == false && temp.next.durability > 0) {
				temp.robot = false;
				temp.next.robot = true;
				temp.next.durability--;
				
				if(temp.next.durability == 0 ) {
					finish++;
				}
			}
			
			if(finish >= K)
				break;
			
			//올라가는 곳에 로봇이 없고 내구도가 0보다 크면 로봇을 올림
			if(head.robot == false && head.durability > 0) {
				head.robot = true;
				head.durability--;
				if(head.durability ==0 ) {
					finish++;
				}
			}
			
		}while(finish < K);	//내구도 0인 칸의 개수가 K보다 작을동안 반복

		//출력
		System.out.println(step);
	}
	
	static class Node{
		int durability;	//내구도
		boolean robot;	//로봇 존재 여부
		Node next;		//다음 칸
		Node prev;		//이전 칸
		
		Node(int durability){
			this.durability = durability;
		}
	}
}