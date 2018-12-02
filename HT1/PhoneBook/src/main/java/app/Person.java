package app;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import util.DBWorker;

public class Person {
	
	// Данные записи о человеке.
	private String id;
	private String name;
	private String surname;
	private String middlename;
	private HashMap<String, Phone> phones = new HashMap<>();
	
	// Конструктор для создания записи о человеке на основе данных из БД. 
	public Person(String id, String name, String surname, String middlename)
	{
		this.id = id;
		this.name = name;
		this.surname = surname;
		this.middlename = middlename;
		
		// Извлечение телефонов человека из БД.
		ResultSet dbResult = DBWorker.getInstance().getDBData("SELECT * FROM `phone` WHERE `owner`=" + id);
		
		try
		{
			// Если у человека нет телефонов, ResultSet будет == null.
			if (dbResult != null)
			{
				while (dbResult.next())
				{
					String phoneId = dbResult.getString("id");
					this.phones.put(phoneId, new Phone(phoneId, id, dbResult.getString("number")));
				}
			}	
		}
		catch (SQLException e)
		{
			e.printStackTrace();
		}
	}
	
	// Конструктор для создания пустой записи о человеке.
	public Person()
	{
		this.id = "0";
		this.name = "";
		this.surname = "";
		this.middlename = "";
	}	

	// Конструктор для создания записи, предназначенной для добавления в БД. 
	public Person(String name, String surname, String middlename)
	{
		this.id = "0";
		this.name = name;
		this.surname = surname;
		this.middlename = middlename;
	}

	// Валидация частей ФИО. Для отчества можно передать второй параетр == true,
	// тогда допускается пустое значение.
	public boolean validateFMLNamePart(String fml_name_part, boolean empty_allowed)
	{
	    if (empty_allowed)
	    {
	    	Matcher matcher = Pattern.compile("[\\w-А-ЯЁ+А-яЁё]+,{0,150}").matcher(fml_name_part);
	    	return matcher.matches();
	    }
	    else
	    {
	    	Matcher matcher = Pattern.compile("[\\w-А-ЯЁ+А-яЁё]+,{0,150}").matcher(fml_name_part);
	    	return matcher.matches();
	    }
	    
	}
	
	// ++++++++++++++++++++++++++++++++++++++
	// Геттеры и сеттеры
	public String getId()
	{
		return this.id;
	}
	
	public String getName()
	{
		return this.name;
	}

	public String getSurname()
	{
		return this.surname;
	}
	
	public String getMiddlename()
	{
		if ((this.middlename != null)&&(!this.middlename.equals("null")))
		{
			return this.middlename;
		}
		else
		{
			return "";
		}
	}

	public HashMap<String,Phone> getPhones()
	{
		return this.phones;
	}
	
	public void setId(String id)
	{
		this.id = id;
	}
	
	public void setName(String name)
	{
		this.name = name;
	}

	public void setSurname(String surname)
	{
		this.surname = surname;
	}
	
	public void setMiddlename(String middlename)
	{
		this.middlename = middlename;
	}

	public void setPhones(HashMap<String,Phone> phones)
	{
		this.phones = phones;
	}
	// Геттеры и сеттеры
	// --------------------------------------
	public String getFML(){
		return this.getName()+this.getMiddlename()+this.getSurname();
	}
}
