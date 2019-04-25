package runner.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import runner.dao.TemplateDAO;
import runner.models.Templates;

public class TemplateService {

    private static final Logger LOGGER = LoggerFactory.getLogger(TemplateService.class);
    private TemplateDAO templateDAO = new TemplateDAO();

    public TemplateService() {
    }

    public void saveTemplate(Templates templates) {
        LOGGER.info("save");
        templateDAO.save(templates);
    }

    public void deleteTemplate(Templates templates) {
        LOGGER.info("delete");
        templateDAO.delete(templates);
    }

    public void updateTemplate(Templates templates) {
        LOGGER.info("upd");
        templateDAO.update(templates);
    }

    public void findTemplate(int id) {
        LOGGER.info("find");
        templateDAO.findTemplatesById(id);
    }

}
