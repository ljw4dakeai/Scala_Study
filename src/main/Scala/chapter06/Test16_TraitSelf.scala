package chapter06

object Test16_TraitSelf {

  def main(args: Array[String]): Unit = {

    val user = new RegisterUser("zoujiahao","123")
    user.insert()
  }
}

class User(val name: String, val password: String)

trait UserDao{
  //既外部注入一个对象
  _: User =>

  def insert(): Unit = {
    println(s" INSERT INTO DB:${this.name}, ${this.password}")
  }

}


class RegisterUser(name: String, password: String) extends User(name, password)with UserDao{

}