import scalanative.native._, stdlib._, stdio._

import com.timothyklim.libuv.clib
import com.timothyklim.libuv.sys
import com.timothyklim.libuv.Libuv._

object TcpServer {
  def main(args: Array[String]): Unit = {
    println(s"libuv version: ${versionString()}, uptime: ${uptime()}")

    val loop = defaultLoop()
    val addr = ip4Addr("127.0.0.1", 8080)
    val server = TcpT()

    tcpInit(loop, server)
    tcpBind(server, addr)
    listen(server, 128, on_new_connection _)
    run(loop)
  }

  def on_new_connection(server: Ptr[clib.uv_stream_t], status: Int): Int = {
    println(s"!!!!!!!!!!: $status")
    exit(0)
    0
  }
}
