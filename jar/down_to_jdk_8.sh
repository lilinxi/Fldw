cd $(dirname $0)
java -jar ./retrotranslator-runtime-1.2.3-bundle.jar -srcjar ./Fldw-0.0.5.jar -destjar ./Fldw-0.0.5-8.jar -embed com.cn.internal -target 8

docker run -it --rm -v /Users/limengfan/IdeaProjects/201122_Fldw/target:/fldw java /bin/bash