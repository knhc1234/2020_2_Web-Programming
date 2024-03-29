# 2020_2_Web-Programming

HW#1 
1) 강의자료 3장의 프로그래밍 실습에 대한 프로그램을 작성하세요.
    - 이차방정식의 근
    - 체질량 지수

2) 강의자료 4장의 프로그래밍 실습에 대한 프로그램을 작성하세요.
    - 바빌로니안 제곱근 알고리즘
    - 빠른 거듭제곱 함수
  
3) 두 문자를 입력 받고, anagram을 판단하는 프로그램을 작성하세요.
    - Anagram은 서로 다른 두 문자열의 문자들을 재배치 할 경우 서로 같은 문자열을 형성할 수 있는 경우를 말한다.
    - "quit"를 입력하면 프로그램을 종료
    - 종료 시 현재까지 입력된 단어 쌍들 중 anagram 가능한 단어 쌍과 불가능한 단어 쌍, 각각의 목록과 수를 출력
    - 두 문자 입력시마다, 입력된 모든 단어들에 사용된 알파벳의 수를 카운트하여 출력

Mid_Term

건물을 생성하고 유닛을 생산하는 프로그램을 상속을 이용해 구현하시오.

> Building 클래스를 만들고, 이를 상속하여 커맨드센터, 서플라이 디팟, 배럭 3개의 클래스를 구현한다.

> Unit 클래스를 만들고, 이를 상속하여 SCV, Marine, Firebat, Medic 4가지 유닛을 구현한다.

> Game 클래스는 main 메서드를 가지먀, 사용자의 입력에 따라서 건물을 생산하고, 유닛을 생산한다.


[건물들의 상속 구조도 및 기능]
   
- 사각형 박스는 건물들의 각각 다른 기능을 명시한다. 상속받은 각 건물들은 해당하는 기능을 수행하는 메서드를 작성해야 한다.

- Building 클래스는 isBuild(), build(), destroy() 메서드를 가진다.

isBuild : 해당 건물이 건설되었는지 반환

build : 해당 건물을 건설

destroy : 해당 건물을 철거

⁙ 건물의 종류는 커맨드센터, 서플라이 디팟, 배럭 3가지이다.

- 커맨드센터

초기에 커맨드센터는 건설되어 있지 않다.

서플라이 디팟과 배럭을 건설하기 위해서는 커맨드 센터를 건설해야 한다.

커맨드센터에서는 SCV를 생산할 수 있다.

 

- 서플라이 디팟

서플라이 디팟의 개수에 따라서 인구수가 증가한다.

 

- 배럭

배럭에서는 Marine, Firebat, Medic 3개의 유닛을 생산할 수 있다.

배럭이 건설되어 있지 않으면 위의 3가지 유닛을 생산할 수 없다.


[유닛들의 상속 구조도 및 기능]

- 사각형 박스는 유닛 클래스가 가지는 메서드를 명시한다.

- Unit 클래스는 killUnit(), printUnitInfo(), printUnitPopulation() 메서드를 가진다.

killUnit : 해당 유닛을 하나 없앤다. 사용 인구수는 감소한다.

printUnitInfo : 유닛의 정보를 출력한다.

printUnitPopulation : 해당 유닛의 숫자와 해당 유닛으로 사용되고 있는 인구수를 출력한다.

 

⁙ 유닛은 체력, 공격력, 방어력, 인구수의 정보를 가지며, 유닛마다 각각 다른 정보를 가진다.

- 상속받은 각 유닛 클래스들은 생성자를 통해 해당 유닛의 정보를 정의한다.


[유닛 인구수 관리]

⁙ 전체 인구수는 10에서 시작하며, 최대 200까지 증가할 수 있다.

- 서플라이 디폿을 건설할 때마다 전체 인구수가 10씩 증가한다.

- 유닛을 생성할 때마다 해당 유닛의 인구수만큼 사용 인구수가 증가한다.

- 전체 인구수를 초과하여 유닛을 생산할 수 없다.


[Game 클래스의 동작]

1) 기능 목록 출력

건물 건설 / 유닛 조회 / 유닛 생성 / 유닛 삭제의 기능 목록을 출력한다.

 

2) 건물 건설

건물들의 종류를  출력하고, 사용자는 목록에서 건설할 건물을 선택한다.

선택한 건물의 제한 조건을 확인한다. (이미 건설된 건물인지, 해당 건물을 건설하기 위해 필요한 건물이 존재하는지)

제한 조건을 모두 만족할 경우 건물을 건설한다.

 

3) 유닛 조회

생성된 유닛들의 목록을 출력하고, 사용한 인구수를 전체 인구수와 같이 출력한다.

ex) 인구수 : 사용 인구수 / 전체 인구수

 

4) 유닛 생성

생성할 수 있는 유닛의 종류를 출력하고, 사용자는 목록에서 생성할 유닛을 선택한다. (건설되어 있는 건물에 따라서 생성 가능한 유닛이 달라진다.)

선택된 유닛의 정보를 출력하고 사용자는 생성할지 결정한다.

유닛 생성시에는 사용 인구수와 총 인구수를 비교하여, 사용 인구수가 총 인구수보다 커지게 되면 유닛을 생성하지 않는다. 그 외에는 유닛을 생성한다.

유닛 생성 과정이 종료되면 사용 인구수와 총 인구수를 출력한다.

 

5) 유닛 삭제

생성되어 있는 유닛들의 목록을 출력하고, 사용자는 목록에서 선택한다.

선택한 유닛을 삭제하고 사용 인구수와 전체 인구수를 출력한다.


HW#2

- 머드 게임 + 블랙잭 구현하기


HW#3

- 회원가입 + 로그인 페이지 구현

[ 기능 및 페이지 설명 ]

1) 회원가입 페이지 구현

- 사용자의 정보(ID, PW, 이름, 전화번호, 이메일 등)을 입력하여 회원가입하는 페이지 구현

- ID는 중복 체크를 통해 유일한 ID를 사용하도록 구현한다.

- 회원가입 시 입력하지 않은 정보가 있을 경우 입력하도록 안내하며, 모든 정보를 입력 시에 회원가입 할 수 있도록 한다.

  

2) 로그인 페이지 구현

- ID와 PW를 입력하여 로그인할 수 있는 페이지 구현

- file로 저장된 회원가입한 사용자 정보를 기반으로 로그인 기능을 구현한다.

- PW 입력은 input의 password type을 사용한다.

- 사용자가 로그인할 때마다 file에 로그인 기록을 저장한다. 사용자의 ID와 로그인 일시(년, 월, 일, 시, 분, 초)를 저장한다.

  

3) 회원 목록 페이지 구현

- 회원가입한 사용자의 목록을 출력한다.

- 사용자의 ID, 이름, 전화번호, e-mail을 출력한다.

 

4) 로그인 기록 페이지 구현

- file에 저장된 로그인 기록을 출력하는 페이지를 구현한다.

- 사용자 ID와 로그인 일시를 출력한다.
