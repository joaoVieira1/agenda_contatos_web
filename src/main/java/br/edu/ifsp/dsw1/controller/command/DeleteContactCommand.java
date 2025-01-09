package br.edu.ifsp.dsw1.controller.command;

import java.io.IOException;

import br.edu.ifsp.dsw1.model.dao.ContactDaoFactory;
import br.edu.ifsp.dsw1.model.dao.ContactDaoFactory.ContactDaoType;
import br.edu.ifsp.dsw1.model.dao.InterfaceContactDao;
import br.edu.ifsp.dsw1.model.dao.JsonContactDao;
import br.edu.ifsp.dsw1.model.dao.MonostateContactDao;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class DeleteContactCommand implements Command{

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String email = request.getParameter("email");
		
		/* Objeto dao é da interface, contudo recebe uma implementação concreta. */
		//InterfaceContactDao dao = new MonostateContactDao();
		//InterfaceContactDao dao = new JsonContactDao();
		InterfaceContactDao dao = new ContactDaoFactory().factory();
		
		var contact = dao.retrieve(email);
		
		dao.delete(contact);
		/*
		 * Não é suficiente devolver a view contacts.jsp, visto que essa view
		 * depende do processo de recuperar dados da lista, então, aqui a view
		 * retornada irá realizar uma chamada ao processo de gerar a lista de 
		 * contados pelo command ListContactsCommand.
		 */
		return "contact.do?action=list";
	}

}
