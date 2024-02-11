# 팔로우

## 팔로우 목록 조회

### Request

| URI | Method | 설명 |
|-----|--------|------|
|`/api/follow/{memberId}`|GET|`memberId`에 해당하는 회원의 팔로우 목록을 조회한다.|

**Request Header**

```
Authorization: Bearer ACCESS_TOKEN
```

**Request Body**

```json
```

### Response

| 상태 | 메시지 | 설명 |
|-----|--------|------|
|`200`|팔로우 목록 조회 성공|팔로우 목록 조회에 성공한 경우|
|`401`|권한 없음|시스템에 로그인하지 않은 경우|
|`404`|존재하지 않는 회원|`memberId`에 해당하는 회원이 존재하지 않는 경우|

**Response Header**

```
```

**Response Body**

```json
{
  "status": 200,
  "message": "팔로우 목록 조회 성공",
  "data": [
    {
      "memberId": 1,
      "email": "user@example.com",
      "username": "John Doe",
      "description": "My name is John Doe.",
      "MemberImageUri": "/storage/images/123.png"
    },
  ]
}
```

## 팔로우

### Request

| URI | Method | 설명 |
|-----|--------|------|
|`/api/follow/me/{memberId}`|POST|`memberId`에 해당하는 회원을 팔로우한다.|

**Request Header**

```
Authorization: Bearer ACCESS_TOKEN
```

**Request Body**

```json
```

### Response

| 상태 | 메시지 | 설명 |
|-----|--------|------|
|`201`|팔로우 성공|팔로우에 성공한 경우|
|`401`|권한 없음|시스템에 로그인하지 않은 경우|
|`404`|존재하지 않는 회원|`memberId`에 해당하는 회원이 존재하지 않는 경우|

**Response Header**

```
```

**Response Body**

```json
{
  "status": 201,
  "message": "팔로우 성공"
}
```

## 언팔로우

### Request

| URI | Method | 설명 |
|-----|--------|------|
|`/api/follow/me/{memberId}`|DELETE|현재 팔로우 중인 `memberId`에 해당하는 회원을 언팔로우한다.|

**Request Header**

```
Authorization: Bearer ACCESS_TOKEN
```

**Request Body**

```json
```

### Response

| 상태 | 메시지 | 설명 |
|-----|--------|------|
|`204`|언팔로우 성공|언팔로우에 성공한 경우|
|`401`|권한 없음|시스템에 로그인하지 않은 경우|
|`404`|존재하지 않는 회원|`memberId`에 해당하는 회원이 존재하지 않는 경우|

**Response Header**

```
```

**Response Body**

```json
{
  "status": 204,
  "message": "언팔로우 성공"
}
```