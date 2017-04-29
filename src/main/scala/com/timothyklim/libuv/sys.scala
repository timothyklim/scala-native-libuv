package com.timothyklim.libuv

import scalanative.native._, stdlib._, stdio._
import scalanative.runtime.struct

@link("sys")
@extern
object sys {
  type sockaddr = extern
}
