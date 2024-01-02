# 스토리지

## 이미지 다운로드

### Request

| URI | Method | 설명 |
|-----|--------|------|
|`/api/storage/images/{imageFileName}`|GET|`{imageFileName}`에 해당하는 이미지를 다운로드한다.|

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
|`200`|이미지 다운로드 성공|이미지 다운로드에 성공한 경우|
|`401`|권한 없음|시스템에 로그인하지 않은 경우|
|`404`|존재하지 않는 이미지|`{imageFileName}`에 해당하는 이미지가 존재하지 않는 경우|

**Response Header**

```
Content-Disposition: inline
```

**Response Body**

```
이미지 파일
```

## 이미지 업로드

### Request

| URI | Method | 설명 |
|-----|--------|------|
|`/api/storage/images`|POST|이미지를 업로드한다.|

**Request Header**

```
Authorization: Bearer ACCESS_TOKEN
Content-Type: multipart/form-data
```

**Request Body**

| 파라미터 | 타입 | 필수 여부 | 설명 |
|-----------|------|----------|------|
|`imageFile`|File|true|업로드 할 이미지|

### Response

| 상태 | 메시지 | 설명 |
|-----|--------|------|
|`200`|이미지 업로드에 성공|이미지 업로드에 성공한 경우|
|`400`|잘못된 이미지 파일|해당 파일의 용량이 너무 크거나 이미지 파일이 아닌 경우|
|`401`|권한 없음|시스템에 로그인하지 않은 경우|

**Response Header**

```
```

**Response Body**

```json
{
  "status": 200,
  "message": "이미지 업로드 성공"
}
```