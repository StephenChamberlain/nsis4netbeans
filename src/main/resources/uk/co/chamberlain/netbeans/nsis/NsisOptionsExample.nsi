; Single line comment

/*
  Multi-line comment
*/
Name "String"

Page directory
Page instfiles

Section "Section1"
  SetOutPath $INSTDIR
  File example.txt
SectionEnd

Function
    plugin::pluginCall
FunctionEnd