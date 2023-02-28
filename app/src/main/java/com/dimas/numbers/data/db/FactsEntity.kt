package com.dimas.numbers.data.db

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "facts")
class FactsEntity(
    var facts: FactItem
) {
    @PrimaryKey(autoGenerate = true) var id = 0
}