package node;

import java.util.HashSet;
import java.util.Set;

public class Node {
    private String id;
    private String name;
    private Set<Node> dependencies = new HashSet<>();

    public Node(String id) {
        this.id = id;
    }

    public Node(String id, String name) {
        this.id = id;
        this.name = name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public String getId() {
        return id;
    }

    public void addDependency(Node node) {
        dependencies.add(node);
    }

    @Override
    public boolean equals(Object obj) {
        if(obj instanceof Node){
            return name.equals(((Node) obj).name) || id.equals(((Node) obj).id);
        }
        return false;
    }

    public Set<Node> getDependencies() {
        return dependencies;
    }

    @Override
    public String toString() {
        String node = "ID : " + id + "\n" +
                      "Name : " + name + "\n" +
                      "Depends On : ";
        for(Node n : dependencies) {
            node += n.getName() + " ";
        }

        return node;
    }
}
