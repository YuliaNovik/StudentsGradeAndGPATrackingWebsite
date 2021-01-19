package ca.sheridancollege.beans;

import java.io.Serializable;

import lombok.*;
@NoArgsConstructor
@AllArgsConstructor
@Data

public class Student implements Serializable {

	private static final long serialVersionUID = -5725123396358520547L;
	
	private int id;
	private String name;
	private String stId;
	private double exs;
	private double assign1;
	private double assign2;
	private double assign3;
	private double midterm;
	private double finalEx;
	private double wA;
	private String grade;
	
}
