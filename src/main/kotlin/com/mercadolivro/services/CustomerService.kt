package com.mercadolivro.services

import com.mercadolivro.models.CustomerModel
import org.springframework.stereotype.Service

@Service
class CustomerService {
    val customers = mutableListOf<CustomerModel>()

    fun getAll(name: String?): List<CustomerModel>{
        name?.let {
            return customers.filter { it.name.contains(name, true) }
        }
        return customers
    }

    fun getOne(id: Int): CustomerModel{
        return customers.filter {
            it.id == id
        }.first()
    }

    fun create(customer: CustomerModel){

        val id = if(customers.isEmpty()){
            1
        }else{
            customers.last().id?.toInt()?.plus(1)
        }

        customers.add(CustomerModel(id, customer.name, customer.email))
    }

    fun update(customer: CustomerModel){
        customers.filter { it.id == customer.id }.first().let {
            it.email = customer.email
            it.name = customer.name
        }
    }

    fun delete(id: Int){
        customers.removeIf{ it.id == id}
    }
}