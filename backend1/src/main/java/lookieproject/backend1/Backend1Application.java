package lookieproject.backend1;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Backend1Application {

	public static void main(String[] args) {
		Hello hello = new Hello();
		hello.setData("hello");
		hello.getData();
		String data = hello.getData();
		System.out.println("data ="+ data);

		SpringApplication.run(Backend1Application.class, args);
	}

}
