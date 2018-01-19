package engagetech.backend_challenge.core;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "expenses")
public class Expenses {
	@Id
	private String id;
	
	@Column(name = "reason", nullable = false)
	private String reason;
	
	@Column(name = "date", nullable = false)
	private Date date;
	
	@Column(name = "amount", nullable = false)
	double amount;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	public static String getSelectAllQuery() {	
		return "SELECT e FROM Expenses e";
	}
}
