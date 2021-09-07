package com.schoolofnet.helpdesk.models;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;

import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
@Table(name = "interactions")
public class Interaction {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column
	@NotEmpty(message = "Can not be empty")
	private String text;
	
	private Date dateCreated;
	
	@ManyToOne
	@JoinColumn(name = "user_id")
	@JsonBackReference
	private User userInteraction;

	@ManyToOne
	@JoinColumn(name = "ticket_id")
	@JsonBackReference
	private Ticket ticket;
	
	public Interaction() {
		
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public Date getDateCreated() {
		return dateCreated;
	}

	public void setDateCreated(Date dateCreated) {
		this.dateCreated = dateCreated;
	}

	public User getUserInteraction() {
		return userInteraction;
	}

	public void setUserInteraction(User userInteraction) {
		this.userInteraction = userInteraction;
	}

	public Ticket getTicket() {
		return ticket;
	}

	public void setTicket(Ticket ticket) {
		this.ticket = ticket;
	}
	
}
