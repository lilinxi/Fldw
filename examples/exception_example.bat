chdir %~dp0
echo %cd%
echo "Exception Example:\n"
type ..\src\main\java\examples\exception.fldw
echo "\n\n=========================================="
echo "Expect Output:"
echo "duplicate symbol:sym, Flow, Data"
echo "=========================================="
echo "Actual Output:"
java -jar ..\jar\Fldw-1.0.0.jar ..\src\main\java\examples\exception.fldw