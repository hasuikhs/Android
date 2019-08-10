## Android(Marshalling, Serialization, bitmap <-> byte[])

- #### Marshalling

  - 하나 이상의 프로그램 또는 연속되어 있지 않은 저장소로부터 데이터를 모은 후, 메시지 버퍼에 집어넣고, 특정 수신기나 프로그래밍 인터페이스에 맞도록 그 데이터를 조직화 하거나, 미리 정해진 다른 형식으로 변환하는 과정
  - 어떤 한 언어로 작성된 프로그램의 출력 매개변수들을 다른 언어로 작성된 프로그램의 입력으로 전달해야 하는 경우에 필요

- #### Unmarshalling

  - 마샬링을 통해 보내진 데이터들을 원래 구조로 복원시키는 것
  - 이러한 의미에서 개체 입출력을 위해 개체를 직렬화(serialize)하고 복원(deserialize)하느 과정에서 비슷
  - 다만 마샬링과 언마샬링은 단순한 데이터의 직렬화가 아니라, 구조화된 대상들에 대해 구조 해제/복원이 개입할떄 사용하는 개념이라는 점이 다름

  ```
  요즘은 대부분 마샬링이란 의미는 이기종 간의 통신을 위해서 서로간의 형식을 맞춘것을 의미
  ```

- #### Serialization

  - 객체의 상태를 저장하기 위해서 객체를 byte stream으로 변환하는 것을 의미

- #### Marshalling vs Serialization

  - 원격 프로시저를 호출하는 것에서는 약간 유사하지만, 의도를 따지면 의미적으로 틀림
  - Marshalling 을 하게 되면, 원격 프로시저를 호출하는 것에서 함수의 parameter 값들 return 값들을 전달 가능
  - 보통 Marshalling 은 여기저기에서 parameter 들을 얻는 반면, Serialization은 구조화된 데이터 를 byte stream 과 같은 primitive 형식 혹은 그 반대로 복사를 하는 것을 의미
  - 이러한 의미에서 Serialization은 marshalling 의 pass-by-value 를 구현하는 것의 일종으로 볼 수 있음
  - Marshalling 은 추가적인 메타 데이터 (코드 베이스) 를 가질 수 있다는 것에서 Serialization 과 구별

- #### Bitmap 이미지를 byte[]로 가져오는 방법

  ```java
  public byte[] bitmapToByteArray(Bitmap bitmap){
      ByteArrayOutputStream stream = new ByteArrayOutputStream();
      bitmap.compress(CompressFormat.JPEG, 100, stream);
      // compress 인자 값에는 압축옵션(JPEG, PNG)와 품질 설정(0 ~ 100)
      // 그리고 압축된 바이트 배열을 담을 stream을 넘겨줌
      byte[] byteArray = stream.toByteArray();
      
      return byteArray;
  }
  ```

- #### byte[]를 bitmap으로 반환하는 방법

  ```java
  public Bitmap byteArrayToBitmap(byte[] byteArray){
      Bitmap bitmap = BitmapFactory.decodeByteArray(byteArray, 0, byteArray.length);
      
      return bitmap;
  }
  ```