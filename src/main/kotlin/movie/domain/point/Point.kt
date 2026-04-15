package movie.domain.point

@JvmInline
value class Point(
    val amount: Int,
) {
    init {
        require(amount >= 0) { "포인트는 0원 이상이어야 합니다." }
    }

    constructor(input: String) : this(
        amount = input.toIntOrNull() ?: throw IllegalArgumentException("포인트는 정수여야 합니다."),
    )
}
