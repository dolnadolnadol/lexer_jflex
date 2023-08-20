@ echo off
rm testmaybe.java
rm testmaybe.class
START /B /WAIT cmd /c jflex test.jflex
javac testmaybe.java
java testmaybe testcode.txt