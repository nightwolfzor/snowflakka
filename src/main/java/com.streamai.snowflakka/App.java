package com.streamai.snowflakka;

import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.actor.Props;
import akka.cluster.routing.AdaptiveLoadBalancingRouter;
import akka.cluster.routing.ClusterRouterConfig;
import akka.cluster.routing.ClusterRouterSettings;
import akka.routing.Broadcast;
import akka.routing.BroadcastRouter;
import com.typesafe.config.Config;
import com.typesafe.config.ConfigFactory;
import scala.concurrent.duration.FiniteDuration;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args ) throws Exception {
        System.out.println( "Hello World!" );
        // Override the configuration of the port when specified as program argument
        final Config config =
                (args.length > 0 ?
                        ConfigFactory.parseString(String.format("akka.remote.netty.tcp.port=%s", args[0])) :
                        ConfigFactory.empty()).
                        withFallback(ConfigFactory.parseString("akka.cluster.roles = [compute]")).
                        withFallback(ConfigFactory.load());
        ActorSystem system = ActorSystem.create("ClusterSystem", config);

        Thread.sleep(2000);

        ArrayList<String> routees = new ArrayList<>();
        routees.add("/user/clusterListener");

        ActorRef clusterListener = system.actorOf(Props.create(SimpleClusterListener.class), "clusterListener");
        ActorRef clustRouter = system.actorOf(Props.create(ClusterRouterListener.class).withRouter(
                new ClusterRouterConfig(new BroadcastRouter(10), new ClusterRouterSettings(100, "/user/clusterListener", true, "compute"))), "slaveRouter");


        //system.scheduler().schedule(new FiniteDuration(2, TimeUnit.SECONDS), new FiniteDuration(5, TimeUnit.SECONDS), clustRouter, "test 123", system.dispatcher(), null);
        clustRouter.tell("test abc xyz", clustRouter);

        ActorRef clientRouter = system.actorOf(Props.create(ClusterRouter.class).withRouter(
                new ClusterRouterConfig(new AdaptiveLoadBalancingRouter(akka.cluster.routing.HeapMetricsSelector.getInstance(), 1),
                        new ClusterRouterSettings(10, 5, true, null))
        ));
        clientRouter.tell(new Broadcast("test client msg"), null);
        //system.scheduler().schedule(new FiniteDuration(2, TimeUnit.SECONDS), new FiniteDuration(5, TimeUnit.SECONDS), clientRouter, new Broadcast("test client msg"), system.dispatcher(), null);
        // Add subscription of cluster events
        //Cluster.get(system).subscribe(clustRouter, ClusterEvent.ClusterDomainEvent.class);
        //Cluster.get(system).subscribe(clusterListener, ClusterEvent.ClusterDomainEvent.class);
    }
}


