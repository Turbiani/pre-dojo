package br.com.leonardo.pre_dojo.controller;

import static spark.Spark.setPort;

import java.io.IOException;
import java.io.StringWriter;
import java.io.Writer;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang3.StringEscapeUtils;

import spark.Request;
import spark.Response;
import spark.Route;
import spark.Spark;
import br.com.leonardo.pre_dojo.factory.ExecutorFactory;
import br.com.leonardo.pre_dojo.interfaces.Executable;
import br.com.leonardo.pre_dojo.utils.JPAUtil;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;

/**
 * @author turbiani
 *
 */
public final class MainController {
	private final Configuration 	cfg;
	private final ExecutorFactory   executorFactory = ExecutorFactory.getInstance();
	private final JPAUtil    		JPAUtil;
	
	public MainController() throws IOException{
		this.cfg 	  			= createFreemarkerConfiguration();
		this.JPAUtil            = new JPAUtil();
		setPort(Integer.valueOf(System.getenv("PORT")));
        carregaRotas();
	}
	
	public static void main(String[] args) {
		try {
			new MainController();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private void carregaRotas() throws IOException{
		//HOME
		Spark.get(new TemplateBaseRoute("/", "index.ftl") {
			@Override
			protected void doHandle(Request request, Response response, Writer writer)
					throws IOException, TemplateException {
				
				Map<String, Object> document = new HashMap<String, Object>();
				template.process(document, writer);
			}
		});		
		Spark.get(new TemplateBaseRoute("/report", "/report/index.ftl") {
			@Override
			protected void doHandle(Request request, Response response, Writer writer)
					throws IOException, TemplateException {
				
				Map<String, Object> document = new HashMap<String, Object>();
                template.process(document, writer);
			}
		});
		Spark.post(new TemplateBaseRoute("/report", "/report/index.ftl") {
			@Override
			protected void doHandle(Request request, Response response, Writer writer)
					throws IOException, TemplateException {
				
				String command = StringEscapeUtils.escapeHtml4(request.queryParams("log"));
				
				Executable executable = executorFactory.createExecutor(command);
				executable.execute();
				
				Map<String, Object> document = new HashMap<String, Object>();
                template.process(document, writer);
			}
		});
	}
	
	
	
	abstract class TemplateBaseRoute extends Route{
		final Template template;
		/**
		 * @param path
		 * @param templateName
		 * @throws IOException 
		 */
		protected TemplateBaseRoute(final String path, final String templateName) throws IOException {
			super(path);
            template = cfg.getTemplate(templateName);			
		}
		
		@Override
        public Object handle(Request request, Response response) {
            StringWriter writer = new StringWriter();
            try {
                doHandle(request, response, writer);
            } catch (Exception e) {
                e.printStackTrace();
                response.redirect("/internal_error");
            }
            return writer;
        }

        protected abstract void doHandle(final Request request, final Response response, final Writer writer)
        throws IOException, TemplateException;
		
	}
	
	private Configuration createFreemarkerConfiguration() {
        Configuration configuration = new Configuration();
        configuration.setClassForTemplateLoading(MainController.class, "/freemarker");
        return configuration;
    }
}
