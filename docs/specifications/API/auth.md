# 인증

## 회원 가입

### Request

| URI | Method | 설명 |
|-----|--------|------|
|`/api/auth/signup`|POST|본 시스템에 회원으로 등록한다.|

**Request Header**

```json
```

**Request Body**

```json
{ 
  "email": "user@example.com",
  "password": "password123" 
}
```

### Response

| 상태 | 메시지 | 설명 |
|-----|--------|------|
|`201`|회원 가입 성공|회원 가입에 성공한 경우|
|`400`|이메일 중복|이메일이 이미 등록되어 있는 경우|

**Response Header**

```
```

**Response Body**

```json
{
  "status": 201,
  "message": "회원 가입 성공"
}
```

## 로그인

### Request

| URI | Method | 설명 |
|-----|--------|------|
|`/api/auth/login`|POST|본 시스템에 로그인한다.|

**Request Header**

```json
```

**Request Body**

```json
{ 
  "email": "user@example.com",
  "password": "password123" 
}
```

### Response

| 상태 | 메시지 | 설명 |
|-----|--------|------|
|`200`|로그인 성공|로그인에 성공한 경우|
|`400`|잘못된 이메일 또는 비밀번호|사용자가 입력한 이메일과 비밀번호에 해당하는 회원이 없는 경우|

**Response Header**

```
Authorization: Bearer ACCESS_TOKEN
```

**Response Body**

```json
{
  "status": 200,
  "message": "로그인 성공"
}
```