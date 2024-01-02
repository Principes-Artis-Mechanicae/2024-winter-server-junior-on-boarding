# 목표

## 목표 조회

### Request

| URI | Method | 설명 |
|-----|--------|------|
|`/api/goals/{memberId}`|GET|`memberId`에 해당하는 회원의 목표를 조회한다.|

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
|`200`|목표 조회 성공|목표 조회에 성공한 경우|
|`401`|권한 없음|시스템에 로그인하지 않은 경우|
|`404`|존재하지 않는 회원|`memberId`에 해당하는 회원이 존재하지 않는 경우|

**Response Header**

```
```

**Response Body**

```json
{
  "status": 200,
  "message": "목표 조회 성공",
  "data": [
    {
      "description": "개발 공부"
    },
  ]
}
```

## 내 목표 조회

### Request

| URI | Method | 설명 |
|-----|--------|------|
|`/api/goals/me`|GET|현재 로그인한 회원의 목표를 조회한다.|

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
|`200`|내 목표 조회 성공|내 목표 조회에 성공한 경우|
|`401`|권한 없음|시스템에 로그인하지 않은 경우|

**Response Header**

```
```

**Response Body**

```json
{
  "status": 200,
  "message": "내 목표 조회 성공",
  "data": [
    {
      "description": "개발 공부"
    },
  ]
}
```

## 목표 등록

### Request

| URI | Method | 설명 |
|-----|--------|------|
|`/api/goals/me`|POST|목표를 등록한다.|

**Request Header**

```
Authorization: Bearer ACCESS_TOKEN
```

**Request Body**

```json
{
  "description": "개발 공부",
  "visibilityStatus": "PUBLIC"
}
```

### Response 

| 상태 | 메시지 | 설명 |
|-----|--------|------|
|`201`|목표 등록 성공|목표 등록에 성공한 경우|
|`401`|권한 없음|시스템에 로그인하지 않은 경우|

**Response Header**

```
```

**Response Body**

```json
{
  "status": 201,
  "message": "목표 등록 성공"
}
```

## 목표 수정

### Reqeust

| URI | Method | 설명 |
|-----|--------|------|
|`/api/goals/me/{goalId}`|PUT|`goalId`에 해당하는 목표를 수정한다.|

**Request Header**

```
Authorization: Bearer ACCESS_TOKEN
```

**Request Body**

```json
{
  "description": "개발 공부2",
  "visibilityStatus": "FOLLOWER_ONLY"
}
```

### Response

| 상태 | 메시지 | 설명 |
|-----|--------|------|
|`201`|목표 수정 성공|목표 수정에 성공한 경우|
|`401`|권한 없음|시스템에 로그인하지 않은 경우|
|`404`|등록되어 있지 않은 목표|`goalId`에 해당하는 목표가 존재하지 않는 경우|

**Response Header**

```
```

**Response Body**

```json
{
  "status": 201,
  "message": "목표 수정 성공"
}
```

## 목표 삭제

### Request

| URI | Method | 설명 |
|-----|--------|------|
|`/api/goals/me/{goalId}`|DELETE|`goalId`에 해당하는 목표를 삭제한다.|

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
|`204`|목표 삭제 성공|목표 삭제에 성공한 경우|
|`401`|권한 없음|시스템에 로그인하지 않은 경우|
|`404`|등록되어 있지 않은 목표|`goalId`에 해당하는 목표가 존재하지 않는 경우|

**Response Header**

```
```

**Response Body**

```json
{
  "status": 204,
  "message": "목표 삭제 성공"
}
```