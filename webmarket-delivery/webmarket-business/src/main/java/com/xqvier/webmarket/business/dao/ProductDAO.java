/*
 * ProductDAO.java                                    18 nov. 2013 
 * 3iL3 DOO-GL 2013-2014
 */
package com.xqvier.webmarket.business.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;
import java.util.List;

import com.xqvier.webmarket.business.core.AbstractDAO;
import com.xqvier.webmarket.common.entity.Product;

/**
 * Classe d'abstraction d'acces aux données pour la table "product"
 * 
 * @author Xavier Mourgues
 * 
 */
public class ProductDAO extends AbstractDAO<Product> {

    /**
     * Default Constructor
     */
    public ProductDAO() {
    }

    /**
     * Retourne la liste de tous les produits. 
     * @return La liste des produits.
     */
    public List<Product> findAll() {

        Connection connection = null;
        Statement statement = null;
        ResultSet rs = null;
        try {
            connection = ds.getConnection();
            statement = connection.createStatement();
            String query = "SELECT * FROM product";
            rs = statement.executeQuery(query);
            List<Product> products = new LinkedList<Product>();
            while (rs.next()) {
                products.add(new Product(rs.getInt(1), rs.getString(2), rs
                        .getDouble(3)));
            }
            return products;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            try {
                if (connection != null) {
                    connection.close();
                }
                if (statement != null) {
                    statement.close();
                }
                if (rs != null) {
                    rs.close();
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * com.xqvier.webmarket.business.core.AbstractDAO#create(java.lang.Object)
     */
    @Override
    public Product create(Product obj) {
        Connection connection = null;
        PreparedStatement statement = null;

        try {
            connection = ds.getConnection();
            statement = connection
                    .prepareStatement("INSERT INTO product (name, price) VALUES (?, ?)");
            statement.setString(1, obj.getName());
            statement.setDouble(2, obj.getPrice());

            statement.executeUpdate();
            return obj;
        } catch (SQLException e) {
            throw new RuntimeException(e);

        } finally {
            try {
                if (connection != null) {
                    connection.close();
                }
                if (statement != null) {
                    statement.close();
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * com.xqvier.webmarket.business.core.AbstractDAO#update(java.lang.Object)
     */
    @Override
    public Product update(Product pProduct) {
        Connection connection = null;
        PreparedStatement statement = null;

        try {
            connection = ds.getConnection();
            statement = connection
                    .prepareStatement("UPDATE product SET name = ?, price = ? WHERE id = ?");
            statement.setString(1, pProduct.getName());
            statement.setDouble(2, pProduct.getPrice());
            statement.setInt(3, pProduct.getId());

            statement.executeUpdate();
            return pProduct;
        } catch (SQLException e) {
            throw new RuntimeException(e);

        } finally {
            try {
                if (connection != null) {
                    connection.close();
                }
                if (statement != null) {
                    statement.close();
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }

    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * com.xqvier.webmarket.business.core.AbstractDAO#delete(java.lang.Object)
     */
    @Override
    public void delete(Product pProduct) {
        Connection connection = null;
        PreparedStatement statement = null;

        try {
            connection = ds.getConnection();
            statement = connection
                    .prepareStatement("DELETE FROM product WHERE id = ?");
            statement.setInt(1, pProduct.getId());

            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);

        } finally {
            try {
                if (connection != null) {
                    connection.close();
                }
                if (statement != null) {
                    statement.close();
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.xqvier.webmarket.business.core.AbstractDAO#find(long)
     */
    @Override
    public Product find(long id) {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet rs = null;
        try {
            connection = ds.getConnection();
            statement = connection
                    .prepareStatement("SELECT * FROM product WHERE id = ?");
            statement.setLong(1, id);
            rs = statement.executeQuery();
            rs.next();
            Product product = new Product(rs.getInt(1), rs.getString(2),
                    rs.getDouble(3));

            return product;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            try {
                if (connection != null) {
                    connection.close();
                }
                if (statement != null) {
                    statement.close();
                }
                if (rs != null) {
                    rs.close();
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
