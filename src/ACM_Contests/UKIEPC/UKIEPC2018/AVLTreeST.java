package ACM_Contests.UKIEPC.UKIEPC2018;

public class AVLTreeST<Key extends Comparable<Key>> {

    private Node root;

    /**
     * This class represents an inner node of the AVL tree.
     */
    private class Node {
        private final Key key;   // the key
        private int height;      // height of the subtree
        private int size;        // number of nodes in subtree
        private Node left;       // left subtree
        private Node right;      // right subtree

        public Node(Key key, int height, int size) {
            this.key = key;
            this.size = size;
            this.height = height;
        }
    }

    public AVLTreeST() {}

    public boolean isEmpty() {
        return root == null;
    }

    public int size() {
        return size(root);
    }

    private int size(Node x) {
        if (x == null) return 0;
        return x.size;
    }

    // height of empty tree is -1, height of tree with 1 node is 0
    public int height() {
        return height(root);
    }

    private int height(Node x) {
        if (x == null) return -1;
        return x.height;
    }

    // looks up the key in the table
    public Key get(Key key) {
        if (key == null) throw new IllegalArgumentException("argument to get() is null");
        Node x = get(root, key); if (x == null) return null; return x.key;
    }

    // looks up the key in the tree with root x, returns the subtree which contains it or null if it does not exist.
    private Node get(Node x, Key key) {
        if (x == null) return null;
        int cmp = key.compareTo(x.key);
        if (cmp < 0) return get(x.left, key);
        else if (cmp > 0) return get(x.right, key);
        else return x;
    }

    public boolean contains(Key key) {
        return get(key) != null;
    }

    public void put(Key key) {
        if (key == null) throw new IllegalArgumentException("first argument to put() is null");
        root = put(root, key);
    }

    // Inserts the key-value pair in the subtree.
    private Node put(Node x, Key key) {
        if (x == null) return new Node(key, 0, 1);
        int cmp = key.compareTo(x.key);
        if (cmp < 0) {
            x.left = put(x.left, key);
        }
        else if (cmp > 0) {
            x.right = put(x.right, key);
        }
        else {
            return x;
        }
        x.size = 1 + size(x.left) + size(x.right);
        x.height = 1 + Math.max(height(x.left), height(x.right));
        return balance(x);
    }

    

    // Balances the AVL tree.
    private Node balance(Node x) {
        if (balanceFactor(x) < -1) {
            if (balanceFactor(x.right) > 0) {
                x.right = rotateRight(x.right);
            }
            x = rotateLeft(x);
        }
        else if (balanceFactor(x) > 1) {
            if (balanceFactor(x.left) < 0) {
                x.left = rotateLeft(x.left);
            }
            x = rotateRight(x);
        }
        return x;
    }

    private int balanceFactor(Node x) {
        return height(x.left) - height(x.right);
    }

    private Node rotateRight(Node x) {
        Node y = x.left;
        x.left = y.right;
        y.right = x;
        y.size = x.size;
        x.size = 1 + size(x.left) + size(x.right);
        x.height = 1 + Math.max(height(x.left), height(x.right));
        y.height = 1 + Math.max(height(y.left), height(y.right));
        return y;
    }

    private Node rotateLeft(Node x) {
        Node y = x.right;
        x.right = y.left;
        y.left = x;
        y.size = x.size;
        x.size = 1 + size(x.left) + size(x.right);
        x.height = 1 + Math.max(height(x.left), height(x.right));
        y.height = 1 + Math.max(height(y.left), height(y.right));
        return y;
    }

    public void delete(Key key) {
        if (key == null) throw new IllegalArgumentException("argument to delete() is null");
        if (!contains(key)) return;
        root = delete(root, key);
    }

    // Deletes key from subtree x
    private Node delete(Node x, Key key) {
        int cmp = key.compareTo(x.key);
        if (cmp < 0) {
            x.left = delete(x.left, key);
        }
        else if (cmp > 0) {
            x.right = delete(x.right, key);
        }
        else {
            if (x.left == null) {
                return x.right;
            }
            else if (x.right == null) {
                return x.left;
            }
            else {
                Node y = x;
                x = min(y.right);
                x.right = deleteMin(y.right);
                x.left = y.left;
            }
        }
        x.size = 1 + size(x.left) + size(x.right);
        x.height = 1 + Math.max(height(x.left), height(x.right));
        return balance(x);
    }

    // Removes the smallest key and associated value from the symbol table; throws exception if set is empty
    public void deleteMin() throws Exception {
        if (isEmpty()) throw new Exception("called deleteMin() with empty symbol table");
        root = deleteMin(root);
    }

    // Removes smallest element from subtree.
    private Node deleteMin(Node x) {
        if (x.left == null) return x.right;
        x.left = deleteMin(x.left);
        x.size = 1 + size(x.left) + size(x.right);
        x.height = 1 + Math.max(height(x.left), height(x.right));
        return balance(x);
    }

    // Removes the largest key and associated value from the set; throws exception if table empty
    public void deleteMax() throws Exception {
        if (isEmpty()) throw new Exception("called deleteMax() with empty symbol table");
        root = deleteMax(root);
    }

    // Removes largest element from subtree
    private Node deleteMax(Node x) {
        if (x.right == null) return x.left;
        x.right = deleteMax(x.right);
        x.size = 1 + size(x.left) + size(x.right);
        x.height = 1 + Math.max(height(x.left), height(x.right));
        return balance(x);
    }

    // Returns smallest element; throws if empty
    public Key min() throws Exception {
        if (isEmpty()) throw new Exception("called min() with empty symbol table");
        return min(root).key;
    }

    // Returns node which holds the smallest key.
    private Node min(Node x) {
        if (x.left == null) return x;
        return min(x.left);
    }

    // Returns biggest node
    public Key max() throws Exception {
        if (isEmpty()) throw new Exception("called max() with empty symbol table");
        return max(root).key;
    }

    // Returns node which holds the largest key
    private Node max(Node x) {
        if (x.right == null) return x;
        return max(x.right);
    }

    //  Returns the largest key in the symbol table less than or equal to key.
    public Key floor(Key key) throws Exception {
        if (key == null) throw new IllegalArgumentException("argument to floor() is null");
        if (isEmpty()) throw new Exception("called floor() with empty symbol table");
        Node x = floor(root, key);
        if (x == null) return null;
        else return x.key;
    }


    // Returns the node in the subtree with the largest key less than or equal to the given key.
    private Node floor(Node x, Key key) {
        if (x == null) return null;
        int cmp = key.compareTo(x.key);
        if (cmp == 0) return x;
        if (cmp < 0) return floor(x.left, key);
        Node y = floor(x.right, key);
        if (y != null) return y;
        else return x;
    }

    // Returns the smallest key in the symbol table greater than or equal to key
    public Key ceiling(Key key) throws Exception {
        if (key == null) throw new IllegalArgumentException("argument to ceiling() is null");
        if (isEmpty()) throw new Exception("called ceiling() with empty symbol table");
        Node x = ceiling(root, key);
        if (x == null) return null;
        else return x.key;
    }

    // Returns the node in the subtree with the smallest key greater than or equal to the given key.
    private Node ceiling(Node x, Key key) {
        if (x == null) return null;
        int cmp = key.compareTo(x.key);
        if (cmp == 0) return x;
        if (cmp > 0) return ceiling(x.right, key);
        Node y = ceiling(x.left, key);
        if (y != null) return y;
        else return x;
    }

    // Returns the kth smallest key in the symbol table.
    public Key select(int k) {
        if (k < 0 || k >= size()) throw new IllegalArgumentException("k is not in range 0-" + (size() - 1));
        Node x = select(root, k);
        return x.key;
    }

    // Returns the node with kth smallest key in the subtree.
    private Node select(Node x, int k) {
        if (x == null) return null;
        int t = size(x.left);
        if (t > k) return select(x.left, k);
        else if (t < k) return select(x.right, k - t - 1);
        else return x;
    }

    // Returns the number of keys in the symbol table strictly less than key.
    public int rank(Key key) {
        if (key == null) throw new IllegalArgumentException("argument to rank() is null");
        return rank(key, root);
    }

    // Returns the number of keys in the subtree less than key.
    private int rank(Key key, Node x) {
        if (x == null) return 0;
        int cmp = key.compareTo(x.key);
        if (cmp < 0) return rank(key, x.left);
        else if (cmp > 0) return 1 + size(x.left) + rank(key, x.right);
        else return size(x.left);
    }

    // Returns the number of keys in the symbol table in the given range.
    public int size(Key lo, Key hi) {
        if (lo == null) throw new IllegalArgumentException("first argument to size() is null");
        if (hi == null) throw new IllegalArgumentException("second argument to size() is null");
        if (lo.compareTo(hi) > 0) return 0;
        if (contains(hi)) return rank(hi) - rank(lo) + 1;
        else return rank(hi) - rank(lo);
    }
}