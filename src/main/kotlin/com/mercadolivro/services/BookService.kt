package com.mercadolivro.services

import com.mercadolivro.enums.BookStatus
import com.mercadolivro.models.BookModel
import com.mercadolivro.repositorys.BookRepository
import org.springframework.stereotype.Service

@Service
class BookService(
    val bookRepository: BookRepository
) {

    fun findAll(name: String?): List<BookModel>{
        name?.let{
            return bookRepository.findByNameContaining(it)
        }
        return bookRepository.findAll().toList()
    }

    fun findById(id: Int) : BookModel {
        return bookRepository.findById(id).orElseThrow()
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
}