package com.mercadolivro.controllers

import com.mercadolivro.dto.response.BookResponse
import com.mercadolivro.dto.request.PostBookRequest
import com.mercadolivro.dto.request.PutBookRequest
import com.mercadolivro.extensions.toConvert
import com.mercadolivro.extensions.toResponse
import com.mercadolivro.services.BookService
import com.mercadolivro.services.CustomerService
import org.springframework.data.domain.Pageable
import org.springframework.data.domain.Page
import org.springframework.data.web.PageableDefault
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/book")
class BookController(
 val bookService: BookService,
 val customerService: CustomerService
) {

    @GetMapping
    fun getAll(@PageableDefault(size = 10, page = 0) pageable: Pageable): Page<BookResponse>{
        return bookService.findAll(pageable).map{ it.toResponse()}
    }

    @GetMapping("/{id}")
    fun getOne(@PathVariable id: Int): BookResponse {
        return bookService.findById(id).toResponse()
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