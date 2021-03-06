package br.com.leonardo.pre_dojo.controller;

import static spark.Spark.setPort;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.StringReader;
import java.io.StringWriter;
import java.io.Writer;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringEscapeUtils;

import spark.Request;
import spark.Response;
import spark.Route;
import spark.Spark;
import br.com.leonardo.pre_dojo.dao.RankingDAO;
import br.com.leonardo.pre_dojo.entidade.Ranking;
import br.com.leonardo.pre_dojo.exception.PreDojoDomainException;
import br.com.leonardo.pre_dojo.factory.DaoFactory;
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
	@SuppressWarnings("unused")
	private final JPAUtil    		JPAUtil;
	
	public MainController() throws IOException{
		this.cfg 	  			= createFreemarkerConfiguration();
		this.JPAUtil            = new JPAUtil();
		//EM DEV
		setPort(8080);
		//EM PROD
		//setPort(Integer.valueOf(System.getenv("PORT")));
		carregaRotas();
	}
	
	public static void main(String[] args) {
		try {
			new MainController();
		} catch (IOException e) {
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
				String command;
				RankingDAO dao;
				List<Ranking> rankings;
				Map<String, Object> document = new HashMap<String, Object>();
				String commands = StringEscapeUtils.escapeHtml4(request.queryParams("log"));
				
				BufferedReader reader = new BufferedReader(
						  new StringReader(commands));
						         
				try {
					while ((command = reader.readLine()) != null) {         
						if (command.length() > 0) {
							//A PARTIR DA LINHA DE COMANDO EU SEI O QUE DEVO EXECUTAR
							Executable executable = executorFactory.createExecutor(command);
							executable.execute();
						}
			        }
					
					dao = (RankingDAO) DaoFactory.getInstance().createDao(Ranking.class);
					//GERANDO RANKING
					rankings = dao.lista();
					if(rankings!=null && rankings.size() > 0) document.put("rankings", rankings);
				} catch(IOException | PreDojoDomainException | InstantiationException | IllegalAccessException | ClassNotFoundException e) {
					document.put("error", e.getMessage());
				}
				
                template.process(document, writer);
			}
		});
		//Error
		Spark.get(new TemplateBaseRoute("/internal_error", "index.ftl") {
			@Override
			protected void doHandle(Request request, Response response, Writer writer)
					throws IOException, TemplateException {
				Map<String, Object> document = new HashMap<String, Object>();
				document.put("error", "Erro no servidor. Por favor, tente mais tarde");
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
