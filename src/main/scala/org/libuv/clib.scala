package org.libuv

import scalanative.native._, stdlib._, stdio._
import scalanative.runtime.struct

@link("uv")
@extern
object clib {
  def uv_version(): UInt = extern
  def uv_version_string(): CString = extern
}
