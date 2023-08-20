import java.io.*;
import java.util.ArrayList;
%%

%class testmaybe
%standalone
%unicode
%char
%{
    ArrayList<String> symbolTable = new ArrayList<String>();
%}

operator = \+ | \- | \* | \/ | = | <= | >= | < | > | == | \++ | \--
reserved =  "if"|"then"|"else"|"endif"|"while"|"do"|"endwhile"|"print"|"newline"|"read" | "for" 
NUM = [0-9]+ ("." [0-9]+)?
LineTerminator = \r|\n|\r\n
whiteSpace = {LineTerminator} | [ \t\f]

%%

{operator}                          {   System.out.println("operator: "+ yytext()); }
<YYINITIAL> {reserved}              {   System.out.println("keyword: "+yytext());   }
{NUM}                               {   System.out.println("Integer: "+yytext());   }
[A-Za-z] + ([A-Za-z0-9])?           { 
                                        String identifier = yytext();
                                        if (symbolTable.contains(identifier)) {
                                            System.out.println("identifier \"" + identifier + "\" already in symbol table");
                                        } else {
                                            System.out.println("new identifier: " + yytext());
                                            symbolTable.add(identifier);
                                        }
                                    }
\"[^\"]*\"                          { System.out.println("string: " + yytext()); }
\( | \) | ;                         { System.out.println("semi or colon: " + yytext()); }
\/\*.*\*\/                          {/* ignore comments */}
{whiteSpace}                        { /* ignore newlines */ }
.                                   { 
                                        System.out.println("ERROR CHARACTERS: " + yytext());
                                        System.out.println("TERMINATE");
                                        System.exit(1);
                                        
                                    }