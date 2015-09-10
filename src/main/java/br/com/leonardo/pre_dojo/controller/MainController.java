package br.com.leonardo.pre_dojo.controller;

import static spark.Spark.setPort;

import java.io.IOException;
import java.io.StringWriter;
import java.io.Writer;
import java.util.HashMap;
import java.util.Map;

import spark.Request;
import spark.Response;
import spark.Route;
import spark.Spark;
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
	/*private final ContaDAO      	contaDAO;
	private final MovimentacaoDAO	movimentacaoDAO;*/
	private final JPAUtil    		JPAUtil;
	
	public MainController() throws IOException{
		this.cfg 	  			= createFreemarkerConfiguration();
		/*this.contaDAO 			= new ContaDAO();
		this.movimentacaoDAO 	= new MovimentacaoDAO();*/
		this.JPAUtil            = new JPAUtil();
		
		setPort(8080);
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
		//CONTA CRUD
		Spark.get(new TemplateBaseRoute("/report", "/report/index.ftl") {
			@Override
			protected void doHandle(Request request, Response response, Writer writer)
					throws IOException, TemplateException {
				
				Map<String, Object> document = new HashMap<String, Object>();
                template.process(document, writer);
			}
		});
		/*Spark.get(new TemplateBaseRoute("/conta/busca", "/conta/dadosConta.ftl") {
			@Override
			protected void doHandle(Request request, Response response, Writer writer)
					throws IOException, TemplateException {
				 
				CrudFacade<Conta> crudConta = new CrudFacade<Conta>(Conta.class);
				
				try {
					List<Conta> contas = crudConta.lista();
				 
					Map<String, Object> document = new HashMap<String, Object>();
					
					document.put("contas", contas);
					
					template.process(document, writer);
				} catch (NumberFormatException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} 
			}
		});
		Spark.post(new TemplateBaseRoute("/conta/busca", "/conta/dadosConta.ftl") {
			@Override
			protected void doHandle(Request request, Response response, Writer writer)
					throws IOException, TemplateException {
				 
				String id = StringEscapeUtils.escapeHtml4(request.queryParams("id"));
				CrudFacade<Conta> crudConta = new CrudFacade<Conta>(Conta.class);
				
				try {
					
					Conta conta = crudConta.busca(Integer.parseInt(id));
				 
					Map<String, Object> document = new HashMap<String, Object>();
					
					if(conta!=null){
						document.put("conta", conta);
					}
					
					List<Conta> contas = crudConta.lista();
					document.put("contas", contas);
					
					template.process(document, writer);
				} catch (NumberFormatException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} 
			}
		});
		Spark.post(new TemplateBaseRoute("/conta/altera", "") {
			@Override
			protected void doHandle(Request request, Response response, Writer writer)
					throws IOException, TemplateException {
				
				String id = StringEscapeUtils.escapeHtml4(request.queryParams("idConta")); 
				CrudFacade<Conta> crudConta = new CrudFacade<Conta>(Conta.class);
				
				EntityManager em = JPAUtil.getEntityManager();
				em.getTransaction().begin();
				
				Conta conta = crudConta.busca(Integer.parseInt(id), em);
				conta.setAgencia(StringEscapeUtils.escapeHtml4(request.queryParams("agencia")));
				conta.setTitular(StringEscapeUtils.escapeHtml4(request.queryParams("titular")));
				conta.setNumero(StringEscapeUtils.escapeHtml4(request.queryParams("numero")));
				conta.setBanco(StringEscapeUtils.escapeHtml4(request.queryParams("banco")));
				
				
				try {
					crudConta.adiciona(conta, em);
					em.close();
					response.redirect("/conta/lista");
				} catch (NumberFormatException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} 
				
				em.close();
			}	
		});
		Spark.delete(new TemplateBaseRoute("/conta/:id", "/conta/contaRemovida.ftl") {
			@Override
			protected void doHandle(Request request, Response response, Writer writer)
					throws IOException, TemplateException {
				 
				String id = request.params(":id");
				CrudFacade<Conta> crudConta = new CrudFacade<Conta>(Conta.class);
				
				try {
					crudConta.remove(Integer.parseInt(id));
				 
					Map<String, Object> document = new HashMap<String, Object>();
										
					template.process(document, writer);
				} catch (NumberFormatException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} 
			}
		});
		Spark.get(new TemplateBaseRoute("/conta/lista", "/conta/listaContas.ftl") {
			@Override
			protected void doHandle(Request request, Response response, Writer writer)
					throws IOException, TemplateException {
				 
				CrudFacade<Conta> crudConta = new CrudFacade<Conta>(Conta.class);
				
				try {
					List<Conta> contas = crudConta.lista();
				 
					Map<String, Object> document = new HashMap<String, Object>();
					
					if(contas!=null){
						document.put("contas", contas);
					}
										
					template.process(document, writer);
				} catch (NumberFormatException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} 
			}
		});
		Spark.get(new TemplateBaseRoute("/conta/cadastra", "/conta/cadastra.ftl") {
			@Override
			protected void doHandle(Request request, Response response, Writer writer)
					throws IOException, TemplateException {
				 
				Map<String, Object> document = new HashMap<String, Object>();
										
				template.process(document, writer);
			}
		});
		Spark.post(new TemplateBaseRoute("/conta/cadastra", "/conta/sucesso.ftl") {
			@Override
			protected void doHandle(Request request, Response response, Writer writer)
					throws IOException, TemplateException {
				 
				CrudFacade<Conta> crudConta = new CrudFacade<Conta>(Conta.class);
				
				Conta conta = new Conta();
				conta.setAgencia(StringEscapeUtils.escapeHtml4(request.queryParams("agencia")));
				conta.setTitular(StringEscapeUtils.escapeHtml4(request.queryParams("titular")));
				conta.setNumero(StringEscapeUtils.escapeHtml4(request.queryParams("numero")));
				conta.setBanco(StringEscapeUtils.escapeHtml4(request.queryParams("banco")));
				
				try {
					crudConta.adiciona(conta);
					
					Map<String, Object> document = new HashMap<String, Object>();
										
					template.process(document, writer);
				} catch (NumberFormatException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} 
			}	
		});*/	
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
