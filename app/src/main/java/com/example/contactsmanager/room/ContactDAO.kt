package com.example.contactsmanager.room

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update

@Dao
interface ContactDAO {

    @Insert
    suspend fun insertContact(contact: Contacts): Long

    @Update
    suspend fun  updateContact(contact: Contacts)

    @Delete
    suspend fun deleteContact(contact: Contacts)

    @Query("DELETE FROM contact_table")
    suspend fun deleteAll()

    @Query("SELECT * FROM contact_table")
    fun getAllContactsInDB():LiveData<List<Contacts>>
}