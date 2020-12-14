docker run -it adoptopenjdk/openjdk15 /bin/bash
apt-get update
apt-get install git
git clone https://github.com/lilinxi/Fldw.git
exit

docker commit -a "imortal" -m "fldw v0.0.5" 1283cfcf9bff fldw:v0.0.5
docker images fldw:v0.0.5
docker tag 00e5da98e693 imortal/fldw:v0.0.5
docker push imortal/fldw:v0.0.5

docker pull imortal/fldw:v0.0.5
docker run -it --rm imortal/fldw:v0.0.5 /bin/bash