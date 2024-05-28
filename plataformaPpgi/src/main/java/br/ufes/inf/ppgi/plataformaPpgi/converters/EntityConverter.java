package br.ufes.inf.ppgi.plataformaPpgi.converters;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import br.ufes.inf.ppgi.plataformaPpgi.domain.ObjetoPersistente;

@FacesConverter(value="entityConverter")
public class EntityConverter implements Converter {

	@Override
    public Object getAsObject(FacesContext facesContext, UIComponent uiComponent, String value) {
        if (value != null && !value.isEmpty()) {
            return (ObjetoPersistente) uiComponent.getAttributes().get(value);
        }
        return null;
    }

    @Override
    public String getAsString(FacesContext facesContext, UIComponent uiComponent, Object value) {
        if (value instanceof ObjetoPersistente) {
            ObjetoPersistente entity= (ObjetoPersistente) value;
            if (entity != null && entity instanceof ObjetoPersistente && entity.getId() != null) {
                uiComponent.getAttributes().put(entity.getId().toString(), entity);
                return entity.getId().toString();
            }
        }
        return "";
    }

//	private static Map<Object, String> entities = new WeakHashMap<Object, String>();
//
//    @Override
//    public String getAsString(FacesContext context, UIComponent component, Object entity) {
//        synchronized (entities) {
//        	System.out.println("Chamou o getAsSting, tamanho: " + entities.size());
//
//            if (!entities.containsKey(entity)) {
//                String uuid = UUID.randomUUID().toString();
//                entities.put(entity, uuid);
//                return uuid;
//            } else {
//                return entities.get(entity);
//            }
//        }
//    }
//
//    @Override
//    public Object getAsObject(FacesContext context, UIComponent component, String uuid) {
//    	synchronized (entities) {
//    		for (Entry<Object, String> entry : entities.entrySet()) {
//            	System.out.println(entry.getValue() + "==" + uuid);
//                if (entry.getValue().equals(uuid)) {
//                    return entry.getKey();
//                }
//    		}
//        }
//        System.out.println("Não foi possivel converter!");
//        return null;
//    }
}
