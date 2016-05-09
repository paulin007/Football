package com.mycompany.football.repositoryInterface;

import com.mycompany.football.domain.Team;
import java.util.List;
import javax.ejb.Local;

@Local
public interface TeamInterface {
    public void insert(Team valueObj);
    
    public void delete(Team valueObj);
    
    public List<Team> selectAll();
    
    public Team search(String name);
    
    public void update(Team valueObj);
    
    public void createQuery(String query);
}
