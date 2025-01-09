package br.edu.ifsp.dsw1.model.dao;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import br.edu.ifsp.dsw1.model.entity.Contact;

public class MonostateContactDao implements InterfaceContactDao{

	private static List<Contact> datasource;
	
	public MonostateContactDao () {
		if(datasource == null) {
			datasource = new LinkedList<Contact>();
		}
	}
	
	public boolean create(Contact contact) {
		 if (contact != null) {
			 Contact inDatasource = datasource.stream()
					 .filter(c -> c.getEmail()
							 .equalsIgnoreCase(contact.getEmail())) 
					 		 .findFirst() 
					 		 .orElse(null);
		 	if (inDatasource == null) {
		 		datasource.add(new Contact(contact.getName(), contact.getFone(), contact.getEmail()));
		 		return true;
		 	}
		 }
		 return false;
	}

	public Contact retrieve(String email) {
		Contact contact = datasource.stream()
										.filter(c -> c.getEmail().equalsIgnoreCase(email)) 
										.findAny() 
										.orElse(null);
		 if (contact == null) {
		 	return null;
		 } else {
		 	return new Contact(contact.getName(), contact.getFone(), contact.getEmail());
		 }
	}

	public List<Contact> retrieve() {
		return new ArrayList(datasource);
	}

	public boolean update(Contact updatedContact, String oldEmail) {
		var inDatasource = datasource.stream()
										.filter(c -> c.getEmail()
										.equalsIgnoreCase(oldEmail))
										.findAny() 
										.orElse(null);

		 if (inDatasource != null) {
		 	inDatasource.setEmail(updatedContact.getEmail());
		 	inDatasource.setFone(updatedContact.getFone());
		 	inDatasource.setName(updatedContact.getName());
		 	return true;
		 }
		 
		 return false;
	}

	public boolean delete(Contact contact) {
		var inDatasouce = datasource.stream()
										.filter(c -> c.getEmail()
										.equalsIgnoreCase(contact.getEmail()))
										.findAny()
										.orElse(null);

		return datasource.remove(inDatasouce);
	}

	@Override
	public List<Contact> findByName(String name) {
		// TODO Auto-generated method stub
		return null;
	}

}
