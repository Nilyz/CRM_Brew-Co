package com.example.brewco.data.repository

import com.example.brewco.data.model.Client
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.QueryDocumentSnapshot

class DaoClient {

    private val db = FirebaseFirestore.getInstance()
    private val clientsCollection = db.collection("clientes") // Nombre de la colección en Firestore

    // Create
    fun addClient(client: Client, onSuccess: () -> Unit, onFailure: (Exception) -> Unit) {
        clientsCollection.add(client)
            .addOnSuccessListener {
                onSuccess()
            }
            .addOnFailureListener { exception ->
                onFailure(exception)
            }
    }

    // Read
    fun getClientByName(name: String, onSuccess: (Client?) -> Unit, onFailure: (Exception) -> Unit) {
        clientsCollection.whereEqualTo("nombre", name).get()
            .addOnSuccessListener { documents ->
                if (documents.isEmpty) {
                    onSuccess(null)
                } else {
                    val client = documents.first().toObject(Client::class.java)
                    onSuccess(client)
                }
            }
            .addOnFailureListener { exception ->
                onFailure(exception)
            }
    }

    fun getAllClients(onSuccess: (List<Client>) -> Unit, onFailure: (Exception) -> Unit) {
        clientsCollection.get()
            .addOnSuccessListener { documents ->
                val clients = mutableListOf<Client>()
                for (document in documents) {
                    val client = document.toObject(Client::class.java)
                    clients.add(client)
                }
                onSuccess(clients)
            }
            .addOnFailureListener { exception ->
                onFailure(exception)
            }
    }

    // Update
    fun updateClient(name: String, updatedClient: Client, onSuccess: () -> Unit, onFailure: (Exception) -> Unit) {
        clientsCollection.whereEqualTo("nombre", name).get()
            .addOnSuccessListener { documents ->
                if (documents.isEmpty) {
                    onFailure(Exception("Client not found"))
                } else {
                    val documentId = documents.first().id
                    clientsCollection.document(documentId).set(updatedClient)
                        .addOnSuccessListener {
                            onSuccess()
                        }
                        .addOnFailureListener { exception ->
                            onFailure(exception)
                        }
                }
            }
            .addOnFailureListener { exception ->
                onFailure(exception)
            }
    }

    // Delete
    fun deleteClient(name: String, onSuccess: () -> Unit, onFailure: (Exception) -> Unit) {
        clientsCollection.whereEqualTo("nombre", name).get()
            .addOnSuccessListener { documents ->
                if (documents.isEmpty) {
                    onFailure(Exception("Client not found"))
                } else {
                    val documentId = documents.first().id
                    clientsCollection.document(documentId).delete()
                        .addOnSuccessListener {
                            onSuccess()
                        }
                        .addOnFailureListener { exception ->
                            onFailure(exception)
                        }
                }
            }
            .addOnFailureListener { exception ->
                onFailure(exception)
            }
    }
}