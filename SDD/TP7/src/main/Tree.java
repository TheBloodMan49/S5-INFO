import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Tree implements QuadTree {

    public static void main(String[] args) throws IOException {
        Tree arbre = new Tree(new Color(255));
        arbre.recreate(new Image("picture.png"));
    }

    private Node root;
    private Node current;

    public Tree(Color c) {
        this.createRoot(c);
    }

    public Tree(Image image) {
        //this.createRoot();
    }

    @Override
    public boolean emptyTree() {
        return this.root == null;
    }

    @Override
    public boolean outOfTree() {
        return this.current == null;
    }

    @Override
    public void goToRoot() {
        this.current = this.root;
    }

    @Override
    public void goToParent() {
        this.current = this.current.getParent();
    }

    @Override
    public void goToChild(int i) {
        this.current = this.current.getChildren().get(i);
    }

    @Override
    public boolean onRoot() {
        return this.current == this.root;
    }

    @Override
    public boolean onLeaf() {
        return this.current.getChildren().isEmpty();
    }

    @Override
    public boolean hasChild(int i) {
        return this.current.getChildren().size()-1 >= i;
    }

    @Override
    public Color getValue() {
        return this.current.getColor();
    }

    @Override
    public void setValue(Color c) {
        this.current.setColor(c);
    }

    @Override
    public void addChildren(Color[] c) {
        if (c.length != 4) throw new IllegalArgumentException();
        List<Node> children = new ArrayList<>();
        for (Color col : c) {
            children.add(new Node(col, this.current));
        }
        this.current.setChildren(children);
    }

    @Override
    public void createRoot(Color c) {
        this.root = new Node(c);
    }

    @Override
    public void delete() {
        if (this.current.getParent() == null) throw new RuntimeException();
        this.goToParent();
        this.current.getChildren().clear();
    }

    public void recreate(Image image) {
        int size = image.getSize();
        if (!Image.power2(size)) throw new RuntimeException();
        this.goToRoot();
        recreateRec(0, 0, size, image);
    }

    public void recreateRec(int i, int j, int width, Image im) {
        if (this.onLeaf()) {
            Color c = this.getValue();
            for (int x = i; x < width+i; x++) {
                for (int y = j; y < width+j; y++)
                    im.setPixel(x, y, c);
            }
        } else {
            int nwidth = width / 2;
            this.goToChild(0);
            recreateRec(i, j, nwidth, im);
            this.goToParent();
            this.goToChild(1);
            recreateRec(i+nwidth, j, nwidth, im);
            this.goToParent();
            this.goToChild(2);
            recreateRec(i, j+nwidth, nwidth, im);
            this.goToParent();
            this.goToChild(3);
            recreateRec(i+nwidth, j+nwidth, nwidth, im);
        }
        this.goToParent();
    }

}


