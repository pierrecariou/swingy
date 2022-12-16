package com.pcariou.DAO;

import java.util.List;
//import com.pcariou.model.*;

public interface DAO<T>
{
    void create(T elem); 
    List<T> readAll();
    void delete();
}
