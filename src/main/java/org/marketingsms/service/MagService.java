package org.marketingsms.service;

import java.util.List;

import org.marketingsms.model.Magasin;
import org.marketingsms.model.Shop;
import org.marketingsms.model.User;

public interface MagService {
	 public List<Magasin> findByusermag(User iduser);
	 public Magasin Save(Magasin magasin);
	 public Magasin findBycodemagasin(int id);
	 public Magasin delete(int id);
}
