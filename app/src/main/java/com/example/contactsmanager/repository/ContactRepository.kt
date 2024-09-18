package com.example.contactsmanager.repository

import com.example.contactsmanager.room.ContactDAO
import com.example.contactsmanager.room.Contacts

class ContactRepository(private val contactDAO: ContactDAO) {

    val contacts = contactDAO.getAllContactsInDB()

    suspend fun insert(contact: Contacts): Long{
        return contactDAO.insertContact(contact)
    }

    suspend fun  update(contact: Contacts){
        return contactDAO.updateContact(contact)
    }

    suspend fun delete (contact: Contacts){
        return contactDAO.deleteContact(contact)
    }

    suspend fun deleteAll(){
        return contactDAO.deleteAll()
    }
}