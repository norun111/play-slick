package controllers

import play.api.mvc._
import play.api.data._
import play.api.data.Forms._
import scala.concurrent._
import javax.inject.Inject


class UserController @Inject()(components: MessagesControllerComponents)
  extends MessagesAbstractController(components) {

  def signUp = Action { implicit request: Request[AnyContent] =>
    Ok(views.html.signUp())
  }


  def create = TODO


  def edit(id: Option[String]) = TODO


  def update = TODO


  def remove(id: Long) = TODO

}