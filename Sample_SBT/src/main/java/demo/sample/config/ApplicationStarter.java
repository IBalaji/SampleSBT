package demo.sample.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.web.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.cassandra.core.CassandraOperations;
import org.springframework.data.cassandra.core.CassandraTemplate;
import org.springframework.web.servlet.ViewResolver;
import org.thymeleaf.extras.springsecurity4.dialect.SpringSecurityDialect;
import org.thymeleaf.spring4.SpringTemplateEngine;
import org.thymeleaf.spring4.view.ThymeleafViewResolver;
import org.thymeleaf.templateresolver.ServletContextTemplateResolver;
import org.thymeleaf.templateresolver.TemplateResolver;

import com.datastax.driver.core.Cluster;

import demo.sample.util.PathSettings;



@SpringBootApplication
@ComponentScan(basePackages={"me.imploy.*"})
public class ApplicationStarter  extends SpringBootServletInitializer {

		@Autowired
		PathSettings pathSettings;

		/**
		 * Used when Deploying to an External Server.
		 */
		@Override
		protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		        return application.sources(ApplicationStarter.class);
		}
		 
		/**
		 * Used to Deploy on Embedded Tomcat as a Java Application
		 * @param args
		 */
		public static void main(String[] args) {
	        SpringApplication.run(ApplicationStarter.class, args);
	    }
    
		 /*Thymeleaf View Resolver  */
		 	@Bean
		    public TemplateResolver templateResolver(){
		        ServletContextTemplateResolver templateResolver = new ServletContextTemplateResolver();
		        templateResolver.setPrefix("/WEB-INF/templates/");
		        templateResolver.setSuffix(".html");
		        templateResolver.setTemplateMode("HTML5");
		        templateResolver.setCacheable(false);

		        return templateResolver;
		    }
		 
		 
		   @Bean
		   public SpringTemplateEngine templateEngine(){
		         SpringTemplateEngine templateEngine = new SpringTemplateEngine();
		         templateEngine.setTemplateResolver(templateResolver());
		         templateEngine.addDialect(new SpringSecurityDialect());
		         return templateEngine;
		   }

		     @Bean
		     public ViewResolver viewResolver(){
		         ThymeleafViewResolver viewResolver = new ThymeleafViewResolver() ;
		         viewResolver.setTemplateEngine(templateEngine());
		         viewResolver.setOrder(0);

		         return viewResolver;
		      }
		     /* Thymeleaf View Resolver */
		
		     
	     /**
	      * Establishes Singleton Database Connection using config.properties values.
	      * @return CassandraOperations
	      */
		@Bean
		public CassandraOperations establishDBConnection(){
			return new CassandraTemplate(Cluster.builder().addContactPoints(pathSettings.getCASSANDRA_CONTACT_POINTS()).build().connect(pathSettings.getCASSANDRA_KEYSPACE()));
		}
}
