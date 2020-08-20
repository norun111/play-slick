package utils.silhouette

import java.util.UUID

import org.joda.time.DateTime

class MailToken {
  def id: String
  def email: String
  def expirationTime: DateTime
  def isExpired = expirationTime.isBeforeNow
}
