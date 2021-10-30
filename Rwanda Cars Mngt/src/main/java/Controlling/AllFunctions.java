package Controlling;

import java.io.IOException;
import java.io.PrintWriter;
import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.Period;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Dao.GariDao;
import Models.Gari;

/**
 * Servlet implementation class AllFunctions
 */
@WebServlet("/AllFunctions/*")
public class AllFunctions extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private Map<String, String> stuckerror;  
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AllFunctions() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out= response.getWriter();
		
		try {
			String storedInfo = request.getPathInfo();
			
			
			Gari gari = new Gari();
			
			GariDao dao = new GariDao(gari);
			
			if ("/delete".equals(storedInfo)) {
				String id = request.getParameter("id");
				
				gari = dao.findById(id);
				
				dao.delete(gari);
				
				response.sendRedirect("../list.jsp");
				
			}
		} catch (Exception e) {
			e.printStackTrace();
			response.sendRedirect("Gariforms.jsp");
		}
	
	
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
		
		
		String storedInfo = request.getPathInfo();
		Gari gari = retrieveDataForm(request);
		Gari g = new Gari();
		GariDao dao = new GariDao(g);
		
		try {
			if (checkAvailableGari(gari)) {
				if(storedInfo == null) {
					if (dao.findById(gari.getPlate()) !=null) {
						throw new IllegalArgumentException("Please there is a car with the Same Plate("+gari.getPlate() +")");
					}
					dao.save(gari);
					response.sendRedirect("list.jsp");
			
			} else if ("/update".equals(storedInfo)) {
				
				if (dao.findById(gari.getPlate()) == null) {
					throw new IllegalArgumentException(" CAR with ("+gari.getPlate()+") does not exist");

				}
				dao.update(gari);
				
				response.sendRedirect("../list.jsp");
			}
			
			}	
		} catch (Exception e) {
			request.getSession().setAttribute("edit",  gari);
			request.getSession().setAttribute("errors", stuckerror);
		    response.sendRedirect("Gariforms.jsp");
		}
		
		
	}


private Gari retrieveDataForm(HttpServletRequest request) {
	Gari gari= new Gari();

String cost = request.getParameter("cost");
String date = request.getParameter("date");

gari.setInstitute(request.getParameter("institution"));
gari.setPlate(request.getParameter("plate"));

try {
	gari.setCost(Long.parseLong(cost));
} catch (NumberFormatException e) {
	e.printStackTrace();
}
try {
	gari.setDate(LocalDate.parse(date));
} catch (DateTimeException e) {
	e.printStackTrace();
}
return gari;
}
private boolean checkAvailableGari (Gari gari) {
	stuckerror = new HashMap<>();
	
	if(!gari.getPlate().matches("^G[PR][0-9]{3}[A-Z]$")) {
		stuckerror.put("plate", "plate number should start with G followed by R or P and 3 digits and a character from A-Z");
	}
	
	if(!(gari.getCost() >= 15000000 && gari.getCost() <= 60000000)) {
		stuckerror.put("cost", "cost must be between 15000000 and 60000000");
	}
	if(gari.getDate() != null) {
		if(gari.getDate().isEqual(LocalDate.now()) || gari.getDate().isAfter(LocalDate.now())) {
			stuckerror.put("date", "Please insert past Date from today ");
		} else if (Period.between(gari.getDate(), LocalDate.now()).getYears() >5) {
			stuckerror.put("date",  "Must not be older than 5 years");
			
		}
		
	}
	if (stuckerror.isEmpty()) {
		return true;
	}
	throw new IllegalArgumentException("Please provide correct information");
	
}

}


