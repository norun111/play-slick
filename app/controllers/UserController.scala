package controllers

import java.util.UUID

import play.api.mvc._
import play.api.data._
import play.api.data.Forms._
import scalikejdbc._
import javax.inject.Inject
import models._
import daos.User
import play.api.i18n._

object UserController {
  // フォームの値を格納するケースクラス
  case class UserForm(id: String = UUID.randomUUID.toString, name: String, email: String, password: String)

  // formから送信されたデータ ⇔ ケースクラスの変換を行う
  val userForm = Form(
    mapping(
      "id"        -> nonEmptyText,
      "name"      -> nonEmptyText(minLength = 1, maxLength = 20),
      "email"     -> email,
      "password"  -> nonEmptyText(minLength = 8, maxLength = 60),
    )(UserForm.apply)(UserForm.unapply)
  )
}

class UserController @Inject()(components: MessagesControllerComponents)
  extends MessagesAbstractController(components) with I18nSupport {

  import UserController._

  def signUp = Action { implicit request =>
    Ok(views.html.user.signUp.apply(userForm))
  }


  def create = Action { implicit request =>
    DB.localTx {
      implicit session =>
        userForm.bindFromRequest.fold(
          error => {
            BadRequest(views.html.user.signUp.apply(error))
          },
          form => {
            val uuid = UUID.randomUUID
            User.create(uuid.toString, form.name, form.email, form.password)
            Redirect(routes.HomeController.index())
          }
        )
    }
  }


  def edit(id: Option[String]) = TODO


  def update = TODO


  def remove(id: Long) = TODO

}