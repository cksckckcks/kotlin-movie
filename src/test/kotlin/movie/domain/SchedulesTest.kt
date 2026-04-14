package movie.domain

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import java.time.LocalDate
import java.time.LocalDateTime

class SchedulesTest {
    @Test
    fun `영화 타이틀을 정상적으로 반환한다`() {
        val schedules = Schedules(listOf(schedule1, schedule3))

        val titles = schedules.getMovieTitles()

        assertThat(titles).isEqualTo(listOf(MovieTitle("시동"), MovieTitle("토토로")))
    }

    @Test
    fun `중복된 영화 타이틀은 제거되어 반환한다`() {
        val schedules = Schedules(listOf(schedule1, schedule2))

        val titles = schedules.getMovieTitles()

        assertThat(titles).isEqualTo(listOf(MovieTitle("시동")))
    }

    @Test
    fun `상영중인 스케줄을 반환한다`() {
        val scheduleList = listOf(schedule1, schedule2)
        val schedules = Schedules(scheduleList)

        val movieSchedules =
            schedules.getMovieSchedules(
                movieTitle = MovieTitle("시동"),
                date = LocalDate.of(2026, 4, 10),
            )

        assertThat(movieSchedules).isEqualTo(scheduleList)
    }

    @Test
    fun `특정 영화의 스케줄을 반환한다`() {
        val scheduleList = listOf(schedule1, schedule2, schedule3)
        val schedules = Schedules(scheduleList)

        val movieSchedules = schedules.getSchedule(
            movieTitle = schedule1.movie.title,
            startTime = schedule1.startTime
        )

        assertThat(movieSchedules).isEqualTo(schedule1)
    }

    companion object {
        val schedule1 =
            Schedule(
                movie =
                    Movie(
                        title = MovieTitle("시동"),
                        runningTime = 120,
                    ),
                startTime = LocalDateTime.of(2026, 4, 10, 10, 0),
                endTime = LocalDateTime.of(2026, 4, 10, 12, 0),
            )
        val schedule2 =
            Schedule(
                movie =
                    Movie(
                        title = MovieTitle("시동"),
                        runningTime = 120,
                    ),
                startTime = LocalDateTime.of(2026, 4, 10, 11, 0),
                endTime = LocalDateTime.of(2026, 4, 10, 13, 0),
            )

        val schedule3 =
            Schedule(
                movie =
                    Movie(
                        title = MovieTitle("토토로"),
                        runningTime = 120,
                    ),
                startTime = LocalDateTime.of(2026, 4, 10, 14, 0),
                endTime = LocalDateTime.of(2026, 4, 10, 19, 0),
            )
    }
}
