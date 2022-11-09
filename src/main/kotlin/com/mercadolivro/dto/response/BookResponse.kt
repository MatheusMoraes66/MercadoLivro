package com.mercadolivro.dto.response

import java.math.BigDecimal


data class BookResponse(
    var id: Int? = null,

    var name: String,

    var price: BigDecimal,

    var customer: CustomerResponse? = null
)