import scalanative.native._, stdlib._, stdio._

import com.timothyklim.libuv.clib
import com.timothyklim.libuv.sys
import com.timothyklim.libuv.Libuv._

object TcpServer {
  def main(args: Array[String]): Unit = {
    println(s"libuv version is: ${versionString()}, uptime: ${uptime()}")

    val loop = defaultLoop()
    val addr = ip4Addr("127.0.0.1", 8080)
    val server = malloc(sizeof[clib.uv_tcp_t]).cast[Ptr[clib.uv_tcp_t]]
    tcpInit(loop, server)
    clib.uv_tcp_bind(server, addr.asInstanceOf[Ptr[sys.sockaddr]], 0.toUInt)

    clib.uv_listen(server.asInstanceOf[Ptr[clib.uv_stream_t]], 128, on_new_connection _)

    run(loop)
  }

  def on_new_connection(server: Ptr[clib.uv_stream_t], status: CInt): CInt = {
    println(s"!!!!!!!!!!: $status")
    0
  }
}
