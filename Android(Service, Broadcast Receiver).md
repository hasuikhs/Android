## Android(Service, Broadcast Receiver)

1. #### Java의 Event 처리

   - Delegation Model을 이용

   1. ##### Event Source

      - 이벤트가 발생한 객체를 지칭
      - 자바 초창기에는 Event Source가 직접 Event를 처리
      - 비효율적인 측면이 있어서 이벤트 처리방식을 변경
        - Delegation Model로 변경
        - 전문적인 이벤트 처리 객체를 이용해서 이벤트를 처리

   2. #####   Event Listener

      - 이벤트 처리 객체를 지칭

   3. ##### Event 객체

      - 이벤트에 대한 세부적인 정보를 가지고 있는 객체를 지칭
      -  Event Listener에게 해당 객체가 전달

2. #### Service

   - 안드로이드를 구성하고 있는 4가지 component 중 하나

   - Background 작업이 필요한 경우에 많이 사용

   - Activity의 가장 큰 관심거리(처리)는 사용자와의 Interaction

   - Service는 사용자와의 interaction은 없음
     Activity를 위한 연산이나 특정 method를 제공
     Thread를 사용해서 처리

   - Service의 lifecycle(callback method)에 대해서 알아둬야 함

   -  Service의 주요한 callback method는 3개

     ```
     - onCreate() : 서비스 객체가 처음 생성될 때 호출
                    사용할 리소스에 대한 초기화 작업
                    
     - onStartCommand() : 실제 서비스가 일을 하기 위해서 호출되는 method
     
     - onDestroy() : 서비스 객체가 종료될때 호출
                     사용하던 Thread를 중지, 리소스 해제
     ```

3. #### Broadcast Receiver

   - Broadcast?
     - 안드로이드 시스템으로부터 나오는 신호
        (배터리 용량이 부족, 네트워크 환경 바꿈, USB케이블 연결/비연결)
     - 사용자 Application에서 발생시키는 임의의 신호

   - Broadcast Receiver는 Broadcast를 청취하는 component
   - Broadcast Receiver는 사용자와의 대면은 하지 않음
