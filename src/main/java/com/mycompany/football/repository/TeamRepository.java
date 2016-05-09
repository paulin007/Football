package com.mycompany.football.repository;

import com.mycompany.football.domain.Team;
import com.mycompany.football.repositoryInterface.TeamInterface;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

@Stateless
public class TeamRepository implements TeamInterface {

    @PersistenceContext(unitName = "Football_PU")
    private EntityManager em;

    private Query q;

    @Override
    public void insert(Team team) {
        em.persist(team);
    }

    @Override
    public void delete(Team team) {
        createQuery("delete from Team t where t.id=:id");
        q.setParameter("id", team.getId());
        int d = q.executeUpdate();
    }

    @Override
    public void update(Team team) {
        em.merge(team);
    }

    @Override
    public Team search(String teamname) {
        createQuery("select t from Team t where t.name=:name");
        q.setParameter("name", teamname);
        List<Team> team = q.getResultList();
        return team.get(0);
    }

    @Override
    public List<Team> selectAll() {
        createQuery("select t from Team t");
        List<Team> teamList = q.getResultList();
        return teamList;
    }

    @Override
    public void createQuery(String query) {
        q = em.createQuery(query);
    }
}
