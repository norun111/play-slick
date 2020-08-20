package models.daos

import java.time.ZonedDateTime
import java.util.UUID
import models.User
import scalikejdbc._
import utils.silhouette.IdentitySilhouette
import com.mohiva.play.silhouette.password.BCryptPasswordHasher
import scala.concurrent.Future
import scala.concurrent.ExecutionContext.Implicits.global

object User extends SQLSyntaxSupport[User] {
  def apply(u: SyntaxProvider[User])(rs: WrappedResultSet): User = apply(u.resultName)(rs)
  def apply(u: ResultName[User])(rs: WrappedResultSet): User = new User(
    id = rs.string(u.id),
    name = rs.string(u.name),
    email = rs.string(u.email),
    password = rs.string(u.password)
  )

  val u = User.syntax("u")

  override val autoSession = AutoSession

  def findUser(id: String)(implicit session: DBSession = autoSession): Option[User] = {
    withSQL{
      select.from(User as u).where.eq(u.id, id)
    }.map(User(u.resultName)).single.apply()
  }

  def findAllUsers(implicit session: DBSession = autoSession) = {
    withSQL {
      select.from(User as u).orderBy(u.created_at.desc)
    }.map(User(u.resultName))
      .list
      .apply()
  }

  def create(id: String = UUID.randomUUID.toString,
              name: String,
              email: String,
              password: String)(implicit session: DBSession = autoSession): Unit = {
    withSQL {
      insert.into(User).values(id, name, email, password, ZonedDateTime.now())
    }.update.apply()
  }

  def save(entity: User)(implicit session: DBSession = autoSession): User = {
    withSQL {
      update(User).set(
        column.id -> entity.id,
        column.name -> entity.name,
        column.email -> entity.email,
        column.password -> entity.password
      ).where.eq(column.id, entity.id)
    }.update.apply()
    entity
  }

  def findByEmail(email: String)(
    implicit session: DBSession = autoSession): Option[User] = {
    withSQL {
      select.from(User as u).where.eq(u.email, email)
    }.map(User(u.resultName)).single.apply()
  }

}