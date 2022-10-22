package com.mercadolivro.controllers

import com.mercadolivro.dto.PostCustomerRequest
import com.mercadolivro.dto.PutCustomerRequest
import com.mercadolivro.models.CustomerModel
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestController
import java.net.http.HttpResponse
import javax.websocket.server.PathParam

@RestController
@RequestMapping("/customer")
class CustomerController {

    val customers = mutableListOf<CustomerModel>()

    @GetMapping
    fun getAll(@RequestParam name: String?): List<CustomerModel>{
        name?.let {
            return customers.filter { it.name.contains(name, true) }
        }
        return customers
    }

    @GetMapping("/{id}")
    fun getCustomer(@PathVariable id: String): CustomerModel{
        return customers.filter {
            it.id == id
        }.first()
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    fun create(@RequestBody customer: PostCustomerRequest){
        val id = if(customers.isEmpty()){
            1
        }else{
            customers.last().id.toInt() + 1
        }.toString()

        customers.add(CustomerModel("${id}", customer.name, customer.email))
        println(customer)
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    fun change(@PathVariable id: String, @RequestBody customer: PutCustomerRequest){
        customers.filter { it.id == id }.first().let {
            it.email = customer.email
            it.name = customer.name
        }
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    fun delete(@PathVariable id: String){
        customers.removeIf{ it.id == id}
    }
}