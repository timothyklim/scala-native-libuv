package com.timothyklim.libuv

import scalanative.native._, stdlib._, stdio._

object Libuv {
  def version(): Int = clib.uv_version().toInt

  def versionString(): String = fromCString(clib.uv_version_string())
}
