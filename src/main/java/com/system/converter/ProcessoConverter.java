package com.system.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import javax.inject.Inject;
import javax.persistence.EntityManager;

import com.system.model.Processo;

@FacesConverter(forClass = Processo.class,value = "processoPickConverter")
public class ProcessoConverter implements Converter {

	@Inject
	private EntityManager manager;

	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String value) {
		Processo retorno = null;

		if (value != null) {
			retorno = this.manager.find(Processo.class, Long.valueOf(value));
		}

		return retorno;
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Object value) {
		if (value != null) {
			Long codigo = ((Processo) value).getCodigo();
			String retorno = (codigo == null ? null : codigo.toString());

			return retorno;
		}

		return "";
	}

}