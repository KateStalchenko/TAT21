package app;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;

/**
 * @author Katsiaryna Stalchanka
 * @since 01-Dec-18
 */
public class ManagePhoneServlet extends HttpServlet {
    private static final String DISPATCHER_MANAGE = "/ManagePhone.jsp";

    private static final long serialVersionUID = 1L;
    private Phonebook phonebook;

    public ManagePhoneServlet() {
        super();
        try {
            this.phonebook = Phonebook.getInstance();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        request.setAttribute("phonebook", this.phonebook);
        HashMap<String, String> jspParameters = new HashMap<>();

        RequestDispatcher dispatcherForManagePhone = request.getRequestDispatcher(DISPATCHER_MANAGE);

        String action = request.getParameter("action");
        String id = request.getParameter("id");
        String ownerId = request.getParameter("ownerId");

        // If id and action weren't specified, just show person's info
        if ((action == null) && (ownerId == null)) {
            request.setAttribute("jspParameters", jspParameters);
            dispatcherForManagePhone.forward(request, response);
        } else {
            switch (action) {
                case "addPhone":
                    actionAddPhone(request, jspParameters);
                    dispatcherForManagePhone.forward(request, response);
                    break;
                case "editPhone":
                    actionEditPhone(request, jspParameters, id, ownerId);
                    dispatcherForManagePhone.forward(request, response);
                    break;
                case "deletePhone":
                    actionDeletePhone(request, jspParameters, id, ownerId);
                    response.sendRedirect(request.getContextPath() + "/?action=" +
                            "editPerson" + "&id=" + ownerId);
                    break;
            }
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        request.setAttribute("phonebook", this.phonebook);
        HashMap<String, String> jspParameters = new HashMap<>();

        RequestDispatcher dispatcherForManagePhone = request.getRequestDispatcher(DISPATCHER_MANAGE);
        String addPhoneGo = request.getParameter("addPhoneGo");
        String editPhoneGo = request.getParameter("editPhoneGo");

        if (addPhoneGo != null) {
            actionAddPhoneSubmit(request, response, jspParameters, dispatcherForManagePhone);
        }
        if (editPhoneGo != null) {
            actionEditPhoneSubmit(request, response, jspParameters, dispatcherForManagePhone);
        }
    }

    /**
     * Sets attributes for request to add new phone.
     *
     * @param request       request to set attributes
     * @param jspParameters page parameters that must be transferred with request
     */
    private void actionAddPhone(HttpServletRequest request,
                                HashMap<String, String> jspParameters) {
        Phone emptyPhone = new Phone();
        jspParameters.put("currentAction", "addPhone");
        jspParameters.put("nextAction", "addPhoneGo");
        jspParameters.put("nextActionLabel", "Сохранить");
        request.setAttribute("phone", emptyPhone);
        request.setAttribute("jspParameters", jspParameters);
    }

    /**
     * Sets attributes for request to edit phone.
     *
     * @param request       request to set attributes
     * @param jspParameters page parameters that must be transferred with request
     */
    private void actionEditPhone(HttpServletRequest request,
                                 HashMap<String, String> jspParameters,
                                 String id,
                                 String ownerId) {
        Phone editablePhone = this.phonebook.getPerson(ownerId).getPhones().get(id);
        jspParameters.put("currentAction", "editPhone");
        jspParameters.put("nextAction", "editPhoneGo");
        jspParameters.put("nextActionLabel", "Сохранить");
        request.setAttribute("phone", editablePhone);
        request.setAttribute("jspParameters", jspParameters);
    }

    /**
     * Sets attributes for request about success of phone deleting. Invokes phonebook method to delete
     * phone entry from database.
     *
     * @param request       request to set attributes
     * @param jspParameters page parameters that must be transferred with request
     */
    private void actionDeletePhone(HttpServletRequest request,
                                   HashMap<String, String> jspParameters,
                                   String id,
                                   String ownerId) {
        if (phonebook.deletePhone(id, ownerId)) {
            jspParameters.put("currentActionResult", "DELETION_SUCCESS");
            jspParameters.put("currentActionResultLabel", "Удаление выполнено успешно");
        } else {
            jspParameters.put("currentActionResult", "DELETION_FAILURE");
            jspParameters.put("currentActionResultLabel", "Ошибка удаления (возможно, запись не " +
                    "найдена)");
        }
        request.setAttribute("jspParameters", jspParameters);
    }

    /**
     * Sets attributes for request about success of phone adding. Invokes phonebook method to add
     * phone entry to database.
     *
     * @param request       request to set attributes
     * @param jspParameters page parameters that must be transferred with request
     */
    private void actionAddPhoneSubmit(HttpServletRequest request,
                                      HttpServletResponse response,
                                      HashMap<String, String> jspParameters,
                                      RequestDispatcher dispatcherForManagePhone)
            throws IOException, ServletException {
        Phone newPhone = new Phone(
                request.getParameter("ownerId"),
                request.getParameter("number")
        );

        String errorMessage = newPhone.validateNumber(newPhone.getPhoneNumber());

        if (errorMessage.equals("")) {
            if (this.phonebook.addPhone(newPhone)) {
                jspParameters.put("currentActionResult", "ADDITION_SUCCESS");
                jspParameters.put("currentActionResultLabel", "Добавление выполнено успешно");
            } else {
                jspParameters.put("currentActionResult", "ADDITION_FAILURE");
                jspParameters.put("currentActionResultLabel", "Ошибка добавления");
            }

            request.setAttribute("jspParameters", jspParameters);
            response.sendRedirect(request.getContextPath() + "/?action=" +
                    "editPerson" + "&id=" +
                    newPhone.getOwnerId());
        }

        // If error occurred, then show adding form again
        else {
            jspParameters.put("currentAction", "addPhone");
            jspParameters.put("nextAction", "addPhoneGo");
            jspParameters.put("nextActionLabel", "Сохранить");
            jspParameters.put("errorMessage", errorMessage);

            request.setAttribute("phone", new Phone());
            request.setAttribute("jspParameters", jspParameters);
            dispatcherForManagePhone.forward(request, response);
        }
    }

    /**
     * Sets attributes for request about success of phone editing. Invokes phonebook method to edit
     * phone entry in database.
     *
     * @param request       request to set attributes
     * @param jspParameters page parameters that must be transferred with request
     */
    private void actionEditPhoneSubmit(HttpServletRequest request,
                                       HttpServletResponse response,
                                       HashMap<String, String> jspParameters,
                                       RequestDispatcher dispatcherForManagePhone)
            throws IOException, ServletException {
        Phone updatablePhone = this.phonebook.getPhone(
                request.getParameter("ownerId"),
                request.getParameter("id")
        );
        updatablePhone.setPhoneNumber(request.getParameter("number"));
        String errorMessage = updatablePhone.validateNumber(updatablePhone.getPhoneNumber());

        if (errorMessage.equals("")) {
            if (this.phonebook.updatePhone(updatablePhone)) {
                jspParameters.put("currentActionResult", "UPDATE_SUCCESS");
                jspParameters.put("currentActionResultLabel", "Обновление выполнено успешно");
            } else {
                jspParameters.put("currentActionResult", "UPDATE_FAILURE");
                jspParameters.put("currentActionResultLabel", "Ошибка обновления");
            }

            request.setAttribute("jspParameters", jspParameters);
            response.sendRedirect(request.getContextPath() + "/?action=" +
                    "editPerson" + "&id=" +
                    updatablePhone.getOwnerId());
        } else {
            jspParameters.put("currentAction", "editPhone");
            jspParameters.put("nextAction", "editPhoneGo");
            jspParameters.put("nextActionLabel", "Сохранить");
            jspParameters.put("errorMessage", errorMessage);

            request.setAttribute("phone", updatablePhone);
            request.setAttribute("jspParameters", jspParameters);
            dispatcherForManagePhone.forward(request, response);
        }
    }
}
