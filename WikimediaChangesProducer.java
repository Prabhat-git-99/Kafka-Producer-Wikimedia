package com.mvc.kafka_producer_wikimedia;

import java.net.URI;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringSerializer;

import com.launchdarkly.eventsource.EventHandler;
import com.launchdarkly.eventsource.EventSource;

public class WikimediaChangesProducer {
	
	public static void main( String[ ] args ) throws InterruptedException {
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
		
		EventHandler eventHandler = new WikimediaChangeHandler( producer, topic );
		String url = "https://stream.wikimedia.org/v2/stream/recentchange";
		EventSource.Builder builder = new EventSource.Builder(eventHandler, URI.create( url ) );
		EventSource eventSource = builder.build( );
		
		// Start the producer in another thread
		eventSource.start( );
		
		// we can produce for 10mins and block program until then
		TimeUnit.MINUTES.sleep( 10 );
		
	}

}
