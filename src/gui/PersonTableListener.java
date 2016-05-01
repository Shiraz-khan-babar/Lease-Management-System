package gui;

public interface PersonTableListener {
 public void rowDeleted(int row);
 public void purchaseNewProduct(int row,String personName);
 public void selectCustomer(int row);
}
