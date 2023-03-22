package com.hackernewsapi.demo.model;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "story")
public class Story {

	@Id
	Long id;
	@Column(name = "Title")
	String title;
	@Column(name = "URL")
	String url;
	@Column(name = "Score")
	Integer score;
	@Column(name = "User_Name")
	String by;
	@Column(name = "Date")
	Date time;
	@Transient
	Long[] kids;

}
