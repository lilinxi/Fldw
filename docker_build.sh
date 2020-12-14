docker run -it adoptopenjdk/openjdk15 /bin/bash
apt-get update
apt-get install git
git clone https://github.com/lilinxi/Fldw.git
exit

docker commit -a "imortal" -m "fldw v1.0.0" 9f4fedb981dd fldw:v1.0.0
docker images fldw:v1.0.0
docker tag 3c1c47316fc2 imortal/fldw:v1.0.0
docker push imortal/fldw:v1.0.0

docker pull imortal/fldw:v1.0.0
docker run -it --rm imortal/fldw:v1.0.0 /bin/bash