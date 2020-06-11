import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Locale;
import java.util.Scanner;

public class Main {


    public static void main(String[] args) throws FileNotFoundException {

        File fileTinyEWG = new File("res/tinyEWG.txt");
        File fileTinyFN = new File("res/tinyFN.txt");
        File fileMediumEWG = new File("res/mediumEWG.txt");

        Scanner scanner = new Scanner(System.in);
        Dijkstra dijkstra = new Dijkstra(0);

        int fileInput = 0;
        int userInput = 0;
        int startInput = 0;
        int endInput = 0;
        int exit = -1;

        /*
         * Choose one of the predefined files, because they have
         * the right format for the algorithm to work properly
         * */
        while(fileInput != exit){
            System.out.println("\nPlease choose a file:" +
                    "\n(1) TinyEWG.txt" +
                    "\n(2) TinyFN.txt" +
                    "\n(3) mediumEWG.txt");
            fileInput = scanner.nextInt();

            switch(fileInput){
                case 1:
                    System.out.println("File path: " + fileTinyEWG.getAbsolutePath());
                    // create initial graph
                    Scanner reader = new Scanner(new FileReader(fileTinyEWG)).useLocale(Locale.US);
                    dijkstra.setN(reader.nextInt());
                    reader.nextInt();
                    while(reader.hasNext()){
                        dijkstra.addEdge(reader.nextInt(), reader.nextInt(), reader.nextDouble());
                    }
                    fileInput = exit;
                    break;
                case 2:
                    System.out.println("File path: " + fileTinyFN.getAbsolutePath());
                    // create initial graph
                    Scanner reader1 = new Scanner(new FileReader(fileTinyFN)).useLocale(Locale.US);
                    dijkstra.setN(reader1.nextInt());
                    reader1.nextInt();
                    while(reader1.hasNext()){
                        dijkstra.addEdge(reader1.nextInt(), reader1.nextInt(), reader1.nextDouble());
                    }
                    fileInput = exit;
                    break;
                case 3:
                    System.out.println("File path: " + fileMediumEWG.getAbsolutePath());
                    // create initial graph
                    Scanner reader2 = new Scanner(new FileReader(fileMediumEWG)).useLocale(Locale.US);
                    dijkstra.setN(reader2.nextInt());
                    reader2.nextInt();
                    while(reader2.hasNext()){
                        dijkstra.addEdge(reader2.nextInt(), reader2.nextInt(), reader2.nextDouble());
                    }
                    fileInput = exit;
                    break;
                default:
                    break;
            }
        }

        /*
        * Choose a starting and destination point for shortest path
        * */
        System.out.println("Dijkstra's algorithm (directed graph)");

        while(userInput != exit){
            System.out.println("\tSpecify a starting point: ");
            userInput = scanner.nextInt();
            if(userInput < 0 || userInput > dijkstra.getGraph().size() - 1){
                System.out.println("Not a valid starting point.");
                break;
            }
            startInput = userInput;
            userInput = exit;
        }

        userInput = 0;

        while(userInput != exit){
            System.out.println("\tSpecify a destination point: ");
            userInput = scanner.nextInt();
            if(userInput < 0 || userInput > dijkstra.getGraph().size() - 1){
                System.out.println("Not a valid ending point.");
                break;
            }
            endInput = userInput;
            userInput = exit;
        }

        dijkstra.dijkstra(startInput, endInput);
        System.out.println(dijkstra.reconstructPath(startInput, endInput).toString());
    }
}
