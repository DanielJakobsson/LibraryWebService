package eagle.library.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Member implements Serializable {

	/**
	 * Auto generated serialVersionUID.
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue
	@Column(name = "MEMBER_ID")
	private long memberId;

	@Column(name = "FIRST_NAME", nullable = false)
	private String firstName;

	@Column(name = "SURNAME", nullable = false)
	private String surname;

	@Column(name = "EMAIL", unique = true, nullable = false)
	private String email;

	/**
	 * Default constructor needed for an {@link Entity}.
	 */
	public Member() {
	}

	public Member(String firstName, String surname, String email) {
		this.firstName = firstName;
		this.surname = surname;
		this.email = email;
	}

	public long getMemberId() {
		return memberId;
	}

	public void setMemberId(long memberId) {
		this.memberId = memberId;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

}
