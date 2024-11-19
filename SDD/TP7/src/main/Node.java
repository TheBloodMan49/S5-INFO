import java.util.ArrayList;
import java.util.List;

public class Node {

    private Color color;
    private Node parent;
    private List<Node> children;

    public Node(Color color, Node parent) {
        this.color = color;
        this.parent = parent;
        this.children = new ArrayList<>();
    }

    public Node(Color color) {
        this.color = color;
        this.parent = null;
        this.children = new ArrayList<>();
    }

    public Color getColor() {
        return this.color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public Node getParent() {
        return this.parent;
    }

    public void setParent(Node parent) {
        this.parent = parent;
    }

    public List<Node> getChildren() {
        return this.children;
    }

    public void setChildren(List<Node> children) {
        this.children = children;
    }


}
