case class User(name: String)

trait Repository {
  def save(user: User)
}

trait DatabaseRepository extends Repository {
  def save(user: User) = {}
  /* ... */
}

trait UserService { self: Repository => // requires Repository
  def create(user: User) {
    // ...
    save(user)
  }
}

new UserService with DatabaseRepository