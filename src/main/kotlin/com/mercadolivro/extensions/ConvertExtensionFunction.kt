package com.mercadolivro.extensions

import com.mercadolivro.dto.PostBookRequest
import com.mercadolivro.dto.PostCustomerRequest
import com.mercadolivro.dto.PutBookRequest
import com.mercadolivro.dto.PutCustomerRequest
import com.mercadolivro.enums.BookStatus
import com.mercadolivro.models.BookModel
import com.mercadolivro.models.CustomerModel
import com.mercadolivro.repositorys.CustomerRepository

fun PostCustomerRequest.toConvert(): CustomerModel{
    return CustomerModel(name = this.name, email = this.email)
}

fun PutCustomerRequest.toConvert(id: Int): CustomerModel{
    return CustomerModel(id = id,name = this.name, email = this.email)
}

fun PostBookRequest.toConvert(customer: CustomerModel): BookModel{
    return BookModel(name = name, price = price, status = BookStatus.ATIVO, customer = customer)
}

fun PutBookRequest.toConvert(oldValue: BookModel): BookModel{
    return BookModel(id = oldValue.id,
        name = this.name ?: oldValue.name,
        price = this.price ?: oldValue.price,
        status = oldValue.status,
        customer = oldValue.customer)
}