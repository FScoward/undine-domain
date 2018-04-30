package fscoward.undine.domain

import java.time.ZonedDateTime

import fscoward.undine.domain.Status.Status

/**
  * @param title
  * @param description
  * @param startAt
  * @param endAt
  * @param estimate
  * */
case class Task(
    id: ID[Task],
    title: String,
    description: Option[String] = None,
    startAt: ZonedDateTime,
    endAt: ZonedDateTime,
    estimate: Int,
    status: Status,
    completedAt: Option[ZonedDateTime],
    repeatCondition: Option[RepeatCondition]
) {
  def done: Task = this.copy(
    status = Status.Done,
    completedAt = Some(ZonedDateTime.now())
  )

  def undone: Task = this.copy(
    status = Status.UnDone,
    completedAt = None
  )

  def doing: Task = this.copy(
    status = Status.Doing,
    completedAt = None
  )
}
