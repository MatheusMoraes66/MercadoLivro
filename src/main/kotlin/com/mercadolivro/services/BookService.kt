package com.mercadolivro.services

import com.mercadolivro.enums.BookStatus
import com.mercadolivro.enums.Errors
import com.mercadolivro.exception.NotFoundException
import com.mercadolivro.models.BookModel
import com.mercadolivro.models.CustomerModel
import com.mercadolivro.repositorys.BookRepository
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Service

@Service
class BookService(
    val bookRepository: BookRepository
) {

    fun findAll(pageable: Pageable): Page<BookModel>{

        return bookRepository.findAll(pageable)
    }

    fun findById(id: Int) : BookModel {
        return bookRepository.findById(id).orElseThrow{ NotFoundException(Errors.ML101.message.format(id), Errors.ML101.code) }
    }

    fun create(book: BookModel){
        bookRepository.save(book)
    }

    fun delete(id: Int){
        var book = findById(id)

        book.status = BookStatus.DELETATO

        update(book)
    }

    fun update(book: BookModel){
        bookRepository.save(book)
    }

    fun deleteByCustomer(customer: CustomerModel){
        val books = bookRepository.findByCustomer(customer)

        for(book in books){
            book.status = BookStatus.DELETATO
        }

        bookRepository.saveAll(books)
    }
}