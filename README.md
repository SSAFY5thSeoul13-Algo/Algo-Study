

#  💡 알고리즘 스터디 💡

SSAFY 5기 서울 13반 알고리즘 스터디 기록

 - [ ] 서울 13반 4조 문성욱
 - [ ] 서울 13반 4조 조다운
 - [ ] 서울 13반 4조 조현민
 - [ ] 서울 13반 4조 최혜원
<br><br>

## 📌 Rule
각자 문제를 선정하여 매주 4~12문제를 풉니다.
매주 월요일 카카오톡 공지방에 댓글로 문제를 등록합니다.
* ❗❗ 문제풀이 마감 : 매주 일요일 20:00 까지
* ❕❕ 리뷰 마감 : 매주 월요일 23:59 까지
<br><br>

## 📌 Convention
###  1️⃣ Code Convention
각 코드 별 목적을 주석으로 작성합니다.
변수와 함수 이름 또한 역할을 알 수 있도록 간단한 주석을 덧붙입니다.

<br>

### 2️⃣ Project Convention

각 멤버별 프로젝트 구조는 다음과 같습니다
**프로젝트이름/week번호/플랫폼_문제번호_레벨_문제이름/...**

    jodawoooon/week15/BOJ_1051_S3_숫자정사각형/...

<br>

### 3️⃣ Commit Convention
한번에 모든 파일을 add하지 않고 type별로 분리하여 commit 합니다.

    docs : README.md 등 문서 작성 및 수정
    code : 코드 작성
    fix : 코드 수정
    add : 기존에 푼 문제에 대한 추가
    remove : 코드 및 문서 삭제
    merge : pr(pull request)을 통해 자신의 repo에서 원본 repo로 merge하기
  <br>

적용 예시 ::
1. BOJ의 1051번 숫자 정사각형 (silver3) 문제를 풀었다면
해당 코드를 하나의 commit으로 분리합니다.  
이 때의 commit message는 다음과 같이 통일합니다
		
		 git commit -m "code : BOJ 1051 S3 숫자정사각형"

	해당 코드를 수정할 때의 commit message는 다음과 같이 통일합니다.
		
		 git commit -m "fix : BOJ 1051 S3 숫자정사각형"

2.  코드에 대한 설명을 작성하고
해당 문서를 하나의 commit으로 분리합니다.  
이 때의 commit message는 다음과 같습니다.
		
		 git commit -m "docs : BOJ 1051 S3 숫자정사각형"

3. main README.md 파일을 수정할 때의 commit message는 다음과 같습니다.
		
		 git commit -m "docs : main README update"

5. 파일을 삭제할 경우 commit message는 다음과 같습니다
		
		 git commit -m "remove : 삭제파일"
		
<br>

### 4️⃣ Review Convention
1. Pull Request의 제목은 다음과 같이 통일합니다.
**이름 : 문제플랫폼 문제번호 문제등급 문제제목** 
		
		 DAUN JO : BOJ 1051 S3 숫자정사각형
		
2. Pull Request의 comment에는 본인이 작성한 README.md의 내용을 추가합니다. 

3. 문제에 해당하는 유형을 선택하여 PR에 label을 attach하고,   
 자신의 PR의 assignee에 자신을 추가 후 문제풀이 week에 해당하는 Milestones을 선택합니다.

4. 기존에 PR을 작성 후 새로운 문제를 풀었을 경우, 새로운 문제에 대한 commit을 하기 전 다음 과정을 수행합니다.

	- ❓ 코드리뷰가 완료 되었을 경우  
		자신의 PR에서 merge 버튼을 눌러 merge 합니다. 
		
	- ❓ 리뷰 완료 전 새로운 문제를 풀 경우
		1. 자신의 local에서 새로 푼 문제에 대한 branch를 생성합니다.  
		이 때 branch의 이름을 **문제플랫폼-문제번호**과 같이 생성하는 것을 권장합니다.
		
			    boj-1051
		
		2. 새로운 문제에 대한 code와 README.md에 대한 commit을 추가 후 push합니다.   
		이 때의 commit message는 2️⃣ Commit Convention에서 언급한 규칙에 맞게 설정합니다.
		3. 이 때 반드시 (a)에서 생성한 branch로 push 되는지 확인합니다.
		4. 본인 계정의 fork된 repo에서 Pull Request을 작성할 때,   
		코드가 push된 브랜치(a에서 생성한 jodawoooon/boj-1051)에서   
		organization repo의 main 브랜치로 Pull Request를 보냅니다.


<br><br>

## 📌 Solved Problems
### 🚩 week 1
| Type | 문제 | 제목 | 유형 | lank |
| -- |--| -- |--|--|
| BOJ | 13460 | [구슬 탈출 2](https://www.acmicpc.net/problem/13460) | BFS | gold2 |
| BOJ | 20055 | [컨베이어 벨트 위의 로봇](https://www.acmicpc.net/problem/20055) | 시뮬 | silver1 |
| BOJ | 17142 | [연구소 3](https://www.acmicpc.net/problem/17142) | BFS | gold4 |
| BOJ | 14891 | [톱니바퀴](https://www.acmicpc.net/problem/14891) | 시뮬| silver1 |



### 🚩 week 2
| Type | 문제 | 제목 | 유형 | lank |
| -- |--| -- |--|--|
| BOJ | 2527 | [직사각형](https://www.acmicpc.net/problem/2527) | 기하학 | silver1 |
| BOJ | 2304 | [창고 다각형](https://www.acmicpc.net/problem/2304) | 브루트포스| silver2 |
| BOJ | 2116 | [주사위 쌓기](https://www.acmicpc.net/problem/2116) | 브루트포스 | gold5 |
| BOJ | 2564 | [경비원](https://www.acmicpc.net/problem/2564) | 시뮬| silver1 |




### 🚩 week 3
| Type | 문제 | 제목 | 유형 | lank |
| -- |--| -- |--|--|
| BOJ | 17281 | [⚾](https://www.acmicpc.net/problem/17281) | 브루트포스| gold4 |
| BOJ | 16953 | [A->B](https://www.acmicpc.net/problem/16953) | DFS | silver1 |
| BOJ | 14503 | [로봇청소기](https://www.acmicpc.net/problem/14503) | 시뮬 | gold5 |
| BOJ | 14888 | [연산자 끼워넣기](https://www.acmicpc.net/problem/14888) | DFS | silver1 |



### 🚩  week 4
| Type | 문제 | 제목 | 유형 | lank |
| -- |--| -- |--|--|
| BOJ | 16637 | [괄호 추가하기](https://www.acmicpc.net/problem/16637) | DFS| gold3 |
| BOJ | 17070 | [파이프 옮기기 1](https://www.acmicpc.net/problem/17070) | DFS | gold5 |
| BOJ | 17471 | [게리맨더링](https://www.acmicpc.net/problem/17471) | BFS| gold5 |
| BOJ | 17472 | [다리 만들기 2](https://www.acmicpc.net/problem/17472) | BFS| gold2 |


### 🚩  week 5
| Type | 문제 | 제목 | 유형 | lank |
| -- |--| -- |--|--|
| BOJ | 14889 | [스타트와 링크](https://www.acmicpc.net/problem/14889) | comb | silver3 |
| BOJ | 15685 | [드래곤 커브](https://www.acmicpc.net/problem/15685) | 시뮬| gold4 |
| BOJ | 16234 | [인구 이동](https://www.acmicpc.net/problem/16234) | BFS| gold5 |
| BOJ | 20056 | [마법사 상어와 파이어볼](https://www.acmicpc.net/problem/20056) | 시뮬| gold5 |


### 🚩 week 6
| Type | 문제 | 제목 | 유형 | lank |
| -- |--| -- |--|--|
| BOJ | 12763 | [지각하면 안 돼](https://www.acmicpc.net/problem/12763) | 플로이드 와샬 | gold2 |
| BOJ | 6497 | [전력난](https://www.acmicpc.net/problem/6497) | 다익스트라 | gold4 |
| BOJ | 1238 | [파티](https://www.acmicpc.net/problem/1238) |크루스칼 | gold3 |
| BOJ | 9694 | [무엇을 아느냐가 아니라 누구를 아느냐가 문제다](https://www.acmicpc.net/problem/9694) | 다익스트라| gold4 |


### 🚩 week 7
| Type | 문제 | 제목 | 유형 | lank |
| -- |--| -- |--|--|
| BOJ | 17071 | [숨바꼭질 5](https://www.acmicpc.net/problem/17071) | BFS | gold1 |
| BOJ | 1242 | [소풍](https://www.acmicpc.net/problem/1242) | 수학 | gold2 |
| BOJ | 12904 | [A와 B](https://www.acmicpc.net/problem/12904) | 문자열 | gold5 |
| BOJ | 16959 | [체스판 여행 1](https://www.acmicpc.net/problem/16959) | BFS | gold1 |


### 🚩 week 8
| Type | 문제 | 제목 | 유형 | lank |
| -- |--| -- |--|--|
| BOJ | 16197 | [두 동전](https://www.acmicpc.net/problem/16197) | BFS | gold4 |
| BOJ | 7579 | [앱](https://www.acmicpc.net/problem/7579) | DP | gold3 |
| BOJ | 1062 | [가르침](https://www.acmicpc.net/problem/1062) | 문자열 | gold4 |
| BOJ | 2156 | [포도주 시식](https://www.acmicpc.net/problem/2156) | DP | silver1 |



### 🚩 week 9
| Type | 문제 | 제목 | 유형 | lank |
| -- |--| -- |--|--|
| BOJ | 2234 | [성곽](https://www.acmicpc.net/problem/2234) | BFS | gold4 |
| BOJ | 14938 | [서강그라운드](https://www.acmicpc.net/problem/14938) | 그래프 | gold4 |
| BOJ | 17822 | [원판 돌리기](https://www.acmicpc.net/problem/17822) | 시뮬 | gold3 |
| BOJ | 2565 | [전깃줄](https://www.acmicpc.net/problem/2565) | DP | gold3 |

### 🚩 week 10
| Type | 문제 | 제목 | 유형 | lank |
| -- |--| -- |--|--|
| BOJ | 1030 | [프렉탈 평면](https://www.acmicpc.net/problem/1030) | 분할 정복 | gold3 |
| BOJ | 8972 | [미친 아두이노](https://www.acmicpc.net/problem/8972) | 시뮬 | gold4 |
| BOJ | 11967 | [불켜기](https://www.acmicpc.net/problem/11967) | 그래프 | gold4 |
| BOJ | 14719 | [빗물](https://www.acmicpc.net/problem/14719) | 구현 | gold5 |

### 🚩 week 11
| Type | 문제 | 제목 | 유형 | lank |
| -- |--| -- |--|--|
| BOJ | 1915 | [가장 큰 정사각형](https://www.acmicpc.net/problem/1915) | dp | silver1 |
| BOJ | 13335 | [트럭](https://www.acmicpc.net/problem/13335) | 시뮬 | silver1 |
| BOJ | 3709 | [레이저빔은 어디로](https://www.acmicpc.net/problem/1915) | dfs | gold4 |
| BOJ | 6087 | [레이저통신](https://www.acmicpc.net/problem/6087) | bfs | gold4 |

### 🚩 week 12
| Type | 문제 | 제목 | 유형 | lank |
| -- |--| -- |--|--|
| BOJ | 12608 | [상어 초등학교](https://www.acmicpc.net/problem/12608) | 시뮬 | silver1 |
| BOJ | 10836 | [여왕벌](https://www.acmicpc.net/problem/10836) | 시뮬 | gold4 |
| BOJ | 1520 | [내리막길](https://www.acmicpc.net/problem/1520) | dp | gold4 |
| BOJ | 11559 | [Puyo Puyo](https://www.acmicpc.net/problem/11559) | 시뮬 | gold5 |

### 🚩 week 13
| Type | 문제 | 제목 | 유형 | lank |
| -- |--| -- |--|--|
| BOJ | 14499 | [주사위 굴리기](https://www.acmicpc.net/problem/14499) | 시뮬 | gold5 |
| BOJ | 15787 | [기차가 어둠을 헤치고 은하수를](https://www.acmicpc.net/problem/15787) | 비트마스킹 | silver2 |
| BOJ | 19238 | [스타트택시](https://www.acmicpc.net/problem/19238) | bfs | gold4 |
| BOJ | 2878 | [캔디캔디](https://www.acmicpc.net/problem/2878) | 그리디 | gold2 |

### 🚩 week 14
| Type | 문제 | 제목 | 유형 | lank |
| -- |--| -- |--|--|
| BOJ | 5014 | [스타트링크](https://www.acmicpc.net/problem/5014) | bfs | gold5 |
| BOJ | 9935 | [문자열 폭발](https://www.acmicpc.net/problem/9935) | 문자열 | gold4 |


### 🚩 week 15 
| Type | 문제 | 제목 | 유형 | lank |
| -- |--| -- |--|--|
| BOJ | 1051 | [숫자 정사각형](https://www.acmicpc.net/problem/1051) | 구현 | silver3 |
| BOJ | 1292 | [쉽게 푸는 문제](https://www.acmicpc.net/problem/1292) | 구현 | silver5 |
| BOJ | 1934 | [최소공배수](https://www.acmicpc.net/problem/1934) | 수학 | silver5 |
| BOJ | 2960 | [에라토스테네스의 체](https://www.acmicpc.net/problem/2960) | 수학 | silver4 |
| BOJ | 3085 | [사탕 게임](https://www.acmicpc.net/problem/3085) | 구현 | silver4 |
| BOJ | 10773 | [제로](https://www.acmicpc.net/problem/10773) | 구현 | silver4 |
| BOJ | 10870 | [피보나치 수 5](https://www.acmicpc.net/problem/10870) | 수학 | bronze2 |
| BOJ | 11068 | [회문인 수](https://www.acmicpc.net/problem/11068) | 수학 | silver5 |
| BOJ | 7568 | [덩치](https://www.acmicpc.net/problem/7568) | 구현 | silver5 |
| BOJ | 1157 | [단어 공부](https://www.acmicpc.net/problem/1157) | 구현 | bronze1 |
| BOJ | 2869 | [달팽이는 올라가고 싶다](https://www.acmicpc.net/problem/2869) | 수학 | bronze1 |
| BOJ | 14646 | [욱제는 결정장애야!!](https://www.acmicpc.net/problem/14646) | 구현 | silver4 |


### 🚩 week 16
| Type | 문제 | 제목 | 유형 | lank |
| -- |--| -- |--|--|
| BOJ | 10546 | [배부른 마라토너](https://www.acmicpc.net/problem/10546) | 자료구조 | silver4 |
| BOJ | 1302 | [베스트셀러](https://www.acmicpc.net/problem/1302) | 자료구조 | silver4 |
| BOJ | 1927 | [최소 힙](https://www.acmicpc.net/problem/1927) | 자료구조 | silver1 |
| BOJ | 2164 | [카드2](https://www.acmicpc.net/problem/2164) | 자료구조 | silver4 |
| BOJ | 2910 | [빈도 정렬](https://www.acmicpc.net/problem/2910) | 자료구조 | silver3 |
| BOJ | 10799 | [쇠막대기](https://www.acmicpc.net/problem/10799) | 자료구조 | silver3 |
| BOJ | 17479 | [정식당](https://www.acmicpc.net/problem/17479) | 자료구조 | silver3 |
| BOJ | 20301 | [반전 요세푸스](https://www.acmicpc.net/problem/20301) | 자료구조 | silver4 |
| BOJ | 1417 | [국회의원 선거](https://www.acmicpc.net/problem/1417) | 자료구조 | silver5 |
| BOJ | 1764 | [듣보잡](https://www.acmicpc.net/problem/1764) | 자료구조 | silver4 |
| BOJ | 7785 | [회사에 있는 사람](https://www.acmicpc.net/problem/7785) | 자료구조 | silver5 |
| BOJ | 12019 | [Yonsei TOTO](https://www.acmicpc.net/problem/12018) | 자료구조 | silver3 |


### 🚩 week 17
| Type | 문제 |  유형 | lank |
| -- | -- |--|--|
| Programmers | [K번째수](https://programmers.co.kr/learn/courses/30/lessons/42748) | 정렬 | level1 |
| Programmers | [가장 큰 수](https://programmers.co.kr/learn/courses/30/lessons/42746) | 정렬 | level2 |
| Programmers | [H-Index](https://programmers.co.kr/learn/courses/30/lessons/42747) | 정렬 | level2 |
| Programmers | [모의고사](https://programmers.co.kr/learn/courses/30/lessons/42840) | 완탐 | level1 |
| Programmers | [소수찾기](https://programmers.co.kr/learn/courses/30/lessons/42839) | 완탐 | level2 |
| Programmers | [카펫](https://programmers.co.kr/learn/courses/30/lessons/42842) | 완탐 | level2 |


### 🚩 week 18
| Type | 문제 |  유형 | lank |
| -- | -- |--|--|
| Programmers | [타겟 넘버](https://programmers.co.kr/learn/courses/30/lessons/43165) | DFS/BFS | level2 |
| Programmers | [네트워크](https://programmers.co.kr/learn/courses/30/lessons/43162) | DFS/BFS | level3 |
| Programmers | [단어 변환](https://programmers.co.kr/learn/courses/30/lessons/43163) | DFS/BFS | level3 |
| Programmers | [여행경로](https://programmers.co.kr/learn/courses/30/lessons/43164) | DFS/BFS | level3 |
| Programmers | [입국심사](https://programmers.co.kr/learn/courses/30/lessons/43238) | 이분탐색 | level3 |
| Programmers | [징검다리](https://programmers.co.kr/learn/courses/30/lessons/43236) | 이분탐색 | level4 |

### 🚩 week 19
| Type | 문제 |  유형 | lank |
| -- | -- |--|--|
| Programmers | [가장 먼 노드](https://programmers.co.kr/learn/courses/30/lessons/49189) | 그래프 | level3 |
| Programmers | [순위](https://programmers.co.kr/learn/courses/30/lessons/49191) | 그래프 | level3 |
| Programmers | [방의 개수](https://programmers.co.kr/learn/courses/30/lessons/49190) | 그래프 | level5 |
| Programmers | [더 맵게](https://programmers.co.kr/learn/courses/30/lessons/42626) | 힙 | level2 |
| Programmers | [디스크 컨트롤러](https://programmers.co.kr/learn/courses/30/lessons/42627) | 힙 | level3 |
| Programmers | [이중우선순위큐](https://programmers.co.kr/learn/courses/30/lessons/42628) | 힙 | level3 |

### 🚩 week 20
| Type | 문제 |  유형 | lank |
| -- | -- |--|--|
| Programmers | [완주하지 못한 선수](https://programmers.co.kr/learn/courses/30/lessons/42576) | 해시 | level1 |
| Programmers | [전화번호 목록](https://programmers.co.kr/learn/courses/30/lessons/42577) | 해시 | level2 |
| Programmers | [위장](https://programmers.co.kr/learn/courses/30/lessons/42578) | 해시 | level2 |
| Programmers | [베스트앨범](https://programmers.co.kr/learn/courses/30/lessons/42579) | 해시 | level3 |
| Programmers | [N 으로표현](https://programmers.co.kr/learn/courses/30/lessons/42895) | DP | level3 |
| Programmers | [정수 삼각형](https://programmers.co.kr/learn/courses/30/lessons/43105) | DP | level3 |

### 🚩 week 21
| Type | 문제 |  유형 | lank |
| -- | -- |--|--|
| Programmers | [기능개발](https://programmers.co.kr/learn/courses/30/lessons/42586) | 스택/큐 | level2 |
| Programmers | [프린터](https://programmers.co.kr/learn/courses/30/lessons/42587) | 스택/큐 | level2 |
| Programmers | [다리를 지나는 트럭](https://programmers.co.kr/learn/courses/30/lessons/42583) | 스택/큐 | level2 |
| Programmers | [주식가격](https://programmers.co.kr/learn/courses/30/lessons/42584) | 스택/큐 | level2 |
| Programmers | [등굣길](https://programmers.co.kr/learn/courses/30/lessons/42898) | DP | level3 |
| Programmers | [도둑질](https://programmers.co.kr/learn/courses/30/lessons/42897) | DP | level4 |

### 🚩 week 22
| Type | 문제 |  유형 | lank |
| -- | -- |--|--|
| BOJ | [랜선 자르기](https://www.acmicpc.net/problem/1654) | 이분 탐색 | silver3 |
| BOJ | [나무 자르기](https://www.acmicpc.net/problem/2805) | 이분 탐색 | silver3 |
| BOJ | [가장 긴 증가하는 부분 수열 2](https://www.acmicpc.net/problem/12015) | 이분 탐색 | gold2 |
| BOJ | [공유기 설치](https://www.acmicpc.net/problem/2110) | 이분 탐색 | silver1 |

### 🚩 week 23
| Type | 문제 |  유형 | lank |
| -- | -- |--|--|
| BOJ | [트리](https://www.acmicpc.net/problem/1068) | 트리 | gold5 |
| BOJ | [전화번호 목록](https://www.acmicpc.net/problem/5052) | 트리 | gold4 |
| BOJ | [이진 검색 트리](https://www.acmicpc.net/problem/5639) | 트리 | silver1 |
| BOJ | [사촌](https://www.acmicpc.net/problem/9489) | 트리 | gold4 |

### 🚩 week 24
| Type | 문제 |  유형 | lank |
| -- | -- |--|--|
| BOJ | [문자 해독](https://www.acmicpc.net/problem/1593) | 슬라이딩 윈도우 | gold4 |
| BOJ | [N번째 큰 수](https://www.acmicpc.net/problem/2075) | 슬라이딩 윈도우 | gold5 |
| BOJ | [내려가기](https://www.acmicpc.net/problem/2096) | 슬라이딩 윈도우 | gold4 |
| BOJ | [표절](https://www.acmicpc.net/problem/2428) | 슬라이딩 윈도우 | silver3 |

### 🚩 week 25
| Type | 문제 |  유형 | lank |
| -- | -- |--|--|
| BOJ | [최소 스패닝 트리](https://www.acmicpc.net/problem/1197) | MST | gold4 |
| BOJ | [도시 분할 계획](https://www.acmicpc.net/problem/1647) | MST | gold4 |
| BOJ | [행성 연결](https://www.acmicpc.net/problem/16398) | MST | gold4 |
| BOJ | [네트워크 연결](https://www.acmicpc.net/problem/1922) | MST | gold4 |

### 🚩 week 26
| Type | 문제 |  유형 | lank |
| -- | -- |--|--|
| Programmers | [경주로 건설](https://programmers.co.kr/learn/courses/30/lessons/67259) | 최단경로 | - |
| BOJ | [특정한 최단 경로](https://www.acmicpc.net/problem/1504) | 최단경로 | gold4 |
| Programmers | [합승 택시 요금](https://programmers.co.kr/learn/courses/30/lessons/72413) | 최단경로 | Lv3 |
| Programmers | [배달](https://programmers.co.kr/learn/courses/30/lessons/12978?language=java) | 최단경로 | - |

### 🚩 week 27
| Type | 문제 |  유형 | lank |
| -- | -- |--|--|
| BOJ | [마법사상어와 비바라기](https://www.acmicpc.net/problem/21610) | 구현 | gold5 |
| BOJ | [뱀](https://www.acmicpc.net/problem/3190) | 구현 | gold5 |
| BOJ | [게리맨더링 2](https://www.acmicpc.net/problem/17779) | 구현 | gold4 |
| BOJ | [통나무 옮기기](https://www.acmicpc.net/problem/1938) | 구현 |  |


### 🚩 week 28
| Type | 문제 |  유형 | lank |
| -- | -- |--|--|
| BOJ | [감소하는 수](https://www.acmicpc.net/problem/1038) | 백트래킹 | gold5 |
| BOJ | [좋은수열](https://www.acmicpc.net/problem/2661) | 백트래킹 | gold4 |
| BOJ | [합 구하기](https://www.acmicpc.net/problem/11441) | 누적합 | silver3 |
| BOJ | [나머지 합](https://www.acmicpc.net/problem/10986) | 누적합 | gold3 |

### 🚩 week 29
| Type | 문제 |  유형 | lank |
| -- | -- |--|--|
| BOJ | []() |  |  |
| BOJ | [벽 부수고 이동하기](https://www.acmicpc.net/problem/2206) | BFS | gold4 |
| BOJ | [색종이 만들기](https://www.acmicpc.net/problem/2630) | 분할정복 | silver3 |
| BOJ | []() |  |  |
