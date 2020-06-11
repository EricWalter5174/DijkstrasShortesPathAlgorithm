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

        Dijkstra dijkstra = null;
        Dijkstra dijkstra1 = null;
        Dijkstra dijkstra2 = null;

        Scanner scanner = new Scanner(System.in);

        int fileInput = 0;
        int userInput = 0;
        int startInput = 0;
        int endInput = 0;
        int exit = -1;

        /*
         * Choose one of the predefined files, because they have
         * the right format for the algorithm to work properly
         * */
        while(userInput != exit){
            System.out.println("\nPlease choose a file:" +
                    "\n(1) TinyEWG.txt" +
                    "\n(2) TinyFN.txt" +
                    "\n(3) mediumEWG.txt");
            userInput = scanner.nextInt();

            switch(userInput){
                case 1:
                    System.out.println("File path: " + fileTinyEWG.getAbsolutePath());
                    // create initial graph
                    Scanner reader = new Scanner(new FileReader(fileTinyEWG)).useLocale(Locale.US);
                    dijkstra = new Dijkstra(reader.nextInt());
                    reader.nextInt();
                    while(reader.hasNext()){
                        dijkstra.addEdge(reader.nextInt(), reader.nextInt(), reader.nextDouble());
                    }
                    fileInput = 1;
                    userInput = exit;
                    break;
                case 2:
                    System.out.println("File path: " + fileTinyFN.getAbsolutePath());
                    // create initial graph
                    Scanner reader1 = new Scanner(new FileReader(fileTinyFN)).useLocale(Locale.US);
                    dijkstra1 = new Dijkstra(reader1.nextInt());
                    reader1.nextInt();
                    while(reader1.hasNext()){
                        dijkstra1.addEdge(reader1.nextInt(), reader1.nextInt(), reader1.nextDouble());
                    }
                    fileInput = 2;
                    userInput = exit;
                    break;
                case 3:
                    System.out.println("File path: " + fileMediumEWG.getAbsolutePath());
                    // create initial graph
                    Scanner reader2 = new Scanner(new FileReader(fileMediumEWG)).useLocale(Locale.US);
                    dijkstra2 = new Dijkstra(reader2.nextInt());
                    reader2.nextInt();
                    while(reader2.hasNext()){
                        dijkstra2.addEdge(reader2.nextInt(), reader2.nextInt(), reader2.nextDouble());
                    }
                    fileInput = 3;
                    userInput = exit;
                    break;
                default:
                    break;
            }
        }

        /*
        * Choose a starting and destination point for shortest path
        * */
        System.out.println("Dijkstra's algorithm (directed graph)");

        userInput = 0;

        while(userInput != exit){
            System.out.println("\tSpecify a starting point: ");
            userInput = scanner.nextInt();
            if(fileInput == 1){
                if(userInput < 0 || userInput > dijkstra.getGraph().size() - 1){
                    System.out.println("Not a valid starting point.");
                    break;
                }
                startInput = userInput;
                userInput = exit;
            }
            if(fileInput == 2){
                if(userInput < 0 || userInput > dijkstra1.getGraph().size() - 1){
                    System.out.println("Not a valid starting point.");
                    break;
                }
                startInput = userInput;
                userInput = exit;
            }
            if(fileInput == 3){
                if(userInput < 0 || userInput > dijkstra2.getGraph().size() - 1){
                    System.out.println("Not a valid starting point.");
                    break;
                }
                startInput = userInput;
                userInput = exit;
            }
        }

        userInput = 0;

        while(userInput != exit){
            System.out.println("\tSpecify a destination point: ");
            userInput = scanner.nextInt();
            if(fileInput == 1){
                if(userInput < 0 || userInput > dijkstra.getGraph().size() - 1){
                    System.out.println("Not a valid ending point.");
                    break;
                }
                endInput = userInput;

                dijkstra.dijkstra(startInput, endInput);
                System.out.println(dijkstra.reconstructPath(startInput, endInput).toString());
                userInput = exit;
            }
            if(fileInput == 2){
                if(userInput < 0 || userInput > dijkstra1.getGraph().size() - 1){
                    System.out.println("Not a valid ending point.");
                    break;
                }
                endInput = userInput;

                dijkstra1.dijkstra(startInput, endInput);
                System.out.println(dijkstra1.reconstructPath(startInput, endInput).toString());
                userInput = exit;
            }
            if(fileInput == 3){
                if(userInput < 0 || userInput > dijkstra2.getGraph().size() - 1){
                    System.out.println("Not a valid ending point.");
                    break;
                }
                endInput = userInput;

                dijkstra2.dijkstra(startInput, endInput);
                System.out.println(dijkstra2.reconstructPath(startInput, endInput).toString());
                userInput = exit;
            }

        }


    }
}
