package movie.domain.payment

import movie.domain.Price
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class PaymentMethodTest {
    @Test
    fun `결제수단으로 신용카드를 선택할 경우 할인된 금액을 반환한다`() {
        val payment = Card()
        val totalPrice = Price(10000)

        val resultPrice = payment.paymentPrice(totalPrice = totalPrice)

        assertThat(resultPrice).isEqualTo(Price(9500))
    }

    @Test
    fun `결제수단으로 현금을 선택할 경우 할인된 금액을 반환한다`() {
        val payment = Cash()
        val totalPrice = Price(10000)

        val resultPrice = payment.paymentPrice(totalPrice = totalPrice)

        assertThat(resultPrice).isEqualTo(Price(9800))
    }
}
