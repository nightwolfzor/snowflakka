package com.streamai.snowflakka;

import akka.actor.UntypedActor;

/**
 * Created with IntelliJ IDEA.
 * User: Nathan
 * Date: 11/05/13
 * Time: 4:10 PM
 * To change this template use File | Settings | File Templates.
 */
public class SimpleClusterListener extends UntypedActor {
    @Override
    public void onReceive(Object message) throws Exception {
        System.out.println("Actor[" + this.getSelf() +"] @" + System.currentTimeMillis() +" MSG: " + message);
    }
}
