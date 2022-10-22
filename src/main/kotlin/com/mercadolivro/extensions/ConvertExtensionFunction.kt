package com.mercadolivro.extensions

import com.mercadolivro.dto.PostCustomerRequest
import com.mercadolivro.dto.PutCustomerRequest
import com.mercadolivro.models.CustomerModel

fun PostCustomerRequest.toConvert(): CustomerModel{
    return CustomerModel(name = this.name, email = this.email)
}

fun PutCustomerRequest.toConvert(id: Int): CustomerModel{
    return CustomerModel(id = id,name = this.name, email = this.email)
}