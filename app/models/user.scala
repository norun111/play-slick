package models

import java.util.UUID
import utils.silhouette.IdentitySilhouette
import com.mohiva.play.silhouette.password.BCryptPasswordHasher
import scala.concurrent.Future
import scala.concurrent.ExecutionContext.Implicits.global

case class User(id: String = UUID.randomUUID.toString,
                name: String,
                email: String,
                password: String
               ) extends IdentitySilhouette {
  def key = email
}