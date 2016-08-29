package node;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;
import java.util.*;

public class DependencySolver {
    private File xml;
    private List<Node> nodes = new ArrayList<>();
    private List<Node> solved = new ArrayList<>();
    private Map<Node, List<String>> tempDep = new HashMap<>();
    private static int node_count = 0;

    public DependencySolver(File file) {
        xml = file;
    }

    public void parse() throws ParserConfigurationException, SAXException, IOException {

        NodeList nodeList = getXMLData();

        for (int temp = 0; temp < nodeList.getLength(); temp++)
            addNodeFromElement((Element) nodeList.item(temp));

    }

    private NodeList getXMLData() throws SAXException, ParserConfigurationException, IOException {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();

        System.out.println("Parsing XML document...");  //Parsing the document here....using inbuilt library DOCUMENTBUILDER
        
        Document document = builder.parse(xml);
        document.getDocumentElement().normalize();

        return document.getElementsByTagName("node");
    }

    private void addNodeFromElement(Element element) throws IllegalStateException {
        String id = element.getAttribute("id");
        NodeList names =  element.getElementsByTagName("name");
        if(names.getLength() == 0) throw new IllegalStateException("Missing name tag "); 

        // Can add here option to throw error where more than 1 name tag is found.
        // Similarly can be done for id tag
        
        String name =names.item(0).getTextContent();

        Node node = new Node(id, name);
        
        System.out.println("\tCreated Node:"+ ++node_count + "\n\t\t"+ "ID: "+node.getId() + "\n\t\tNAME: "+node.getName());//Printing the nodes created here 
        
        if(nodes.contains(node)) {
            System.out.println("Skipping duplicate node : Node - " + node.getId() + " " + node.getName());
            return;
        }
        nodes.add(node);
        generateDependenciesFromElement(element, node);
    }

    private void generateDependenciesFromElement(Element element, Node node) {
        
        

        NodeList dp = element.getElementsByTagName("depends");
        List<String> dependencies = new ArrayList<>();
        for (int i = 0; i < dp.getLength(); i++) {
            dependencies.add(dp.item(i).getTextContent());
        }
        tempDep.put(node, dependencies);
    }

    public void makeDependencies() throws NullPointerException {
        
        
        for(Node node : nodes){
            System.out.println("\tGenerating the dependencies of node: "+"ID: "+node.getId() + " NAME: "+node.getName());
            for(String name : tempDep.get(node)){

                if(getNode(name) == null)
                    throw new NullPointerException();

                node.addDependency(getNode(name));
               
            }
            System.out.print("\tGenerated dependencies: ");
            if(node.getDependencies().size() >0){
                for(Node n : node.getDependencies()){
                    System.out.print(" "+n.getName());
                }
            }else
                System.out.print(" NULL");

            System.out.println();
        }

    }

    public List<Node> getNodes() {
        return nodes;
    }

    public List<Node> getSolvedNodes() throws IllegalStateException {

        System.out.println("\n\nSolving the dependencies..\n");


        List<Node> unsolved = new ArrayList<>(nodes);
        print(unsolved,"UnsolvedNodes");

        while (unsolved.size()>0){
            
            Node solvedNode = getLeastDependentNode();

            System.out.println("\tFinding the Least depenedent node ---> " + solvedNode.getName() + " ---> Adding to solved nodes");
            

            if(solvedNode == null)
                throw new IllegalStateException("Cyclic Dependency");

            for(Node node : unsolved) {
                Set<Node> dependencies = node.getDependencies();
                if(dependencies.contains(solvedNode))
                    dependencies.remove(solvedNode);
            }

            solved.add(solvedNode);
            unsolved.remove(solvedNode);
            print(solved,"SolvedNode" );
            print(unsolved,"UnsolvedNodes");
            System.out.println();
            
        }

        return solved;
    }

    private Node getLeastDependentNode() {
        for(Node node : nodes) {
            if(!solved.contains(node) && node.getDependencies().size()==0)
                return node;
        }

        return null;
    }

    private Node getNode(String name) {
        for(Node node : nodes) {
            if(node.getName().equals(name))
                return node;
        }
        return null;
    }

    private void print(List<Node> any , String tag){

        System.out.print("\t"+tag+" :-> ");
        if (any.size()>0){
            for(Node n : any){
                System.out.print(n.getName()+" ");
            }
        }else
            System.out.print("NULL ");

        System.out.println();
    }
}
