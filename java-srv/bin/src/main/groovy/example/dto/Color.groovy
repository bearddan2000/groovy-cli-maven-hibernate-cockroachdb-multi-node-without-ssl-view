package example.dto;

import org.hibernate.Query;
import org.hibernate.Session;
import java.util.List;

import example.dto.interfaces.ILookup;
import example.entity.ColorLookupEntity;

public class Color implements ILookup {
  Session session = null;
  public Color(Session s){session = s;}

  @Override
  public void insert(int i, String color) throws Exception {
    if (color == null)
      throw new Exception("must provide color");

		session.beginTransaction();

    ColorLookupEntity dog = new ColorLookupEntity(i, color);

		session.save(dog);

		session.getTransaction().commit();
  }

  @Override
  public void selectAll(){

    String hql = "FROM ColorLookupEntity";
    Query query = session.createQuery(hql);
    List<ColorLookupEntity> lst = query.list();
    System.out.println(hql);
    for (ColorLookupEntity entity : lst)
      System.out.println(entity.toString());

  }
}
