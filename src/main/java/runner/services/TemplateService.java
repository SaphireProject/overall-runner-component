package runner.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import runner.dao.TemplateDAO;
import runner.models.Templates;

@Service
public class TemplateService {

    private static final Logger LOGGER = LoggerFactory.getLogger(TemplateService.class);
    private TemplateDAO templateDAO = new TemplateDAO();

    public TemplateService() {
    }

    public void saveTemplate(Templates templates) {
        templateDAO.save(templates);
    }

    public void deleteTemplate(Templates templates) {
        templateDAO.delete(templates);
    }

    public void updateTemplate(Templates templates){
        templateDAO.update(templates);
    }

    public void findTemplate(int id) {
        templateDAO.findTemplatesById(id);
    }

}
