cd $(dirname $0)
echo "While Flow Example:\n"
cat ../src/main/java/examples/while_flow.fldw
echo "\n\n=========================================="
echo "Expect Output:"
echo "1
1
null
null
"
echo "=========================================="
echo "Actual Output:"
java -jar ../jar/Fldw-1.0.0.jar ../src/main/java/examples/while_flow.fldw