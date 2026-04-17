package movie.controller

import movie.domain.Cart
import movie.domain.MovieManager
import movie.domain.MovieTitle
import movie.domain.PaymentManager
import movie.domain.Schedule
import movie.domain.payment.Card
import movie.domain.payment.Cash
import movie.domain.payment.PaymentMethod
import movie.domain.point.Point
import movie.domain.seat.SeatNumber
import movie.service.ReservationService
import movie.service.ScheduleService
import movie.view.InputView
import movie.view.OutputView
import java.time.LocalDate
import java.time.LocalDateTime

class MovieController(
    private val scheduleService: ScheduleService,
    private val reservationService: ReservationService,
    private val paymentManager: PaymentManager = PaymentManager(),
    private val cart: Cart = Cart(),
    private val movieManager: MovieManager = MovieManager(scheduleService.getSchedules()),
) {
    fun run() {
        if (!getReservationStart()) {
            return
        }

        while (true) {
            movieReservationStart()

            if (!getContinueReservation()) {
                break
            }
        }

        moviePricePayment()
    }

    private fun getReservationStart(): Boolean =
        whileGetInput {
            InputView.readReservationStart()
        }

    private fun movieReservationStart() {
        val title = getTitle()
        val movieTimes = getMovieTimes(title)

        val schedule = getSchedule(title = title, movieTimes = movieTimes)
        val seats = getSeats(schedule)

        schedule.addSeats(seats)
        cart.addReservation(schedule = schedule, seats = seats)

        OutputView.printReservationAddMessage(schedule, seats)
    }

    private fun getContinueReservation(): Boolean =
        whileGetInput {
            InputView.readContinueReservation()
        }

    private fun moviePricePayment() {
        OutputView.printCart(cart)

        val usePoint = getUsePoint()
        val paymentMethod = getPaymentMethod()

        val paymentPrice =
            paymentManager.calculateFinalPrice(
                cart = cart,
                usePoint = usePoint,
                paymentMethod = paymentMethod,
            )

        OutputView.printTotalPrice(paymentPrice)

        if (getUserPayment()) {
            cart.getReservations().forEach { reservationService.saveReservation(it) }
            OutputView.printReceipt(cart = cart, paymentPrice = paymentPrice, usePoint = usePoint)
            OutputView.printThankYou()
        }
    }

    private fun getTitle(): MovieTitle =
        whileGetInput {
            val input = InputView.readMovieTitle()
            val title = MovieTitle(input)

            require(movieManager.hasMovieTitle(title)) { "상영 중인 영화가 없습니다." }

            title
        }

    private fun getMovieTimes(title: MovieTitle): List<LocalDateTime> =
        whileGetInput {
            val date = getDate()
            val movieTimes = movieManager.getMovieStartTime(title, date)

            movieTimes
        }

    private fun getSchedule(
        title: MovieTitle,
        movieTimes: List<LocalDateTime>,
    ): Schedule {
        OutputView.printMovieStartTimes(movieTimes)

        return whileGetInput {
            val index = InputView.readSelectedMovieTimeNumber()

            require(index < movieTimes.size) {
                "시간 인덱스가 유효하지 않습니다."
            }

            val schedule = movieManager.getSchedule(title = title, startTime = movieTimes[index])

            require(!cart.isDuplicateTime(schedule)) {
                "선택하신 상영 시간이 겹칩니다. 다른 시간을 선택해 주세요."
            }

            OutputView.printSeats(schedule)

            schedule
        }
    }

    private fun getSeats(schedule: Schedule): List<SeatNumber> =
        whileGetInput {
            val input = InputView.readReservationSeat()

            val seats = input.map { SeatNumber(it) }

            require(!schedule.isReservationSeats(seats)) { "이미 예약된 좌석입니다." }

            seats
        }

    private fun getUsePoint(): Point =
        whileGetInput {
            val input = InputView.readUsePoint()

            Point(input)
        }

    private fun getPaymentMethod(): PaymentMethod =
        whileGetInput {
            val paymentMethods = listOf(Cash(), Card())
            val index = InputView.readPaymentType(paymentMethods)

            paymentMethods[index]
        }

    private fun getUserPayment(): Boolean =
        whileGetInput {
            InputView.readUserPayment()
        }

    private fun getDate(): LocalDate =
        whileGetInput {
            val date = InputView.readMovieDate()

            LocalDate.parse(date)
        }

    private fun <T> whileGetInput(action: () -> T): T {
        while (true) {
            try {
                val input = action()

                return input
            } catch (e: IllegalArgumentException) {
                OutputView.printErrorMessage(e.message)
            }
        }
    }
}
