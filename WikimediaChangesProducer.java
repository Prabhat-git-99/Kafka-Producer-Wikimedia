package com.mvc.kafka_producer_wikimedia;

import java.util.Properties;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringSerializer;

public class WikimediaChangesProducer {
	
	public static void main( String[ ] args ) {
		System.out.println( "Hey this is project time!" );
		
		String bootstrapServers = "127.0.0.1:9092";
		
		// Create producer properties
		Properties properties = new Properties( );
		properties.setProperty( ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServers );
		properties.setProperty( ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName( ) );
		properties.setProperty( ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName( ) );
		
		// Create the producer
		KafkaProducer< String, String > producer = new KafkaProducer< String, String >( properties );
		
		String topic = "wikimedia.recentchange";
		
		
	}

}
