package dev.example.cdk

import typings.awsCdkAwsEc2.mod.{SubnetType, Vpc}
import typings.awsCdkAwsEc2.vpcMod.{SubnetConfiguration, VpcProps}
import typings.awsCdkAwsEcs.clusterMod.ClusterProps
import typings.awsCdkAwsEcs.mod.{Cluster, ContainerImage}
import typings.awsCdkAwsEcsPatterns.applicationLoadBalancedFargateServiceMod.ApplicationLoadBalancedFargateServiceProps
import typings.awsCdkAwsEcsPatterns.applicationLoadBalancedServiceBaseMod.ApplicationLoadBalancedTaskImageOptions
import typings.awsCdkAwsEcsPatterns.mod.ApplicationLoadBalancedFargateService
import typings.awsCdkCore.mod.{Construct, Stack}

import scala.scalajs.js

class WebServerStack(scope: Construct, id: String) extends Stack(scope, id) {
  private val self = this.asInstanceOf[Construct]

  val vpc = new Vpc(
    self,
    "Vpc",
    VpcProps()
      .setMaxAzs(2)
      .setSubnetConfiguration(
        js.Array(SubnetConfiguration("PublicSubnet", SubnetType.PUBLIC))
      )
  )

  val cluster = new Cluster(self, "Cluster", ClusterProps().setVpc(vpc))

  val fargate: ApplicationLoadBalancedFargateService = {
    val imageOpts = ApplicationLoadBalancedTaskImageOptions(
      image = ContainerImage.fromAsset("server/target/docker/stage/").asInstanceOf[ContainerImage]
    )

    new ApplicationLoadBalancedFargateService(
      self,
      "Fargate",
      ApplicationLoadBalancedFargateServiceProps()
        .setCluster(cluster)
        .setAssignPublicIp(true)
        .setCpu(256)
        .setMemoryLimitMiB(512)
        .setTaskImageOptions(imageOpts)
    )
  }

}
