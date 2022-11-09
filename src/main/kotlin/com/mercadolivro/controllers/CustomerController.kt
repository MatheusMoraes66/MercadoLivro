package com.mercadolivro.controllers

import com.mercadolivro.dto.response.CustomerResponse
import com.mercadolivro.dto.request.PostCustomerRequest
import com.mercadolivro.dto.request.PutCustomerRequest
import com.mercadolivro.extensions.toConvert
import com.mercadolivro.extensions.toResponse
import com.mercadolivro.services.CustomerService
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/customer")
class CustomerController(
    val customerService: CustomerService
) {

    @GetMapping
    fun getAll(@RequestParam name: String?): List<CustomerResponse>{
        return customerService.getAll(name).map{ it.toResponse() }
    }

    @GetMapping("/{id}")
    fun getOne(@PathVariable id: Int): CustomerResponse {
        return customerService.getOne(id).toResponse()
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    fun create(@RequestBody customer: PostCustomerRequest){
        customerService.create(customer.toConvert())
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    fun update(@PathVariable id: Int, @RequestBody customer: PutCustomerRequest){
        val currentCustomer = customerService.getOne(id)
        customerService.update(customer.toConvert(currentCustomer))
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    fun delete(@PathVariable id: Int){
        customerService.delete(id)
    }
}