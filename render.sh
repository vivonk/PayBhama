interg=6f047b84c4124961d71e31a8675528636f18fc86

git filter-branch --env-filter \
    'if [ $GIT_COMMIT = ${interg} ]
     then
         export GIT_AUTHOR_DATE="Fri Jun 2 21:38:53 2017 -0800"
         export GIT_COMMITTER_DATE="Sat May 19 01:01:01 2017 -0700"
     fi'

