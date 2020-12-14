chdir %~dp0
echo %cd%
echo "FuncDynamicParam Example:\n"
type ..\src\main\java\examples\func_dynamic_param.fldw
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
java -jar ..\jar\Fldw-1.0.0.jar ..\src\main\java\examples\func_dynamic_param.fldw