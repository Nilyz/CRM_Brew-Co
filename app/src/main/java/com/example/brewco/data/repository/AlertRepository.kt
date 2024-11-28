package com.example.brewco.data.repository

import com.example.brewco.data.model.Alert
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.toObject
import kotlinx.coroutines.tasks.await
import java.time.LocalDate
import java.time.format.DateTimeFormatter

// CRUD PARA ALERTAS
class AlertRepository {
    private val db = FirebaseFirestore.getInstance()
    private val alertsCollection = db.collection("eventos")

    suspend fun addAlert(alert: Alert): Boolean {
        return try {
            // Agrega el documento a Firestore y obtiene la referencia
            val documentReference = alertsCollection.add(alert).await()

            // Recupera el ID generado
            val generatedId = documentReference.id

            // Actualiza el documento con el ID generado
            alertsCollection.document(generatedId).set(alert.copy(id = generatedId)).await()

            true
        } catch (e: Exception) {
            false
        }
    }


    suspend fun getAlerts(): List<Alert> {
        return try {
            val snapshot = alertsCollection.get().await()
            snapshot.documents.mapNotNull { it.toObject<Alert>()?.copy(id = it.id) }
        } catch (e: Exception) {
            emptyList()
        }
    }

    suspend fun getAlertById(alertId: String): Alert? {
        return try {
            val documentSnapshot = alertsCollection.document(alertId).get().await()
            if (documentSnapshot.exists()) {
                documentSnapshot.toObject<Alert>()?.copy(id = documentSnapshot.id)
            } else {
                null
            }
        } catch (e: Exception) {
            null
        }
    }

    suspend fun updateAlert(alert: Alert): Boolean {
        return try {
            alertsCollection.document(alert.id).set(alert).await()
            true
        } catch (e: Exception) {
            false
        }
    }

    suspend fun deleteAlert(alertId: String): Boolean {
        return try {
            alertsCollection.document(alertId).delete().await()
            true
        } catch (e: Exception) {
            false
        }
    }

    suspend fun getAlertsForToday(): List<Alert> {
        val today = LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"))
        return try {
            val snapshot = alertsCollection
                .whereEqualTo("aviso", true)
                .whereEqualTo("inicio", today)
                .get()
                .await()

            snapshot.documents.mapNotNull { it.toObject<Alert>()?.copy(id = it.id) }
        } catch (e: Exception) {
            emptyList()
        }
    }
}