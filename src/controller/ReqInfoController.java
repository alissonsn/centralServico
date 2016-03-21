package controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;

import modelo.IReqInfo;
import modelo.IServico;
import entidades.ReqInfo;
import entidades.Servico;
import util.Repositorios;


@ManagedBean(name="cadastroReqInfoBean")
@RequestScoped
public class ReqInfoController implements Serializable{

	private Repositorios repositorios = new Repositorios();
	private ReqInfo reqInfo = new ReqInfo();
	private List<ReqInfo> reqInfos = new ArrayList<ReqInfo>();
	private List<Servico> servicos = new ArrayList<Servico>();



	@PostConstruct
	public void init(){
		IReqInfo reqInfos = this.repositorios.getReqInfo();
		IServico servicos = this.repositorios.getServico();
		this.servicos = servicos.listar();
		this.reqInfos = reqInfos.listar();
	}

	public void cadastrar(){
		IReqInfo reqInfos = this.repositorios.getReqInfo();
		reqInfos.salvar(reqInfo);
		this.reqInfo = new ReqInfo();


		String msg = "Cadastro efetuado com sucesso!";
		FacesContext.getCurrentInstance().addMessage(null,
				new FacesMessage(FacesMessage.SEVERITY_INFO, msg, msg));
	}

	public void update(ReqInfo reqInfo){
		IReqInfo reqInfos = this.repositorios.getReqInfo();
		reqInfos.editar(reqInfo);
	}

	public void excluir(ReqInfo reqInfo){
		IReqInfo reqInfos = this.repositorios.getReqInfo();
		reqInfos.remover(reqInfo);
		this.init();
	}

	public ReqInfo getReqInfo() {
		return reqInfo;
	}

	public void setReqInfo(ReqInfo reqInfo) throws CloneNotSupportedException {
		this.reqInfo = reqInfo;
		if (this.reqInfo == null) {
			this.reqInfo = new ReqInfo();
		}else{
			this.reqInfo = (ReqInfo) reqInfo.clone();
		}
	}

	public List<ReqInfo> getReqInfos() {
		return reqInfos;
	}

	public void setReqInfos(List<ReqInfo> reqInfos) {
		this.reqInfos = reqInfos;
	}

	public List<Servico> getServicos() {
		return servicos;
	}

	public void setServicos(List<Servico> servicos) {
		this.servicos = servicos;
	}

}