package com.mvc.kafka_producer_wikimedia;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.launchdarkly.eventsource.EventHandler;
import com.launchdarkly.eventsource.MessageEvent;

public class WikimediaChangeHandler implements EventHandler {
	
	KafkaProducer< String, String > kafkaProducer;
	String topic;
	
	private final Logger log = LoggerFactory.getLogger( WikimediaChangeHandler.class.getSimpleName( ) );
	
	public WikimediaChangeHandler( KafkaProducer< String, String > kafkaProducer, String topic ) {
		
		this.kafkaProducer = kafkaProducer;
		this.topic = topic;
	}

	@Override
	public void onOpen() {
		// TODO Auto-generated method stub
		// nothing here
	}

	@Override
	public void onClosed() {
		// TODO Auto-generated method stub
		kafkaProducer.close( );
	}

	@Override
	public void onMessage(String event, MessageEvent messageEvent) {
		// TODO Auto-generated method stub
		log.info( messageEvent.getData( ) );
		// Asynchronous 
		kafkaProducer.send( new ProducerRecord<> ( topic, messageEvent.getData( ) ) );
	}

	@Override
	public void onComment(String comment) {
		// TODO Auto-generated method stub
		// nothing here
	}

	@Override
	public void onError(Throwable t) {
		// TODO Auto-generated method stub
		log.error( "Error in stream reading ", t );
	}



}
