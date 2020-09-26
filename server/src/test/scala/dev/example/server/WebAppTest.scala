package dev.example.server

import org.scalatest.funsuite.AnyFunSuite

class WebAppTest extends AnyFunSuite {
  test("hello returns 'Hello World!'") {
    assert(WebApp.hello() === "Hello World!")
  }
}
