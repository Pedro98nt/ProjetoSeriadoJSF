package br.edu.faculdadedelta.projetoseriadojsf.controller;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import br.edu.faculdadedelta.projetoseriadojsf.dao.SeriadoDao;
import br.edu.faculdadedelta.projetoseriadojsf.modelo.Seriado;

@ManagedBean
@SessionScoped
public class SeriadoController {

	private Seriado seriado = new Seriado();
	private SeriadoDao dao = new SeriadoDao();
	
	public Seriado getSeriado() {
		return seriado;
	}
	public void setSeriado(Seriado seriado) {
		this.seriado = seriado;
	}
	
	public void limparCampos() {
		seriado = new Seriado();
	}
	
	private void exibirMensagem(String mensagem) {
		FacesMessage msg = new FacesMessage(mensagem);
		FacesContext.getCurrentInstance().addMessage(null, msg);
	}
	
	public String salvar() {
		try {
			if (seriado.getId() == null) {
					dao.incluir(seriado);
					limparCampos();
					exibirMensagem("Insclusão realizada com sucesso!");
			} else {
				dao.alterar(seriado);
				limparCampos();
				exibirMensagem("Alteração realizada com sucesso!");
			}
		} catch (ClassNotFoundException | SQLException e) {
			exibirMensagem("Erro ao realizar a operação, "
					+ "tente novamente mais tarde! " + e.getMessage());
			e.printStackTrace();
		}
		return "cadastroSeriado.xhtml";
	}
	
	public String excluir() {
		try {
			dao.excluir(seriado);
			limparCampos();
			exibirMensagem("Exclusão realizada com sucesso!");
		} catch (ClassNotFoundException | SQLException e) {
			exibirMensagem("Erro ao realizar a operação, "
					+ "tente novamente mais tarde! " + e.getMessage());			
			e.printStackTrace();
		}
		return "listaSeriado.xhtml";
	}
	
	public String editar() {
		return "cadastroSeriado.xhtml";
	}
	
	public List<Seriado> getLista() {
		List<Seriado> listaRetorno = new ArrayList<>();
		try {
			listaRetorno = dao.listar();
		} catch (ClassNotFoundException | SQLException e) {
			exibirMensagem("Erro ao realizar a operação, "
					+ "tente novamente mais tarde! " + e.getMessage());
			e.printStackTrace();
		}
		return listaRetorno;
	}
}






