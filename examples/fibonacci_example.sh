cd $(dirname $0)
echo "Fibonacci Example:\n"
cat ../src/main/java/examples/fibonacci.fldw
echo "\n\n=========================================="
echo "Expect Output:"
echo "0
1
1
2
3
5
8
13
21
34"
echo "=========================================="
echo "Actual Output:"
java -jar ../jar/Fldw-1.0.0.jar ../src/main/java/examples/fibonacci.fldw