안드로이드 어플리케이션입니다.

url을 저장해서 리스트뷰에서 눌렀을때에 링크를 타고 해당 url의 비디오를 볼수있습니다.
사용자가 직접 url을 저장해서 붙여넣기 해야합니다.

to do
편집기능 수정
페이스북 앱에서 실행하는 동영상을 저장해보고싶다!


메모
문제점

jfeinstein10/SlidingMenu를 사용했고
sqlite 조금 연동해본정도


리스트뷰가 커스텀리스트뷰가 아닌 기본 리스트뷰 single_item_1?
이여서 리스트뷰에서 정보를 가져오는 방식이 안좋은 방식이다
-id가 아닌 이름으로 가져옴
-물론 삭제도 이름으로..
-->커스텀 리스트뷰로 바꾸면 해결될듯?

액티비티와 프래그먼트간에 정보 교환이 기묘하다...
-public 클래스에서 public static 변수를 만들어서(모두공유) 액티비티에 정보 넘기거나할때 사용중

프래그먼트를 썼는데 태블릿(회전)을 고려하지않은 레이아웃
-스마트폰을 옆으로 눕히면 화면이 기묘해진다
