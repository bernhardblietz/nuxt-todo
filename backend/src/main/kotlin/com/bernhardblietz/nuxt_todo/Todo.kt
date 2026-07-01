package com.bernhardblietz.nuxt_todo

import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.Id
import jakarta.persistence.GenerationType
import lombok.NoArgsConstructor


@NoArgsConstructor
@Entity(name = "todo")
data class Todo (
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,
    var title: String = "",
    var description: String = "",
    var done: Boolean = false  
)