chdir %~dp0
echo %cd%
echo "Cat Example:\n"
type ..\src\main\java\examples\cat.fldw
echo "=========================================="
echo "Input Flow:"
java -jar ..\jar\Fldw-1.0.0.jar ..\src\main\java\examples\cat.fldw