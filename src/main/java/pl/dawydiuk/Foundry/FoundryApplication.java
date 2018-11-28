package pl.dawydiuk.Foundry;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import pl.dawydiuk.Foundry.kafka.Producer;

@SpringBootApplication
public class FoundryApplication implements CommandLineRunner {

	private final Producer producer;

	@Autowired
	public FoundryApplication(Producer producer) {
		this.producer = producer;
	}

	public static void main(String[] args) {
		SpringApplication.run(FoundryApplication.class, args);
	}

	@Override
	public void run(String... strings) throws Exception {
		producer.send("Message from FoundryApplication");
	}

}
