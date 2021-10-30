package Models;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="CarsTb")
public class Gari {
    @Id
    @Column(name = "plate")
	private String plate;
    @Column(name = "date")
	private LocalDate date;
    @Column(name = "cost")
	private Long cost ;
    @Column(name = "institute")
	private String institute;
	
	
	public Gari(String plate, String LocalDate, Long cost, String institute) {
		super();
		this.plate = plate;
		this.date = date;
		this.cost = cost;
		this.institute = institute;
	}


	public Gari() {
		
		super();
	}


	public String getPlate() {
		return plate;
	}


	public void setPlate(String plate) {
		this.plate = plate;
	}


	public LocalDate getDate() {
		return date;
	}


	public void setDate(LocalDate date) {
		this.date = date;
	}


	public Long getCost() {
		return cost;
	}


	public void setCost(Long cost) {
		this.cost = cost;
	}


	public String getInstitute() {
		return institute;
	}


	public void setInstitute(String institute) {
		this.institute = institute;
	}
}


