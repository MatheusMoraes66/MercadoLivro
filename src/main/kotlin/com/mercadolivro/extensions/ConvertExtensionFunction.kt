package com.mercadolivro.extensions

import com.mercadolivro.dto.request.PostBookRequest
import com.mercadolivro.dto.request.PostCustomerRequest
import com.mercadolivro.dto.request.PutBookRequest
import com.mercadolivro.dto.request.PutCustomerRequest
import com.mercadolivro.dto.response.BookResponse
import com.mercadolivro.dto.response.CustomerResponse
import com.mercadolivro.enums.BookStatus
import com.mercadolivro.enums.CustomerStatus
import com.mercadolivro.models.BookModel
import com.mercadolivro.models.CustomerModel

fun PostCustomerRequest.toConvert(): CustomerModel{
    return CustomerModel(name = this.name, email = this.email, status = CustomerStatus.ATIVO)
}

fun PutCustomerRequest.toConvert(oldValue: CustomerModel): CustomerModel{
    return CustomerModel(id = oldValue.id,name = this.name, email = this.email, status = oldValue.status)
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

fun CustomerModel.toResponse(): CustomerResponse {
    return CustomerResponse(
        id = this.id,
        name = this.name,
        email = this.email
    )
}

fun BookModel.toResponse(): BookResponse {
    return BookResponse(
        id = this.id,
        name = this.name,
        price = this.price,
        customer = this.customer?.toResponse()
    )
}