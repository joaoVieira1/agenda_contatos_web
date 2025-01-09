package br.edu.ifsp.dsw1.controller;

import java.io.IOException;
import java.util.List;

import br.edu.ifsp.dsw1.controller.command.Command;
import br.edu.ifsp.dsw1.controller.command.DeleteContactCommand;
import br.edu.ifsp.dsw1.controller.command.ErrorCommand;
import br.edu.ifsp.dsw1.controller.command.FormCommandContact;
import br.edu.ifsp.dsw1.controller.command.ListContactsCommand;
import br.edu.ifsp.dsw1.controller.command.SaveContactCommand;
import br.edu.ifsp.dsw1.controller.command.SearchContactCommand;
import br.edu.ifsp.dsw1.model.entity.Contact;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/contact.do")
public class ContactServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		processRequest(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		processRequest(request, response);
	}
	
	protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		
		String action = request.getParameter("action");
		Command command;
		
		if(action.equals("list")) {
			command = new ListContactsCommand();
		}else if(action.equals("newContact")) {
			command = new SaveContactCommand();
		}else if(action.equals("getForm")) {
			command = new FormCommandContact();
		}else if(action.equals("deleteContact")){
			command = new DeleteContactCommand();
		}else if(action.equals("searchContact")) {
			command = new SearchContactCommand();
		}else {
			command = new ErrorCommand();
		}
		
		String view = command.execute(request, response);
		RequestDispatcher dispatcher = request.getRequestDispatcher(view);
		dispatcher.forward(request, response);
		
	}
	
	
}
