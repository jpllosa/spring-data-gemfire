package joel.llosa;

import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.gemfire.config.annotation.ClientCacheApplication;
import org.springframework.data.gemfire.config.annotation.EnableEntityDefinedRegions;
import org.springframework.data.gemfire.repository.config.EnableGemfireRepositories;
import org.apache.geode.cache.client.ClientRegionShortcut;

@SpringBootApplication
@ClientCacheApplication(name="CrudGemFireExample", logLevel = "fatal") // the pivotal gemfire cache
@EnableEntityDefinedRegions(basePackageClasses = Blog.class, clientRegionShortcut = ClientRegionShortcut.LOCAL) // enable the creation of Pivotal GemFire/Apache Geode Regions based on the application persistent entities
@EnableGemfireRepositories
public class Main {
	
	public static void main(String args[]) {
		SpringApplication.run(Main.class, args);
	}

	@Bean
	ApplicationRunner run(BlogRepository blogRepo) {
		
		System.out.println("Spring Data GemFire example");
		
		Blog example = new Blog("http://example.com", "Example");
		Blog jcg = new Blog("http://javacodegeeks.com", "JCG");
		Blog dzone = new Blog("https://dzone.com", "Dzone");
		
		// create
		blogRepo.save(example);
		blogRepo.save(jcg);
		blogRepo.save(dzone);
		
		// read
		blogRepo.findAll().forEach(blog -> System.out.println(blog));
		
		// find by title
		System.out.println("Finding JCG...");
		Blog temp = blogRepo.findByTitle("JCG");
		System.out.println(temp);

		// update
		temp.setTitle("new JCG");
		blogRepo.save(temp);
		System.out.println("JCG updated...");
		blogRepo.findAll().forEach(blog -> System.out.println(blog));
		
		// delete
		System.out.println("Deleting Example");
		temp = blogRepo.findByTitle("Example");
		blogRepo.delete(temp);
		blogRepo.findAll().forEach(blog -> System.out.println(blog));
		
		return null;
	}
}

