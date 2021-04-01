package beans;

public class Members {

	public int id;
	public String cardNumber;
	public String username;
	public String password;
	public String dateTime;
	public String email;
	public String surname;
	public String title;
	public String dateOfBirth;
	public String city;
	public String category;

	public Members() {
	}

	public Members(String cardNumber, String username, String password, String dateTime, String email, String surname,
			String title, String dateOfBirth, String city, String category) {
		this.cardNumber = cardNumber;
		this.username = username;
		this.password = password;
		this.dateTime = dateTime;
		this.email = email;
		this.surname = surname;
		this.title = title;
		this.dateOfBirth = dateOfBirth;
		this.city = city;
		this.category = category;
	}
	
	public Members(int id,String cardNumber, String username, String dateTime, String email, String surname,
			String title, String dateOfBirth, String city, String category) {
		this.id=id;
		this.cardNumber = cardNumber;
		this.username = username;
		this.dateTime = dateTime;
		this.email = email;
		this.surname = surname;
		this.title = title;
		this.dateOfBirth = dateOfBirth;
		this.city = city;
		this.category = category;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getCardNumber() {
		return cardNumber;
	}

	public void setCardNumber(String cardNumber) {
		this.cardNumber = cardNumber;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getDateTime() {
		return dateTime;
	}

	public void setDateTime(String dateTime) {
		this.dateTime = dateTime;
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

	public String getDateOfBirth() {
		return dateOfBirth;
	}

	public void setDateOfBirth(String dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

}
