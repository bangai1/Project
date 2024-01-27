import java.util.ArrayList;
import java.util.List;

public class DocumentList {

    private List<Document> documents;

    public DocumentList() {
        documents = new ArrayList<>();
    }

    public void addDocument(Document document) {
        documents.add(document);
    }

    public void removeDocument(Document document) {
        documents.remove(document);
    }

    public Document findDocument(String documentName) {
        for (Document document : documents) {
            if (document.getName().equals(documentName)) {
                return document;
            }
        }
        return null;
    }

    public void clear() {
        documents.clear();
    }

    public void printDocuments() {
        for (Document document : documents) {
            System.out.println("Document Name: " + document.getName());
            System.out.println("Document Terms: " + document.getTerms());
            System.out.println(); // Add a blank line for separation
        }
    }
}
