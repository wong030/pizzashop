package app.api.dto;

import app.model.User;

public class UserResponseData {

	
		private Integer userId;
		private String email;
		private String firstName;
		private String lastName;
		private String street;
		private String streetNr;
		private String zip;
		private String city;

		public Integer getUserId() {
			return userId;
		}

		public void setUserId(Integer userId) {
			this.userId = userId;
		}

		public String getEmail() {
			return email;
		}

		public void setEmail(String email) {
			this.email = email;
		}

		public String getFirstName() {
			return firstName;
		}

		public void setFirstName(String firstName) {
			this.firstName = firstName;
		}

		public String getLastName() {
			return lastName;
		}

		public void setLastName(String lastName) {
			this.lastName = lastName;
		}

		public String getStreet() {
			return street;
		}

		public void setStreet(String street) {
			this.street = street;
		}

		public String getStreetNr() {
			return streetNr;
		}

		public void setStreetNr(String streetNr) {
			this.streetNr = streetNr;
		}

		public String getZip() {
			return zip;
		}

		public void setZip(String zip) {
			this.zip = zip;
		}

		public String getCity() {
			return city;
		}

		public void setCity(String city) {
			this.city = city;
		}

		public static UserResponseData fromEntity(User entity) {
			UserResponseData user = new UserResponseData();
			user.setUserId( entity.getUserId() );
			user.setCity(entity.getCity());
			user.setEmail(entity.getEmail());
			user.setFirstName(entity.getFirstName());
			user.setLastName(entity.getLastName());
			user.setStreet(entity.getStreet());
			user.setStreetNr(entity.getStreetNr());
			user.setZip(entity.getZip());

			return user;
		}

}


