# Git 설정 확인
- git config --list

# Git 계정 정보 저장
- 현재 레포지터리에 대해서만 영구 적용
  - git config credential.helper store
- 모든 프로젝트에 영구 적용
  - git config credential.helper store --global


# Git status 한글 깨짐
- git config --global core.quotepath false


# Git 개행문자
## Windows CRLF(\r\n), Linux(\n)

### Windows
- git config --global core.autocrlf true

### Linux, Max
- git config --global core.autocrlf input

### Windows 에서만 개발
- git config --global core.autocrlf false

# 로컬 브랜치 삭제 명령어
- git branch -d dexx(local branch name)

# 원격 브랜치 삭제 명령어
- gir branch --delete dexx(remote branch name)

# Git Cache 삭제 명령어 -> add -> commit
- git rm -r --cached .