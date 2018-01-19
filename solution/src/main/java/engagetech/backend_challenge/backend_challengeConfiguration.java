package engagetech.backend_challenge;

import java.time.format.DateTimeFormatter;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotEmpty;

import com.fasterxml.jackson.annotation.JsonProperty;

import io.dropwizard.Configuration;
import io.dropwizard.db.DataSourceFactory;

public class backend_challengeConfiguration extends Configuration {
	@NotEmpty
	private String dateFormat = "dd/MM/yyyy";
	
	@NotEmpty
	private String vatTax = "0";
	
	@Valid
	@NotNull
	private DataSourceFactory database = new DataSourceFactory();
	
	@JsonProperty
	public String getDateFormat() {
		return dateFormat;
	}
	
	@JsonProperty
	public void setDateFormat(String dateFormat) {
		// check the dateFormat validity. Throws an exception if not valid
		DateTimeFormatter.ofPattern(dateFormat); 
		this.dateFormat = dateFormat;
	}
	
	@JsonProperty
	public String getVatTax() {
		return vatTax;
	}
	
	@JsonProperty
	public void setVatTax(String vatTax) {
		this.vatTax = vatTax;
	}

	public double getVatTaxValue() {
		double result = 0; 
		try {
			result = Double.parseDouble(vatTax);
		} catch (Exception e) {
			// TODO
			// Report the configuration problem
			e.printStackTrace();
		}
		return result;
	}
	
	@JsonProperty("database")
	public DataSourceFactory getDataSourceFactory() {
		return database;
	}
	
	@JsonProperty("database")
	public void setDataSourceFactory(DataSourceFactory dataSourceFactory) {
		this.database = dataSourceFactory;
	}
}
