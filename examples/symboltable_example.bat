chdir %~dp0
echo %cd%
echo "SymbolTable Example:\n"
type ..\src\main\java\examples\symboltable.fldw
echo "\n\n=========================================="
echo "Expect Output:"
echo "4"
echo "null"
echo "=========================================="
echo "Actual Output:"
java -jar ..\jar\Fldw-1.0.0.jar ..\src\main\java\examples\symboltable.fldw