package dev.example.server

object WebApp extends cask.MainRoutes {
  @cask.get("/")
  def hello(): String = {
    "Hello World!"
  }

  override val host: String = "0.0.0.0"

  initialize()
}
