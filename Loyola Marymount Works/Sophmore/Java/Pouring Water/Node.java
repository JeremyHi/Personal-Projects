import java.util.   ArrayList;
import java.util.   List;

public class Node<Integer> {
    private List<Node<Integer>> children = new ArrayList<Node<Integer>>();
    private Node<Integer> parent = null;
    private Integer data = null;

    public Node(Integer data) {
        this.data = data;
    }

    public Node(Integer data, Node<Integer> parent) {
        this.data = data;
        this.parent = parent;
    }

    public List<Node<Integer>> getChildren() {
        return children;
    }

    public void setParent(Node<Integer> parent) {
        parent.addChild(this);
        this.parent = parent;
    }

    public void addChild(Integer data) {
        Node<Integer> child = new Node<Integer>(data);
        child.setParent(this);
        this.children.add(child);
    }

    public void addChild(Node<Integer> child) {
        child.setParent(this);
        this.children.add(child);
    }

    public Integer getData() {
        return this.data;
    }
}