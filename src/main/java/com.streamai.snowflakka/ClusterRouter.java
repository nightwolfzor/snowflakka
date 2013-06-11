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

    /**
     * User overridable callback.
     * <p/>
     * Is called when an Actor is started.
     * Actor are automatically started asynchronously when created.
     * Empty default implementation.
     */
            @Override
     public void preStart() throws Exception {
        System.out.println("starting new clust router instance.");
        super.preStart();    //To change body of overridden methods use File | Settings | File Templates.
    }
}
