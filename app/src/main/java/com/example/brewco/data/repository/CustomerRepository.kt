import com.example.brewco.data.model.Client
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.toObject
import kotlinx.coroutines.tasks.await

class CustomerRepository {

    private val db = FirebaseFirestore.getInstance()
    private val clientsCollection = db.collection("clientes") // Nombre de la colección en Firestore

    // Crear un nuevo cliente
    suspend fun addClient(client: Client): Boolean {
        return try {
            clientsCollection.add(client).await()
            true
        } catch (e: Exception) {
            false
        }
    }

    // Obtener todos los clientes
    suspend fun getClients(): List<Client> {
        return try {
            val snapshot = clientsCollection.get().await()
            snapshot.documents.mapNotNull { it.toObject<Client>()?.copy(id = it.id) }
        } catch (e: Exception) {
            emptyList() // Devuelve una lista vacía en caso de error
        }
    }

    // Obtener un cliente por ID
    suspend fun getClientById(ClientId: String): Client? {
        return try {
            val documentSnapshot = clientsCollection.document(ClientId).get().await()
            if (documentSnapshot.exists()) {
                documentSnapshot.toObject(Client::class.java)?.copy(id = documentSnapshot.id)
            } else {
                null
            }
        } catch (e: Exception) {
            null
        }
    }

    // Actualizar un cliente
    suspend fun updateClient(Client: Client): Boolean {
        return try {
            clientsCollection.document(Client.id).set(Client).await()
            true
        } catch (e: Exception) {
            false
        }
    }

    // Eliminar un cliente
    suspend fun deleteClient(ClientId: String): Boolean {
        return try {
            clientsCollection.document(ClientId).delete().await()
            true
        } catch (e: Exception) {
            false
        }
    }
}
