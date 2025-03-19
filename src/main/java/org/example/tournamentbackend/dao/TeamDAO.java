package org.example.tournamentbackend.dao;

import org.example.tournamentbackend.config.DatabaseConfig;
import org.example.tournamentbackend.dto.TeamDTO;
import org.example.tournamentbackend.entity.Team;
import org.example.tournamentbackend.mapper.TeamMapper;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TeamDAO {
    public List<TeamDTO> getAllTeams() {
        List<Team> teams = new ArrayList<>();
        String query = "SELECT * FROM teams";

        try (Connection conn = DatabaseConfig.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {

            while (rs.next()) {
                Team team = new Team(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getInt("played"),
                        rs.getInt("wins"),
                        rs.getInt("draws"),
                        rs.getInt("losses"),
                        rs.getInt("goal_difference"),
                        rs.getInt("goals_scored"),
                        rs.getInt("goals_conceded"),
                        rs.getString("last_5_games"),
                        rs.getInt("points")
                );
                teams.add(team);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return TeamMapper.toDTOList(teams);  // 🔹 DTO list qaytarırıq!
    }
    public void addTeam(Team team) {
        String query = "INSERT INTO teams (name, played, wins, draws, losses, goal_difference, goals_scored, goals_conceded, last_5_games, points) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try (Connection conn = DatabaseConfig.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {

            pstmt.setString(1, team.getName());
            pstmt.setInt(2, team.getPlayed());
            pstmt.setInt(3, team.getWins());
            pstmt.setInt(4, team.getDraws());
            pstmt.setInt(5, team.getLosses());
            pstmt.setInt(6, team.getGoalDifference());
            pstmt.setInt(7, team.getGoalsScored());
            pstmt.setInt(8, team.getGoalsConceded());
            pstmt.setString(9, team.getLast5Games());
            pstmt.setInt(10, team.getPoints());

            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // 🔹 3️⃣ Komandanı sil
    public void deleteTeam(int id) {
        String query = "DELETE FROM teams WHERE id = ?";

        try (Connection conn = DatabaseConfig.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {

            pstmt.setInt(1, id);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // 🔹 1. Komandanı adına görə bazadan götür (SELECT)
    public Team getTeamByName(String teamName) {
        String query = "SELECT * FROM teams WHERE name = ?";
        try (Connection conn = DatabaseConfig.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setString(1, teamName);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                return new Team(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getInt("played"),
                        rs.getInt("wins"),
                        rs.getInt("draws"),
                        rs.getInt("losses"),
                        rs.getInt("goal_difference"),
                        rs.getInt("goals_scored"),
                        rs.getInt("goals_conceded"),
                        rs.getString("last_5_games"),
                        rs.getInt("points")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null; // Komanda tapılmasa, null qaytar
    }

    // 🔹 2. Komandanın məlumatlarını yenilə (UPDATE)
    public void updateTeam(Team team) {
        String query = "UPDATE teams SET played = ?, wins = ?, draws = ?, losses = ?, " +
                "goal_difference = ?, goals_scored = ?, goals_conceded = ?, points = ? " +
                "WHERE name = ?";
        try (Connection conn = DatabaseConfig.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setInt(1, team.getPlayed());
            stmt.setInt(2, team.getWins());
            stmt.setInt(3, team.getDraws());
            stmt.setInt(4, team.getLosses());
            stmt.setInt(5, team.getGoalDifference());
            stmt.setInt(6, team.getGoalsScored());
            stmt.setInt(7, team.getGoalsConceded());
            stmt.setInt(8, team.getPoints());
            stmt.setString(9, team.getName());

            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

