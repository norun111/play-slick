package controllers

import models._
import utils.silhouette._
import utils.silhouette.Implicits._
import com.mohiva.play.silhouette.api.{ Silhouette, LoginInfo, SignUpEvent, LoginEvent, LogoutEvent }
import com.mohiva.play.silhouette.api.repositories.AuthInfoRepository
import com.mohiva.play.silhouette.api.util.{ Credentials, PasswordHasherRegistry, Clock }
import com.mohiva.play.silhouette.api.exceptions.ProviderException
import com.mohiva.play.silhouette.impl.providers.CredentialsProvider
import com.mohiva.play.silhouette.impl.authenticators.CookieAuthenticator
import com.mohiva.play.silhouette.impl.exceptions.{ IdentityNotFoundException, InvalidPasswordException }
import com.mohiva.play.silhouette.api.Authenticator.Implicits._
import play.api._
import play.api.mvc._
import play.api.data.Form
import play.api.data.Forms._
import play.api.data.validation.Constraints._
import play.api.i18n.{ MessagesApi, Messages }
import scala.concurrent.Future
import scala.concurrent.ExecutionContext.Implicits._
import scala.concurrent.duration._
import javax.inject.{ Inject, Singleton }
import views.html.{ auth => viewsAuth }

@Singleton
class SignInController @Inject() (val silhouette: Silhouette[MyEnv],
                                  val messagesApi: MessagesApi,
                                  userService: UserService,
                                  authInfoRepository: AuthInfoRepository,
                                  credentialsProvider: CredentialsProvider,
                                  tokenService: MailTokenService[MailTokenUser],
                                  passwordHasherRegistry: PasswordHasherRegistry,
                                  mailer: Mailer,
                                  conf: Configuration,
                                  clock: Clock
                                 ) extends AuthController  {
  {

}
