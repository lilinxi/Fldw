cd $(dirname $0)
git add .
time=$(date "+%Y-%m-%d %H:%M:%S")
git commit -m"daily add at ${time}: $1"
git push origin v0.0.4