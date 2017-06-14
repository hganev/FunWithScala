case class Event(source: String)

type EventHandler = PartialFunction[Event, Unit]

val defaultHandler: EventHandler = PartialFunction(_ => ())

val keyboardHandler: EventHandler = {
  case Event("keyboard") => println("Keyboard does sth")
}

def mouseHandler(delay: Int): EventHandler = {
  case Event("mouse") => println("Mouse does sth")
}

keyboardHandler.orElse(mouseHandler(100)).orElse(defaultHandler)