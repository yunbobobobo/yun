package com.example.demo;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.activemq.command.ActiveMQTextMessage;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.jms.*;

@SpringBootTest
class DemoApplicationTests {

    public static void main(String[] args) {

        ConnectionFactory connect = new ActiveMQConnectionFactory("tcp://localhost:61616");
        try {
            Connection connection = connect.createConnection();
            connection.start();
            //第一个值表示是否使用事务，如果选择true，第二个值相当于选择0
            Session session = connection.createSession(true, Session.SESSION_TRANSACTED);
            Queue testqueue = session.createQueue("TEST1");

            MessageProducer producer = session.createProducer(testqueue);
            TextMessage textMessage = new ActiveMQTextMessage();
            textMessage.setText("今天天气真好！");
            producer.setDeliveryMode(DeliveryMode.PERSISTENT);
            producer.send(textMessage);
            session.commit();
            connection.close();

        } catch (JMSException e) {
            e.printStackTrace();
        }

    }


    @Test
    void contextLoads() {


    }

}
