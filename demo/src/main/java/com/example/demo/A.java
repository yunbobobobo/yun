package com.example.demo;

import com.sun.org.apache.xpath.internal.operations.String;
import javafx.scene.input.DataFormat;
import org.apache.activemq.ActiveMQConnection;
import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;
import javax.xml.crypto.Data;
import java.text.SimpleDateFormat;

public class A {

    public static void main(String[] args) {
        ConnectionFactory connect = new ActiveMQConnectionFactory(ActiveMQConnection.DEFAULT_USER, ActiveMQConnection.DEFAULT_PASSWORD, "tcp://localhost:61616");
        try {
            Connection connection = connect.createConnection();
            connection.start();
            //第一个值表示是否使用事务，如果选择true，第二个值相当于选择0
            Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
            Destination testqueue = session.createQueue("TEST1");

            MessageConsumer consumer = session.createConsumer(testqueue);
            consumer.setMessageListener(message -> {
                if (message instanceof TextMessage) {
                    try {
                        java.lang.String text = ((TextMessage) message).getText();
                        System.out.println(text + "aaaaa");

                        //session.rollback();
                    } catch (JMSException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
                }
            });
            // session.commit();

        } catch (Exception e) {
            e.printStackTrace();
            ;
        }
    }


}
