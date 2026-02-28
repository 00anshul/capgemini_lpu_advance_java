package com.library.entity;

import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "library_branch")
public class LibraryBranch {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long branchId;
	@Column(nullable = false)
	private String name;
	@Column(columnDefinition = "TEXT")
	private String location;
	private String contactNumber;
	@OneToMany(mappedBy = "branch")
	private List<Book> books;
	 LibraryBranch() {
		
	}
	 @Override
	public String toString() {
		return "LibraryBranch [branchId=" + branchId + ", name=" + name + ", locatioon=" + location
				+ ", contactNumber=" + contactNumber + "]";
	}
	 public Long getBranchId() {
		 return branchId;
	 }
	 public void setBranchId(Long branchId) {
		 this.branchId = branchId;
	 }
	 public String getName() {
		 return name;
	 }
	 public void setName(String name) {
		 this.name = name;
	 }
	 public String getLocation() {
		 return location;
	 }
	 public void setLocation(String location) {
		 this.location = location;
	 }
	 public String getContactNumber() {
		 return contactNumber;
	 }
	 public void setContactNumber(String contactNumber) {
		 this.contactNumber = contactNumber;
	 }
	 public List<Book> getBooks() {
		 return books;
	 }
	 public void setBooks(List<Book> books) {
		 this.books = books;
	 }
}
