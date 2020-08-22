package controllers

import com.mohiva.play.silhouette.api.LogoutEvent
import com.mohiva.play.silhouette.api.actions._
import com.mohiva.play.silhouette.impl.providers._
import javax.inject.Inject
import play.api.mvc._
import utils.route.Calls

/**
 * The basic application controller.
 */
import scala.concurrent.ExecutionContext

class ApplicationController @Inject() (
  scc: SilhouetteControllerComponents,
  home: views.html.home
)(implicit ex: ExecutionContext) extends SilhouetteController(scc) {
  /**
   * Handles the index action.
   *
   * @return The result to display.
   */
  def index = SecuredAction.async { implicit request: SecuredRequest[EnvType, AnyContent] =>
    authInfoRepository.find[GoogleTotpInfo](request.identity.loginInfo).map { totpInfoOpt =>
      Ok(home(request.identity, totpInfoOpt))
    }
  }

  /**
   * Handles the Sign Out action.
   *
   * @return The result to display.
   */
  def signOut = SecuredAction.async { implicit request: SecuredRequest[EnvType, AnyContent] =>
    val result = Redirect(Calls.home)
    eventBus.publish(LogoutEvent(request.identity, request))
    authenticatorService.discard(request.authenticator, result)
  }
}
