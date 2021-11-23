package com.leoncoder.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.leoncoder.conexion.Conexion;
import com.leoncoder.model.Producto;

public class ProductoDAO {

	private Connection cnx;
	private PreparedStatement pst;
	private boolean estadoOp;
	
	public boolean guardar(Producto p) throws SQLException {
		String sql = null;
		estadoOp = false;
		cnx = getConnection();
		
		try {
			cnx.setAutoCommit(false);
			sql = "INSERT INTO productos (nombre, cantidad, precio, fecha_crear, fecha_actualizar) VALUES(?,?,?,?,?)";
			pst = cnx.prepareStatement(sql);
			
			pst.setString(1, p.getNombre());
			pst.setDouble(2, p.getCantidad());
			pst.setDouble(3, p.getPrecio());
			pst.setDate(4, p.getFechaCrear());
			pst.setDate(5, p.getFechaActualizar());
			
			estadoOp = pst.executeUpdate()>0;
			
			cnx.commit();
			pst.close();
			cnx.close(); // Devuelvo al pool de las conexiones
		} catch (SQLException e) {
			cnx.rollback();
			e.printStackTrace();
		}
		
		return estadoOp;
	}
	
	public boolean editar(Producto p) throws SQLException {
		String sql = null;
		estadoOp = false;
		cnx = getConnection();
		
		try {
			cnx.setAutoCommit(false);
			sql = "UPDATE productos SET nombre = ?, cantidad = ?, precio = ?, fecha_actualizar = ? WHERE id = ?";
			pst = cnx.prepareStatement(sql);
			
			pst.setString(1, p.getNombre());
			pst.setDouble(2, p.getCantidad());
			pst.setDouble(3, p.getPrecio());		
			pst.setDate(4, p.getFechaActualizar());
			pst.setInt(5, p.getId());
			
			estadoOp = pst.executeUpdate()>0;
			
			cnx.commit();
			pst.close();
			cnx.close(); // Devuelvo al pool de las conexiones
		} catch (SQLException e) {
			cnx.rollback();
			e.printStackTrace();
		}
		
		return estadoOp;
	}
	
	public boolean eliminar(int id) throws SQLException {
		String sql = null;
		estadoOp = false;
		cnx = getConnection();
		
		try {
			cnx.setAutoCommit(false);
			sql = "DELETE FROM productos WHERE id = ?";
			pst = cnx.prepareStatement(sql);
			
			pst.setInt(1, id);
			
			estadoOp = pst.executeUpdate()>0;
			
			cnx.commit();
			pst.close();
			cnx.close(); // Devuelvo al pool de las conexiones
		} catch (SQLException e) {
			cnx.rollback();
			e.printStackTrace();
		}
		
		return estadoOp;
	}
	
	public List<Producto> obtenerProductos() {
		ResultSet rs = null;
		List<Producto> listaProducto = new ArrayList<>();
		String sql = null;
		try {
			cnx = getConnection();
			sql = "SELECT * FROM productos";
			pst = cnx.prepareStatement(sql);
			rs =  pst.executeQuery();
			
			while (rs.next()) {
				Producto p = new Producto();
				p.setId(rs.getInt(1));
				p.setNombre(rs.getString(2));
				p.setCantidad(rs.getDouble(3));
				p.setPrecio(rs.getDouble(4));
				p.setFechaCrear(rs.getDate(5));
				p.setFechaActualizar(rs.getDate(6));
				
				listaProducto.add(p);
			}
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		return listaProducto;
	}
	
	public Producto obtenerPorId(int id) {
		ResultSet rs = null;
		Producto p = new Producto();
		String sql = null;
		try {
			cnx = getConnection();
			sql = "SELECT * FROM productos where id = ?";
			pst = cnx.prepareStatement(sql);
			pst.setInt(1, id);
			rs = pst.executeQuery();
			
			if (rs.next()) {				
				p.setId(rs.getInt(1));
				p.setNombre(rs.getString(2));
				p.setCantidad(rs.getDouble(3));
				p.setPrecio(rs.getDouble(4));
				p.setFechaCrear(rs.getDate(5));
				p.setFechaActualizar(rs.getDate(6));
				
			}
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		return p;
	}
	
	private Connection getConnection() throws SQLException {
		return Conexion.getConnection();
	}
}
