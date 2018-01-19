package engagetech.backend_challenge;

import java.util.EnumSet;

import javax.servlet.DispatcherType;
import javax.servlet.FilterRegistration.Dynamic;

import org.eclipse.jetty.servlets.CrossOriginFilter;

import engagetech.backend_challenge.api.Expense;
import engagetech.backend_challenge.core.Expenses;
import engagetech.backend_challenge.db.ExpensesDAO;
import engagetech.backend_challenge.mappers.DataMapperFactory;
import engagetech.backend_challenge.resources.ExpensesResource;
import io.dropwizard.Application;
import io.dropwizard.db.DataSourceFactory;
import io.dropwizard.hibernate.HibernateBundle;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;

public class backend_challengeApplication extends Application<backend_challengeConfiguration> {
	private final HibernateBundle<backend_challengeConfiguration> hibernateBundle = 
			new HibernateBundle<backend_challengeConfiguration>(Expenses.class) {
				@Override
				public DataSourceFactory getDataSourceFactory(backend_challengeConfiguration configuration) {
					return configuration.getDataSourceFactory();
				}
			};
	
    public static void main(final String[] args) throws Exception {
        new backend_challengeApplication().run(args);
    }

    @Override
    public String getName() {
        return "backend_challenge";
    }

    @Override
    public void initialize(final Bootstrap<backend_challengeConfiguration> bootstrap) {
        bootstrap.addBundle(hibernateBundle);
    }

    @Override
    public void run(final backend_challengeConfiguration configuration,
                    final Environment environment) {
    	// Initialize some configuration stuff
    	DataMapperFactory.configuration = configuration;
    	Expense.vatTax = configuration.getVatTaxValue();
    	
        environment.jersey().register(new ExpensesResource(new ExpensesDAO(hibernateBundle.getSessionFactory())));
        Dynamic filter = environment.servlets().addFilter("CORS", CrossOriginFilter.class);
        filter.setInitParameter(CrossOriginFilter.ALLOWED_METHODS_PARAM, "GET, POST");
        filter.setInitParameter(CrossOriginFilter.ALLOWED_ORIGINS_PARAM, "*");
        filter.addMappingForUrlPatterns(EnumSet.allOf(DispatcherType.class), true, "/expenses");
    }

}
