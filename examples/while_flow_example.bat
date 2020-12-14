chdir %~dp0
echo %cd%
echo "While_Flow Example:"
type ..\src\main\java\examples\while_flow.fldw
echo "=========================================="
java -jar ..\jar\Fldw-0.0.5.jar ..\src\main\java\examples\while_flow.fldw