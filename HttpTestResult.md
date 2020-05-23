GET http://localhost:8080/goods/page/1

```
HTTP/1.1 200 
Content-Type: application/json
Transfer-Encoding: chunked
Date: Sat, 23 May 2020 11:05:48 GMT
Keep-Alive: timeout=60
Connection: keep-alive
```


```json
{
  "success": true,
  "message": "Success",
  "data": "test string",
  "page": {
    "totalElements": 1,
    "totalPages": 1,
    "pageSize": 1,
    "currentPage": 1
  }
}
```

Response code: 200; Time: 98ms; Content length: 118 bytes
