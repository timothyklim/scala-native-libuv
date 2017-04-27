package com.timothyklim.libuv

import scalanative.native._, stdlib._, stdio._

object Libuv {

  /** Loop data type.
    *
    * @see [[http://docs.libuv.org/en/stable/loop.html#c.uv_loop_t]]
    */
  type LoopT = CStruct1[Ptr[Byte]]

  /** Returns the initialized default loop. It may return NULL in case of allocation failure.
    * This function is just a convenient way for having a global loop throughout an application, the default loop is in no way different than the ones initialized with [[loopInit()]]. As such, the default loop can (and should) be closed with [[loopClose()]] so the resources associated with it are freed.
    *
    * @see [[http://docs.libuv.org/en/v1.x/loop.html#c.uv_default_loop]]
    */
  def defaultLoop(): Ptr[LoopT] =
    clib.uv_default_loop().asInstanceOf[Ptr[LoopT]]

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
}
