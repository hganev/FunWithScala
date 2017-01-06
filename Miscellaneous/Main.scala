package recfun

object Main {
  def main(args: Array[String]) {
    println("Pascal's Triangle")
    for (row <- 0 to 10) {
      for (col <- 0 to row)
        print(pascal(col, row) + " ")
      println()
    }
  }

  /**
   * Exercise 1
   */
    def pascal(c: Int, r: Int): Int = {
      if (c == 0 || r == 0 || c == r) 1
      else  pascal(c-1, r-1) + pascal(c, r-1)
    }
  
  /**
   * Exercise 2
   */
  def balance(chars: List[Char]): Boolean = {
    def findBalance(chars: List[Char], open: Int): Boolean = {
      if (chars.isEmpty)
        open == 0
      else
        if (chars.head == '(')
          findBalance(chars.tail, open + 1)
        else
          if (chars.head == ')')
            open > 0 && findBalance(chars.tail, open - 1)
          else
            findBalance(chars.tail, open)
    }
    findBalance(chars,0)
  }

    /**
     * Exercise 3
     */
    def countChange(money: Int, coins: List[Int]): Int = {
      if(money == 0) return 1
      else if(money > 0 && !coins.isEmpty)
        countChange(money - coins.head, coins) + countChange(money, coins.tail)
      else return 0
    }
  }
