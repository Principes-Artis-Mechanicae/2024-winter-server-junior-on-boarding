# 2024년 겨울 Server 온보딩 6주차: Docker와 Github Action을 이용한 CI/CD 파이프라인 구축

## ✏️ 6주차 과제 요구 사항

- Docker가 무엇인지에 대해 알아보고 본 과제에 이를 적용해보자.
    - Linux의 컨테이너 기술 Docker, Docker 이미지와 컨테이너에 대해 알아보자.
    - Docker의 기본 명령어(`ps`, `images`, `run`, `exec`, `rm` 등)에 대해 알아보자.
    - Docker의 네트워크와 볼륨에 대해 알아보자.
    - 여러 개의 Docker 컨테이너를 쉽게 생성 및 관리할 수 있는 Docker Compose에 대해 알아보자.
    - 위 사항에 대해 `docs`에 마크 다운 문서를 작성해보자.
- Github Action을 이용해 CI/CD 파이프라인을 구축해보자.
    - [Github Action을 이용해 외부 서버에 SSH로 접속하기](https://blog.princip.es/posts/Github-Action%EC%9D%84-%EC%9D%B4%EC%9A%A9%ED%95%B4-%EC%99%B8%EB%B6%80-%EC%84%9C%EB%B2%84%EC%97%90-SSH%EB%A1%9C-%EC%A0%91%EC%86%8D%ED%95%98%EA%B8%B0/)를 읽고 Github Action을 이용해 `main` 브랜치에 push가 발생하면 자동으로 헬륨 서버에 접속 후 배포가 진행되도록 해보자.