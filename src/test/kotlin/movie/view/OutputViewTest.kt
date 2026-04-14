package movie.view

import movie.view.OutputView
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class OutputViewTest {
    @Test
    fun `천의 단위가 넘어가면 ,가 천의자리 단위로 추가된다`() {
        val value = 1000
        val result = OutputView.formatWithComma(value)

        assertThat(result).isEqualTo("1,000")
    }

    @Test
    fun `천의 단위 이하라면 ,가 추가되지 않는다`() {
        val value = 100
        val result = OutputView.formatWithComma(value)

        assertThat(result).isEqualTo("100")
    }
}
