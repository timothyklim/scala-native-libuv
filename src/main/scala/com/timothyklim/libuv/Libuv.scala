package com.timothyklim.libuv

import scalanative.native._, stdlib._, stdio._

object Libuv {

  /** Loop data type.
    *
    * @see [[http://docs.libuv.org/en/stable/loop.html#c.uv_loop_t]]
    */
  type LoopT = clib.uv_loop_t
  type LoopTStruct = CStruct1[Ptr[Byte]] // .data

  sealed abstract class RunMode(val value: CInt) {
    def toUvRunMode(): clib.uv_run_mode = value.asInstanceOf[clib.uv_run_mode]
  }

  object RunMode {
    case object Default extends RunMode(0)
    case object Once extends RunMode(1)
    case object NoWait extends RunMode(2)
  }

  /** Initializes the given [[LoopT]] structure.
    *
    * @see [[http://docs.libuv.org/en/v1.x/loop.html#c.uv_loop_init]]
    */
  def loopInit(loop: Ptr[LoopT]): Int =
    clib.uv_loop_init(loop)

  /** Returns the initialized default loop. It may return NULL in case of allocation failure.
    * This function is just a convenient way for having a global loop throughout an application, the default loop is in no way different than the ones initialized with [[loopInit()]]. As such, the default loop can (and should) be closed with [[loopClose()]] so the resources associated with it are freed.
    *
    * @see [[http://docs.libuv.org/en/v1.x/loop.html#c.uv_default_loop]]
    */
  def defaultLoop(): Ptr[LoopT] =
    clib.uv_default_loop()

  def run(loop: Ptr[LoopT], mode: RunMode = RunMode.Default): Int =
    clib.uv_run(loop, mode.toUvRunMode)

  /** Gets the current system uptime.
    *
    * @see [[http://docs.libuv.org/en/v1.x/misc.html#c.uv_uptime]]
    */
  def uptime(): Double = {
    val ut = stackalloc[Double]
    clib.uv_uptime(ut)
    !ut
  }

  /** Returns [[http://docs.libuv.org/en/v1.x/version.html#c.UV_VERSION_HEX UV_VERSION_HEX]].
    *
    * @see [[http://docs.libuv.org/en/v1.x/version.html#c.uv_version]]
    */
  def version(): Int = clib.uv_version().toInt

  /** Returns the libuv version number as a string. For non-release versions the version suffix is included.
    *
    * @see [[http://docs.libuv.org/en/v1.x/version.html#c.uv_version_string]]
    */
  def versionString(): String = fromCString(clib.uv_version_string())

  type TcpT = clib.uv_tcp_t

  object TcpT {
    def apply(): Ptr[TcpT] = malloc(sizeof[TcpT]).cast[Ptr[TcpT]]
  }

  def tcpInit(loop: Ptr[LoopT], handle: Ptr[TcpT]): Int =
    clib.uv_tcp_init(loop, handle)

  def tcpBind(server: Ptr[TcpT], addr: Ptr[sys.sockaddr], flags: Int = 0): Int =
    clib.uv_tcp_bind(server, addr, flags.toUInt)

  def listen(stream: Ptr[clib.uv_stream_t], backlog: Int, cb: CFunctionPtr2[Ptr[clib.uv_stream_t], Int, Int]): Int =
    clib.uv_listen(stream, backlog, cb)

  def ip4Addr(ip: String, port: Int): Ptr[netinet.sockaddr_in] = {
    val addr = malloc(sizeof[netinet.sockaddr_in])
      .cast[Ptr[netinet.sockaddr_in]]
    clib.uv_ip4_addr(toCString(ip), port, addr)
    addr
  }

  def ip6Addr(ip: String, port: Int): Ptr[netinet.sockaddr_in6] = {
    val addr = malloc(sizeof[netinet.sockaddr_in6])
      .cast[Ptr[netinet.sockaddr_in6]]
    clib.uv_ip6_addr(toCString(ip), port, addr)
    addr
  }
}
