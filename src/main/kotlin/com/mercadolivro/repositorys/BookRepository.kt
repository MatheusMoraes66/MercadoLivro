package com.mercadolivro.repositorys

import com.mercadolivro.models.BookModel
import com.mercadolivro.models.CustomerModel
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.repository.CrudRepository

interface BookRepository: JpaRepository<BookModel, Int>{
    fun findByCustomer(customer: CustomerModel): List<BookModel>

}