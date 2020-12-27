import org.apache.log4j.Logger;

public class AppMain {

	public static void main(String[] args) {

		Logger logger = Logger.getLogger(AppMain.class);
		
		logger.info("Hello World!");
		logger.info(new Calculator().add(10, 5));

	}

}
