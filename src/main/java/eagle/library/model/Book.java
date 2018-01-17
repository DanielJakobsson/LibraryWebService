package eagle.library.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Book implements Serializable {

	/**
	 * Auto generated serialVersionUID.
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "PUBLICATION_ID", nullable = false)
	private Publication publication;

	/**
	 * Book has default value ON_SHELF.
	 */
	@Enumerated(EnumType.ORDINAL)
	private PhysicalPosition physicalPosition = PhysicalPosition.ON_SHELF;

	/**
	 * Default constructor needed for an {@link Entity}.
	 */
	public Book() {
	}

	public Book(Publication publication) {
		this.publication = publication;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Publication getPublication() {
		return publication;
	}

	public void setPublication(Publication publication) {
		this.publication = publication;
	}

	public PhysicalPosition getPhysicalPosition() {
		return physicalPosition;
	}

	public void setPhysicalPosition(PhysicalPosition physicalPosition) {
		this.physicalPosition = physicalPosition;
	}

}
