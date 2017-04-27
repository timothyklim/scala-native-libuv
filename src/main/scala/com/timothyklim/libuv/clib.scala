package com.timothyklim.libuv

import scalanative.native._, stdlib._, stdio._
import scalanative.runtime.struct

@link("uv")
@extern
object clib {

  /** Loop data type.
    *
    * @see [[http://docs.libuv.org/en/stable/loop.html#c.uv_loop_t]]
    */
  type uv_loop_t = extern

  type uv_run_mode = extern

  /** Initializes the given [[uv_loop_t]] structure.
    *
    * @see [[http://docs.libuv.org/en/v1.x/loop.html#c.uv_loop_init]]
    */
  def uv_loop_init(loop: Ptr[uv_loop_t]): CInt = extern

  def uv_loop_close(loop: Ptr[uv_loop_t]): CInt = extern

  /** Returns the initialized default loop. It may return NULL in case of allocation failure.
    * This function is just a convenient way for having a global loop throughout an application, the default loop is in no way different than the ones initialized with [[loopInit()]]. As such, the default loop can (and should) be closed with [[loopClose()]] so the resources associated with it are freed.
    *
    * @see [[http://docs.libuv.org/en/v1.x/loop.html#c.uv_default_loop]]
    */
  def uv_default_loop(): Ptr[uv_loop_t] = extern

  def uv_run(loop: Ptr[uv_loop_t], mode: uv_run_mode): CInt = extern

  /** Gets the current system uptime.
    *
    * @see [[http://docs.libuv.org/en/v1.x/misc.html#c.uv_uptime]]
    */
  def uv_uptime(uptime: Ptr[Double]): UInt = extern

  /** Returns [[http://docs.libuv.org/en/v1.x/version.html#c.UV_VERSION_HEX UV_VERSION_HEX]].
    *
    * @see [[http://docs.libuv.org/en/v1.x/version.html#c.uv_version]]
    */
  def uv_version(): UInt = extern

  /** Returns the libuv version number as a string. For non-release versions the version suffix is included.
    *
    * @see [[http://docs.libuv.org/en/v1.x/version.html#c.uv_version_string]]
    */
  def uv_version_string(): CString = extern
}
