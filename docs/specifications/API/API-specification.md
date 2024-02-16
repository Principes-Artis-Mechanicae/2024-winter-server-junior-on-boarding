# todo mate 애플리케이션 API 명세서

## [인증](./auth.md)

| URI | Method | 설명 |
|-----|--------|------|
|`/api/auth/signup`|POST|회원 가입|
|`/api/auth/login`|POST|로그인|

## [회원](./member.md)

| URI | Method | 설명 |
|-----|--------|------|
|`/api/members?email={email}`|GET|회원 검색|
|`/api/members/{memberId}`|GET|회원 상세 검색|
|`/api/members/me/Member`|POST|프로필 등록|
|`/api/members/me/Member`|PUT|프로필 수정|

## [팔로우](./follow.md)

| URI | Method | 설명 |
|-----|--------|------|
|`/api/follow/{memberId}`|GET|팔로우 목록 조회|
|`/api/follow/me/{memberId}`|POST|팔로우|
|`/api/follow/me/{memberId}`|DELETE|언팔로우|

## [목표](./goal.md)

| URI | Method | 설명 |
|-----|--------|------|
|`/api/goals/{memberId}`|GET|목표 조회|
|`/api/goals/me`|GET|내 목표 조회|
|`/api/goals/me`|POST|목표 등록|
|`/api/goals/me/{goalId}`|PUT|목표 수정|
|`/api/goals/me/{goalId}`|DELETE|목표 삭제|

## [할 일](./task.md)

| URI | Method | 설명 |
|-----|--------|------|
|`/api/tasks/{memberId}?date={date}`|GET|할 일 조회|
|`/api/tasks/me?date={date}`|GET|내 할 일 조회|
|`/api/tasks/me`|POST|할 일 등록|
|`/api/tasks/me/{tasksId}`|PUT|할 일 수정|
|`/api/tasks/me/{tasksId}`|DELETE|할 일 삭제|

## [스토리지](./storage.md)
| URI | Method | 설명 |
|-----|--------|------|
|`/api/storage/images/{imageFileName}`|GET|이미지 다운로드|
|`/api/storage/images`|POST|이미지 업로드|