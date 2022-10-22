package com.mercadolivro.services

import com.mercadolivro.dto.PutCustomerRequest
import com.mercadolivro.models.CustomerModel
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestBody

class CustomerService {
    val customers = mutableListOf<CustomerModel>()

    fun getAll(name: String?): List<CustomerModel>{
        name?.let {
            return customers.filter { it.name.contains(name, true) }
        }
        return customers
    }

    fun getOne(id: String): CustomerModel{
        return customers.filter {
            it.id == id
        }.first()
    }

    fun create(customer: CustomerModel){

        val id = if(customers.isEmpty()){
            1
        }else{
            customers.last().id?.toInt()?.plus(1)
        }.toString()

        customers.add(CustomerModel("${id}", customer.name, customer.email))
    }

    fun update(id: String, customer: CustomerModel){
        customers.filter { it.id == id }.first().let {
            it.email = customer.email
            it.name = customer.name
        }
    }

    fun delete(id: String){
        customers.removeIf{ it.id == id}
    }
}