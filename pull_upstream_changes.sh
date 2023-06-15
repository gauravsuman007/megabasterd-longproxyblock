git remote add upstream https://github.com/tonikelope/megabasterd.git
#Verbose way
git fetch upstream
git merge upstream/master master

#Rebase way - not working
#git rebase upstream/master
