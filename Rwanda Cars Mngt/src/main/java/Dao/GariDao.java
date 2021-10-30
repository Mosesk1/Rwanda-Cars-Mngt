package Dao;

import java.io.Serializable;
import java.util.List;

import Models.Gari;

public class GariDao {
	
private final Gari type;
	
	ManagingDb conn = new ManagingDb();

	public GariDao(Gari type) {
		super();
		this.type = type;
	}
		
		public void save(Gari obj){
	        conn.createSession().saveOrUpdate(obj);
	        conn.closeSession();
	    }
	    
	    public void delete(Gari obj){
	        conn.createSession().delete(obj);
	        conn.closeSession();
	    }
	    
	    public void update(Gari obj){
	        conn.createSession().update(obj);
	        conn.closeSession();
	    }
	    
	    public Gari  findById(Serializable id){
	    	Gari obj = (Gari) conn.createSession().get(Gari.class, id);
	        conn.closeSession();
	        return obj;
	    }
	    
	    public List<Gari>  findAll(){
	        List<Gari> obj = conn.createSession().createCriteria(Gari.class.getName()).list();
	        conn.closeSession();
	        return obj;
	    }

}
