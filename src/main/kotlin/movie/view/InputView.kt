package movie.view

import movie.domain.payment.Card
import movie.domain.payment.Cash
import movie.domain.payment.PaymentMethod

object InputView {
    fun readReservationStart(): Boolean {
        println("영화 예매를 시작합니다. 새 예매를 생성하시겠습니까? (Y/N)")

        val input = readln()
        InputValidator.validateYesNo(input)

        return input.uppercase() == "Y"
    }

    fun readMovieTitle(): String {
        println("예매할 영화 제목을 입력하세요:")
        return readln()
    }

    fun readMovieDate(): String {
        println("날짜를 입력하세요 (YYYY-MM-DD):")

        val input = readln()
        InputValidator.validateDate(input)

        return input
    }

    fun readSelectedMovieTimeNumber(): Int {
        println("상영 번호를 선택하세요:")

        val input = readln()
        InputValidator.validateNumber(input)

        return input.toInt() - 1
    }

    fun readReservationSeat(): List<String> {
        println("예약할 좌석을 입력하세요 (A1, B2):")

        val input = readln()

        InputValidator.validateSeatNumbers(input)

        val splitInput = input.split(",").map { it.trim() }

        return splitInput
    }

    fun readContinueReservation(): Boolean {
        println("다른 영화를 추가하시겠습니까? (Y/N)")
        val input = readln()
        InputValidator.validateYesNo(input)

        return input.uppercase() == "Y"
    }

    fun readUsePoint(): String {
        println("사용할 포인트를 입력하세요 (없으면 0):")
        return readln()
    }

    fun readPaymentType(paymentMethods: List<PaymentMethod>): Int {
        println("결제 수단을 선택하세요:")
        paymentMethods.forEachIndexed { index, method ->
            val name =
                when (method) {
                    is Card -> "신용카드"
                    is Cash -> "현금"
                    is PaymentMethod -> "없음"
                }

            val discountPercent = (method.discountRate * 100).toInt()
            println("${index + 1}) $name($discountPercent% 할인)")
        }

        val input = readln()
        InputValidator.validateNumber(input)

        return input.toInt() - 1
    }

    fun readUserPayment(): Boolean {
        println("위 금액으로 결제하시겠습니까? (Y/N)")

        val input = readln()
        InputValidator.validateYesNo(input)

        return input.uppercase() == "Y"
    }
}
