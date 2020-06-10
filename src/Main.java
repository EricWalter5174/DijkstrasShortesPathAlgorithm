import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Locale;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws FileNotFoundException {
        Scanner reader = new Scanner(new FileReader("C:\\Users\\Eric\\IdeaProjects\\DijkstrasShortesPathAlgorithm\\res\\mediumEWG.txt")).useLocale(Locale.US);
        Dijkstra dijkstra = new Dijkstra(reader.nextInt());
        reader.nextInt();
        while(reader.hasNext()){
            dijkstra.addEdge(reader.nextInt(), reader.nextInt(), reader.nextDouble());
        }
        dijkstra.dijkstra(0,dijkstra.getGraph().size()-1);
        
        dijkstra.reconstructPath(0, dijkstra.getGraph().size()-1);
    }
}
