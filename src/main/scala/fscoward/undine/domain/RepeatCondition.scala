package fscoward.undine.domain

import fscoward.undine.domain.WeekDay.WeekDay
import fscoward.undine.domain.error.DomainError

/**
  * @param times 繰り返し回数
  * @param weekDay 繰り返しする曜日
  * */
case class RepeatCondition(
    times: Int,
    weekDay: Seq[WeekDay] = Nil
)

object RepeatCondition {
  def apply(
      times: Int,
      weekDay: Seq[WeekDay] = Nil
  ): RepeatCondition = {
    assert(times <= 999)
    new RepeatCondition(times, weekDay)
  }
}
