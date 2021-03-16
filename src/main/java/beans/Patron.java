package beans;

import java.util.Date;

public class Patron {
	int borrowernumber;
	String cardnumber;
	String userid;
	String password;
	String updated_on;
	String email;
	String surname;
	String title;
	Date dateofbirth;
	String city;
	String category;
	int accesscount;

	public Patron() {
	}

	public Patron(int borrowernumber, String cardnumber, String userid, String password, String updated_on,
			String email, String surname, String title, Date dateofbirth, String city, String category) {
		super();
		this.borrowernumber = borrowernumber;
		this.cardnumber = cardnumber;
		this.userid = userid;
		this.password = password;
		this.updated_on = updated_on;
		this.email = email;
		this.surname = surname;
		this.title = title;
		this.dateofbirth = dateofbirth;
		this.city = city;
		this.category = category;
	}

	public int getBorrowernumber() {
		return borrowernumber;
	}

	public void setBorrowernumber(int borrowernumber) {
		this.borrowernumber = borrowernumber;
	}

	public String getCardnumber() {
		return cardnumber;
	}

	public void setCardnumber(String cardnumber) {
		this.cardnumber = cardnumber;
	}

	public String getUserid() {
		return userid;
	}

	public void setUserid(String userid) {
		this.userid = userid;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getUpdated_on() {
		return updated_on;
	}

	public void setUpdated_on(String updated_on) {
		this.updated_on = updated_on;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Date getDateofbirth() {
		return dateofbirth;
	}

	public void setDateofbirth(Date dateofbirth) {
		this.dateofbirth = dateofbirth;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public int getAccesscount() {
		return accesscount;
	}

	public void setAccesscount(int accesscount) {
		this.accesscount = accesscount;
	}
}
