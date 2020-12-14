cd $(dirname $0)
echo "Cat Example:\n"
cat ../src/main/java/examples/cat.fldw
echo "\n\n=========================================="
echo "Input Flow:"
java -jar ../jar/Fldw-1.0.0
.jar ../src/main/java/examples/cat.fldw