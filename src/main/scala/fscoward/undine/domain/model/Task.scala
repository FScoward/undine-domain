package fscoward.undine.domain.model

import java.time.ZonedDateTime

import fscoward.undine.domain.model.Status.Status

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

object Task {
  implicit class Author(user: User) {
    def createTask(
      title: String,
      description: Option[String],
      startAt: ZonedDateTime,
      endAt: ZonedDateTime,
      estimate: Int,
      repeatCondition: Option[RepeatCondition]
    ): Task = Task(
      id = ID[Task](ID.generate),
      title = title,
      description = description,
      startAt = startAt,
      endAt = endAt,
      estimate = estimate,
      status = Status.UnDone,
      completedAt =  None,
      repeatCondition = repeatCondition
    )
  }

  implicit class Asignee(user: User) {
    def closeTask(task: Task): Task = ???
  }
}
