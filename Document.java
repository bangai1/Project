

import java.util.List;

    public class Document {
        private String name;
        private List<String> terms;

        public Document(String name, List<String> terms) {
            this.name = name;
            this.terms = terms;
        }

        public String getName() {
            return name;
        }

        public List<String> getTerms() {
            return terms;
        }

        public String getContent() {
            StringBuilder contentBuilder = new StringBuilder();
            for (String term : terms) {
                contentBuilder.append(term).append(" ");
            }
            return contentBuilder.toString();
        }
    }


