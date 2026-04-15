package movie.domain.discount

import movie.domain.Price
import movie.domain.Schedule

class TimePolicy : DateDiscountPolicy {
    override fun discount(
        price: Price,
        schedule: Schedule,
    ): Price {
        if (isTimeDiscount(schedule)) {
            return price - Price(2000)
        }

        return price
    }

    private fun isTimeDiscount(schedule: Schedule): Boolean = schedule.startTime.hour !in DISCOUNT_TIME

    companion object {
        private val DISCOUNT_TIME = 11..19
    }
}
