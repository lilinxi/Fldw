chdir %~dp0
echo %cd%
echo "While Example:"
type ..\src\main\java\examples\while.fldw
echo "=========================================="
echo "Input Number(Int or Double) Flow:"
java -jar ..\jar\Fldw-0.0.5.jar ..\src\main\java\examples\while.fldw