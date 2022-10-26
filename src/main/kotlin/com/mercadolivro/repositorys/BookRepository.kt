package com.mercadolivro.repositorys

import com.mercadolivro.models.BookModel
import com.mercadolivro.models.CustomerModel
import org.springframework.data.repository.CrudRepository

interface BookRepository: CrudRepository<BookModel, Int>{
    fun findByNameContaining(name: String): List<BookModel>
}