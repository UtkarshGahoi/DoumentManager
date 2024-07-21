package org.example;

import exception.EmailAuthentication;
import model.*;
import exception.AuthenticationException;

import java.util.List;
import java.util.UUID;

// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class Main {
    public static void main(String[] args) throws AuthenticationException, EmailAuthentication {
        long startTime = System.currentTimeMillis();
        UserManager userManager = new UserManager();
        DocumentManager documentManager = new DocumentManager();

        // Create users
        userManager.createUser("Utkarsh Gahoi", "utkershgahoi140@gmail.com", "password123");
        userManager.createUser("Rajesh Kumar", "utkershgahoi140@gmail.com", "password456");

        // Attempt to log in
        try {
            User loggedInUser = userManager.loginUser("utkershgahoi140@gmail.com", "password123");
            User waitedUser=userManager.loginUser("rajeshkumar@gmail.com", "password456");
            System.out.println("Login successful for user: " + loggedInUser.getName());

            // Create document
            Document doc = documentManager.createDocument("Hello, World!", loggedInUser);

            // Update document
            documentManager.updateDocument(doc.getId(), "Hello, Java!", loggedInUser);

            // View document
            Document retrievedDoc = documentManager.getDocument(doc.getId());
            System.out.println("Document Content: " + retrievedDoc.getContent());

            // Revert document to version 1
            documentManager.revertToVersion(doc.getId(), 1, loggedInUser);

            //Latest Active version of document.
            int latestActiveVersion=doc.getLatestActiveVersion(doc);
            System.out.println("The latest active version of doc:" +latestActiveVersion);


            //maintaining the history of document.
            List<DocumentVersion>versions= doc.getVersions();
            for(int i=0;i<versions.size();i++){
                System.out.println(versions.get(i).getContent());
            }

            // View reverted document
            retrievedDoc = documentManager.getDocument(doc.getId());
            System.out.println("Reverted Document Content: " + retrievedDoc.getContent());

            // Delete document
            documentManager.deleteDocument(doc.getId(), loggedInUser);
        } catch (AuthenticationException e) {
            System.out.println("Login failed: " + e.getMessage());
        } catch (IllegalAccessException e) {
            System.out.println("Access error: " + e.getMessage());
        }

        long endTime = System.currentTimeMillis();

        // Calculate the total time taken
        long timeTaken = endTime - startTime;
        System.out.println("Time taken: " + timeTaken + " milliseconds");
    }
}