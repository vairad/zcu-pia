package cz.zcu.pia.revoloot.dao;

import cz.zcu.pia.revoloot.entities.Template;

import java.util.List;

public interface ITemplateDAO extends IGenericDAO<Template> {

    List<Template> getAllTemplatesByUser(Long id);

    Template fidByName(String name);
}
