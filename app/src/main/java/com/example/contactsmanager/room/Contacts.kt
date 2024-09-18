package com.example.contactsmanager.room

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "contact_table")

data class Contacts(
    @PrimaryKey(autoGenerate = true)
    val contact_id : Int,

    var contact_name : String,

    var contact_email : String
)
