package zapmyparcel.control;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

public class AuthInterceptor extends HandlerInterceptorAdapter {

	@Override
	/* Interceptor  for handling admin login request for management user report screen */
	public boolean preHandle(HttpServletRequest req, HttpServletResponse res,
			Object handler) throws Exception {

		String className = handler.getClass().getSimpleName();
		String methodName = req.getRequestURI();
		if (className.equalsIgnoreCase("AdminController") && !methodName.contains("/adminlogin.html")) {
			HttpSession session = req.getSession();
			if (session.getAttribute("userdetail") == null) {
				System.out.println("Session Expiry");
				try {
					res.sendRedirect(req.getContextPath()+"/adminlogin.html");
				} catch (Exception e) {
					e.printStackTrace();
				}
				return false;
			}
		}
		return true;
	}
}