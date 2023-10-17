@ echo off
rm generatejava.java
rm generatejava.class
START /B /WAIT cmd /c jflex test.jflex
javac generatejava.java
java generatejava testcase.txt