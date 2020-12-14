cd $(dirname $0)
echo "While Example:\n"
cat ../src/main/java/examples/while.fldw
echo "\n\n=========================================="
echo "Expect Output:"
echo "1
2
"---"
2
2
"
echo "=========================================="
echo "Actual Output:"
java -jar ../jar/Fldw-1.0.0.jar ../src/main/java/examples/while.fldw