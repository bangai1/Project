import java.util.List;
import java.util.ArrayList;


public class BST {

    private class TreeNode {
        String term;
        List<Document> documents;
        TreeNode left;
        TreeNode right;

        TreeNode(String term) {
            this.term = term;
            this.documents = new ArrayList<>();
            this.left = null;
            this.right = null;
        }
    }

    private TreeNode root;

    public void insert(String term, Document document) {
        root = insertRec(root, term, document);
    }

    private TreeNode insertRec(TreeNode node, String term, Document document) {
        if (node == null) {
            node = new TreeNode(term);
            node.documents.add(document);
        } else {
            int compareResult = term.compareTo(node.term);
            if (compareResult < 0) {
                node.left = insertRec(node.left, term, document);
            } else if (compareResult > 0) {
                node.right = insertRec(node.right, term, document);
            } else {
                node.documents.add(document);
            }
        }
        return node;
    }

    public List<Document> search(String term) {
        return searchRec(root, term);
    }

    private List<Document> searchRec(TreeNode node, String term) {
        if (node == null) {
            return new ArrayList<>();
        }
        int compareResult = term.compareTo(node.term);
        if (compareResult < 0) {
            return searchRec(node.left, term);
        } else if (compareResult > 0) {
            return searchRec(node.right, term);
        } else {
            return node.documents;
        }
    }

    public void removeDocument(Document document) {
        removeDocumentRec(root, document);
    }

    private TreeNode removeDocumentRec(TreeNode node, Document document) {
        if (node == null) {
            return null;
        }

        int compareResult = document.getName().compareTo(node.term);

        if (compareResult < 0) {
            node.left = removeDocumentRec(node.left, document);
        } else if (compareResult > 0) {
            node.right = removeDocumentRec(node.right, document);
        } else {
            node.documents.remove(document);

            if (node.documents.isEmpty()) {
                if (node.left == null) {
                    return node.right;
                } else if (node.right == null) {
                    return node.left;
                }
                node.term = minValue(node.right);

                node.right = removeDocumentRec(node.right, document);
            }
        }
        return node;
    }

    private String minValue(TreeNode node) {
        String minValue = node.term;
        while (node.left != null) {
            minValue = node.left.term;
            node = node.left;
        }
        return minValue;
    }

    public void clear() {
        root = null;
    }
}
