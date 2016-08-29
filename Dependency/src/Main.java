import node.Node;
import node.DependencySolver;

import java.io.File;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        DependencySolver xmlParser = new DependencySolver(new File("/home/divy/Downloads/Dependency/dependency.xml"));
        try {
            xmlParser.parse();
            xmlParser.makeDependencies();

            List<Node> nodes = xmlParser.getSolvedNodes();
            for(Node node : nodes) {
                System.out.print(node.getName() + " -> ");
            }
            System.out.println("END");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
