package com.streamai.snowflakka;

import org.junit.Test;

import java.util.ArrayList;

/**
 * Created with IntelliJ IDEA.
 * User: Nathan
 * Date: 9/06/13
 * Time: 1:36 AM
 * To change this template use File | Settings | File Templates.
 */
public class SnowflakeTest {

    private Snowflake snowflake = new Snowflake();

    private ArrayList<Long> list = new ArrayList();

    @Test
    public void testNewId() throws Exception {

        for(int i = 0; i < 1100; i++) {
//            long id = snowflake.newId();
//            String val = Long.toBinaryString(id);
//            for(int k= 0; k < Long.numberOfLeadingZeros(id); k++) {
//                val = '0' + val;
//            }
//            System.out.println(id + "=" + val + ":" + val.substring(0,42) +"--"+ val.substring(42,47)
//                    +"--"+ val.substring(47,52) +"--"+ val.substring(52));
            //System.out.println(snowflake.newId());
            //list.add(snowflake.newId());
            snowflake.newId();snowflake.newId();snowflake.newId();snowflake.newId();
        }
        System.out.println(list);
    }
}
