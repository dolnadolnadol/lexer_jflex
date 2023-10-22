import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class kireee{
    public static void main(String[] args) throws FileNotFoundException{
        File inputFile = new File("C:\\Users\\jack-\\OneDrive\\เอกสาร\\work\\testcode.txt");
        Scanner sc = new Scanner(inputFile);
        ArrayList<String> symbolTable = new ArrayList<String>();
        ArrayList<String> operator = new ArrayList<String>(Arrays.asList("+", "-","/","*","=","<=",">","<",">="));
        ArrayList<String> keyword = new ArrayList<String>(Arrays.asList("if", "endif","then","else","while","endwhile","do","print","newline","read"));
        while (sc.hasNextLine()) {
            String line = sc.nextLine();
            String[] parts = line.split(" ");
            String strTemp = "";
            int commentCheck = 0;
            int strCheck = 0;
            for (String word : parts) {
                if (operator.contains(word)) {
                    System.out.println("operator: " + word);
                } else if (keyword.contains(word)) {
                    System.out.println("keyword: " + word);
                } else if (word.matches("\\d+")) {
                    System.out.println("integer: " + word);
                } else if (word.startsWith("\"") && strCheck == 0) {
                    strTemp += word;
                    strCheck=1;
                } else if (word.endsWith("\"") && strCheck == 1) {
                    strTemp += " "+word;
                    System.out.println("string: " + strTemp);
                    strTemp = "";
                    strCheck = 0;
                } else if (strCheck == 1) {
                    strTemp += " "+word;
                } else if (word.startsWith("//")){
                    break;
                } else if(word.startsWith("*\\")){
                    commentCheck = 0;
                } else if (word.startsWith("/*")|| commentCheck == 1) {
                    commentCheck = 1;
                } else if (word.equals(";") || word.equals("(") || word.equals(")")) {
                    System.out.println("brackets, semicolon: " + word);
                } else if (word.matches("[a-zA-Z_][a-zA-Z0-9_]*")) {
                    if (symbolTable.contains(word)) {
                        System.out.println("identifier \"" + word + "\" already in symbol table");
                    } else {
                        System.out.println("new identifier: " + word);
                        symbolTable.add(word);
                    }
                } else if (word.matches("([0-9]+) + (\".\") + ([0-9]+)?") || word.matches("[0-9] + ([A-Za-z0-9]+)?") ) {
                    System.out.println("error character: " + word);
                    System.out.println("Terminated");
                    System.exit(1);

                } else {
                    if(word.matches("\\d+"))
                    System.out.println("error character: " + word);
                    System.out.println("Terminated");
                    System.exit(1);
                }
            }
        }
        sc.close();
    }
}