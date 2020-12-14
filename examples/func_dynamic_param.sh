cd $(dirname $0)
echo "FuncDynamicParam Example:\n"
cat ../src/main/java/examples/func_dynamic_param.fldw
echo "\n\n=========================================="
echo "Expect Output:"
echo "1
2
null
"-----"
1
2
3
"-----"
null
null
null"
echo "=========================================="
echo "Actual Output:"
java -jar ../jar/Fldw-1.0.0.jar ../src/main/java/examples/func_dynamic_param.fldw