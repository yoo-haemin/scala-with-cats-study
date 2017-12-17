package com.yoohaemin

import cats.free.Free

object FreeTest {
  type KVStore[A] = Free[KVStoreA, A]
}
