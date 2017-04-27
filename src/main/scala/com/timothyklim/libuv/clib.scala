package com.timothyklim.libuv

import scalanative.native._, stdlib._, stdio._
import scalanative.runtime.struct

@link("uv")
@extern
object clib {
  type uv_loop_s = extern
  type uv_loop_t = uv_loop_s

  def uv_default_loop(): Ptr[uv_loop_t] = extern

  def uv_uptime(uptime: Ptr[Double]): UInt = extern

  def uv_version(): UInt = extern
  def uv_version_string(): CString = extern
}
