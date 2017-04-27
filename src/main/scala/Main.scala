import scalanative.native._, stdlib._, stdio._

import com.timothyklim.libuv.Libuv._

object Main {
  def main(args: Array[String]): Unit =
    println(s"libuv version is: ${version()}, ${versionString()}")
}
