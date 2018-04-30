package fscoward.undine.domain

import java.time.{LocalDateTime, ZoneId, ZonedDateTime}

import org.scalatest.FunSpec

class DayScheduleSpecs extends FunSpec {
  describe("Day Schedule") {
    describe("when empty") {
      it("タスクを追加できる") {
        val daySchedule = DaySchedule(
          id = ID[DaySchedule](0),
          taskList = Nil,
          startHour = 9,
          startMinute = 30,
          endHour = 18,
          endMinute = 30
        )
        val task = Task(
          id = ID[Task](0),
          title = "test",
          description = None,
          startAt = ZonedDateTime.of(2018, 4, 30, 9, 30, 0, 0, ZoneId.of("UTC")),
          endAt = ZonedDateTime.of(2018, 4, 30, 10, 0, 0, 0, ZoneId.of("UTC")),
          estimate = 30,
          status = Status.UnDone,
          completedAt = None,
          repeatCondition = None
        )

        assert(daySchedule.canAppend(task))
      }

    }

    it("予定が完全に被っている場合は追加出来ない") {
      val task = Task(
        id = ID[Task](0),
        title = "test",
        description = None,
        startAt = ZonedDateTime.of(2018, 4, 30, 9, 30, 0, 0, ZoneId.of("UTC")),
        endAt = ZonedDateTime.of(2018, 4, 30, 10, 0, 0, 0, ZoneId.of("UTC")),
        estimate = 30,
        status = Status.UnDone,
        completedAt = None,
        repeatCondition = None
      )
      val daySchedule = DaySchedule(
        id = ID[DaySchedule](0),
        taskList = Seq(task),
        startHour = 9,
        startMinute = 30,
        endHour = 18,
        endMinute = 30
      )
      val appendTask = Task(
        id = ID[Task](0),
        title = "test",
        description = None,
        startAt = ZonedDateTime.of(2018, 4, 30, 9, 45, 0, 0, ZoneId.of("UTC")),
        endAt = ZonedDateTime.of(2018, 4, 30, 10, 0, 0, 0, ZoneId.of("UTC")),
        estimate = 30,
        status = Status.UnDone,
        completedAt = None,
        repeatCondition = None
      )

      assert(daySchedule.canAppend(appendTask) == false)
    }

    it("予定が被っている場合は追加出来ない") {
      val task = Task(
        id = ID[Task](0),
        title = "test",
        description = None,
        startAt = ZonedDateTime.of(2018, 4, 30, 9, 30, 0, 0, ZoneId.of("UTC")),
        endAt = ZonedDateTime.of(2018, 4, 30, 10, 0, 0, 0, ZoneId.of("UTC")),
        estimate = 30,
        status = Status.UnDone,
        completedAt = None,
        repeatCondition = None
      )
      val daySchedule = DaySchedule(
        id = ID[DaySchedule](0),
        taskList = Seq(task),
        startHour = 9,
        startMinute = 30,
        endHour = 18,
        endMinute = 30
      )
      val appendTask = Task(
        id = ID[Task](0),
        title = "test",
        description = None,
        startAt = ZonedDateTime.of(2018, 4, 30, 9, 45, 0, 0, ZoneId.of("UTC")),
        endAt = ZonedDateTime.of(2018, 4, 30, 10, 15, 0, 0, ZoneId.of("UTC")),
        estimate = 30,
        status = Status.UnDone,
        completedAt = None,
        repeatCondition = None
      )

      assert(daySchedule.canAppend(appendTask) == false)
    }

    it("予定が被っている場合は追加出来ない2") {
      val task1 = Task(
        id = ID[Task](0),
        title = "test",
        description = None,
        startAt = ZonedDateTime.of(2018, 4, 30, 9, 30, 0, 0, ZoneId.of("UTC")),
        endAt = ZonedDateTime.of(2018, 4, 30, 10, 0, 0, 0, ZoneId.of("UTC")),
        estimate = 30,
        status = Status.UnDone,
        completedAt = None,
        repeatCondition = None
      )
      val task2 = Task(
        id = ID[Task](0),
        title = "test",
        description = None,
        startAt = ZonedDateTime.of(2018, 4, 30, 11, 0, 0, 0, ZoneId.of("UTC")),
        endAt = ZonedDateTime.of(2018, 4, 30, 12, 0, 0, 0, ZoneId.of("UTC")),
        estimate = 30,
        status = Status.UnDone,
        completedAt = None,
        repeatCondition = None
      )
      val daySchedule = DaySchedule(
        id = ID[DaySchedule](0),
        taskList = Seq(task1, task2),
        startHour = 9,
        startMinute = 30,
        endHour = 18,
        endMinute = 30
      )
      val appendTask = Task(
        id = ID[Task](0),
        title = "test",
        description = None,
        startAt = ZonedDateTime.of(2018, 4, 30, 10, 0, 0, 0, ZoneId.of("UTC")),
        endAt = ZonedDateTime.of(2018, 4, 30, 11, 30, 0, 0, ZoneId.of("UTC")),
        estimate = 30,
        status = Status.UnDone,
        completedAt = None,
        repeatCondition = None
      )

      assert(daySchedule.canAppend(appendTask) == false)
    }

  }

}
