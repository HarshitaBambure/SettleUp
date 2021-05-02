package com.example.settleup.db.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
class Expense {
    constructor(id: Int, purpose: String, whopaid: Int, amonut: Int, forwhom: Int) {
        this.id = id
        this.purpose = purpose
        this.whopaid = whopaid
        this.amonut = amonut
        this.forwhom = forwhom
    }


    @PrimaryKey(autoGenerate = true)
    val id : Int
    @ColumnInfo(name = "purpose")
    val purpose:String
    @ColumnInfo(name = "whopaid")
    val whopaid:Int
    @ColumnInfo(name = "amonut")
    val amonut:Int
    @ColumnInfo(name = "forwhom")
    val forwhom:Int
}