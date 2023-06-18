# minidooray_gateway

## user

### POST /api/users
- Description: 새로운 사용자를 추가합니다.
- Path Variables: None
- Request Body: JSON 형식 요청

  |파라미터|타입|필수 여부|설명|
  |---|---|-|-----|
  |userUUID|string|Y||
  |userId|string|Y||
  |userNickName|string|Y||
  |userEmail|string|Y||

- Response Code: 200 ok
- Response Body: None
</br>

### GET /api/users/{userUUID}
- Description: 사용자 UUID로 조회한 사용자 정보를 반환합니다.
- Path Variables: 
    - userUUID: 사용자 UUID

- Response Code: 200 ok
- Response Body: JSON 형식 응답

  |파라미터|타입|필수 여부|설명|
  |---|---|-|-----|
  |userUUID|string|Y||
  |userId|string|Y||
  |userNickName|string|Y||
  |userEmail|string|Y||
</br>

### GET /api/users
- Description: 모든 사용자 정보리스트를 반환합니다.
- Path Variables: None
- Response Code: 200 ok
- Response Body: JSON 형식 응답

  |파라미터|타입|필수 여부|설명|
  |---|---|-|-----|
  |userUUID|string|Y||
  |userId|string|Y||
  |userNickName|string|Y||
  |userEmail|string|Y||
</br>

### PUT /api/users/{userUUID}
- Description: 사용자 UUID로 조회하여 사용자 정보를 수정합니다.
- Path Variables: 
    - userUUID: 사용자 UUID
- Request Body: JSON 형식 요청

  |파라미터|타입|필수 여부|설명|
  |---|---|-|-----|
  |userUUID|string|Y||
  |userId|string|Y||
  |userNickName|string|Y||
  |userEmail|string|Y||

- Response Code: 200 ok
- Response Body: None
</br>

### DELETE /api/users/{userUUID}
- Description: 사용자 UUID로 대상 사용자 정보를 삭제합니다.
- Path Variables: 
    - userUUID: 사용자 UUID

- Response Code: 200 ok
- Response Body: None
</br>

### GET /api/users/{userUUID}/tasks
- Description: 사용자 UUID로 조회한 사용자의 모든 태스크 리스트를 반환합니다.
- Path Variables: 
    - userUUID: 사용자 UUID
- Response Code: 200 ok
- Response Body: JSON 형식 응답

  |파라미터|타입|필수 여부|설명|
  |---|---|-|-----|
  |userUUID|string|Y||
  |taskId|long|Y||
</br>

### GET /api/users/{userUUID}/projects
- Description: 사용자 UUID로 조회한 사용자의 모든 프로젝트 리스트를 반환합니다.
- Path Variables: 
    - userUUID: 사용자 UUID
- Response Code: 200 ok
- Response Body: JSON 형식 응답

  |파라미터|타입|필수 여부|설명|
  |---|---|-|-----|
  |projectId|string|Y||
  |projectName|string|Y||
  |projectDescription|string|Y||
  |projectStatus|ACTIVE, INACTIVE, CLOSED|Y||
  |userUUID|string|Y||
</br>


## project

### POST /api/projects
- Description: 새로운 프로젝트를 추가합니다.
- Path Variables: None
- Request Body: JSON 형식 요청
  
    |파라미터|타입|필수 여부|설명|
    |---|---|-|-----|
    |projectName|string|Y||
    |projectDescription|string|Y|| 
    |projectStatus|string|Y||
    |userUUID|string|Y||

- Response Code: 200 ok
- Response Body: None
</br>

### GET /api/projects/{projectId}
- Description: 프로젝트 ID로 조회한 프로젝트 정보를 반환합니다.
- Path Variables: 
    - projectId: 프로젝트 ID

- Response Code: 200 ok
- Response Body: JSON 형식 응답

    |파라미터|타입|필수 여부|설명|
    |---|---|-|-----|
    |projectId|string|Y||
    |projectName|string|Y||
    |projectDescription|string|Y|| 
    |projectStatus|ACTIVE, INACTIVE, CLOSED|Y||
    |userUUID|string|Y||
</br>

### PUT /api/projects/{projectId}
- Description: 프로젝트 ID로 조회하여 프로젝트 정보를 수정합니다.
- Path Variables: 
    - projectId: 프로젝트 ID
- Request Body: JSON 형식 요청
  
    |파라미터|타입|필수 여부|설명|
    |---|---|-|-----|
    |projectName|string|Y||
    |projectDescription|string|Y|| 
    |projectStatus|string|Y||
    |userUUID|string|Y||

- Response Code: 200 ok
- Response Body: None
</br>

### DELETE /api/projects/{projectId}
- Description: 프로젝트 ID로 대상 프로젝트 정보를 삭제합니다.
- Path Variables: 
    - projectId: 프로젝트 ID
- Response Code: 200 ok
- Response Body: None
</br>

### POST /api/projects/{projectId}/users/{userUUID}
- Description: 프로젝트 ID와 사용자 UUID로 조회하여 프로젝트에 사용자를 추가합니다.
- Path Variables: 
    - projectId: 프로젝트 ID
    - userUUID: 사용자 UUID
- Response Code: 200 ok
- Response Body: None
</br>

### GET /api/projects/{projectId}/users
- Description: 프로젝트 ID로 조회한 프로젝트의 모든 사용자 리스트를 반환합니다.
- Path Variables: 
    - projectId: 프로젝트 ID
- Response Code: 200 ok
- Response Body: JSON 형식 응답

    |파라미터|타입|필수 여부|설명|
    |---|---|-|-----|
    |userUUID|string|Y||
    |userId|string|Y||
    |userNickName|string|Y||
    |userEmail|string|Y||
</br>

### DELETE /api/projects/{projectId}/users/{userUUID}
- Description: 프로젝트 ID와 사용자 UUID로 조회하여 프로젝트에서 사용자를 삭제합니다.
- Path Variables: 
    - projectId: 프로젝트 ID
    - userUUID: 사용자 UUID
- Response Code: 200 ok
- Response Body: None
</br>

### DELETE /api/projects/{projectId}/users
- Description: 프로젝트 ID로 조회한 프로젝트의 모든 사용자를 삭제합니다.
- Path Variables: 
    - projectId: 프로젝트 ID
- Response Code: 200 ok
- Response Body: None
</br>

## task

### POST /api/tasks
- Description: 새로운 태스크를 추가합니다.
- Path Variables: None
- Request Body: JSON 형식 요청
  
    |파라미터|타입|필수 여부|설명|
    |---|---|-|-----|
    |taskName|string|Y||
    |taskContent|string|Y|| 
    |taskCreationDate|datetime|Y||
    |taskEndDate|datetime|Y||
    |userUUID|string|Y||
    |projectId|long|Y||

- Response Code: 200 ok
- Response Body: None
</br>

### GET /api/tasks/{taskId}
- Description: 태스크 ID로 조회한 태스크 정보를 반환합니다.
- Path Variables: 
    - taskId: 태스크 ID
- Response Code: 200 ok
- Response Body: JSON 형식 응답

    |파라미터|타입|필수 여부|설명|
    |---|---|-|-----|
    |taskId|long|Y||
    |taskName|string|Y||
    |taskContent|string|Y|| 
    |taskCreationDate|datetime|Y||
    |taskEndDate|datetime|Y||
    |userUUID|string|Y||
    |projectId|long|Y||
</br>

### GET /api/tasks/projects/{projectId}
- Description: 프로젝트 ID로 조회한 태스크 정보 리스트를 반환합니다.
- Path Variables: 
    - projectId: 프로젝트 ID
- Response Code: 200 ok
- Response Body: JSON 형식 응답

    |파라미터|타입|필수 여부|설명|
    |---|---|-|-----|
    |taskId|long|Y||
    |taskName|string|Y||
    |taskContent|string|Y|| 
    |taskCreationDate|datetime|Y||
    |taskEndDate|datetime|Y||
    |userUUID|string|Y||
    |projectId|long|Y||

### GET /api/tasks/users/{userUUID}/admin
- Description: 사용자 UUID로 조회하여 관리자로 있는 태스크 리스트를 반환합니다.
- Path Variables: 
    - userUUID: 사용자 UUID
- Response Code: 200 ok
- Response Body: JSON 형식 응답

    |파라미터|타입|필수 여부|설명|
    |---|---|-|-----|
    |taskId|long|Y||
    |taskName|string|Y||
    |taskContent|string|Y|| 
    |taskCreationDate|datetime|Y||
    |taskEndDate|datetime|Y||
    |userUUID|string|Y||
    |projectId|long|Y||
</br>

### PUT /api/tasks/{taskId}
- Description: 태스크 ID로 조회하여 태스크 정보를 수정합니다.
- Path Variables: 
    - taskId: 태스크 ID
- Request Body: JSON 형식 요청
  
    |파라미터|타입|필수 여부|설명|
    |---|---|-|-----|
    |taskName|string|Y||
    |taskContent|string|Y|| 
    |taskCreationDate|datetime|Y||
    |taskEndDate|datetime|Y||
</br>

### DELETE /api/tasks/{taskId}
- Description: 태스크 ID로 대상 태스크 정보를 삭제합니다.
- Path Variables: 
    - taskId: 태스크 ID
- Response Code: 200 ok
- Response Body: None
</br>

### GET /api/tasks/{taskId}/milestones
- Description: 태스크 ID로 조회한 마일스톤 정보 리스트를 반환합니다.
- Path Variables: 
    - taskId: 태스크 ID
- Response Code: 200 ok
- Response Body: JSON 형식 응답

    |파라미터|타입|필수 여부|설명|
    |---|---|-|-----|
    |milestoneId|long|Y||
    |milestoneName|string|Y||
    |milestoneStartDate|datetime|Y||
    |milestoneEndDate|datetime|Y||
    |milestoneStatus|ACTIVE, INACTIVE, COMPLETED|Y||
    |projectId|long|Y||
</br>

### POST /api/tasks/{taskId}/tags/{tagId}
- Description: 태스크 ID로 조회한 태스크에 태그를 추가합니다.
- Path Variables: 
    - taskId: 태스크 ID
    - tagId: 태그 ID
- Response Code: 200 ok
- Response Body: None
</br>

### GET /api/tasks/{taskId}/tags
- Description: 태스크 ID로 조회한 태스크가 가지는 태그 리스트를 반환합니다.
- Path Variables: 
    - taskId: 태스크 ID
- Response Code: 200 ok
- Response Body: JSON 형식 응답

    |파라미터|타입|필수 여부|설명|
    |---|---|-|-----|
    |tagId|long|Y||
    |tagName|string|Y||
    |projectId|long|Y||
</br>

### DELETE /api/tasks/{taskId}/tags/{tagId}
- Description: 태스크가 가진 태그를 삭제합니다.
- Path Variables: 
    - taskId: 태스크 ID
    - tagId: 태그 ID
- Response Code: 200 ok
- Response Body: None
</br>

### POST /api/tasks/{taskId}/users/{userUUID}
- Description: 태스크 ID로 조회한 태스크에 사용자를 추가합니다.
- Path Variables: 
    - taskId: 태스크 ID
    - userUUID: 사용자 UUID
- Response Code: 200 ok
- Response Body: None

### GET /api/tasks/{taskId}/users
- Description: 태스크에 등록된 사용자 리스트를 반환합니다.
- Path Variables: 
    - taskId: 태스크 ID
- Response Code: 200 ok
- Response Body: JSON 형식 응답

    |파라미터|타입|필수 여부|설명|
    |---|---|-|-----|
    |userUUID|string|Y||
    |TaskId|long|Y||
</br>

### DELETE /api/tasks/{taskId}/users/{userUUID}
- Description: 태스크에 등록된 사용자를 삭제합니다.
- Path Variables: 
    - taskId: 태스크 ID
    - userUUID: 사용자 UUID
- Response Code: 200 ok
- Response Body: None
</br>



## tag

### POST /api/tags
- Description: 새로운 태그를 추가합니다.
- Path Variables: None
- Request Body: JSON 형식 요청
  
    |파라미터|타입|필수 여부|설명|
    |---|---|-|-----|
    |tagName|string|Y||
    |projectId|long|Y||

- Response Code: 200 ok
- Response Body: None

### GET /api/tags/{tagId}
- Description: 태그 ID로 조회한 태그 정보를 반환합니다.
- Path Variables: 
    - tagId: 태그 ID
- Response Code: 200 ok
- Response Body: JSON 형식 응답

    |파라미터|타입|필수 여부|설명|
    |---|---|-|-----|
    |tagId|long|Y||
    |tagName|string|Y||
    |projectId|long|Y||
</br>

### GET /api/tags/projects/{projectId}
- Description: 프로젝트 ID로 조회한 태그 정보 리스트를 반환합니다.
- Path Variables: 
    - projectId: 프로젝트 ID
- Response Code: 200 ok
- Response Body: JSON 형식 응답

    |파라미터|타입|필수 여부|설명|
    |---|---|-|-----|
    |tagId|long|Y||
    |tagName|string|Y||
    |projectId|long|Y||

### PUT /api/tags/{tagId}
- Description: 태그 ID로 조회하여 태그 정보를 수정합니다.
- Path Variables: 
    - tagId: 태그 ID
- Request Body: JSON 형식 요청
  
    |파라미터|타입|필수 여부|설명|
    |---|---|-|-----|
    |tagName|string|Y||

- Response Code: 200 ok
- Response Body: None
</br>

### DELETE /api/tags/{tagId}
- Description: 태그 ID로 대상 태그 정보를 삭제합니다.
- Path Variables: 
    - tagId: 태그 ID
- Response Code: 200 ok
- Response Body: None
</br>

### GET /api/tags/{tagId}/tasks
- Description: 해당 태그를 가진 태스크 리스트를 반환합니다.
- Path Variables: 
    - tagId: 태그 ID
- Response Code: 200 ok
- Response Body: JSON 형식 응답

    |파라미터|타입|필수 여부|설명|
    |---|---|-|-----|
    |taskId|long|Y||
    |taskName|string|Y||
    |taskContent|string|Y||
    |taskCreationDate|datetime|Y||
    |taskEndDate|datetime|Y||
    |userUUID|string|Y||
    |projectId|long|Y||
</br>



## milestone

### POST /api/milestones
- Description: 새로운 마일스톤을 추가합니다.
- Path Variables: None
- Request Body: JSON 형식 요청
  
    |파라미터|타입|필수 여부|설명|
    |---|---|-|-----|
    |milestoneName|string|Y||
    |milestoneStartDate|datetime|Y|| 
    |milestoneEndDate|datetime|Y||
    |milestoneStatus|ACTIVE, INACTIVE, COMPLETED|Y||
    |projectId|long|Y||

- Response Code: 200 ok
- Response Body: None
</br>

### GET /api/milestones/{milestoneId}
- Description: 마일스톤 ID로 조회한 마일스톤 정보를 반환합니다.
- Path Variables: 
    - milestoneId: 마일스톤 ID
- Response Code: 200 ok
- Response Body: JSON 형식 응답

    |파라미터|타입|필수 여부|설명|
    |---|---|-|-----|
    |milestoneId|long|Y||
    |milestoneName|string|Y||
    |milestoneStartDate|datetime|Y|| 
    |milestoneEndDate|datetime|Y||
    |milestoneStatus|ACTIVE, INACTIVE, COMPLETED|Y||
    |projectId|long|Y||
</br>

### GET /api/milestones/projects/{projectId}
- Description: 프로젝트 ID로 조회한 마일스톤 정보 리스트를 반환합니다.
- Path Variables: 
    - projectId: 프로젝트 ID
- Response Code: 200 ok
- Response Body: JSON 형식 응답

    |파라미터|타입|필수 여부|설명|
    |---|---|-|-----|
    |milestoneId|long|Y||
    |milestoneName|string|Y||
    |milestoneStartDate|datetime|Y|| 
    |milestoneEndDate|datetime|Y||
    |milestoneStatus|ACTIVE, INACTIVE, COMPLETED|Y||
    |projectId|long|Y||
</br>

### PUT /api/milestones/{milestoneId}
- Description: 마일스톤 ID로 조회하여 마일스톤 정보를 수정합니다.
- Path Variables: 
    - milestoneId: 마일스톤 ID
- Request Body: JSON 형식 요청
  
    |파라미터|타입|필수 여부|설명|
    |---|---|-|-----|
    |milestoneName|string|Y||
    |milestoneStartDate|datetime|Y|| 
    |milestoneEndDate|datetime|Y||
    |milestoneStatus|ACTIVE, INACTIVE, COMPLETED|Y||

- Response Code: 200 ok
- Response Body: None
</br>

### DELETE /api/milestones/{milestoneId}
- Description: 마일스톤 ID로 대상 마일스톤 정보를 삭제합니다.
- Path Variables: 
    - milestoneId: 마일스톤 ID
- Response Code: 200 ok
- Response Body: None
</br>

### POST /api/milestones/{milestoneId}/tasks/{taskId}
- Description: 마일스톤에 태스크를 추가합니다.
- Path Variables: 
    - milestoneId: 마일스톤 ID
    - taskId: 태스크 ID
- Response Code: 200 ok
- Response Body: None

### GET /api/milestones/{milestoneId}/tasks
- Description: 마일스톤 ID로 조회한 태스크 정보 리스트를 반환합니다.
- Path Variables: 
    - milestoneId: 마일스톤 ID
- Response Code: 200 ok
- Response Body: JSON 형식 응답

    |파라미터|타입|필수 여부|설명|
    |---|---|-|-----|
    |taskName|string|Y||
    |taskContent|string|Y||
    |taskCreationDate|datetime|Y||
    |taskEndDate|datetime|Y||
</br>

### DELETE /api/milestones/{milestoneId}/tasks/{taskId}
- Description: 태스크에 등록된 마일스톤을 삭제합니다.
- Path Variables: 
    - milestoneId: 마일스톤 ID
    - taskId: 태스크 ID
- Response Code: 200 ok
- Response Body: None
</br>

## comment

### POST /api/comments
- Description: 새로운 코멘트를 추가합니다.
- Path Variables: None
- Request Body: JSON 형식 요청
  
    |파라미터|타입|필수 여부|설명|
    |---|---|-|-----|
    |commentContent|string|Y||
    |commentCreationDate|string|Y||
    |userUUID|string|Y||
    |taskId|long|Y||

- Response Code: 200 ok
- Response Body: None

### GET /api/comments/{commentId}
- Description: 코멘트 ID로 조회한 코멘트 정보를 반환합니다.
- Path Variables: 
    - commentId: 코멘트 ID
- Response Code: 200 ok
- Response Body: JSON 형식 응답

    |파라미터|타입|필수 여부|설명|
    |---|---|-|-----|
    |commentId|long|Y||
    |commentContent|string|Y||
    |commentCreationDate|string|Y||
    |userUUID|string|Y||
    |taskId|long|Y||
</br>

### GET /api/comments/tasks/{taskId}
- Description: 태스크 ID로 조회한 코멘트 정보 리스트를 반환합니다.
- Path Variables: 
    - taskId: 태스크 ID
- Response Code: 200 ok
- Response Body: JSON 형식 응답

    |파라미터|타입|필수 여부|설명|
    |---|---|-|-----|
    |commentId|long|Y||
    |commentContent|string|Y||
    |commentCreationDate|string|Y||
    |userUUID|string|Y||
    |taskId|long|Y||

### PUT /api/comments/{commentId}
- Description: 코멘트 ID로 조회하여 코멘트 정보를 수정합니다.
- Path Variables: 
    - commentId: 코멘트 ID
- Request Body: JSON 형식 요청
      
     |파라미터|타입|필수 여부|설명|
     |---|---|-|-----|
     |commentContent|string|Y||
     |commentCreationDate|string|Y||
     |userUUID|string|Y||

- Response Code: 200 ok
- Response Body: None
</br>

### DELETE /api/comments/{commentId}
- Description: 코멘트 ID로 대상 코멘트 정보를 삭제합니다.
- Path Variables: 
    - commentId: 코멘트 ID
- Response Code: 200 ok
- Response Body: None

