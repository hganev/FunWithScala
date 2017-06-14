trait Animal
private class Dog extends Animal
private class Cat extends Animal

object Animal {
  def apply(kind: String) = kind match {
    case "dog" => new Dog()
    case "cat" => new Cat()
    case _ => throw new Exception(s"No type can be instantiated from string '${kind}'")
  }
}

Animal("dog")
Animal("cat")
Animal("no")