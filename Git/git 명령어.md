# Git 설정 확인
- git config --list

# Git 계정 정보 저장
- 현재 레포지터리에 대해서만 영구 적용
  - git config credential.helper store
- 모든 프로젝트에 영구 적용
  - git config credential.helper store --global


# Git status 한글 깨짐
- git config --global core.quotepath false