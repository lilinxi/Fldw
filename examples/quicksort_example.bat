chdir %~dp0
echo %cd%
echo "QuickSort Example:"
type ..\src\main\java\examples\quicksort.fldw
echo "=========================================="
echo "Input Number(Int or Double) Flow:"
java -jar ..\jar\Fldw-0.0.5.jar ..\src\main\java\examples\quicksort.fldw
