package com.mycompany.football.repositoryInterface;

import com.mycompany.football.domain.Player;
import java.util.List;
import javax.ejb.Local;

@Local
public interface PlayerInterface {

    public void insert(Player player);

    public void delete(Player player);

    public List<Player> selectAll();

    public Player search(String name);

    public void createQuery(String value);

    public void update(Player player);
}
