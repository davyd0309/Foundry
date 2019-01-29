package pl.dawydiuk.Foundry;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class FoundryApplication
//		implements CommandLineRunner
{

//	private final ProductProducer productProducer;
//
//	@Autowired
//	public FoundryApplication(ProductProducer productProducer) {
//		this.productProducer = productProducer;
//	}

	public static void main(String[] args) {
		SpringApplication.run(FoundryApplication.class, args);
	}

//	@Override
//	public void run(String... strings) throws Exception {
//		Scanner reader = new Scanner(System.in);
//		if(reader.next().equals("send")){
//			productProducer.sendProduct();
//		}
//	}

}
