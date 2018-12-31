package cz.zcu.pia.revoloot.manager;

import cz.zcu.pia.revoloot.dao.IBankerDAO;
import cz.zcu.pia.revoloot.entities.Banker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
@Service
public class BankerManager implements IBankerManager {

    @Autowired
    public IBankerDAO bankerDAO;


    public List<Banker> getPublicBankersList(){
        return bankerDAO.loadAllPublicBankers();
    }

}
