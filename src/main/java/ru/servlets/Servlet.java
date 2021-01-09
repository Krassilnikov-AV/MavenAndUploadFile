package ru.servlets;

import javax.servlet.annotation.*;
import javax.servlet.http.*;
import java.io.*;
import java.nio.file.*;

/**
 * Класс Servlet
 */
@WebServlet("/servlet")
@MultipartConfig
public class Servlet extends HttpServlet {

	private static final String SERVER_PATH = "D:\\REPOSITORIES-2";

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws javax.servlet.ServletException, IOException {
		Part part = request.getPart("file");
		download(part.getInputStream(), part.getSubmittedFileName());
		request.getRequestDispatcher("/index.html").forward(request, response);
	}

	private void download(InputStream fileStream, String name) {
		try (BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(
			Files.newOutputStream(Paths.get(SERVER_PATH + File.separator + name))
		)) {
			int read;
			byte[] readBuffer = new byte[1024];
			while ((read = fileStream.read(readBuffer)) != -1) {
				bufferedOutputStream.write(readBuffer, 0, read);
			}
			bufferedOutputStream.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

//	@Override
//	protected void doGet(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws javax.servlet.ServletException, IOException {

}
//}
