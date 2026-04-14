package movie.view

import java.time.LocalDate

object InputParser {
    fun parseYesNo(input: String): Boolean = input.uppercase() == "Y"

    fun parseDate(input: String): LocalDate = LocalDate.parse(input)

    fun parseSeatNumbers(input: String): List<String> =
        input
            .split(",")
            .map { it.trim() }

    fun parseIndex(
        input: String,
        size: Int,
    ): Int {
        val index = input.toInt()
        require(index in 1..size) { "유효한 번호를 입력해주세요" }

        return index - 1
    }
}
