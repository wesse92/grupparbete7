package se.kau.chatterbox;

import java.util.Date;

public class Message {
	private int id;
	private String text;
	private Date timestamp;
	private User author;
	
	public Message(int id, String text, Date timestamp, User author) {
		setId(id);
		setText(text);
		setTimestamp(timestamp);
		setAuthor(author);
	}	
		public int getId() {
			return id;
		}
		public String getText() {
			return text;
		}
		public Date getTimestamp() {
			return timestamp;
		}
		public User getAuthor() {
			return author;
		}
		public void setId(int id) {
			if (id > 0)
				this.id = id;
			else
				System.out.println("Id must be more than zero");
		}
		public void setText(String text) {
			if(text.length() >= 1)
				this.text = text;
			else
				System.out.println("Text must have atleast one letter");
		}
		public void setTimestamp(Date timestamp) {
				this.timestamp = timestamp;
		}
		public void setAuthor(User author) {
			if(author != null)
				this.author = author;
			else
				System.out.println("Author cannot be null");
		}
	}

