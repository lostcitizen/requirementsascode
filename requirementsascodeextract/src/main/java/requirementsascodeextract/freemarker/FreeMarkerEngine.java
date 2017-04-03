package requirementsascodeextract.freemarker;

import java.io.File;
import java.io.Writer;
import java.util.HashMap;
import java.util.Map;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateExceptionHandler;

public class FreeMarkerEngine {
	private Map<String, Object> dataModel;
	private Configuration cfg;
	
	public FreeMarkerEngine() {
		this.dataModel = new HashMap<String, Object>();
		createConfiguration();
	}
	private void createConfiguration() {
		cfg = new Configuration(Configuration.VERSION_2_3_26);
	    cfg.setLogTemplateExceptions(false);
	    setDefaultEncoding("UTF-8");
	    setTemplateExceptionHandler(TemplateExceptionHandler.RETHROW_HANDLER);
	}
	
	public void put(String key, Object value){
		dataModel.put(key, value);
	}

	public void setDefaultEncoding(String encoding) {
		cfg.setDefaultEncoding(encoding);
	}
	
	public void setTemplateExceptionHandler(TemplateExceptionHandler handler) {
		cfg.setTemplateExceptionHandler(handler);
	}
	
	public void process(File templateFile, Writer outputWriter) throws Exception{
		cfg.setDirectoryForTemplateLoading(templateDirectory(templateFile));
	    Template template = cfg.getTemplate(templateFileName(templateFile));
	    template.process(dataModel, outputWriter);
	}
	private File templateDirectory(File templateFile) {
		return templateFile.getParentFile();
	}
	private String templateFileName(File templateFile) {
		return templateFile.getName();
	}
}
