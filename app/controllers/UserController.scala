package controllers

import play.api.mvc._
import play.api.data._
import play.api.data.Forms._
import scala.concurrent._
import javax.inject.Inject


class UserController @Inject()(components: MessagesControllerComponents)
  extends MessagesAbstractController(components) {

  /**
   * 一覧表示
   */
  def signUp = TODO

  /**
   * 登録実行
   */
  def create = TODO

  /**
   * 編集画面表示
   */
  def edit(id: Option[Long]) = TODO

  /**
   * 更新実行
   */
  def update = TODO

  /**
   * 削除実行
   */
  def remove(id: Long) = TODO

}