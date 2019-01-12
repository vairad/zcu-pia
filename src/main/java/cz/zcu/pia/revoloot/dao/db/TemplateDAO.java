package cz.zcu.pia.revoloot.dao.db;

import cz.zcu.pia.revoloot.dao.ITemplateDAO;
import cz.zcu.pia.revoloot.entities.Template;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;
import java.util.List;

@Repository
public class TemplateDAO extends GenericDAO<Template> implements ITemplateDAO {

    private Logger logger = LoggerFactory.getLogger(ProductDAO.class.getName());

    TemplateDAO() {
        super(Template.class);
    }

    public TemplateDAO(EntityManager em) {
        super(em, Template.class);
    }

    @Override
    public List<Template> getAllTemplatesByUser(Long id) {
        logger.info("Load all moves to process");
        TypedQuery<Template> q = em.createQuery("SELECT t FROM Template t WHERE owner.id = :userId", Template.class);
        q.setParameter("userId", id);
        try {
            List<Template> moves = q.getResultList();
            logger.info("Templates to process found");
            return moves;
        } catch (NoResultException e) {
            logger.debug("No templates found");
            //no result found
            return null;
        }
    }

    @Override
    public Template fidByName(String name){
        logger.info("Load all moves to process");
        TypedQuery<Template> q = em.createQuery("SELECT t FROM Template t WHERE name = :name", Template.class);
        q.setParameter("name", name);
        try {
            Template template = q.getSingleResult();
            logger.info("Templates to process found");
            return template;
        } catch (NoResultException e) {
            logger.debug("No templates found");
            //no result found
            return null;
        }
    }
}
