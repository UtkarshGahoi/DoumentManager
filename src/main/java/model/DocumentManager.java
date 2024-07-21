package model;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

public class DocumentManager {
    private Map<UUID, Document> documents;
    private int currentVersion;

    public DocumentManager() {
        this.documents = new HashMap<>();
    }

    public Document createDocument(String content, User author) {
        Document doc = new Document(content, author);
        documents.put(doc.getId(), doc);
        return doc;
    }

    public Document updateDocument(UUID documentId, String newContent, User user) throws IllegalAccessException {
        Document doc = documents.get(documentId);
        if (doc != null && doc.getAuthor().getId().equals(user.getId())) {
            doc.addVersion(newContent);

        } else {
            throw new IllegalAccessException("Only the author can update the document.");
        }
        return doc;
    }

    public void deleteDocument(UUID documentId, User user) throws IllegalAccessException {
        Document doc = documents.get(documentId);
        if (doc != null && doc.getAuthor().getId().equals(user.getId())) {
            documents.remove(documentId);
        } else {
            throw new IllegalAccessException("Only the author can delete the document.");
        }
    }

    public Document getDocument(UUID documentId) {
        return documents.get(documentId);
    }

    public Document revertToVersion(UUID documentId, int versionNumber, User user) throws IllegalAccessException {
        Document doc = documents.get(documentId);
        if (doc != null && doc.getAuthor().getId().equals(user.getId())) {
            DocumentVersion version = doc.getVersion(versionNumber);
            if (version != null) {
                doc.addVersion(version.getContent());
            }
        } else {
            throw new IllegalAccessException("Only the author can revert the document.");
        }
        return doc;
    }


}
