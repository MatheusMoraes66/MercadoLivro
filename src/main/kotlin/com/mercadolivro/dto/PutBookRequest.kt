package com.mercadolivro.dto

import java.math.BigDecimal

data class PutBookRequest (
    var name: String?,

    var price: BigDecimal?
    )