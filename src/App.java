import java.util.Random;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.io.File;

public class App {
    
    public static int[] createRandomArray(int arrayLength){
        
        Random random = new Random();
        int[] array = new int[arrayLength];

        for(int i = 0; i < arrayLength; i++){
            array[i] = random.nextInt(101);
        }
        
        return array;
    }

    public static int[] readFileToArray(String filename){

        ArrayList<Integer> read = new ArrayList<>();

        try{
            Scanner reader = new Scanner(new File(filename));
            while(reader.hasNextLine()){
                String data = reader.nextLine();
                read.add(Integer.parseInt(data.trim()));
            }
            reader.close();
        }catch(FileNotFoundException e){
            System.out.println("An error has occured.");
            e.printStackTrace();
        }

        int[] array = new int[read.size()];
        for(int i = 0; i < read.size(); i++){
            array[i] = read.get(i);
        }

        return array;
    }

    public static void writeArrayToFile(int[] array, String filename){

        try{
            FileWriter writer = new FileWriter(filename + ".txt");
            for(int i : array){
                writer.write(String.valueOf(i));
                writer.write("\n");
            }
            writer.close();
            System.out.println("Successfuly written to: " + filename);
        }catch(IOException e){
            System.out.println("An error has Occured.");
            e.printStackTrace();
        }
    }

    public static void bubbleSort(int[] array){

        int l = array.length;
        boolean moved;

        for(int i = 0; i < l-1; i++){
            moved = false;
            for(int j = 0; j < l-1; j++){
                if(array[j] > array[j+1]){
                    int swapping = array[j];
                    array[j] = array[j+1];
                    array[j+1] = swapping;
                    moved = true;
                }
            }
            if(!moved){
                break;
            }
        }
    }

    public static void mergeSort(int[] array){
        mergeSort(array, 0, array.length);
    }

    public static void mergeSort(int[] array, int start, int end){
        if(end - start <= 1){
            return;
        }
        int middle = (start + end)/2;
        mergeSort(array, start, middle);
        mergeSort(array, middle, end);
        merge(array, start, middle, end);
    }

    public static void merge(int[] array, int start, int middle, int end){
        int i = start, j = middle, k = 0;
        int[] tempArray = new int[end-start];
        while(i < middle && j < end){
            if(array[i] <= array[j]){
                tempArray[k] = array[i];
                i++;
            }
            else{
                tempArray[k] = array[j];
                j++;
            }
            k++;
        }

        while(i < middle){
            tempArray[k] = array[i];
            i++;
            k++;
        }

        while(j < end){
            tempArray[k] = array[j];
            j++;
            k++;
        }

        for(i = start; i < end; i++){
            array[i] = tempArray[i - start];
        }
    }
    
    public static void main(String[] args) throws Exception {
       
        boolean using = true;

        Scanner main = new Scanner(System.in);

        while(using){
        String input;

        System.out.println("Please select a command from the list below\nand type the word in parenthesis ;\n\nWrite a new array to file w/ bubble sort (writeB)\nRead a file into an array w/ bubble sort (readB)\nWrite a new array to file w/ merge sort (writeM)\nRead a file into an array w/ merge sort (readM)\nStop (stop)");

        String filename;
        int arrayLength;

        input = main.nextLine();

        switch(input){
            
            case "writeB":
            System.out.print("Please enter the length of the array: ");
            arrayLength = Integer.parseInt(main.nextLine());

            System.out.print("Please enter desired file name: ");
            filename = main.nextLine();

            int[] randomBArray = createRandomArray(arrayLength);
            bubbleSort(randomBArray);
            writeArrayToFile(randomBArray, filename);

            break;

            case "readB":
            System.out.print("Enter the file to be read: ");
            filename = main.nextLine() + ".txt";

            int[] readBArray = readFileToArray(filename);
            bubbleSort(readBArray);
            for (int i : readBArray){
                System.out.println(i);
            }

            break;

            case "writeM":
            System.out.print("Please enter the length of the array: ");
            arrayLength = Integer.parseInt(main.nextLine());

            System.out.print("Please enter desired file name: ");
            filename = main.nextLine();

            int[] randomMArray = createRandomArray(arrayLength);
            mergeSort(randomMArray);
            writeArrayToFile(randomMArray, filename);

            break;

            case "readM":
            System.out.print("Enter the file to be read: ");
            filename = main.nextLine() + ".txt";

            int[] readMArray = readFileToArray(filename);
            mergeSort(readMArray);
            for (int i : readMArray){
                System.out.println(i);
            }

            break;

            case "stop":
            using = false;

            break;

            default:
            System.out.println("An error has occured.");

            break;

        }
        }
        main.close();
    }
}
