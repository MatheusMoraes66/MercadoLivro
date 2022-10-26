package com.mercadolivro.controllers

import com.mercadolivro.dto.PostBookRequest
import com.mercadolivro.dto.PutBookRequest
import com.mercadolivro.extensions.toConvert
import com.mercadolivro.models.BookModel
import com.mercadolivro.services.BookService
import com.mercadolivro.services.CustomerService
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/book")
class BookController(
 val bookService: BookService,
 val customerService: CustomerService
) {

    @GetMapping
    fun getAll(@RequestParam name: String?): List<BookModel>{
        return bookService.findAll(name)
    }

    @GetMapping("/{id}")
    fun getOne(@PathVariable id: Int): BookModel {
        return bookService.findById(id)
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    fun create(@RequestBody book: PostBookRequest){
        bookService.create(book.toConvert(customerService.getOne(book.customerId)))
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    fun update(@PathVariable id: Int, @RequestBody book: PutBookRequest){
        val bookSaved = bookService.findById(id)
        bookService.update(book.toConvert(bookSaved))
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    fun delete(@PathVariable id: Int){
        bookService.delete(id)
    }
}