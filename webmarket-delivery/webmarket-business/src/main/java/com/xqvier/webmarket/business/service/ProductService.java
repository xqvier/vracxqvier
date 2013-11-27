package com.xqvier.webmarket.business.service;

import java.util.List;

import javax.ejb.Stateless;

import com.xqvier.webmarket.business.core.DAOFactory;
import com.xqvier.webmarket.common.entity.Product;
import com.xqvier.webmarket.common.service.ProductServiceLocal;

/**
 * Session Bean implementation class ProductService
 */
@Stateless
public class ProductService implements ProductServiceLocal{

    /**
     * Default constructor. 
     */
    public ProductService() {
    }

    /* (non-Javadoc)
     * @see com.xqvier.webmarket.common.service.ProductServiceLocal#findAll()
     */
    @Override
    public List<Product> findAll() {
        return DAOFactory.getProductDAO().findAll();
    }

    /* (non-Javadoc)
     * @see com.xqvier.webmarket.common.service.ProductServiceLocal#find(long)
     */
    @Override
    public Product find(long pId) {
        return DAOFactory.getProductDAO().find(pId);
    }

}
