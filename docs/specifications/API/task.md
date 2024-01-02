# 할 일

## 할 일 조회

### Request

| URI | Method | 설명 |
|-----|--------|------|
|`/api/tasks/{memberId}?date={date}`|GET|`memberId`에 해당하는 회원이 `date`에 할 일을 조회한다.|

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
|`date`|date|true|할 일을 검색할 날짜(`yyyy-mm-dd`)|

### Response

| 상태 | 메시지 | 설명 |
|-----|--------|------|
|`200`|할 일 조회 성공|할 일 조회에 성공한 경우|
|`401`|권한 없음|시스템에 로그인하지 않은 경우|
|`404`|존재하지 않는 있지 회원|사용자가 입력한 이메일에 대한 회원이 존재하지 않는 있지 경우|

**Response Header**

```
```

**Response Body**

```json
{
  "status": 200,
  "message": "할 일 조회 성공",
  "data": [
    {
      "taskId": 1
      "description": "밥 먹기"
      "date": "2023-12-13"
      "done": false
    },
  ]
}
```

## 내 할 일 조회

### Request

| URI | Method | 설명 |
|-----|--------|------|
|`/api/tasks/me?date={date}`|GET|현재 로그인한 회원이 `date`에 할 일을 조회한다.|

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
|`date`|date|true|할 일을 검색할 날짜(`yyyy-mm-dd`)|

### Response

| 상태 | 메시지 | 설명 |
|-----|--------|------|
|`200`|할 일 조회 성공|내 할 일 조회에 성공한 경우|
|`401`|권한 없음|시스템에 로그인하지 않은 경우|

**Response Header**

```
```

**Response Body**

```json
{
  "status": 200,
  "message": "내 할 일 조회 성공",
  "data": [
    {
      "taskId": 1
      "description": "밥 먹기"
      "date": "2023-12-13"
      "done": false
    },
  ]
}
```

## 할 일 등록

### Request

| URI | Method | 설명 |
|-----|--------|------|
|`/api/tasks/me`|POST|할 일을 등록한다.|

**Request Header**

```
Authorization: Bearer ACCESS_TOKEN
```

**Request Body**

```json
{
  "goalId": 3,
  "description": "Spring 2주차 과제",
  "date": "2023-12-12"
}
```

### Response

| 상태 | 메시지 | 설명 |
|-----|--------|------|
|`201`|할 일 등록 성공|할 일 등록에 성공한 경우|
|`401`|권한 없음|시스템에 로그인하지 않은 경우|
|`404`|존재하지 않는 목표|`goalId`에 해당하는 목표가 존재하지 않는 경우|

**Response Header**

```
```

**Response Body**

```json
{
  "status": 201,
  "message": "할 일 등록 성공"
}
```

## 할 일 수정

### Reqeust

| URI | Method | 설명 |
|-----|--------|------|
|`/api/tasks/me/{taskId}`|PUT|`taskId`에 해당하는 할 일을 수정한다.|

**Request Header**

```
Authorization: Bearer ACCESS_TOKEN
```

**Request Body**

```json
{
  "goalId": 3,
  "description": "Spring 3주차 과제",
  "date": "2023-12-19"
}
```

### Response

| 상태 | 메시지 | 설명 |
|-----|--------|------|
|`201`|할 일 수정 성공|할 일 수정에 성공한 경우|
|`401`|권한 없음|시스템에 로그인하지 않은 경우|
|`404`|존재하지 않는 목표|`goalId`에 해당하는 목표가 존재하지 않는 경우|
|`404`|존재하지 않는 할 일|`taskId`에 해당하는 할 일이 존재하지 않는 경우|

**Response Header**

```
```

**Response Body**

```json
{
  "status": 201,
  "message": "할 일 수정 성공"
}
```

## 할 일 삭제

### Request

| URI | Method | 설명 |
|-----|--------|------|
|`/api/tasks/me/{taskId}`|DELETE|`taskId`에 해당하는 할 일을 삭제한다.|

**Request Header**

```json
Au: Authorization: Bearer ACCESS_TOKEN
```

**Request Body**

```json
```

### Response

| 상태 | 메시지 | 설명 |
|-----|--------|------|
|`204`|할 일 삭제 성공|할 일 삭제에 성공한 경우|
|`404`|존재하지 않는 할 일|`taskId`에 해당하는 할 일이 존재하지 않는 경우|

**Response Header**

```
```

**Response Body**

```json
{
  "status": 204,
  "message": "할 일 삭제 성공"
}
```