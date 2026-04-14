package movie.domain.discount

import movie.domain.Price
import movie.fixture.ScheduleFixture
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class DiscountTest {
    @Test
    fun `무비데이 할인이 적용된 가격을 반환한다`() {
        val schedule = ScheduleFixture.createSchedule()
        val price = Price(30_000)
        val discount = Discount()
        val discountPrice = discount.getTotalDiscountPrice(price, schedule)

        assertThat(discountPrice).isEqualTo(Price(27_000))
    }

    @Test
    fun `타임 할인이 적용된 가격을 반환한다`() {
        val schedule = ScheduleFixture.createSchedule()
        val price = Price(30_000)
        val discount = Discount()
        val discountPrice = discount.getTotalDiscountPrice(price, schedule)

        assertThat(discountPrice).isEqualTo(Price(28_000))
    }

    @Test
    fun `무비데이, 타임 할인이 동ㅇ시에 적용된 가격을 반환한다`() {
        val schedule = ScheduleFixture.createSchedule()
        val price = Price(30_000)
        val discount = Discount()
        val discountPrice = discount.getTotalDiscountPrice(price, schedule)

        assertThat(discountPrice).isEqualTo(Price(25_000))
    }
}
