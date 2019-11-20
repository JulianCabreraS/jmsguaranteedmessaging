package com.bharath.jms.guaranteedmessaging;

import org.apache.activemq.artemis.jms.client.ActiveMQConnectionFactory;

import javax.jms.*;
import javax.naming.InitialContext;
import javax.naming.NamingException;


public class MessageConsumer {
    public static void main(String[] args) throws NamingException, JMSException {
        InitialContext initialContext = new InitialContext();
        Queue requestQueue = (Queue) initialContext.lookup("queue/requestQueue");


        try (
                ActiveMQConnectionFactory connectionFactory = new ActiveMQConnectionFactory();
                JMSContext jmsContext = connectionFactory.createContext(JMSContext.SESSION_TRANSACTED))
        {
            //Producer
            JMSConsumer consumer = jmsContext.createConsumer(requestQueue);
            TextMessage message = (TextMessage) consumer.receive();
            System.out.println(message.getText());
            jmsContext.commit();
            //message.acknowledge();
        }
    }
}
