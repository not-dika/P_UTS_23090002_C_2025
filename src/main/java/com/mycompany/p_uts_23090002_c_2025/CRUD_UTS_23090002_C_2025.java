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
        MongoClient mc = MongoClients.create("mongodb+srv://dbAdmin:4AMWWK9J5Qs2LSLN@uts-23090002-c-2025.vlgqpd9.mongodb.net/?retryWrites=true&w=majority&appName=uts-23090002-C-2025");

        // Pilih database dan collection
        MongoDatabase db = mc.getDatabase("uts_23090002_C_2025");
        MongoCollection<Document> coll = db.getCollection("coll_23090002_C_2025");

        // Kosongkan collection
        coll.drop();

        // CREATE
        Document d1 = new Document("name", "buah")
                .append("items", Arrays.asList("apel", "pisang", "jeruk"));
        Document d2 = new Document("name", "mobil")
                .append("items", Arrays.asList(
                        Arrays.asList("Toyota", "Honda"),
                        Arrays.asList("Ford", "Chevrolet")
                ));
        Document d3 = new Document("name", "warna")
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
        coll.insertMany(Arrays.asList(d1, d2, d3));

        // READ
        System.out.println("\nList Document (read):");
        for (Document d : coll.find()) {
            System.out.println(d.toJson());
        }

        // UPDATE
        coll.updateOne(eq("name", "buah"),
                new Document("$set", new Document("items", Arrays.asList("durian", "mangga", "anggur"))));
        System.out.println("\nList document (update):");
        for (Document d : coll.find()) {
            System.out.println(d.toJson());
        }

        // DELETE
        coll.deleteOne(eq("name", "mobil"));
        System.out.println("\nList document (delete):");
        for (Document d : coll.find()) {
            System.out.println(d.toJson());
        }

        // SEARCH
        System.out.println("\nCari kuning (search):");
        for (Document d : coll.find(eq("items", "kuning"))) {
            System.out.println(d.toJson());
        }

        // Tutup koneksi
        mc.close();
    }
}
