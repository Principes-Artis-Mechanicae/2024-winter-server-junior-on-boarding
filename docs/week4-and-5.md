# 2024년 겨울 Server 온보딩 4 & 5주차: Spring MVC를 이용한 RESTful API 구현

## ✏️ 4 & 5주차 과제 요구 사항

- 다음 [API 명세서](./specifications/API/API-specification.md)를 읽고 Spring MVC를 이용해 RESTful API를 구현해보자.
    - 인증된 사용자 만이 사용할 수 있는 API는 Spring Security를 이용해 접근 제한을 처리해보자.
    - 사용자가 자신의 목표 및 할 일을 관리하거나 프로필을 수정하는 등의 기능은 해당 리소스의 소유자만 접근할 수 있어야 한다.
    - 각 API에는 적절한 HTTP 메소드(GET, POST, PUT, DELETE 등)를 사용하고, 요청과 응답의 형식은 명세서에 정의된 대로 구현해야 한다.
    - 예외 처리는 안전성을 보장하기 위해 중요하다. 예외가 발생할 수 있는 상황에 대한 적절한 예외 처리 로직을 추가한다.
    - 사용자에게 의미있는 에러 메시지를 반환하여 디버깅 및 유지보수를 용이하게 한다.
- 코드는 읽기 쉽고 유지 보수가 용이하도록, 또한 확정 가능성을 고려하여 작성되어야 한다.
- [Google의 Java 스타일 가이드](https://google.github.io/styleguide/javaguide.html)를 준수하고, 주석을 추가하여 코드의 의도를 명확하게 전달한다.