package com.bharath.jms.guaranteedmessaging;

import org.apache.activemq.artemis.jms.client.ActiveMQConnectionFactory;

import javax.jms.*;
import javax.naming.InitialContext;
import javax.naming.NamingException;


public class MessageProducer {
    public static void main(String[] args) throws NamingException, JMSException {
        InitialContext initialContext = new InitialContext();
        Queue requestQueue = (Queue) initialContext.lookup("queue/requestQueue");
        try (
                ActiveMQConnectionFactory connectionFactory = new ActiveMQConnectionFactory();
                //SET AUTO ACKNOWLEDGE
                JMSContext jmsContext = connectionFactory.createContext(JMSContext.SESSION_TRANSACTED))
        {
            //Producer
            JMSProducer producer = jmsContext.createProducer();
            producer.send(requestQueue,"Message 1");
            producer.send(requestQueue,"Message 2");
            jmsContext.commit();

        }
    }
}
