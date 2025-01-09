package br.edu.ifsp.dsw1.controller.command;

import java.io.IOException;
import java.util.List;

import br.edu.ifsp.dsw1.model.dao.ContactDaoFactory;
import br.edu.ifsp.dsw1.model.dao.ContactDaoFactory.ContactDaoType;
import br.edu.ifsp.dsw1.model.dao.InterfaceContactDao;
import br.edu.ifsp.dsw1.model.dao.JsonContactDao;
import br.edu.ifsp.dsw1.model.dao.MonostateContactDao;
import br.edu.ifsp.dsw1.model.entity.Contact;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class ListContactsCommand implements Command{

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		/* Objeto dao é da interface, contudo recebe uma implementação concreta. */
		//InterfaceContactDao dao = new MonostateContactDao();
		//InterfaceContactDao dao = new JsonContactDao();
		InterfaceContactDao dao = new ContactDaoFactory().factory();
		
		List<Contact> contacts = dao.retrieve();
		request.setAttribute("contacts", contacts);
		
		return "contacts.jsp";
	}

}
