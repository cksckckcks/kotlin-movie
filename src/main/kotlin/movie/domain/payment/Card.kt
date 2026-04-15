package movie.domain.payment

import movie.domain.Price

class Card : PaymentMethod {
    override val discountRate: Float = 0.05f

    override fun paymentPrice(totalPrice: Price): Price = totalPrice.getDiscountPrice(0.05f)
}
