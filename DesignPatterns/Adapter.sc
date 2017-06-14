import java.util.logging.Level

trait Log {
  def warning(message: String)
  def error(message: String)
}

final class Logger {
  def log(level: Level, message: String) { /* ... */ }
}

implicit class LoggerToLogAdapter(logger: Logger) extends Log {
  def warning(message: String) { logger.log(Level.WARNING, message) }
  def error(message: String) { logger.log(Level.SEVERE, message) }
}

val log: Log = new Logger()
log.error("STOP")