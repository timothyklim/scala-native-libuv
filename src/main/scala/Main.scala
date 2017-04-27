import scalanative.native._, stdlib._, stdio._

import com.timothyklim.libuv.Libuv._

object Main {
  def main(args: Array[String]): Unit = {
    val loop = defaultLoop()
    println(s"libuv version is: ${version()}, ${versionString()}, ${uptime()}")
  }
}
