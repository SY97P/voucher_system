# SpringBoot Basic Weekly Mission

## 📌 과제 설명 <!-- 어떤 걸 만들었는지 대략적으로 설명해주세요 -->

### 흐름도
[flowchart.mermaid](flowchart.mermaid)

### 클래스 다이어그램
[application.mermaid](application.mermaid)


## ✅ PR 포인트 & 궁금한 점 <!-- 리뷰어 분들이 집중적으로 보셨으면 하는 내용을 적어주세요 -->
* 테스트 코드
  * @ValueSource, @CsvSource, @MethodSource 처럼 테스트 소스를 받아오는 구문이 given-when-then 구조 중 given에 해당하는지 궁금합니다.
  * @SpringBootTest 로 모든 빈을 등록해서 테스트하는데도 yaml 프로퍼티에 접근하지 못하는 경우가 있습니다.
    * @Value 는 IoC에서 처리하지는 않는 건지 궁금합니다.
    * 어플리케이션 실행에는 IoC 통해 프로퍼티를 얻는것 같은데, 테스트에서만 동작하지 않는게 이해되지 않습니다.
* Domain
  * 컨트롤러, 서비스, 레포지토리 각 레벨별로 주고받는 데이터 타입이 통일되는게 좋은건가요?
    * 파일에서 읽은 String을 도메인으로 변환하고, 컨트롤러에서 다시 String으로 변환해서 출력하도록 수정했는데,
      불필요한 연산이 반복되는 것 같습니다.
    * 변환을 하는 비용보다 레벨 별로 통일된 데이터 전달이 더 이득이라 이렇게 구현하는게 맞는건지 궁금합니다.
  * 로직은 도메인에 있고, VO와 DTO에는 없는게 좋다고 합니다.
    * 유효성 검사를 VO, DTO에 구현했는데 올바른 방식인지 궁금합니다.
    * 바우처 할인 검사는 로직이라고 생각해 도메인에서 구현했습니다.
* 책임 분리
  * 기존 구조의 책임이 복잡하다고 생각해 변경했습니다.
  * 변경 후의 결합도는 어떤지 확인부탁드립니다. 


## 👩‍💻 요구 사항과 구현 내용 <!-- 기능을 Commit 별로 잘개 쪼개고, Commit 별로 설명해주세요 -->
- [x] IO
  - [x] 입출력 (TextIO 라이브러리)
  - [x] 파일 입출력 (CSV 파일)
- [x] 생성
  - [x] 고정값 바우처 생성
  - [x] 비율값 바우처 생성
  - [x] 메모리 저장 (dev 프로파일)
  - [x] 파일 저장 (default 프로파일)
- [x] 조회
  - [x] 바우처 조회
  - [x] 블랙고객 조회
- [x] 종료
- [x] YAML 프로퍼티
- [x] 로그 (level = {`error`, `debug`})
- [x] jar 파일 추출

## ✅ 피드백 반영사항  <!-- 지난 코드리뷰에서 고친 사항을 적어주세요. 재PR 시에만 사용해 주세요! (재PR 아닌 경우 삭제) -->
- 테스트 코드 작성
- 클래스 간 책임 분리
- 불필요한 책임 전가 제거
- DTO, VO 적용

### Git Commit Convention
* feat : 기능
* fix  : 버그 수정
* docs : 문서 작업
* style: 포맷팅, ;추가
* refactor : 리팩토링 (기능 변경 X)
* test : 테스트 코드 추가
* chore : 유지 (빌드 작업, 패키지 메니저 작업)