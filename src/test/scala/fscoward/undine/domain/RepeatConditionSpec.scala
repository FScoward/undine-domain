package fscoward.undine.domain

import fscoward.undine.domain.model.RepeatCondition
import org.scalatest.FunSpec

class RepeatConditionSpec extends FunSpec {
  describe("繰り返し条件") {
    describe("繰り返し回数") {
      it("100はOK") {
        val times = 100
        assert(RepeatCondition(times = times).times == times)
      }
      it("999はOK") {
        val times = 999
        assert(RepeatCondition(times = times).times == times)
      }
      it("999以上はエラーになること") {
        assertThrows[AssertionError] {
          RepeatCondition(times = 1000)
        }
      }
    }
  }
}
