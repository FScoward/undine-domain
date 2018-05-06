package fscoward.undine.domain.model

import com.google.common.collect.Range
import fscoward.undine.domain.error.DomainError

case class DaySchedule(
    id: ID[DaySchedule],
    taskList: Seq[Task],
    startHour: Int, // TODO Validate
    startMinute: Int, // TODO Validate
    endHour: Int, // TODO Validate
    endMinute: Int // TODO Validate
) {
  def canAppend(task: Task): Boolean = {
    val result = this.taskList.find { t =>
      val range = Range.closed(t.startAt, t.endAt)
      range.contains(task.startAt) || range.contains(task.endAt)
    }
    result.isEmpty
  }

  private def overlap(addTask: Task, nextTask: Task): Unit = {
  }

  /**
    * タスクの追加
    * 次のタスクとかぶる場合には次のタスクを押し出す。
    *
    * */
  def append(task: Task): Either[DomainError, DaySchedule] = {
    if (canAppend(task)) {
      Right(this.copy(taskList = this.taskList :+ task))
    } else {
      Left(new DomainError("can't add task"))
    }
  }
}
