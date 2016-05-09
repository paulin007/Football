package com.mycompany.football.repository;

import com.mycompany.football.domain.Player;
import com.mycompany.football.repositoryInterface.PlayerInterface;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

@Stateless
public class PlayerRepository implements PlayerInterface {

    @PersistenceContext(unitName = "Football_PU")
    private EntityManager em;

    private Query q;

    @Override
    public void insert(Player player) {
        em.persist(player);
    }

    @Override
    public void delete(Player player) {
        createQuery("delete from Player p where p.id=:id");
        q.setParameter("id", player.getId());
        int d = q.executeUpdate();
    }

    @Override
    public Player search(String name) {
        createQuery("select p from Player p where p.name=:name");
        q.setParameter("name", name);
        List<Player> player = q.getResultList();
        return player.get(0);
    }

    @Override
    public List<Player> selectAll() {
        createQuery("select p from Player p");
        List<Player> playerList = q.getResultList();
        return playerList;
    }

    @Override
    public void update(Player player) {
        em.merge(player);
    }

    @Override
    public void createQuery(String query) {
        q = em.createQuery(query);
    }
}
