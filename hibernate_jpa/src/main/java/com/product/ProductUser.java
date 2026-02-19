package com.product;

public class ProductUser {

    public static void main(String[] args) {

        ProductDao dao = new ProductDao();

        Product p = new Product();
        p.setId(3);
        p.setName("Pencil");
        p.setPrice(10);
        p.setQuantity(5);

        dao.addData(p);

        Product result = dao.findById(1);
        System.out.println(result.getName());
        dao.removeData(1);
        dao.removeData(2);
    }
}
