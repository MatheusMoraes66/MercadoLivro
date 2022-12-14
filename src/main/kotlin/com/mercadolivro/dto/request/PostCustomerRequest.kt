package com.mercadolivro.dto.request

import javax.validation.constraints.Email
import javax.validation.constraints.NotEmpty

data class PostCustomerRequest(
    @field:NotEmpty(message = "Nome deve ser valido")
    var name: String,
    @field:Email(message = "E-mail deve ser valido")
    var email: String
)