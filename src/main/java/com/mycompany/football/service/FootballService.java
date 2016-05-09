package com.mycompany.football.service;

import com.mycompany.football.repositoryInterface.PlayerInterface;
import com.mycompany.football.repositoryInterface.TeamInterface;
import javax.ejb.EJB;
import javax.ejb.Stateless;

@Stateless
public class FootballService {

    @EJB
    private PlayerInterface playerRepo;
    @EJB
    private TeamInterface teamRepo;

    public PlayerInterface getPlayerRepo() {
        return playerRepo;
    }

    public TeamInterface getTeamRepo() {
        return teamRepo;
    }
}
