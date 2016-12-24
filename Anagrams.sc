/**
  * Task description:
  *
  * The task is to write a program which finds all anagrams of a given word in a
  * dictionary (the anagram must also be part of the dictionary).
  * The dictionary is a simple text file which contains one word per line. The program
  * should handle anagram-requests as fast as possible. Consider a pre-processing
  * of the dictionary to optimize the requests.
  * */
object Anagrams {

  type Word = String
  type Occurrences = List[(Char, Int)]

  val dictionary: List[String] = loadDictionary("wordlist.txt")

  def main(args: Array[String]): Unit = {
    // load dictionary
//    println(dictionary.length)

    // read the user choice
    val ln = scala.io.StdIn.readLine()

    // find anagrams
    time({
      val anagrams = findWordAnagrams(ln)
      // print the found anagrams
      println(anagrams.mkString("|"))
    })
  }

  def findWordAnagrams(word: Word): List[Word] = dictionaryByOccurrences(charOccurrences(word))

  lazy val dictionaryByOccurrences: Map[Occurrences, List[Word]] =
    dictionary map (word => (word, charOccurrences(word))) groupBy(_._2) map (kv => (kv._1, kv._2.map({case(w,l) => w})))

  def charOccurrences(w: Word): Occurrences =
    w.toLowerCase.groupBy(x => x).toList map ({case (c,l) => (c,l.length)}) sorted

  def loadDictionary(fileName: String) = {
    val wordStream = getClass.getResourceAsStream("/" + fileName)
    try {
      val s = io.Source.fromInputStream(wordStream)
      s.getLines.toList
    } catch {
      case e: Exception =>
        println("Could not load word list: " + e)
        throw e
    } finally {
      wordStream.close()
    }
  }

  def time[R](block: => R): R = {
    val startTime = System.nanoTime()
    val result = block    // call-by-name
    val endTime = System.nanoTime()
    val elapsedTime = endTime - startTime;

    val elapsedTimeInseconds = elapsedTime / 1000000000.0;
    println("Time for operation: " + elapsedTimeInseconds + "s")
    result
  }
}
