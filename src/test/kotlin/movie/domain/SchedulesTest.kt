package movie.domain

import movie.fixture.ScheduleFixture
import movie.fixture.SchedulesFixture
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import java.time.LocalDate

class SchedulesTest {
    @Test
    fun `영화 타이틀을 정상적으로 반환한다`() {
        val schedules = SchedulesFixture.defaultSchedules

        val titles = schedules.getMovieTitles()

        assertThat(titles).isEqualTo(listOf(MovieTitle("시동"), MovieTitle("토토로")))
    }

    @Test
    fun `중복된 영화 타이틀은 제거되어 반환한다`() {
        val schedules = SchedulesFixture.defaultSchedules
        val titles = schedules.getMovieTitles()

        assertThat(titles).isEqualTo(listOf(MovieTitle("시동"), MovieTitle("토토로")))
    }

    @Test
    fun `상영중인 스케줄을 반환한다`() {
        val scheduleList = listOf(ScheduleFixture.createSchedule())
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
        val schedule = ScheduleFixture.createSchedule()
        val schedules = Schedules(listOf(schedule))
        val movieSchedules = schedules.getSchedule(
            movieTitle = schedule.movie.title,
            startTime = schedule.startTime
        )

        assertThat(movieSchedules).isEqualTo(schedule)
    }
}
