package com.timothyklim.libuv

import scalanative.native._, stdlib._, stdio._
import scalanative.runtime.struct

@link("netinet")
@extern
object netinet {
  type sockaddr_in = extern
  type sockaddr_in6 = extern
}
