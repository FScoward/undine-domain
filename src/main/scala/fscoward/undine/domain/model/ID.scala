package fscoward.undine.domain.model

import java.util.UUID

case class ID[T](value: String) extends AnyVal

object ID {
  def generate: String = UUID.randomUUID().toString()
}
