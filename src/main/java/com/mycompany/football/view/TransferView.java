package com.mycompany.football.view;

import com.mycompany.football.domain.Player;
import com.mycompany.football.domain.Team;
import com.mycompany.football.service.FootballService;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;

import javax.inject.Inject;
import javax.inject.Named;
import org.primefaces.event.TransferEvent;
import org.primefaces.model.DualListModel;

@Named
@ViewScoped
public class TransferView implements Serializable {

    @Inject
    private FootballService service;

    private List<Team> teams;
    private List<Player> players;
    private List<Player> droppedPlayers;
    private DualListModel<Player> playerModel;

    private Team selectedTeam;

    @PostConstruct
    public void init() {
        teams = service.getTeamRepo().selectAll();
        players = service.getPlayerRepo().selectAll();
        droppedPlayers = new ArrayList<>();
        List<Player> teamplayers = new ArrayList<>();

        playerModel = new DualListModel<>(players, teamplayers);
    }

    public void onTransfer(TransferEvent event) {
        StringBuilder sb = new StringBuilder();
        for (Object item : event.getItems()) {
            sb.append(((Player) item).getName()).append("<br />");
        }

        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Player Transferred", sb.toString()));
    }

    public void onSave() {
        for (Player player : playerModel.getTarget()) {
            player.setTeam(selectedTeam);
            service.getPlayerRepo().update(player);
        }
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Save", ""));
    }

    /* Getters and Setters */
    public FootballService getService() {
        return service;
    }

    public void setService(FootballService service) {
        this.service = service;
    }

    public List<Team> getTeams() {
        return teams;
    }

    public void setTeams(List<Team> teams) {
        this.teams = teams;
    }

    public List<Player> getPlayers() {
        return players;
    }

    public void setPlayers(List<Player> players) {
        this.players = players;
    }

    public Team getSelectedTeam() {
        return selectedTeam;
    }

    public void setSelectedTeam(Team selectedTeam) {
        this.selectedTeam = selectedTeam;
    }

    public List<Player> getDroppedPlayers() {
        return droppedPlayers;
    }

    public void setDroppedPlayers(List<Player> droppedPlayers) {
        this.droppedPlayers = droppedPlayers;
    }

    public DualListModel<Player> getPlayerModel() {
        return playerModel;
    }

    public void setPlayerModel(DualListModel<Player> playerModel) {
        this.playerModel = playerModel;
    }

}
