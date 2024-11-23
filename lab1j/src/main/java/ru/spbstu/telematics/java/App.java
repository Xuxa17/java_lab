package ru.spbstu.telematics.java;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import static java.lang.Integer.parseInt;

public class App 
{
    public static void main( String[] args )
    {
        try (BufferedReader reader = new BufferedReader(new FileReader("/Users/kseniasklarova/lab1j/file.txt"))){
            String line;
            ArrayList<ArrayList<Integer>> matrix = new ArrayList<>();
            while ((line = reader.readLine()) != null) {
                ArrayList<Integer> lines = distribute_str(line);
                matrix.add(lines);
            }
            print_trans_matrix(matrix);
            } catch (IOException e){
                System.out.println("Exception");
            }
    }
    
    private static void print_trans_matrix(ArrayList<ArrayList<Integer>> matrix){
        for (int i = 0; i < matrix.size(); i++) {
            for (int j = 0; j < matrix.get(i).size(); j++) {
                System.out.print(matrix.get(j).get(i));
                System.out.print(" ");
            }
            System.out.println();
        }
    }

    private static ArrayList<Integer> distribute_str(String str){
        String[] strArr = str.split(" ");
        ArrayList<Integer> numArr = new ArrayList<>();
        for (String s : strArr) {
            numArr.add(parseInt(s));
        }
        return  numArr;
        //Arrays.stream(str.split(" ")).mapToInt(Integer::parseInt).toArray();
    }
}
