package models

import java.util.UUID
import daos.User

case class User(id: String = UUID.randomUUID.toString,
                name: String,
                email: String,
                password: String
               )