package com.timothyklim.libuv

import scalanative.native._, stdlib._, stdio._

object Libuv {

  /** Loop data type.
    *
    * @see [[http://docs.libuv.org/en/stable/loop.html#c.uv_loop_t]]
    */
  type LoopT = CStruct1[Ptr[Byte]]

  def defaultLoop(): Ptr[LoopT] =
    clib.uv_default_loop().asInstanceOf[Ptr[LoopT]]

  def uptime(): Double = {
    val ut = stackalloc[Double]
    clib.uv_uptime(ut)
    !ut
  }

  def version(): Int = clib.uv_version().toInt
  def versionString(): String = fromCString(clib.uv_version_string())
}
