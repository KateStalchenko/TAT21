package app;

import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class ManagePersonServlet extends HttpServlet {
	
	// Идентификатор для сериализации/десериализации.
	private static final String DISPATCHER_MANAGE = "/ManagePerson.jsp";
	private static final String DISPATCHER_LIST = "/List.jsp";

	private static final long serialVersionUID = 1L;
	
	// Основной объект, хранящий данные телефонной книги.
	private Phonebook phonebook;
       
    public ManagePersonServlet()
    {
        // Вызов родительского конструктора.
    	super();
		
    	// Создание экземпляра телефонной книги.
        try
		{
			this.phonebook = Phonebook.getInstance();
		}
		catch (SQLException e)
		{
			e.printStackTrace();
		}        
        
    }

    // Валидация ФИО и генерация сообщения об ошибке в случае невалидных данных.
    private String validatePersonFMLName(Person person)
    {
		String error_message = "";
		
		if (!person.validateFMLNamePart(person.getName(), false))
		{
			error_message += "Имя должно быть строкой от 1 до 150 символов из букв, цифр, знаков подчёркивания и знаков минус.<br />";
		}
		
		if (!person.validateFMLNamePart(person.getSurname(), false))
		{
			error_message += "Фамилия должна быть строкой от 1 до 150 символов из букв, цифр, знаков подчёркивания и знаков минус.<br />";
		}
		
		if (!person.validateFMLNamePart(person.getMiddlename(), true))
		{
			error_message += "Отчество должно быть строкой от 0 до 150 символов из букв, цифр, знаков подчёркивания и знаков минус.<br />";
		}
		
		return error_message;
    }
    
    // Реакция на GET-запросы.
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		// Обязательно ДО обращения к любому параметру нужно переключиться в UTF-8,
		// иначе русский язык при передаче GET/POST-параметрами превращается в "кракозябры".
		request.setCharacterEncoding("UTF-8");
		
		// В JSP нам понадобится сама телефонная книга. Можно создать её экземпляр там,
		// но с архитектурной точки зрения логичнее создать его в сервлете и передать в JSP.
		request.setAttribute("phonebook", this.phonebook);
		
		// Хранилище параметров для передачи в JSP.
		HashMap<String,String> jspParameters = new HashMap<>();

		// Диспетчеры для передачи управления на разные JSP (разные представления (view)).
		RequestDispatcher dispatcherForManagerPerson  = request.getRequestDispatcher("/ManagePerson.jsp");
        RequestDispatcher dispatcherForList = request.getRequestDispatcher("/List.jsp");

		// Действие (action) и идентификатор записи (id) над которой выполняется это действие.
		String action = request.getParameter("action");
		String id = request.getParameter("id");
		
		// Если идентификатор и действие не указаны, мы находимся в состоянии
		// "просто показать список и больше ничего не делать".
        if ((action == null)&&(id == null))
        {
        	request.setAttribute("jspParameters", jspParameters);
            dispatcherForList .forward(request, response);
        }
        // Если же действие указано, то...
        else
        {
        	switch (action)
        	{
        		// Добавление записи.
        		case "addPerson":
        			// Создание новой пустой записи о пользователе.
        			Person emptyPerson = new Person();
        			
        			// Подготовка параметров для JSP.
        			jspParameters.put("currentAction", "addPerson");
        			jspParameters.put("nextAction", "addPersonGo");
        			jspParameters.put("nextActionLabel", "Добавить");
        			
        			// Установка параметров JSP.
					request.setAttribute("person", emptyPerson);
					request.setAttribute("jspParameters", jspParameters);
        			
        			// Передача запроса в JSP.
        			dispatcherForManagerPerson .forward(request, response);
        		break;
			
        		// Редактирование записи.
        		case "editPerson":
        			// Извлечение из телефонной книги информации о редактируемой записи.        			
					Person editablePerson = this.phonebook.getPerson(id);
        			
        			// Подготовка параметров для JSP.
					jspParameters.put("currentAction", "editPerson");
					jspParameters.put("nextAction", "editPersonGo");
					jspParameters.put("nextActionLabel", "Сохранить");
					request.setAttribute("person", editablePerson);
					request.setAttribute("jspParameters", jspParameters);
        			
        			// Передача запроса в JSP.
        			dispatcherForManagerPerson .forward(request, response);
        		break;
			
        		// Удаление записи.
        		case "deletePhone":
        			
        			// Если запись удалось удалить...
					if (phonebook.deletePerson(id)) {
						jspParameters.put("currentActionResult", "DELETION_SUCCESS");
						jspParameters.put("currentActionResultLabel", "Удаление выполнено успешно");
					} else {
						jspParameters.put("currentActionResult", "DELETION_FAILURE");
						jspParameters.put("currentActionResultLabel", "Ошибка удаления (возможно, запись не " +
								"найдена)");
					}
					request.setAttribute("jspParameters", jspParameters);
        			
        			// Передача запроса в JSP.
        			dispatcherForList.forward(request, response);
       			break;
       		}
        }
		
	}

	// Реакция на POST-запросы.
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		// Обязательно ДО обращения к любому параметру нужно переключиться в UTF-8,
		// иначе русский язык при передаче GET/POST-параметрами превращается в "кракозябры".
		request.setCharacterEncoding("UTF-8");

		// В JSP нам понадобится сама телефонная книга. Можно создать её экземпляр там,
		// но с архитектурной точки зрения логичнее создать его в сервлете и передать в JSP.
		request.setAttribute("phonebook", this.phonebook);
		
		// Хранилище параметров для передачи в JSP.
		HashMap<String,String> jspParameters = new HashMap<>();

		// Диспетчеры для передачи управления на разные JSP (разные представления (view)).
		RequestDispatcher dispatcher_for_manager = request.getRequestDispatcher("/ManagePerson.jsp");
		RequestDispatcher dispatcher_for_list = request.getRequestDispatcher("/List.jsp");
		
		
		// Действие (add_go, edit_go) и идентификатор записи (id) над которой выполняется это действие.
		String addPersonGo = request.getParameter("addPersonGo");
		String editPersonGo = request.getParameter("editPersonGo");
		String id = request.getParameter("id");
		
		// Добавление записи.
		if (addPersonGo != null)
		{
			// Создание записи на основе данных из формы.
			Person newPerson = new Person(request.getParameter("name"), request.getParameter("surname"), request.getParameter("middlename"));

			// Валидация ФИО.
			String errorMessage = this.validatePersonFMLName(newPerson);
			
			// Если данные верные, можно производить добавление.
			if (errorMessage.equals(""))
			{

				// Если запись удалось добавить...
				if (this.phonebook.addPerson(newPerson))
				{
					jspParameters.put("currentActionResult", "ADDITION_SUCCESS");
					jspParameters.put("currentActionResultLabel", "Добавление выполнено успешно");
				}
				// Если запись НЕ удалось добавить...
				else
				{
					jspParameters.put("currentActionResult", "ADDITION_FAILURE");
					jspParameters.put("currentActionResultLabel", "Ошибка добавления");
				}

				// Установка параметров JSP.
				request.setAttribute("jspParameters", jspParameters);
	        
				// Передача запроса в JSP.
				dispatcher_for_list.forward(request, response);
			}
			// Если в данных были ошибки, надо заново показать форму и сообщить об ошибках.
			else
			{
    			// Подготовка параметров для JSP.
    			jspParameters.put("currentAction", "addPerson");
    			jspParameters.put("nextAction", "addPersonGo");
    			jspParameters.put("nextActionLabel", "Добавить");
    			jspParameters.put("errorMessage", errorMessage);
    			
    			// Установка параметров JSP.
    			request.setAttribute("person", newPerson);
    			request.setAttribute("jspParameters", jspParameters);
    			
    			// Передача запроса в JSP.
    			dispatcher_for_manager.forward(request, response);
			}
		}
		
		// Редактирование записи.
		if (editPersonGo != null)
		{
			// Получение записи и её обновление на основе данных из формы.
			Person updatablePerson = this.phonebook.getPerson(request.getParameter("id"));
			updatablePerson.setName(request.getParameter("name"));
			updatablePerson.setSurname(request.getParameter("surname"));
			updatablePerson.setMiddlename(request.getParameter("middlename"));

			// Валидация ФИО.
			String errorMessage = this.validatePersonFMLName(updatablePerson);
			
			// Если данные верные, можно производить добавление.
			if (errorMessage.equals(""))
			{
			
				// Если запись удалось обновить...
				if (this.phonebook.updatePerson(updatablePerson))
				{
					jspParameters.put("currentActionResult", "UPDATE_SUCCESS");
					jspParameters.put("currentActionResultLabel", "Обновление выполнено успешно");
				}
				// Если запись НЕ удалось обновить...
				else
				{
					jspParameters.put("currentActionResult", "UPDATE_FAILURE");
					jspParameters.put("currentActionResultLabel", "Ошибка обновления");
				}

				// Установка параметров JSP.
				request.setAttribute("jspParameters", jspParameters);
	        
				// Передача запроса в JSP.
				dispatcher_for_list.forward(request, response);
			}
			// Если в данных были ошибки, надо заново показать форму и сообщить об ошибках.
			else
			{

    			// Подготовка параметров для JSP.
    			jspParameters.put("currentAction", "edit");
    			jspParameters.put("nextAction", "edit_go");
    			jspParameters.put("nextActionLabel", "Сохранить");
    			jspParameters.put("errorMessage", errorMessage);

    			// Установка параметров JSP.
    			request.setAttribute("person", updatablePerson);
    			request.setAttribute("jspParameters", jspParameters);
    			
    			// Передача запроса в JSP.
    			dispatcher_for_manager.forward(request, response);    			
    			
			}
		}
	}
}
