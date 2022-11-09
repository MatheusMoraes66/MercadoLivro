package com.mercadolivro.services

import com.mercadolivro.enums.CustomerStatus
import com.mercadolivro.models.CustomerModel
import com.mercadolivro.repositorys.CustomerRepository
import org.springframework.stereotype.Service
import java.lang.Exception

@Service
class CustomerService(
    val customerRepository: CustomerRepository,
    val bookService: BookService
) {
    val customers = mutableListOf<CustomerModel>()

    fun getAll(name: String?): List<CustomerModel>{
        name?.let {
            return customerRepository.findByNameContaining(name)
        }

        return customerRepository.findAll().toList()
    }

    fun getOne(id: Int): CustomerModel{
        return customerRepository.findById(id).get()
    }

    fun create(customer: CustomerModel){
        customerRepository.save(customer)
    }

    fun update(customer: CustomerModel){

        if(!customerRepository.existsById(customer.id!!)){
            throw Exception()
        }

        customerRepository.save(customer)

    }

    fun delete(id: Int){
        val customer = getOne(id)
        bookService.deleteByCustomer(customer)

        customer.status = CustomerStatus.INATIVO

        customerRepository.save(customer)

    }
}