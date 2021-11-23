package com.leoncoder.controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.leoncoder.dao.ProductoDAO;
import com.leoncoder.model.Producto;

/**
 * Servlet implementation class ProductoController
 */
@WebServlet(description = "administra peticiones para la tabla productos", urlPatterns = { "/Productos" })
public class ProductoController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ProductoController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		String op = request.getParameter("op");
		
		if(op.equals("crear")) {
			System.out.println("Opcion crear");
			RequestDispatcher rd;
			rd = request.getRequestDispatcher("/views/crear.jsp");
			rd.forward(request, response);
		}else if(op.equals("listar")){
			ProductoDAO pDao = new ProductoDAO();
			List<Producto> list = new ArrayList<Producto>();
			list = pDao.obtenerProductos();
			
			for (Producto producto : list) {
				System.out.println(producto);
			}
			
			request.setAttribute("lista", list);
			
			RequestDispatcher rd;
			rd = request.getRequestDispatcher("/views/listar.jsp");
			rd.forward(request, response);
			System.out.println("Opcion listar");
		}else if (op.equals("editar")) {
			System.out.println("Editar");
			int id = Integer.parseInt(request.getParameter("id"));
			System.out.println("El id recibido es: " + id);
			
			ProductoDAO pDao = new ProductoDAO();
			Producto producto = new Producto();
			producto = pDao.obtenerPorId(id);
			System.out.println(producto);
			
			request.setAttribute("producto", producto);
			RequestDispatcher rDispatcher;
			rDispatcher = request.getRequestDispatcher("/views/editar.jsp");
			rDispatcher.forward(request, response);
		}else if (op.equals("eliminar")) {
			System.out.println("Eliminar");
			int id = Integer.parseInt(request.getParameter("id"));
			System.out.println("El id recibido es: " + id);
			
			ProductoDAO pDao = new ProductoDAO();
			try {
				pDao.eliminar(id);
				System.out.println("Producto eliminado");
				RequestDispatcher rDispatcher;
				rDispatcher = request.getRequestDispatcher("index.jsp");
				rDispatcher.forward(request, response);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			
		}
		
		//response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		String opcion = request.getParameter("op");
		Date fechaActual = new Date();
		
		switch (opcion) {
		case "guardar":
			{ProductoDAO pDao = new ProductoDAO();
			Producto producto = new Producto();
			
			producto.setNombre(request.getParameter("nombre"));
			producto.setCantidad(Double.parseDouble(request.getParameter("cantidad")));
			producto.setPrecio(Double.parseDouble(request.getParameter("precio")));
			producto.setFechaCrear(new java.sql.Date(fechaActual.getTime()));
			
			try {
				pDao.guardar(producto);
				System.out.println("Registro guardado satisfactorio");
				RequestDispatcher rd;
				rd = request.getRequestDispatcher("index.jsp");
				rd.forward(request, response);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}}
			break;
		case "editar":
		{	ProductoDAO pDao = new ProductoDAO();
			Producto producto = new Producto();
			
			producto.setId(Integer.parseInt(request.getParameter("id")));
			producto.setNombre(request.getParameter("nombre"));
			producto.setCantidad(Double.parseDouble(request.getParameter("cantidad")));
			producto.setPrecio(Double.parseDouble(request.getParameter("precio")));
			producto.setFechaActualizar(new java.sql.Date(fechaActual.getTime()));
			
			try {
				pDao.editar(producto);
				RequestDispatcher rd;
				rd = request.getRequestDispatcher("index.jsp");
				rd.forward(request, response);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}					
			break;
		case "eliminar":
		{	ProductoDAO pDao = new ProductoDAO();
			Producto producto = new Producto();
			
			producto.setId(Integer.parseInt(request.getParameter("id")));
			producto.setNombre(request.getParameter("nombre"));
			producto.setCantidad(Double.parseDouble(request.getParameter("cantidad")));
			producto.setPrecio(Double.parseDouble(request.getParameter("precio")));
			producto.setFechaActualizar(new java.sql.Date(fechaActual.getTime()));
			
			try {
				pDao.editar(producto);
				RequestDispatcher rd;
				rd = request.getRequestDispatcher("index.jsp");
				rd.forward(request, response);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}					
			break;
		default:
			break;
		}
		
		
		
		//doGet(request, response);
	}

}
