/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */
package com.mycompany.p_uts_23090002_c_2025;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import static com.mongodb.client.model.Filters.eq;
import java.util.Arrays;
import org.bson.Document;

/**
 *
 * @author LENOVO
 */
public class CRUD_UTS_23090002_C_2025 {

    public static void main(String[] args) {
        // Buka koneksi ke MongoDB
        MongoClient mongoClient = MongoClients.create("mongodb+srv://dbAdmin:4AMWWK9J5Qs2LSLN@uts-23090002-c-2025.vlgqpd9.mongodb.net/?retryWrites=true&w=majority&appName=uts-23090002-C-2025");

        // Pilih database dan collection
        MongoDatabase database = mongoClient.getDatabase("uts_23090002_C_2025");
        MongoCollection<Document> collection = database.getCollection("coll_23090002_C_2025");

        // Kosongkan collection
        collection.drop();

        // CREATE
        Document doc1 = new Document("name", "buah")
                .append("items", Arrays.asList("apel", "pisang", "jeruk"));
        Document doc2 = new Document("name", "mobil")
                .append("items", Arrays.asList(
                        Arrays.asList("Toyota", "Honda"),
                        Arrays.asList("Ford", "Chevrolet")
                ));
        Document doc3 = new Document("name", "warna")
                .append("items", Arrays.asList(
                        Arrays.asList(
                                Arrays.asList("merah", "biru"),
                                Arrays.asList("hitam", "putih")
                        ),
                        Arrays.asList(
                                Arrays.asList("kuning", "hijau"),
                                Arrays.asList("coklat", "abu-abu")
                        )
                ));
        collection.insertMany(Arrays.asList(doc1, doc2, doc3));

        // READ
        System.out.println("\nList Document (read):");
        for (Document doc : collection.find()) {
            System.out.println(doc.toJson());
        }

        // UPDATE
        collection.updateOne(eq("name", "buah"),
                new Document("$set", new Document("items", Arrays.asList("durian", "mangga", "anggur"))));
        System.out.println("\nList document (update):");
        for (Document doc : collection.find()) {
            System.out.println(doc.toJson());
        }

        // DELETE
        collection.deleteOne(eq("name", "mobil"));
        System.out.println("\nList document (delete):");
        for (Document doc : collection.find()) {
            System.out.println(doc.toJson());
        }

        // SEARCH
        System.out.println("\nCari kuning (search):");
        for (Document doc : collection.find(eq("items", "kuning"))) {
            System.out.println(doc.toJson());
        }

        // Tutup koneksi
        mongoClient.close();
    }
}
