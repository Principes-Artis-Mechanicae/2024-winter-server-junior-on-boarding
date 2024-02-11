# 회원

## 회원 검색

### Request

| URI | Method | 설명 |
|-----|--------|------|
|`/api/members?email={email}`|POST|본 시스템에 등록된 회원을 이메일로 검색한다.|

**Request Header**

```
Authorization: Bearer ACCESS_TOKEN
```

**Request Body**

```json
```

**Request Query String Parameters**

| 파라미터 | 타입 | 필수 여부 | 설명 |
|-----------|------|----------|------|
|`email`|string|true|검색할 회원의 이메일|

### Response

| 상태 | 메시지 | 설명 |
|-----|--------|------|
|`200`|회원 검색 성공|회원 검색에 성공한 경우|
|`404`|등록되어 있지 않은 회원|사용자가 입력한 이메일에 대한 회원이 등록되어 있지 않은 경우|

**Response Header**

```
```

**Response Body**

```json
{
  "status": 200,
  "message": "회원 검색 성공",
  "data": {
    "email": "user@example.com",
    "username": "John Doe",
    "description": "My name is John Doe.",
    "MemberImageUri": "/storage/images/123.png"
  }
}
```

## 프로필 등록

## Request

| URI | Method | 설명 |
|-----|--------|------|
|`/api/members/me/Member`|POST|프로필을 등록한다.|

**Request Header**

```
Authorization: Bearer ACCESS_TOKEN
```

**Request Body**

```json
{ 
  "username": "John Doe",
  "description": "My name is John Doe.",
  "MemberImageUri": "/storage/images/123.png"
}
```

## Response

| 상태 | 메시지 | 설명 |
|-----|--------|------|
|`200`|프로필 등록 성공|프로필 등록에 성공한 경우|

**Response Header**

```
```

**Response Body**

```json
{
  "status": 201,
  "message": "프로필 등록 성공"
}
```

## 프로필 수정

### Request

| URI | Method | 설명 |
|-----|--------|------|
|`/api/members/me/Member`|PUT|프로필을 수정한다.|

**Request Header**

```json
Authorization: Bearer ACCESS_TOKEN
```

**Request Body**

```json
{ 
  "username": "John Doe John Doe",
  "description": "My name is John Doe John Doe.",
  "MemberImageUri": "/storage/images/123.png"
}
```

### Response

| 상태 | 메시지 | 설명 |
|-----|--------|------|
|`200`|프로필 수정 성공|프로필 수정에 성공한 경우|

**Response Header**

```
```

**Response Body**

```json
{
  "status": 201,
  "message": "프로필 수정 성공"
}
```