@echo off
title bat实现弹出输入框 code by 控盘惨
::===============================================================================
::================================软件设置区=====================================
:================================================================================
set hideform=False
::这里设置是否隐藏bat窗口,如果是请将"set hideform=False"改成"set hideform=True"即可.(建议不要立即就改,因为待会你要看看效果呢~)
set "message=请输入内容"
::对话框中要显示的内容
set "title=请输入"
::对话框的标题
set "note=啊哈哈哈哈"
::输入框中的内容注释[这里一般为空(set "note="),这是个测试,所以弄上了点字符.]
:===============================================================================
:===============================================================================
if /i "%hideform%"=="True" (
if "%1"=="h" goto begin
mshta vbscript:createobject^("wscript.shell"^).run^("%~nx0 h",0^)^(window.close^)&&exit
)
:begin
set "vbs=%Temp%\GotUserInputedDataProgram.vbs"
set "data=%Temp%\UserInputedData.tmp"
::====================================
::=========开始预置对话框=============
::====================================
if exist "%vbs%" del /s /q /f "%vbs%">nul
::delete file
echo On Error Resume Next>>"%vbs%"
echo Dim fso,wri,data,file>>"%vbs%"
echo Set fso=Wscript.CreateObject^("Scripting.FileSystemObject"^)>>"%vbs%"
echo file="%data%">>"%vbs%"
echo if fso.FileExists^(file^) Then fso.DeleteFile^(file^)>>"%vbs%"
echo Set wri=fso.CreateTextFile^(file,True^)>>"%vbs%"
echo data=InputBox^("%message%","%title%","%note%"^)>>"%vbs%"
echo wri.Write data>>"%vbs%"
echo wri.Close>>"%vbs%"
echo Set fso=Nothing>>"%vbs%"
echo Set op=Nothing>>"%vbs%"
echo fso.DeleteFile^(Wscript.ScriptFullName^)>>"%vbs%"
echo Wscript.Quit>>"%vbs%"
::Write down visual basic script file to temp folder.Let it put user inputed data bring here.
::====================================
::====================================
::这里写你的代码
::====================================
::====================================
call "%vbs%"
for /f "delims=" %%i in (%data%) do set "content=%%i"
if exist "%vbs%" del /s /q /f "%data%">nul
if exist "%vbs%" del /s /q /f "%vbs%">nul
::开始弹出对话框
::==============================================================================
::=====呵呵~内容获取成功!用户输入在对话框的内容会被保存在变量"%content%"中~=====
::==============================================================================
echo 你在输入框中输入的内容是"%content%",怎么样,厉害吧?
::==============================================================================
::==============================================================================
pause>nul