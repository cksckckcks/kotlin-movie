package movie.domain

import movie.fixture.SchedulesFixture
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import java.time.LocalDate
import java.time.LocalDateTime

class MovieManagerTest {
    @Test
    fun `특정 영화의 시작 시간을 반환한다`() {
        val schedules = SchedulesFixture.defaultSchedules
        val movieManager =
            MovieManager(
                schedules = schedules,
            )

        val startTimes =
            movieManager.getMovieStartTime(
                movieTitle = MovieTitle("시동"),
                date = LocalDate.of(2026, 4, 10),
            )

        assertThat(startTimes).isEqualTo(
            listOf(
                LocalDateTime.of(2026, 4, 10, 10, 0),
                LocalDateTime.of(2026, 4, 10, 13, 0),
            ),
        )
    }
}
