package controllers

import java.util.UUID
import javax.inject.Inject
import models.daos.User
import play.api.data.Forms._
import play.api.data._
import play.api.i18n._
import play.api.mvc._
import scalikejdbc._

object SignUpController {
  // フォームの値を格納するケースクラス
  case class UserForm(name: String, email: String, password: String)

  // formから送信されたデータ ⇔ ケースクラスの変換を行う
  val userForm = Form(
    mapping(
      "name"      -> nonEmptyText(minLength = 1, maxLength = 20),
      "email"     -> email,
      "password"  -> nonEmptyText(minLength = 8, maxLength = 60),
    )(UserForm.apply)(UserForm.unapply)
  )
}

class SignUpController @Inject()(components: MessagesControllerComponents)
  extends MessagesAbstractController(components) with I18nSupport {

  import SignUpController._

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
            println("Hi")
            val uuid = UUID.randomUUID
            User.create(uuid.toString, form.name, form.email, form.password)
            Redirect(routes.HomeController.index())
          }
        )
    }
  }
}
