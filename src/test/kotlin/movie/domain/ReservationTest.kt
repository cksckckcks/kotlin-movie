package movie.domain

import movie.domain.seat.SeatNumber
import movie.fixture.ScheduleFixture
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class ReservationTest {
    @Test
    fun `스케줄과 선택한 좌석들을 리스트로 전달하면 예약 객체가 생성된다`() {
        val schedule = ScheduleFixture.createSchedule()
        val seats =
            listOf(
                SeatNumber(row = 'A', col = 1),
                SeatNumber(row = 'A', col = 2),
            )

        val reservation = Reservation(schedule = schedule, seats = seats)

        assertThat(reservation.schedule).isEqualTo(schedule)
        assertThat(reservation.seats).isEqualTo(seats)
    }

    @Test
    fun `예약된 모든 좌석의 가격을 합산하여 총 금액을 반환한다`() {
        val schedule = ScheduleFixture.createSchedule()
        val seats =
            listOf(
                SeatNumber(row = 'A', col = 1),
                SeatNumber(row = 'A', col = 2),
                SeatNumber(row = 'A', col = 3),
            )
        val reservation = Reservation(schedule = schedule, seats = seats)

        val totalPrice = reservation.calculateTotalPrice()

        assertThat(totalPrice).isEqualTo(Price(36_000))
    }
}
