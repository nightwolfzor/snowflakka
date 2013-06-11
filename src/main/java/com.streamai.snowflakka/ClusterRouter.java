package com.streamai.snowflakka;

import akka.actor.UntypedActor;

/**
 * Created with IntelliJ IDEA.
 * User: Nathan
 * Date: 11/06/13
 * Time: 9:44 PM
 * To change this template use File | Settings | File Templates.
 */
public class ClusterRouter extends UntypedActor {
    @Override
    public void onReceive(Object message) throws Exception {
        System.out.println(message);
    }
}
