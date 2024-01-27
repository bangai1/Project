import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class SearchEngine {

    private List<String> documents;
    private static String outputFile;

    public SearchEngine(String outputFile) {
        this.documents = new ArrayList<>();
        this.outputFile = outputFile;
    }

    public void clearList() {
        documents.clear();
    }

    public void load(String filePath) {
        try {
            File file = new File(filePath);
            Scanner scanner = new Scanner(file);
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                documents.add(line);
            }
            scanner.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void search(String queryString) {
        List<Integer> result = new ArrayList<>();

        String[] queryTerms = queryString.split("\\s+"); // Split by spaces


        List<String> positiveTerms = new ArrayList<>();
        List<String> negativeTerms = new ArrayList<>();

        for (String term : queryTerms) {
            if (term.startsWith("!")) {
                negativeTerms.add(term.substring(1));
            } else {
                positiveTerms.add(term);
            }
        }

        for (int i = 0; i < documents.size(); i++) {
            boolean includeDocument = true;

            // Check for positive terms
            for (String term : positiveTerms) {
                if (!documents.get(i).contains(term)) {
                    includeDocument = false;
                    break;
                }
            }


            for (String term : negativeTerms) {
                if (documents.get(i).contains(term)) {
                    includeDocument = false;
                    break;
                }
            }

            if (includeDocument) {
                result.add(i);
            }
        }

        if (result.isEmpty()) {
            System.out.println("No matching documents found.");
        } else {
            System.out.println("The search term is found in the following documents:");
            for (int documentIndex : result) {
                System.out.println("- Document " + (documentIndex + 1)); // Adding 1 to make it 1-based
            }
        }
    }


    private void appendResultsToFile(List<String> results) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(outputFile, true))) {
            for (String result : results) {
                writer.write(result);
                writer.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void remove(String document) {
        documents.remove(document);
    }

    public static void main(String[] args) {
        SearchEngine searchEngine = new SearchEngine(""); // Replace with the actual output file path
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("Enter command: ");
            String command = scanner.nextLine();
            if (command.startsWith("clear list")) {
                searchEngine.clearList();
                System.out.println("List cleared.");
            } else if (command.startsWith("load")) {
                String filePath = command.substring(5).trim();
                searchEngine.load(filePath);
                System.out.println("File loaded successfully.");
            } else if (command.startsWith("search")) {
                String queryString = command.substring(7).trim();
                searchEngine.search(queryString);
            } else if (command.startsWith("remove")) {
                String document = command.substring(7).trim();
                searchEngine.remove(document);
                System.out.println("Document removed successfully.");
            } else {
                System.out.println("Invalid command. Please try again.");
            }
        }
    }
}
