# [10분 테코톡] 렉스의 Git 브랜치 전략 보고 정리

## 대표적인 전략
- GitHub flow
- Git flow
- GitLab flow



### 1. GitHub flow
- 배포주기가 짧은 경우 유용하다.
- Master 브랜치에 작업용 브랜치를 만들어서 개발을 하고 Master에 PR 보내고 컨펌 전 배포가 가능해 개발서버, 스테이지 서버에서 미리 테스트 할 수 있다.


### 2. Git flow
- 배포주기가 길고 팀의 여력이 있는경우 적합
- 메인 브랜치 : master, develop
- 보조 브랜치 : feature, release, hotfix
- 메인 브랜치는 계속 유지 된다.
- develop 브랜치는 개발자들이 개발하는 브랜치
- 보조 브랜치는 사용 후 삭제한다.
- merge --no-ff 옵션 사용 다른 브랜치 머지한 이력을 남긴다.
- release 브랜치는 develop에서 분기된다.
- release 브랜치가 머지 되는곳은 develop, master이다.
- QA 진행하는 브랜치, merge 할 때는 -no-ff를 사용 기록을 그룹화
- master로 merge후에는 tag 명령을 통해 버전을 명시한다.

