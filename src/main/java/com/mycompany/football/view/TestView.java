package com.mycompany.football.view;

import com.mycompany.football.domain.Player;
import com.mycompany.football.domain.Team;
import com.mycompany.football.service.FootballService;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import org.primefaces.event.RowEditEvent;

@RequestScoped
@Named("testView")
public class TestView {

    @Inject
    private FootballService service;

    private Player player;
    private List<Player> players;
    private Team team;
    private List<Team> teams;
    private List<Player> selectedPlayers = new ArrayList<>();
    private List<Team> selectedTeams = new ArrayList<>();

    private final static String[] colors;
    private final static String[] positions;

    @PostConstruct
    public void init() {
        player = new Player();
        team = new Team();
        players = service.getPlayerRepo().selectAll();
        teams = service.getTeamRepo().selectAll();
    }

    public void addPlayer() {
        service.getPlayerRepo().insert(player);
        players = service.getPlayerRepo().selectAll();
        player = new Player();
    }

    public void addTeam() {
        service.getTeamRepo().insert(team);
        teams = service.getTeamRepo().selectAll();
        team = new Team();
    }

    public void editPlayer(RowEditEvent event) {
        Player editedPlayer = (Player) event.getObject();
        service.getPlayerRepo().update(editedPlayer);

        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Player Edited", editedPlayer.getName()));
    }

    public void editTeam(RowEditEvent event) {
        Team editedTeam = (Team) event.getObject();
        service.getTeamRepo().update(editedTeam);

        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Team Edited", editedTeam.getName()));
    }

    public void deletePlayer() {
        for (Player deletedPlayer : selectedPlayers) {
            service.getPlayerRepo().delete(deletedPlayer);
        }

        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Player Deleted"));
    }

    public void deleteTeam() {
        for (Team deletedTeam : selectedTeams) {
            service.getTeamRepo().delete(deletedTeam);
        }

        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Team Deleted"));
    }

    public void randomPlayer() {
        Player newPlayer;
        for (int i = 0; i < 10; i++) {
            newPlayer = new Player();
            newPlayer.setName("Player " + String.valueOf((int) (Math.random() * 1000)));
            newPlayer.setPosition(positions[(int) (Math.random() * 11)]);
            newPlayer.setNumber(((int) (Math.random() * 100)) + 1);
            newPlayer.setGoals((int) (Math.random() * 1000));
            service.getPlayerRepo().insert(newPlayer);
        }
    }

    public void randomTeam() {
        Team newTeam;
        for (int i = 65; i < 76; i++) {
            newTeam = new Team();
            newTeam.setName(String.valueOf((char) i));
            newTeam.setStadium(String.valueOf((char) i) + " ARENA");
            newTeam.setCity(String.valueOf((char) i) + " City");
            String clr = colors[(int) (Math.random() * 10)] + " - " + colors[(int) (Math.random() * 10)];
            newTeam.setColors(clr);
            service.getTeamRepo().insert(newTeam);
        }
    }

    /* Getters and Setters */
    public FootballService getService() {
        return service;
    }

    public void setService(FootballService service) {
        this.service = service;
    }

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public List<Player> getPlayers() {
        return players;
    }

    public void setPlayers(List<Player> players) {
        this.players = players;
    }

    public Team getTeam() {
        return team;
    }

    public void setTeam(Team team) {
        this.team = team;
    }

    public List<Team> getTeams() {
        return teams;
    }

    public void setTeams(List<Team> teams) {
        this.teams = teams;
    }

    public List<Player> getSelectedPlayers() {
        return selectedPlayers;
    }

    public void setSelectedPlayers(List<Player> selectedPlayers) {
        this.selectedPlayers = selectedPlayers;
    }

    public List<Team> getSelectedTeams() {
        return selectedTeams;
    }

    public void setSelectedTeams(List<Team> selectedTeams) {
        this.selectedTeams = selectedTeams;
    }

    static {
        colors = new String[10];
        colors[0] = "Black";
        colors[1] = "White";
        colors[2] = "Green";
        colors[3] = "Red";
        colors[4] = "Blue";
        colors[5] = "Orange";
        colors[6] = "Silver";
        colors[7] = "Yellow";
        colors[8] = "Brown";
        colors[9] = "Maroon";

        positions = new String[11];
        positions[0] = "GK";
        positions[1] = "CB";
        positions[2] = "LB";
        positions[3] = "RB";
        positions[4] = "DM";
        positions[5] = "CM";
        positions[6] = "AM";
        positions[7] = "RW";
        positions[8] = "LW";
        positions[9] = "WF";
        positions[10] = "CF";

    }
}
