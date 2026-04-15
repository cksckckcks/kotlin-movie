package movie.domain.payment

import movie.domain.Price

interface PaymentMethod {
    val discountRate: Float

    fun paymentPrice(totalPrice: Price): Price
}
