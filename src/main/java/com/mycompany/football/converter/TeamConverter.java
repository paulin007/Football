package com.mycompany.football.converter;

import com.mycompany.football.domain.Team;
import com.mycompany.football.service.FootballService;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import javax.naming.InitialContext;
import javax.naming.NamingException;

@FacesConverter("teamConverter")
public class TeamConverter implements Converter {

    private FootballService service;

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        if (value != null && value.trim().length() > 0) {
            try {
                InitialContext jndi = new InitialContext();
                service = (FootballService) jndi.lookup("java:module/FootballService");
                return service.getTeamRepo().search(value);
            } catch (NamingException ex) {
                Logger.getLogger(TeamConverter.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return null;
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {

        if (value != null) {
            return String.valueOf(((Team) value).getName());
        } else {
            return null;
        }
    }

}
