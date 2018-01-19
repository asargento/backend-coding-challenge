package engagetech.backend_challenge.api;

import java.util.UUID;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotEmpty;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;
import com.google.common.base.MoreObjects;

public class Expense {
	@JsonIgnore
	static public double vatTax;
	
	private String id;
	@NotEmpty @NotNull
	private String date;
	@NotEmpty @NotNull
	private String reason;
	@NotNull
	private double amount;
	
	public Expense() {
		this.id = UUID.randomUUID().toString();
	}
	
	public Expense(String id, String date, String reason, double amount) {
		this.id = id == null ? UUID.randomUUID().toString() : id;
		this.date = date;
		this.reason = reason;
		this.amount = amount;
	}
	
	public Expense(String date, String reason, double amount) {
		this.id = UUID.randomUUID().toString();
		this.date = date;
		this.reason = reason;
		this.amount = amount;
	}

	@JsonProperty
	public String getId() {
		return id;
	}

	@JsonProperty
	public String getDate() {
		return date;
	}

	@JsonProperty
	public String getReason() {
		return reason;
	}

	@JsonProperty
	public double getAmount() {
		return amount;
	}
	
	@JsonProperty(required=false, access=Access.READ_ONLY)
	public double getVat() {
		return amount - amount/(1 + vatTax);
	}
	
	@Override
	public String toString() {
		return MoreObjects.toStringHelper(this)
				.add("id", id)
				.add("date", date)
				.add("reason", reason)
				.add("amount", amount)
				.toString();
	}
}
