package dev.example.cdk

import typings.awsCdkCore.appMod.App
import typings.awsCdkCore.mod.Construct

object CDKApp {
  def main(args: Array[String]): Unit = {
    val app = new App()

    new WebServerStack(app.asInstanceOf[Construct], "WebServerStack")

    app.synth()
  }
}
