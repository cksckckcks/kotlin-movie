package movie.domain.payment

import movie.domain.Price

class Cash : PaymentMethod {
    override val discountRate: Float = 0.02f

    override fun paymentPrice(totalPrice: Price): Price = totalPrice.getDiscountPrice(discountRate)
}
